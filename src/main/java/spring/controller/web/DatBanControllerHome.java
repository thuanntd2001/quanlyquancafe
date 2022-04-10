package spring.controller.web;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.entity.BanEntity;

@Transactional
@Controller
public class DatBanControllerHome {
	@Autowired
	SessionFactory factory;
	@RequestMapping("trang-chu")
	public String datban(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		model.addAttribute("ban", list);
		System.out.println("ok");
		return "web/datban";
	}
}