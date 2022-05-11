package spring.controller.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletContext;

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
	@Autowired
	ServletContext session;

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

	@RequestMapping(value = "user", params = "btnupdate-info", method = RequestMethod.POST)
	public String editInfo(HttpServletRequest request, ModelMap model, @ModelAttribute("nv") NhanVienEntity nv,
			BindingResult er) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();
		UserTBEntity user = this.getUser(id);
		Date ngaySinh;
		try {
			ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ngaySinhh"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ngaySinh = new Date();
		}
		System.out.print(nv.getSdt().trim() + " " + nv.getCmnd().trim() + "\n" + nv.getSdt().trim().length() + "\n"
				+ nv.getCmnd().trim().length());
		nv.setNgaySinh(ngaySinh);
		if (request.getParameter("email")!=null && !request.getParameter("email").equals(""))
			user.setEmail(request.getParameter("email"));
		else {
			er.rejectValue("email", "Vui lòng nhập địa chỉ email");
			System.out.print("Vui lòng nhập địa chỉ email");
		}
		if (nv.getDiaChi()!=null && nv.getDiaChi().trim().equals("")) {
			er.rejectValue("diaChi", "nv", "Vui lòng nhập địa chỉ");
			System.out.print("Vui lòng nhập địa chỉ");
		}
		if (nv.getSdt()!=null&&(nv.getSdt().trim().length() < 1 || nv.getSdt().trim().length() > 12||!checknum(nv.getSdt().trim()))) {
			er.rejectValue("sdt", "nv", "Vui lòng nhập sdt đúng định dạng");
			System.out.print("Vui lòng nhập sdt");
		}

		if (nv.getCmnd()!=null&&(nv.getCmnd().trim().length() < 1 || nv.getCmnd().trim().length() > 15||!checknum(nv.getCmnd().trim()))) {
			er.rejectValue("cmnd", "nv", "Vui lòng nhập CMND đúng");
			System.out.print("Vui lòng nhập CMND");
		}
		if (er.hasErrors()) {
			model.addAttribute("message1", "sửa thất bại, kiểm tra lai các trường");


		} else {
			Integer temp = this.updateInfo(request, nv, user);
			if (temp != 0) {
				session.setAttribute("message1", "Cập nhật thành công");
			} else {
				session.setAttribute("message1", "Cập nhật không thành công");
			}
		}
	

		/*UserTBEntity user2 = this.getUser(id);
		model.addAttribute("user", user2);
		model.addAttribute("nv", this.getNV(id));
		model.addAttribute("changePW", new Password());*/
		
		return "redirect:user.htm";
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
			session.setAttribute("message1", "Cập nhật password không thành công, kiểm tra lại các trường");

		} else {
			Integer temp = changePW(request, password.getPassword(), password.getNewpassword(),
					password.getRenewpassword());
			if (temp != 0) {
				session.setAttribute("message1", "Cập nhật password thành công");
			} else {
				session.setAttribute("message1", "Cập nhật password không thành công");
			}
		}

		return "redirect:user.htm";
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

	public boolean checknum(String str) 
	{
	  
	    for(int i=0; i<str.length();i++)
	    {
	        if(str.charAt(i) < '0' || str.charAt(i) > '9')
	            return false;
	    }
	    return true;
	} 
}