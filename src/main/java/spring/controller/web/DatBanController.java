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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.BanEntity;
import spring.entity.ChiTietDatEntity;

@Transactional
@Controller
public class DatBanController {
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
	
	@RequestMapping(value = "dat-ban/{id}.htm", params = "linkView")
	public String xemDatBan(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) {
		List<ChiTietDatEntity> chiTietDat = this.getChiTietDat(id);
		model.addAttribute("chiTietDat", chiTietDat);
		return "web/datban2";
	}
	
	public List<ChiTietDatEntity> getChiTietDat (Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDatEntity where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ChiTietDatEntity> list = query.list();
		return list;
	}
	
	
}