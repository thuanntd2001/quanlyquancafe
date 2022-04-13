package spring.controller.web;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.NhanVienEntity;
import spring.entity.UserTBEntity;

@Transactional
@Controller
public class UserController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "user" , method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();

		UserTBEntity user = this.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("nv", this.getNV(id));
		return "web/user";
	}
	
	@RequestMapping(value = "user" , method = RequestMethod.POST)
	public String index2(ModelMap model,HttpServletRequest request, @ModelAttribute("nv") NhanVienEntity nv) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user1.getID();

		UserTBEntity user = this.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("nv", this.getNV(id));
		return "web/user";
	}
	
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
	
