package spring.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.BanEntity;
import spring.entity.ChiTietDatEntity;

@Transactional
@Controller
public class DatBanControllerHome {

	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "trang-chu", method = RequestMethod.GET)
	public String datban(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		model.addAttribute("bans", list);
		return "web/datban";
	}


}