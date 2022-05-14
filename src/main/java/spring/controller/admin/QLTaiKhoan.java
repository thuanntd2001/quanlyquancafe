package spring.controller.admin;

import java.util.ArrayList;
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
	public String formInputTaikhoan(ModelMap model) {
		model.addAttribute("tk", new UserTBEntity());
		model.addAttribute("chucvus", this.getChucVus());
		model.addAttribute("fixmanv", "false");
		return "admin/form/inputTaiKhoan";
	}

	public List<ChucVuEntity> getChucVus() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChucVuEntity";
		Query query = session.createQuery(hql);
		List<ChucVuEntity> list = query.list();

		return list;
	}

	public ChucVuEntity getChucVu(long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChucVuEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		ChucVuEntity list = (ChucVuEntity) query.list().get(0);
		return list;
	}

	public NhanVienEntity getNV(long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}

	// thêm
	public int CheckUserName_Email(String username, String email) {
		List<UserTBEntity> list = getTaiKhoans();
		int n = list.size();
		String user;
		String emailtmp;
		for (int i = 0; i < n; i++) {
			user = list.get(i).getUserName();
			if (user.equals(username)) {
				return 1;
			}

			emailtmp = list.get(i).getEmail();
			if (emailtmp.equals(email)) {
				return 2;
			}
		}
		return 0;
	}


	@RequestMapping(value = "formTaiKhoan", params = "Insert", method = RequestMethod.POST)
	public <E> String addTaiKhoan(HttpServletRequest request, ModelMap model, @ModelAttribute("tk") UserTBEntity tk) {
		String error = "";
		Integer temp = 0;
		int check = CheckUserName_Email(tk.getUserName(), tk.getEmail());
		List<String> listError = new ArrayList<>();
		if (tk.getUserName().trim().equals("")) {
			error = "Tên tài khoản không được để trống!";
		} else if (check == 1) {
			error = "vì tên tài khoản đã tồn tại!!!";
		} else if (check == 2) {
			error = "email không được trùng!!!";
		} else {
			String maNVtmp = request.getParameter("manv");

			Integer maNV = Integer.parseInt(maNVtmp);
			String tmp = request.getParameter("chucvu").trim();
			Integer idChucVU = Integer.parseInt(tmp);
			/*
			 * if(CheckMaNhanVien(maNV)) { error ="Mã Nhân Viên không tồn tại!"; }else {
			 */
			tk.setUsernv(getNV(maNV));
			tk.setChucVu(getChucVu(idChucVU));
			tk.setStatus(1);
			tk.setIcon("1");
			/*listError = checkInfo(tk);*/

			temp = this.insertTaiKhoan(tk);
			// }
		}

		if (temp != 0) {
			model.addAttribute("message", "Thêm mới thành công");

		} else {
			model.addAttribute("message", "Thêm thất bại " + error + " " + listError);
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
		} catch (Exception e) {

			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	// end thêm

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

	// phần chỉnh sửa

	@RequestMapping(value = "formTaiKhoan", params = "linkEdit")
	public String editTK_showform(HttpServletRequest request, ModelMap model, @ModelAttribute("tk") UserTBEntity tk) {

		model.addAttribute("tk", this.getTaiKhoan(tk.getUserName()));
		model.addAttribute("maNV", this.getTaiKhoan(tk.getUserName()).getUsernv().getMaNV());
		model.addAttribute("chucvus", this.getChucVus());
		model.addAttribute("idCV", this.getTaiKhoan(tk.getUserName()).getChucVu().getId());
		/* System.out.println(tk.getUserName()); */

		/*
		 * System.out.println(tk.getEmail()); System.out.println(tk.getIcon());
		 * System.out.println(tk.getUsernv().getMaNV());
		 * System.out.println(tk.getUserName());
		 */
		model.addAttribute("fixmanv", "true");// không đc sủa mã nhân viên
		model.addAttribute("doc", "true");// không được sửa usernam
		model.addAttribute("btnupdate", "true");
		return "admin/form/inputTaiKhoan";
	}

	public List<NhanVienEntity> getNhanVien() {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where daNghi = false";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		return list;
	}

	public boolean CheckMaNhanVien(long manv) {
		List<NhanVienEntity> list = getNhanVien();
		int n = list.size();
		long MANV;
		for (int i = 0; i < n; i++) {
			MANV = list.get(i).getMaNV();
			if (MANV == manv) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "formTaiKhoan", params = "btnupdate", method = RequestMethod.POST)
	public <E> String editTK(HttpServletRequest requets, ModelMap model, @ModelAttribute("tk") UserTBEntity tk) {

		Integer temp = 0;

		String maNVtmp = requets.getParameter("manv");
		Integer maNV = Integer.parseInt(maNVtmp);
		String tmp = requets.getParameter("chucvu");
		Integer idChucVU = Integer.parseInt(tmp);
		tk.setUsernv(getNV(maNV));
		tk.setChucVu(getChucVu(idChucVU));
		tk.setStatus(1);
		tk.setIcon("1");

		temp = this.updateTK(tk);

		if (temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
		} else {
			model.addAttribute("message", "Cập nhật không thành công");

		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getTaiKhoans());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qltaikhoan";
	}

	public Integer updateTK(UserTBEntity tk) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(tk);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	// end phần chỉnh sửa

//	phần xóa
	public Boolean checkAdmin(String username) {
		List<UserTBEntity> list = getTaiKhoans();
		int n = list.size();
		UserTBEntity tmp;
		for (int i = 0; i < n; i++) {
			tmp = this.getTaiKhoan(list.get(i).getUserName());

			if (tmp.getChucVu().getId() == 1 && tmp.getUserName().equals(username)) {
				return true;

			}
		}

		return false;
	}

	@RequestMapping(value = "admin-taikhoan", params = "linkDelete", method = RequestMethod.GET)
	public <E> String deleteNV(HttpServletRequest requests, ModelMap model, @ModelAttribute("tk") UserTBEntity tk) {

		String error = "";
		Integer temp = 0;

		String userName = tk.getUserName();

		System.out.println(userName);
		System.out.println(checkAdmin(userName));
		if (checkAdmin(userName)) {
			error = ", không thể xóa tài khoản admin";
		} else {
			UserTBEntity tmp = this.getTaiKhoan(userName);
			tmp.setStatus(0);
			temp = this.updateTK(tmp);
		}
		if (temp != 0) {
			model.addAttribute("message", "Delete thành công");
		} else {
			model.addAttribute("message", "Delete k thành công" + error);
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getTaiKhoans());
		int page = ServletRequestUtils.getIntParameter(requests, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);

		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qltaikhoan";

	}

	public List<UserTBEntity> getTaiKhoans() {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where status =:status";
		Query query = session.createQuery(hql);
		query.setParameter("status", 1);
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

	public UserTBEntity getTaiKhoan(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where userName =:username and status=:status";

		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("status", 1);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}
}