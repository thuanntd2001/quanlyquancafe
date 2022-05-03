package spring.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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

import spring.entity.BanEntity;
import spring.entity.DatBanEntity;
import spring.entity.LoaiBanEntity;


@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLBan {

	//show trang quan li ban
	@Autowired
	ServletContext application;
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-qlban", method = RequestMethod.GET)
	public <E> String showQLB(HttpServletRequest request,ModelMap model){	
		
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	
	//HIỂN THỊ FORM NHẬP LIỆU
	/*@RequestMapping(value="formBan", method = RequestMethod.GET) 
    public String formBan(ModelMap model) {
		
		application.setAttribute("tenLoaiaban", getLoaiBans());
		Map<String, Integer> listLoaiBan_giathanh = new HashMap<>();
		List<LoaiBanEntity> list=(List<LoaiBanEntity>) application.getAttribute("tenLoaiaban");
		int n= list.size();
		for (int i =0 ; i<n ;i++) {
			
			listLoaiBan_giathanh.put(list.get(i).getTenLoai(), list.get(i).getGiaDat());
		}
		application.setAttribute("tenloai_giathanh", listLoaiBan_giathanh);
		
		model.addAttribute("lb",new LoaiBanEntity());
          return "admin/form/inputBan";
    }*/
	
	/*thêm ban thêm loại bàn
	@RequestMapping(value = "formBan",params = "Insert", method = RequestMethod.POST )
	public <E> String add_BAN(HttpServletRequest request, ModelMap model,@ModelAttribute("lb") LoaiBanEntity lb) {
		
		
		
		BanEntity b = new BanEntity();
		Integer soghe =  null;
		try {
			soghe = (int) Long.parseLong(request.getParameter("soghe"));
		}catch (Exception e) {
		}
		b.setSoGhe(soghe);
		b.setLoaiBan(lb);
		Integer temp1 =  this.insert_Ban2(b, lb, model);
		if(temp1 != 0) {
		    model.addAttribute("message","them thanh cong");
		    
			
		}else {
			model.addAttribute("message","them that bai");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(3);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	
	public Integer insert_Ban2(BanEntity b, LoaiBanEntity lb, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Integer tmp = this.insert_LoaiBan(lb);
			if(tmp != 0) {
			    model.addAttribute("message","them loai ban thanh cong");
			    session.save(b);
				
			}else {
				model.addAttribute("message","them loai ban that bai");
				
				
			}
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} 
	
	public Integer insert_Ban(BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(b);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} 
	
	public Integer insert_LoaiBan(LoaiBanEntity lb) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(lb);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} */
	
 //hiển thị form
	@RequestMapping(value="formBan", method = RequestMethod.GET) 
    public String formBan(ModelMap model, HttpServletRequest request,@ModelAttribute("b") BanEntity b) {
		model.addAttribute("loaibans",getLoaiBans());	
          return "admin/form/inputBan1";
    }
	
	//thêm bàn xổ combobox
	@RequestMapping(value = "formBan",params = "Insert", method = RequestMethod.POST )
	public <E> String add_BAN(HttpServletRequest request, ModelMap model /*,@ModelAttribute("lb") LoaiBanEntity lb*/ ) {
			
		String id = request.getParameter("loaiBan"); 
		int id1 = Integer.parseInt(id);
		String a = request.getParameter("soGhe");
		int soghe = Integer.parseInt(a);
		BanEntity ban = new BanEntity();
		ban.setSoGhe(soghe);
		ban.setTinhTrang(0);
		ban.setLoaiBan(getLoaiBan(id1));
		Integer temp1 = this.InsertBan(ban);
		if(temp1 != 0) {
		    model.addAttribute("message","them thanh cong");
		    
			
		}else {
			model.addAttribute("message","them that bai");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(3);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
		
//	END THÊM
/* phần chỉnh sửa */
	
	@RequestMapping(value = "formBan", params = "linkEdit" )
	public String editBan_showform (@ModelAttribute("b") BanEntity b,HttpServletRequest request, ModelMap model) {
		
		long id = b.getId();
		model.addAttribute("loaiBan",getLoaiBans());	
		model.addAttribute("soGhe",b.getSoGhe());	
		
		
//		System.out.println(getLoaiBans()+"|"+getBan(id).getSoGhe());
		model.addAttribute("btnupdate","true");
		return "admin/form/inputBan1";
	}

	@RequestMapping(value = "formBan", params = "btnupdate" , method = RequestMethod.POST )
	public <E> String edit_QLBan(HttpServletRequest requets, ModelMap model, 
			@ModelAttribute("b") BanEntity b) {
		
		String id = requets.getParameter("loaiBan"); 
		int idLB = Integer.parseInt(id);
		String a = requets.getParameter("soGhe");
		int soghe = Integer.parseInt(a);
		
		b.setSoGhe(soghe);
		b.setTinhTrang(0);
		b.setLoaiBan(getLoaiBan(idLB));
		Integer temp = this.updateBan(b);
		if( temp != 0) {
			model.addAttribute("message", "Cập nhật thành công");
			
		}
		else {
			model.addAttribute("message", "Cập nhật không thành công");
			
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(requets, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	
	
	public Integer updateBan(BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(b);
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
	
	
	
	 public List<LoaiBanEntity> getLoaiBans(){
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiBanEntity";
			Query query = session.createQuery(hql);
			List<LoaiBanEntity> list = query.list();
			
			return list;
		}
	 public LoaiBanEntity getLoaiBan (long id) {
			Session session = factory.getCurrentSession();
			String hql = "FROM LoaiBanEntity where id =:id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			LoaiBanEntity list = (LoaiBanEntity) query.list().get(0);
			return list;
		}

	
	public List<BanEntity> getBans(){
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		return list;
	}
	
	public BanEntity getBan (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity where id =:id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BanEntity list = (BanEntity) query.list().get(0);
		return list;
	}
	
//	phần tìm kiếm
	@SuppressWarnings("unchecked")
	@RequestMapping(value="admin-qlban", params = "btnsearch", method=RequestMethod.POST)
	public <E> String searchBan1(HttpServletRequest request, ModelMap model) {
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>)this.searchBan(request.getParameter("searchInput")));
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
		
		pagedListHolder.setPageSize(4);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/qlban";
	}
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
//	kết thúc tìm kiếm
	
//	XÓA
	@RequestMapping(value = "admin-qlban", params = "linkDelete")
	public <E> String deleteDonNhapHang (HttpServletRequest request, ModelMap model, @ModelAttribute("b") BanEntity b) {
		
		Integer temp = this.deleteBan(b);
		//System.out.println(getBan(id).ge);
		
		if(temp != 0) {
			model.addAttribute("message","Delete thành công");
		}
		else {
			model.addAttribute("message", "Delete không thành công");
		}
		@SuppressWarnings("unchecked")
		PagedListHolder<E> pagedListHolder = new PagedListHolder<E>((List<E>) this.getBans());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(10);
	
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("bans", list);
		return "admin/qlban";
	}
	public Integer deleteBan (BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.delete(b);
			t.commit();
		}
		catch (Exception e){
			t.rollback();
			return 0;
		}
		finally{
			session.close();
		}
		return 1;
	}
	public Integer InsertBan (BanEntity b) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {
			session.save(b);
			t.commit();
		}
		catch (Exception e){
			t.rollback();
			return 0;
		}
		finally{
			session.close();
		}
		return 1;
	}
	
	//END XÓA

}