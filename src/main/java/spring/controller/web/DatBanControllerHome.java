package spring.controller.web;


import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.BanEntity;
import spring.entity.ChiTietDatEntity;
import spring.entity.DatBanEntity;
import spring.entity.NhanVienEntity;

@Transactional
@Controller
public class DatBanControllerHome {

	@Autowired
	SessionFactory factory;
	// CONTROLLER
	@RequestMapping(value = "trang-chu", method = RequestMethod.GET)
	public <E> String datban(HttpServletRequest request,ModelMap model){	
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "web/datban";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="trang-chu", params = "btnsearch")
	public <E> String searchBan(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchBan(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		 return "web/datban";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="trang-chu", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchBan1(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchBan(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		
		pagedListHolder.setPageSize(10);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		 return "web/datban";
	}
	
	@RequestMapping(value = "dat-ban/{id}.htm", params = "linkView")
	public String xemDatBan(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) {
		List<ChiTietDatEntity> chiTietDat = this.getChiTietDat(id);
		model.addAttribute("chiTietDat", chiTietDat);
		model.addAttribute("id", id);
		return "web/datban2";
	}
	
	
	@RequestMapping(value = "dat-ban/{id}.htm", params = "btndatban", method=RequestMethod.POST)
	public String datBan1(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id, @ModelAttribute("datban") DatBanEntity datban) {
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long idnv = user1.getID();
		datban.setDbnv(this.getNV(idnv));
		datban.setNgayDat(new Date());
		try {
			datban.setTgDuKien(Timestamp.valueOf(request.getParameter("tg").replace("T", " ") + ":00"));
		} catch (Exception e) {
			datban.setTgDuKien(null);
		}
		
		Integer temp = this.themDatBan(datban,id,model);
		if(temp != 0) {
			model.addAttribute("message","Thêm mới dat ban thành công");
			
		}else {
			model.addAttribute("message","Thêm mới dat ban thất bại");
		}
		List<ChiTietDatEntity> chiTietDat = this.getChiTietDat(id);
		model.addAttribute("chiTietDat", chiTietDat);
		model.addAttribute("id", id);
		return "web/datban2";
	};
	//END CONTROLLER
	public List<BanEntity> searchBan(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where id = :id OR soGhe = :soGhe OR loaiBan.tenLoai LIKE :name OR loaiBan.giaDat = :soGhe";
		Query query = session.createQuery(hql);
		Long id = null;
		Integer soGhe = null;
		try {
			id = Long.parseLong(name);
		}
		catch (Exception e) {
		}
		
		try {
			soGhe = Integer.parseInt(name);
		}
		catch (Exception e) {
		}
		
		query.setParameter("id", id);			
		query.setParameter("soGhe", soGhe);
		
		query.setParameter("name", "%" +  name + "%");
		List<BanEntity> list = query.list();
		return list;
	}
	
	public Integer themDatBan(DatBanEntity datban, Long id, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			
			session.save(datban);
			Integer temp = this.themChiTietDat(datban,id);
			if(temp != 0) {
				model.addAttribute("message1","Thêm mới chi tiet thành công");
				
			}else {
				model.addAttribute("message1","Thêm mới chi tiet thất bại");
				t.rollback();
				return 0;
			}
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;			
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public Integer themChiTietDat(DatBanEntity datban,Long id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			ChiTietDatEntity chiTietDat = new ChiTietDatEntity();
			
			chiTietDat.setBans(getBan(id));
			chiTietDat.setDatBan(datban);
			session.save(chiTietDat);
			
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return 0;			
		}
		finally {
			session.close();
		}
		return 1;
	}
	
	public NhanVienEntity getNV(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
	
	public List<ChiTietDatEntity> getChiTietDat (Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDatEntity where bans.id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ChiTietDatEntity> list = query.list();
		return list;
	}
	public List<BanEntity> getBans () {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		return list;
	}
	public BanEntity getBan(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BanEntity list = (BanEntity) query.list().get(0);
		return list;
	}
}