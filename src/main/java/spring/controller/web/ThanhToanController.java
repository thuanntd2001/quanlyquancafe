package spring.controller.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
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

import spring.bean.BanHoaDonModel;
import spring.entity.BanEntity;
import spring.entity.LoaiThucUongEntity;
import spring.entity.ThucDonEntity;
import spring.service.web.BanService;

@Transactional
@Controller
public class ThanhToanController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext application;

	@RequestMapping(value = "thanh-toan", method = RequestMethod.GET)
	public String createList(ModelMap model, @ModelAttribute("banHoaDon") BanHoaDonModel bhd) {
		if (application.getAttribute("listBan") == null)
		{Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		
		application.setAttribute("listBan", list);
		}
		
		if (application.getAttribute("banHoaDons") == null) {
			List<BanHoaDonModel> listBHD = new ArrayList();
			List<Long> listIdsBan = new ArrayList();

			List<BanEntity> list=(List<BanEntity>) application.getAttribute("listBan");
			int n= list.size();
			for (int i =0 ; i<n ;i++) {
				listBHD.add(new BanHoaDonModel());
				listIdsBan.add(new Long(list.get(i).getId()));
			}
			application.setAttribute("banHoaDons", listBHD);
			application.setAttribute("banids", listIdsBan);
		}
		

		
		
		List<BanEntity> listBan=(List<BanEntity>) application.getAttribute("listBan");
		
		model.addAttribute("bans", listBan);
		model.addAttribute("loaiTUs", getLoaiTUs());
		model.addAttribute("thucDons", getThucDons());
		/*list.get(2).setTinhTrang(1);*/
		return "web/pay";
	}
	public List<LoaiThucUongEntity> getLoaiTUs() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiThucUongEntity";
		Query query = session.createQuery(hql);
		List<LoaiThucUongEntity> list = query.list();
		return list;
	}
	
	public List<ThucDonEntity> getThucDons() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity";
		Query query = session.createQuery(hql);
		List<ThucDonEntity> list = query.list();
		return list;
	}

}
