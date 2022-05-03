package spring.controller.admin;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.NhanVienEntity;
@Transactional
@Controller
@RequestMapping(value = "/admin-home/" )
public class QLNhanVienHome {
	@Autowired
	SessionFactory factory;
	// CONTROLLER
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public <E> String index(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
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
	public <E> String addUser(HttpServletRequest request, ModelMap model,@ModelAttribute("nv") NhanVienEntity nv) {
		nv.setDaNghi(false);
		Integer temp = this.insertUser(nv);
		if(temp != 0) {
		    model.addAttribute("message","them thanh cong");
			nv.setHoTen(null);
			nv.setGioiTinh(null);
			nv.setLuong(null);
			nv.setSdt(null);
			nv.setCmnd(null);
			nv.setDiaChi(null);
		
		}else {
			model.addAttribute("message","them that bai");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";
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
	public String editNV_showform (HttpServletRequest request, ModelMap model) {
		String id1 =request.getParameter("id");
		long maNV = Long.parseLong(id1);
		List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("pagedListHolder", nhanvien);

		model.addAttribute("nv", this.getNV(maNV));
		model.addAttribute("btnupdate","true");
		return "admin/form/inputNV";
	}

	@RequestMapping(value = "form", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String editNV(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("nv") NhanVienEntity nv) {
		
		Integer temp = this.updateNV(nv);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");	
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công nhân vien số "+nv.getMaNV());

		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";
	}
	
	
	public Integer updateNV(NhanVienEntity nv) {
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
	@RequestMapping(value = "index", params = "linkDelete",method = RequestMethod.GET)
	public <E> String deleteNV (HttpServletRequest request, ModelMap model) {
		String id1 =request.getParameter("id");
		long maNV = Long.parseLong(id1);

		this.getNV(maNV).setDaNghi(true);
		Integer temp = this.updateNV(this.getNV(maNV));
		if(temp != 0) {
			model.addAttribute("message","Delete k thành công");
		}
		else {
			model.addAttribute("message", "Delete thành công");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/QLNV";

}
	/*@RequestMapping(value = "index", params = "linkDelete",method = RequestMethod.GET)*/

	/*@RequestMapping(value = "{id}.htm", params = "linkDelete",method = RequestMethod.GET)
	public <E> String deleteNV (HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) {
		this.getNV(id).setDaNghi(true);
		Integer temp = this.updateNV(this.getNV(id));
		if(temp != 0) {
			model.addAttribute("message","Delete k thành công");
		}
		else {
			model.addAttribute("message", "Delete thành công");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/QLNV";
	}*/
//	kết thúc xóa
	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="index", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchBan1(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchNhanVien(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(5);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/QLNV";
	}
	public List<NhanVienEntity> searchNhanVien(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV = :id OR hoTen like :name";
		Query query = session.createQuery(hql);
		Long id = null;
	
		try {
			id = Long.parseLong(name);
		}
		catch (Exception e) {
		}
		
		query.setParameter("id", id);			
		query.setParameter("name", "%" +  name + "%");
		List<NhanVienEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm
	
	public NhanVienEntity getNV (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
}
