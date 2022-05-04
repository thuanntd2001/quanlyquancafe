package spring.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import spring.entity.ChiPhiEntity;
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
		Integer ngay = calendar.get(Calendar.DAY_OF_MONTH);
		Integer thang = calendar.get(Calendar.MONTH)+1;
		Integer nam = calendar.get(Calendar.YEAR);
		List<Long> s = new ArrayList<>();
		List<HoaDonEntity> b = this.getHoaDon(ngay, thang, nam);
		for(HoaDonEntity hd:b){
			s.add(this.getTongTienHD(hd.getId()));
		}
		
		model.addAttribute("day", ngay);
		model.addAttribute("month", thang);
		model.addAttribute("year", nam);
		model.addAttribute("soHoaDon", this.soHoaDon(ngay,thang,nam));
		model.addAttribute("doanhThu", this.doanhThu(ngay,thang,nam));
		model.addAttribute("chiPhi", this.chiPhi(ngay,thang,nam));
		model.addAttribute("loiNhuan",this.doanhThu(ngay,thang,nam)- this.chiPhi(ngay,thang,nam));
		model.addAttribute("hoaDon", b);
		model.addAttribute("bangChiPhi", this.getChiPhi(ngay, thang, nam));	
		model.addAttribute("tongHD", s);
		return "admin/thongke";
	}
	
	@RequestMapping(value = "thong-ke",params="btn-search", method = RequestMethod.POST)
	public String searchThongKe1(ModelMap model, HttpServletRequest request) {
		Integer ngay,thang;
		try {
			ngay = Integer.parseInt(request.getParameter("day"));
		}
		catch (Exception e) {
			ngay = null;
		}
		try {
			thang = Integer.parseInt(request.getParameter("month"));
		}
		catch (Exception e) {
			thang = null;
		}
		Integer nam = Integer.parseInt(request.getParameter("year"));
		List<Long> s = new ArrayList<>();
		List<HoaDonEntity> b = this.getHoaDon(ngay, thang, nam);
		for(HoaDonEntity hd:b){
			s.add(this.getTongTienHD(hd.getId()));
		}
		
		model.addAttribute("timeradio", request.getParameter("totime"));
		model.addAttribute("day", ngay);
		model.addAttribute("month", thang);
		model.addAttribute("year", nam);
		model.addAttribute("soHoaDon", this.soHoaDon(ngay, thang, nam));
		model.addAttribute("doanhThu", this.doanhThu(ngay, thang, nam));
		model.addAttribute("chiPhi", this.chiPhi(ngay, thang, nam));
		model.addAttribute("loiNhuan",this.doanhThu(ngay, thang, nam)- this.chiPhi(ngay, thang, nam));
		model.addAttribute("hoaDon", this.getHoaDon(ngay, thang, nam));
		model.addAttribute("bangChiPhi", this.getChiPhi(ngay, thang, nam));
		model.addAttribute("tongHD", s);
		return "admin/thongke";
	}
	public Long soHoaDon(Integer ngay, Integer thang, Integer nam) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "SELECT COUNT(*) FROM HoaDonEntity WHERE YEAR(ngayThucHien) = :nam";
			} else {
				hql = "SELECT COUNT(*) FROM HoaDonEntity WHERE MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam";
			}
		} else {
			hql = "SELECT COUNT(*) FROM HoaDonEntity WHERE DAY(ngayThucHien) = :ngay AND MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam";
		}
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		
		Long soHoaDon = (Long) query.list().get(0);
		return soHoaDon;
	}
	public Long doanhThu(Integer ngay, Integer thang, Integer nam) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE YEAR(hd.ngayThucHien) = :nam";
			} else {
				hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE MONTH(hd.ngayThucHien) = :thang AND YEAR(hd.ngayThucHien) = :nam";
			}
		} else {
			hql = "SELECT sum(cthd.tongTien) FROM HoaDonEntity hd INNER JOIN hd.chiTietHD cthd WHERE DAY(hd.ngayThucHien) = :ngay AND MONTH(hd.ngayThucHien) = :thang AND YEAR(hd.ngayThucHien) = :nam";
		}
		
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		Long tongTien = (Long) query.list().get(0);
		if (tongTien == null) tongTien = (long) 0;
		return tongTien;
	}
	public Long chiPhi(Integer ngay, Integer thang, Integer nam) {
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE YEAR(ngayNhap) = :nam";
			} else {
				hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam";
			}
		} else {
			hql = "SELECT sum(soLuong*giaMoiDV) FROM ChiPhiEntity WHERE DAY(ngayNhap) = :ngay AND MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam";
		}
		
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		Long chiPhi = (Long) query.list().get(0);
		if (chiPhi == null) chiPhi = (long) 0;
		return chiPhi;
	}
	public List<HoaDonEntity> getHoaDon(Integer ngay, Integer thang, Integer nam){
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "FROM HoaDonEntity WHERE YEAR(ngayThucHien) = :nam";
			} else {
				hql = "FROM HoaDonEntity WHERE MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam";
			}
		} else {
			hql = "FROM HoaDonEntity WHERE DAY(ngayThucHien) = :ngay AND MONTH(ngayThucHien) = :thang AND YEAR(ngayThucHien) = :nam";
		}	
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		List<HoaDonEntity> list = query.list();
		return list;
	}
	public Long getTongTienHD(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT sum(tongTien) FROM ChiTietHDEntity where hoaDon.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		Long tongTien = (Long) query.list().get(0);
		if (tongTien == null) tongTien = (long) 0;
		return tongTien;
	}
	public List<ChiPhiEntity> getChiPhi(Integer ngay, Integer thang, Integer nam){
		Session session = factory.getCurrentSession();
		String hql = "";
		if (ngay == null) {
			if (thang == null)
			{
				hql = "FROM ChiPhiEntity WHERE YEAR(ngayNhap) = :nam";
			} else {
				hql = "FROM ChiPhiEntity WHERE MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam";
			}
		} else {
			hql = "FROM ChiPhiEntity WHERE DAY(ngayNhap) = :ngay AND MONTH(ngayNhap) = :thang AND YEAR(ngayNhap) = :nam";
		}	
		Query query = session.createQuery(hql);
		if (ngay != null) query.setParameter("ngay",ngay);
		if (thang != null) query.setParameter("thang", thang);
		if (nam != null) query.setParameter("nam", nam);
		List<ChiPhiEntity> list = query.list();
		return list;
	}
}