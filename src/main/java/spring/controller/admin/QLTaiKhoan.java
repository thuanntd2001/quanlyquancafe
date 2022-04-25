package spring.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.BanEntity;
import spring.entity.ChucVuEntity;
import spring.entity.LoaiBanEntity;
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
	public String formInputTaikhoan(ModelMap model, HttpServletRequest request,
			@ModelAttribute("tk") UserTBEntity tk,@ModelAttribute("cv") ChucVuEntity cv) {

		model.addAttribute("chucvus", this.getChucVu_Id());

		return "admin/form/inputTaiKhoan";
	}

	public Map<Integer, String> getChucVu_Id() {
		List<ChucVuEntity> list = this.getchucvus();
		Map<Integer, String> m = new HashMap<>();
		int n = list.size();
		for (int i = 0; i < n; i++) {
			m.put((int) (long) (list.get(i).getId()), list.get(i).getTenChucVu());
		}
		return m;
	}

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