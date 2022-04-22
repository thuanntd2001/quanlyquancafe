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
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import spring.entity.ChiPhiEntity;
@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLNhapHang {

	
	/*@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-nhaphang" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity";
		Query query = session.createQuery(hql);
		List<ChiPhiEntity> list = query.list();
		model.addAttribute("nhaphang", list);
		
		return "admin/qlnhaphang";
	}*/
	//show trang quan li nhap hang
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-nhaphang", method = RequestMethod.GET)
	public <E> String showQLNH(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}
	
	
	/*hiển thị form*/
	@RequestMapping(value="formNhapHang", method = RequestMethod.GET) 
    public String index_formNhapHang(ModelMap model) {
		model.addAttribute("nh",new ChiPhiEntity());
          return "admin/form/inputNhapHang";
    }
	/*thêm đơn nhập hàng*/
	@RequestMapping(value = "formNhapHang",params = "Insert", method = RequestMethod.POST )
	public <E> String add_DonNhapHang(HttpServletRequest request, ModelMap model,@ModelAttribute("nh") ChiPhiEntity nh) {
		
		Integer temp = this.insert_NhapHang(nh);
		

		if(temp != 0) {
		    model.addAttribute("message","them thanh cong");
//		    nv.setMaNV(null);
		    nh.setDv(null);
		    nh.setGhiChu(null);
		    nh.setGiaMoiDV(null);
		    nh.setLoai(null);
		    nh.setNhaCungCap(null);
		    nh.setSoLuong(null);
		    nh.setTenNL(null);
		  
			
			
		}else {
			model.addAttribute("message","them that bai");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}
	public Integer insert_NhapHang(ChiPhiEntity nv) {
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
	//end thêm 
	
	/* phần chỉnh sửa */
	
	@RequestMapping(value = "formNhapHang", params = "linkEdit" )
	public String editNhapHang_showform (HttpServletRequest request, ModelMap model) {
		String id1 =request.getParameter("id");
		long id = Long.parseLong(id1);
		List<ChiPhiEntity> nhanvien = this.getDonNhapHangs();
		model.addAttribute("pagedListHolder", nhanvien);
		model.addAttribute("nh", this.getDonNhapHang(id));
		model.addAttribute("btnupdate","true");
		return "admin/form/inputNhapHang";
	}

	@RequestMapping(value = "formNhapHang", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String edit_NhapHang(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("nh") ChiPhiEntity nh) {
		
		Integer temp = this.updateNH(nh);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
			
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
			
		}
		/*List<NhanVienEntity> nhanvien = this.getNhanVien();
		model.addAttribute("nhanvien", nhanvien);
		return "admin/QLNV";*/
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getDonNhapHangs());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlnhaphang";
	}
	
	
	public Integer updateNH(ChiPhiEntity nh) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nh);
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
	
	public List<ChiPhiEntity> getDonNhapHangs(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity";
		Query query = session.createQuery(hql);
		List<ChiPhiEntity> list = query.list();
		return list;
	}
	public ChiPhiEntity getDonNhapHang (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiPhiEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ChiPhiEntity list = (ChiPhiEntity) query.list().get(0);
		return list;
	}

}