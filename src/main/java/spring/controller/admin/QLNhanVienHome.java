package spring.controller.admin;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.NhanVienEntity;
@Transactional
@Controller
@RequestMapping(value = "/admin-home/" )
public class QLNhanVienHome {

	/*@Autowired
	SessionFactory factory;
	@RequestMapping(value = "index" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		model.addAttribute("nhanvien", list);
		
		return "admin/QLNV";
	}
	
	@RequestMapping(value="form", method = RequestMethod.GET) 
    public String index(ModelMap model) {
		model.addAttribute("nhanvien",new NhanVienEntity());
          return "admin/form/updateNV";
    }*/

	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index( ModelMap model/*, @ModelAttribute("nv") NhanVienEntity nv*/) {
		List<NhanVienEntity> nhanvien = this.getNhanVien();			  
		model.addAttribute("nhanvien", nhanvien);
		return "admin/QLNV";
		
	}
	/*hiển thị form*/
	@RequestMapping(value="form", method = RequestMethod.GET) 
    public String index_form(ModelMap model) {
		model.addAttribute("nv",new NhanVienEntity());
          return "admin/form/inputNV";
    }
	
	public List<NhanVienEntity> getNhanVien(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where daNghi = false";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		return list;
	}
	/*thêm nhân viên*/
	@RequestMapping(value = "form",params = "Insert", method = RequestMethod.POST )
	public String addUser(HttpServletRequest request, ModelMap model,@ModelAttribute("nv") NhanVienEntity nv) {
		nv.setNgaySinh(new Date());
		nv.setNgayVaoLam(new Date());
		nv.setDaNghi(false);
		Integer temp = this.insertUser(nv);
		
		if(temp != 0) {
		    model.addAttribute("message","them thanh cong");
//		    nv.setMaNV(null);
			nv.setHoTen(null);
		
			nv.setGioiTinh(null);
			nv.setLuong(null);
			nv.setSdt(null);
			nv.setCmnd(null);
			nv.setDiaChi(null);
			
			
		}else {
			model.addAttribute("message","them that bai");
		}
		List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("nhanvien", nhanvien);
		return"admin/QLNV";
	}
	
	public Integer insertUser(NhanVienEntity nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nv);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} 
	
	/* phần chỉnh sửa */
	
	@RequestMapping(value = "form", params = "linkEdit" )
	public String editNV (HttpServletRequest request, ModelMap model, @ModelAttribute("nv") NhanVienEntity nv) {
		String id1 =request.getParameter("id");
		long maNV = Long.parseLong(id1);
		List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("nv", this.getNV(maNV));
		model.addAttribute("btnupdate","true");
		return "admin/form/inputNV";
	}
	@RequestMapping(value = "form", params = "btnupdate" , method = RequestMethod.POST )
	public String edit_NVs(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("nv") NhanVienEntity nv) {
		Integer temp = this.updateUser(nv);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
			nv.setHoTen(null);
			
			nv.setGioiTinh(null);
			nv.setLuong(null);
			nv.setSdt(null);
			nv.setCmnd(null);
			nv.setDiaChi(null);
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
		}
		List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("nhanvien", nhanvien);
		return "admin/QLNV";
	}
	
	
	public Integer updateUser(NhanVienEntity nv) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nv);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			return 0;
		}
		finally {
			session.close();
		}
		return 1;
	}
	/* end phần chỉnh sửa */
	
//	phần xóa
	@RequestMapping(value = "form", params = "linkDelete",method = RequestMethod.POST)
	public String deleteNV (HttpServletRequest request, ModelMap model, @ModelAttribute("nv") NhanVienEntity nv) {
		String id1 =request.getParameter("id");
		long maNV = Long.parseLong(id1);
		this.getNV(maNV).setDaNghi(true);
		Integer temp = this.updateUser(this.getNV(maNV));
		if(temp != 0) {
			model.addAttribute("message","Delete thành công");
		}
		else {
			model.addAttribute("message", "Delete không thành công");
		}
		List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("nhanvien", nhanvien);
		return "admin/QLNV";
	}
	public NhanVienEntity getNV (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
	
}
