package spring.controller.admin;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.HoaDonEntity;

@Transactional
@Controller
@RequestMapping(value = "/admin-home/")
public class ThongKe {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "thong-ke" , method = RequestMethod.GET)
	public String thongKe(ModelMap model) {
		
		Calendar calendar = Calendar.getInstance();
		model.addAttribute("year", calendar.get(Calendar.YEAR));
		model.addAttribute("day", calendar.get(Calendar.DAY_OF_MONTH));
		model.addAttribute("month", calendar.get(Calendar.MONTH)+1);
		model.addAttribute("soHoaDon", this.soHoaDon());
		model.addAttribute("tongTien", this.tongTien());
		model.addAttribute("chiPhi", this.chiPhi());
		model.addAttribute("loiNhuan",this.tongTien()- this.chiPhi());
		return "admin/thongke";
	}
	
	@RequestMapping(value = "thong-ke",params="btn-search", method = RequestMethod.POST)
	public String searchThongKe1(ModelMap model, HttpServletRequest request) {
		model.addAttribute("year", request.getParameter("year"));
		model.addAttribute("day", request.getParameter("day"));
		model.addAttribute("month", request.getParameter("month"));
		model.addAttribute("soHoaDon", this.soHoaDon());
		model.addAttribute("tongTien", this.tongTien());
		model.addAttribute("loiNhuan",this.tongTien()- this.chiPhi());
		return "admin/thongke";
	}
	public Long soHoaDon() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM HoaDonEntity";
		Query query = session.createQuery(hql);
		Long soHoaDon = (Long) query.list().get(0);
		return soHoaDon;
	}
	public Long tongTien() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd";
		Query query = session.createQuery(hql);
		Long tongTien = (Long) query.list().get(0);
		return tongTien;
	}
	public Long chiPhi() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity";
		Query query = session.createQuery(hql);
		Long chiPhi = (Long) query.list().get(0);
		return chiPhi;
	}

}