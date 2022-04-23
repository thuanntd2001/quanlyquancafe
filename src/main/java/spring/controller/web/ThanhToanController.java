package spring.controller.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
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

import spring.bean.BanHoaDonModel;
import spring.entity.BanEntity;
import spring.entity.ChiTietHDEntity;
import spring.entity.HoaDonEntity;
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
	public String createList(ModelMap model) {
		// kt co list ban tong he thong ko co thi tao list nay2 set ra view
		if (application.getAttribute("listBan") == null) {
			Session session = factory.getCurrentSession();
			String hql = "FROM BanEntity";
			Query query = session.createQuery(hql);
			List<BanEntity> list = query.list();

			application.setAttribute("listBan", list);
		}
		// kt co list ban tong he thong ko co thi tao list nay tinh hoa don
		if (application.getAttribute("banHoaDons") == null) {
			List<BanHoaDonModel> listBHD = new ArrayList();
			List<Long> listIdsBan = new ArrayList();

			List<BanEntity> list = (List<BanEntity>) application.getAttribute("listBan");
			int n = list.size();
			for (int i = 0; i < n; i++) {
				listBHD.add(new BanHoaDonModel(i));
				listIdsBan.add(new Long(list.get(i).getId()));
			}
			application.setAttribute("banHoaDons", listBHD);
			application.setAttribute("banids", listIdsBan);
			application.setAttribute("thucDons", getThucDons());
		}

		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");

		model.addAttribute("bans", listBan);
		model.addAttribute("loaiTUs", getLoaiTUs());
		model.addAttribute("thucDons", getThucDons());

		return "web/thanhtoan";
	}

	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST, params="xem")
	public String view(ModelMap model, HttpServletRequest request) {
		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		List<ThucDonEntity> listTD = (List<ThucDonEntity>) application.getAttribute("thucDons");

		// lay data tu form
		long ban = Long.parseLong(request.getParameter("Ban"));


		// set view
		model.addAttribute("bans", listBan);

	
		if (Long.valueOf(ban) != null) {
			BanHoaDonModel BHD=listBHD.get((int) findBanHD(ban,listBHD));
			model.addAttribute("banHD", BHD);
			model.addAttribute("tongtien", tinhTong(BHD.getCthds()));
		}

		// 
		
		

		listBHD.get((int) findBanHD(ban, listBHD)).xuat();
		return "web/thanhtoan";

	}
	@RequestMapping(value = "thanh-toan", method = RequestMethod.POST, params="thanhtoan")
	public String pay(ModelMap model, HttpServletRequest request) {
		List<BanEntity> listBan = (List<BanEntity>) application.getAttribute("listBan");
		List<BanHoaDonModel> listBHD = (List<BanHoaDonModel>) application.getAttribute("banHoaDons");
		List<ThucDonEntity> listTD = (List<ThucDonEntity>) application.getAttribute("thucDons");

		// lay data tu form
		long ban = Long.parseLong(request.getParameter("Ban"));


		// set view
		model.addAttribute("bans", listBan);

		// set ban co nguoi dung
		if (Long.valueOf(ban) != null) {
			model.addAttribute("banHD", listBHD.get((int) findBanHD(ban,listBHD)));
		}

		// 
		
		System.out.println(listBHD.get((int) findBanHD(ban,listBHD)).getCthds().get(0).getThucDon().getLoaiThucUong());

		listBHD.get((int) findBanHD(ban, listBHD)).xuat();
		return "web/thanhtoan";

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

	public long findBan(long id, List<BanEntity> list) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId() == id)
				return i;
		return -1;
	}

	public long findBanHD(long id, List<BanHoaDonModel> list) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getIdBan() == id)
				return i;
		return -1;
	}

	public long findTD(String id, List<ThucDonEntity> list) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getId().equals(id))
				return i;
		return -1;
	}
	public int tinhTong( List<ChiTietHDEntity> list) {
		int tong=0;
		for (int i = 0; i < list.size(); i++)
			tong += list.get(i).getTongTien();
			
		return tong;
	}

}
