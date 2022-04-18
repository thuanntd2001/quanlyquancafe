package spring.controller.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.ThucDonEntity;
@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLThucDon {

	
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-qlthucdon" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity";
		Query query = session.createQuery(hql);
		List<ThucDonEntity> list = query.list();
		model.addAttribute("thucdon", list);
		
		return "admin/qlthucdon";
	}


}