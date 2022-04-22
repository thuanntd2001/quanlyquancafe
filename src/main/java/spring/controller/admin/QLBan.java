package spring.controller.admin;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.BanEntity;
import spring.entity.ChiPhiEntity;
import spring.entity.LoaiBanEntity;

@Controller
@Transactional
@RequestMapping(value = "/admin-home/")
public class QLBan {

/*	
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "admin-qlban" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		model.addAttribute("ban", list);
		return "admin/qlban";
	}*/
	
	//show trang quan li ban
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
	@RequestMapping(value="formBan", method = RequestMethod.GET) 
    public String formBan(ModelMap model) {
		model.addAttribute("b",new BanEntity());
          return "admin/form/inputBan";
    }
	
	/*thêm đơn ban*/
	@RequestMapping(value = "formBan",params = "Insert", method = RequestMethod.POST )
	public <E> String add_BAN(HttpServletRequest request, ModelMap model,@ModelAttribute("b") BanEntity b) {
		
		Integer temp = this.insert_Ban(b);
		if(temp != 0) {
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
	
	 @SuppressWarnings("unchecked")
	@ModelAttribute("loaiban")
	 public List<String> getLoaiBans(){
			Session session = factory.getCurrentSession();
			String hql = "SELECT L.tenLoai FROM LoaiBanEntity L";
			Query query = session.createQuery(hql);
			List<String> list = (List<String>)query.list();
			System.out.println(list);
			return list;
		}

	
	public List<BanEntity> getBans(){
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		return list;
	}

}