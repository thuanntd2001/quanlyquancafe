package spring.controller.admin;

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

import spring.entity.ChucVuEntity;
import spring.entity.NhanVienEntity;
import spring.entity.ThucDonEntity;
import spring.entity.UserTBEntity;

@Transactional
@Controller
@RequestMapping(value = "/admin-home/")
public class QLTaiKhoan {
	@Autowired
	SessionFactory factory;

	// CONTROLLER
	@RequestMapping(value = "admin-taikhoan", method = RequestMethod.GET)
	public <E> String index_TaiKhoan(HttpServletRequest request, ModelMap model) {
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getTaiKhoans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qltaikhoan";
	}

	// show form
	@RequestMapping(value = "formTaiKhoan", method = RequestMethod.GET)
	public String formInputTaikhoan(ModelMap model
		) {
		model.addAttribute("tk",new UserTBEntity());
		model.addAttribute("chucvus", this.getChucVus());

		return "admin/form/inputTaiKhoan";
	}

	 public List<ChucVuEntity> getChucVus(){
			Session session = factory.getCurrentSession();
			String hql = "FROM ChucVuEntity";
			Query query = session.createQuery(hql);
			List<ChucVuEntity> list = query.list();
			
			return list;
		}
	 
	 public ChucVuEntity getChucVu (long id) {
			Session session = factory.getCurrentSession();
			String hql = "FROM ChucVuEntity where id =:id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			ChucVuEntity list = (ChucVuEntity) query.list().get(0);
			return list;
		}
	 
	 public NhanVienEntity getNV (long id) {
			Session session = factory.getCurrentSession();
			String hql = "FROM NhanVienEntity where id =:id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			NhanVienEntity list = (NhanVienEntity) query.list().get(0);
			return list;
		}
	 
	 //thêm
	 @RequestMapping(value = "formTaiKhoan",params = "Insert", method = RequestMethod.POST )
		public <E> String addTaiKhoan(HttpServletRequest request, ModelMap model,@ModelAttribute("tk") UserTBEntity tk) {
			
			String maNVtmp =request.getParameter("manv");
			Integer maNV = Integer.parseInt(maNVtmp);
			String tmp = request.getParameter("chucvu");
			Integer idChucVU = Integer.parseInt(tmp);
			tk.setUsernv(getNV(maNV));
			tk.setChucVu(getChucVu(idChucVU));
			tk.setStatus(1);	
		 	tk.setIcon("1");
			Integer temp = this.insertTaiKhoan(tk);
			
			
			if(temp != 0) {
			    model.addAttribute("message","them thanh cong");
				
			
			}else {
				model.addAttribute("message","them that bai");
			}
			@SuppressWarnings("unchecked")
			PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getTaiKhoans());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(10);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolder", pagedListHolder);
			return "admin/qltaikhoan";
		}
		
		public Integer insertTaiKhoan(UserTBEntity tk) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(tk);
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
	 
	 

//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "admin-taikhoan", params = "btnsearch", method = RequestMethod.POST)
	public <E> String searchTaiKhoan(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>(
				(List<E>) this.searchTaiKhoan(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(5);

		model.addAttribute("pagedListHolder", pagedListHolder);

		return "admin/qltaikhoan";
	}

	public List<UserTBEntity> searchTaiKhoan(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where chucVu.tenChucVu = :name OR email like :name";
		Query query = session.createQuery(hql);

		query.setParameter("name", "%" + name + "%");
		List<UserTBEntity> list = query.list();
		return list;
	}
	
	
/* phần chỉnh sửa */
	
	/*@RequestMapping(value = "formTaiKhoan", params = "linkEdit" )
	public String editTD_showform (HttpServletRequest request, ModelMap model,@ModelAttribute("td") ThucDonEntity td) {
			
		model.addAttribute("chucvus", this.getChucVus()); 
		model.addAttribute("td",td);
		model.addAttribute("btnupdate","true");
		return "admin/form/inputTaiKhoan";
	}

	@RequestMapping(value = "formTaiKhoan", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String editTK(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("tk") UserTBEntity tk) {
		
		tk
		String idLoaiTU =requets.getParameter("loaithucuong");	
		td.setLoaiThucUong(getLoaiThucUong(idLoaiTU));
		
		
		String tmp =requets.getParameter("gia");
		Integer giaTD = Integer.parseInt(tmp);
		String tenTD =requets.getParameter("ten");
		
		td.setGia(giaTD);
		td.setTen(tenTD);
		Integer temp = this.updateTD(td);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");	
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");

		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qltaikhoan";
	}
	
	
	
	public Integer updateTD(ThucDonEntity td) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(td);
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
	}*/
	/* end phần chỉnh sửa */
//	kết thúc tìm kiếm

	public List<UserTBEntity> getTaiKhoans() {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity";
		Query query = session.createQuery(hql);
		List<UserTBEntity> list = query.list();
		return list;
	}

	public List<ChucVuEntity> getchucvus() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChucVuEntity";
		Query query = session.createQuery(hql);
		List<ChucVuEntity> list = query.list();
		return list;
	}
}