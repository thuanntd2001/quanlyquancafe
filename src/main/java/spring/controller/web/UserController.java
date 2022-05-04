package spring.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.bean.Password;
import spring.entity.NhanVienEntity;
import spring.entity.UserTBEntity;

@Transactional
@Controller
public class UserController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();

		UserTBEntity user = this.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("nv", this.getNV(id));
		model.addAttribute("changePW", new Password());
		return "web/user";
	}

/*	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String index2(ModelMap model, HttpServletRequest request, @ModelAttribute("nv") NhanVienEntity nv) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();

		UserTBEntity user = this.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("nv", this.getNV(id));
		model.addAttribute("changePW", new Password());

		return "web/user";
	}*/

	@RequestMapping(value = "user", params = "btnupdate-info")
	public String editInfo(HttpServletRequest request, ModelMap model, @ModelAttribute("nv") NhanVienEntity nv,
			@ModelAttribute("user") UserTBEntity user) {
		Integer temp = this.updateInfo(request, nv, user);
		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật không thành công");
		}
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();
		UserTBEntity user2 = this.getUser(id);
		model.addAttribute("user", user2);
		model.addAttribute("nv", this.getNV(id));
		return "web/user";
	}

	public Integer updateInfo(HttpServletRequest request, NhanVienEntity nv,
			@ModelAttribute("user") UserTBEntity user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nv);
			UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			Long id = user1.getID();
			UserTBEntity user2 = this.getUser(id);
			user2.setEmail(user.getEmail());
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	@RequestMapping(value = "user", params = "btnChangePw", method = RequestMethod.GET)
	public String changePasswordd(HttpServletRequest request, ModelMap model,
			@ModelAttribute("password") String password, @ModelAttribute("newpassword") String newpassword,
			@ModelAttribute("renewpassword") String renewpassword) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();
		UserTBEntity user2 = this.getUser(id);
		model.addAttribute("user", user2);
		model.addAttribute("nv", this.getNV(id));
		model.addAttribute("changePW", new Password());
		return "web/user";
	}

	@RequestMapping(value = "user", params = "btnChangePw", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request, ModelMap model,
			@ModelAttribute("password") Password password, BindingResult er) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();
		// validation
		if (password.getPassword().equals("")) {
			er.rejectValue("password", "changePW", "Vui lòng nhập password");
		}
		if (!password.getPassword().equals(user1.getPasswd())) {
			er.rejectValue("password", "changePW", "Vui lòng nhập lại password");
		}
		if (password.getNewpassword().equals("")) {
			er.rejectValue("newpassword", "changePW", "Vui lòng nhập password mới");
		}
		if (password.getRenewpassword().equals("")) {
			er.rejectValue("renewpassword", "changePW", "Vui lòng nhập lại password mới");
		}
		if (!password.getNewpassword().equals(password.getRenewpassword())) {
			er.rejectValue("renewpassword", "changePW", "Vui lòng nhập password đúng");
		}

		// end validation
		if (er.hasErrors()) {
			model.addAttribute("message", "Cập nhật không thành công, kiểm tra lại các trường");
			
		} else {
			Integer temp = changePW(request, password.getPassword(), password.getNewpassword(),
					password.getRenewpassword());
			if (temp != 0) {
				model.addAttribute("message", "Cập nhật thành công");
			} else {
				model.addAttribute("message", "Cập nhật không thành công");
			}
		}

		UserTBEntity user2 = this.getUser(id);
		model.addAttribute("user", user2);
		model.addAttribute("nv", this.getNV(id));
		model.addAttribute("changePW", password);

		return "web/user";
	}

	public Integer changePW(HttpServletRequest request, @ModelAttribute("password") String password,
			@ModelAttribute("newpassword") String newpassword, @ModelAttribute("renewpassword") String renewpassword) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();
		UserTBEntity user2 = this.getUser(id);
		if (password.equals(user2.getPasswd()) && !newpassword.isEmpty() && !renewpassword.isEmpty()
				&& newpassword.equals(renewpassword)) {
			user2.setPasswd(newpassword);
			return 1;
		}
		return 0;
	};

	public UserTBEntity getUser(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where usernv.maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}

	public NhanVienEntity getNV(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
}