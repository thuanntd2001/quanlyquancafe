package spring.controller.web;

import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.BanEntity;
import spring.service.web.BanService;

@Transactional
@Controller
public class ThanhToanController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext application;

	@RequestMapping(value = "thanh-toan", method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		if (application.getAttribute("list") == null)
		{Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		
		application.setAttribute("list", list);
		}
		
		
		List<BanEntity> list=(List<BanEntity>) application.getAttribute("list");
		model.addAttribute("bans", list);
		list.get(2).setTinhTrang(1);
		return "web/pay";
	}

}
