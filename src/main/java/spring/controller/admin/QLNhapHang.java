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

import spring.entity.ChiPhiEntity;

@Controller
@Transactional
public class QLNhapHang {

	
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-nhaphang" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity";
		Query query = session.createQuery(hql);
		List<ChiPhiEntity> list = query.list();
		model.addAttribute("nhaphang", list);
		
		return "admin/qlnhaphang";
	}

}