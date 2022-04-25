package spring.controller.admin;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.ChiPhiEntity;
import spring.entity.HoaDonEntity;
import spring.entity.ThucDonEntity;
@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLThucDon {

	@Autowired
	SessionFactory factory;
	// CONTROLLER
	@RequestMapping(value = "admin-qlthucdon", method = RequestMethod.GET)
	public <E> String showThucDon(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getThucDons());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/qlthucdon";
	}
	
	/*hiển thị form*/
	@RequestMapping(value="formThucDon", method = RequestMethod.GET) 
    public String index_formThucDon(ModelMap model) {
		model.addAttribute("td",new ThucDonEntity());
          return "admin/form/inputThucDon";
    }
	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="admin-qlthucdon", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchThucDon(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchThucDon(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(5);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/qlthucdon";
	}
	public List<ThucDonEntity> searchThucDon(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity where gia = :gia OR ten like :name "
				+"OR loaiThucUong.tenLoai like :name OR id like :name";
			
		Query query = session.createQuery(hql);
		Long gia = null;
	
		try {
			gia = Long.parseLong(name);
		}
		catch (Exception e) {
		}
		
		query.setParameter("gia", gia);			
		query.setParameter("name", "%" +  name + "%");
		List<ThucDonEntity> list = query.list();
		return list;
	}
//	kết thúc tìm kiếm
	
	public List<ThucDonEntity> getThucDons(){
		Session session = factory.getCurrentSession();
		String hql = "FROM ThucDonEntity";
		Query query = session.createQuery(hql);
		List<ThucDonEntity> list = query.list();
		return list;
	}

}