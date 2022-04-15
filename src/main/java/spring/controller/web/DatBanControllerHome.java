package spring.controller.web;

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

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.BanEntity;
import spring.entity.ChiTietDatEntity;
import spring.entity.DatBanEntity;
import spring.entity.UserTBEntity;

@Transactional
@Controller
public class DatBanControllerHome {

	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "trang-chu", method = RequestMethod.GET)
	public String datban(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BanEntity";
		Query query = session.createQuery(hql);
		List<BanEntity> list = query.list();
		model.addAttribute("bans", list);
		return "web/datban";
	}

	@RequestMapping(value = "dat-ban/{id}.htm", params = "linkView")
	public String xemDatBan(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) {
		List<ChiTietDatEntity> chiTietDat = this.getChiTietDat(id);
		model.addAttribute("chiTietDat", chiTietDat);
		DatBanEntity datban = new DatBanEntity();
		model.addAttribute("datban", datban);
		return "web/datban2";
	}
	
	@RequestMapping(value = "dat-ban/{id}.htm?linkView", params = "btndatban")
	public String datBan(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id, @ModelAttribute("datban") DatBanEntity datban) {
		Integer temp = this.themDatBan(datban);
		if(temp != 0) {
			model.addAttribute("message","Thêm mới thành công");
			
		}else {
			model.addAttribute("message","Thêm mới thất bại");
		}
		List<ChiTietDatEntity> chiTietDat = this.getChiTietDat(id);
		model.addAttribute("chiTietDat", chiTietDat);
		return "web/datban2";
	};			
	
	public Integer themDatBan(DatBanEntity datban) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			/*UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			Long id = user1.getID();
			UserTBEntity user = this.getUser(id);
			datban.set*/
			datban.setNgayDat(new Date());
			session.save(datban);
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
	
	
	public List<ChiTietDatEntity> getChiTietDat (Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietDatEntity where bans.id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<ChiTietDatEntity> list = query.list();
		return list;
	}
}