package spring.controller.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
import spring.entity.NhanVienEntity;

@Transactional
@Controller
public class DatBanControllerHome {

	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "trang-chu", method = RequestMethod.GET)
	public String datban(ModelMap model){
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
		model.addAttribute("id", id);
		DatBanEntity datban = new DatBanEntity();
		model.addAttribute("datban", datban);
		return "web/datban2";
	}
	
	
	@RequestMapping(value = "dat-ban/{id}.htm", params = "btndatban", method=RequestMethod.POST)
	public String datBan1(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id, @ModelAttribute("datban") DatBanEntity datban) {
		System.out.println(datban.getTgDuKien());
		List<ChiTietDatEntity> chiTietDat = this.getChiTietDat(id);
		model.addAttribute("chiTietDat", chiTietDat);
		model.addAttribute("id", id);
		
		UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id1 = user1.getID();
		datban.setDbnv(this.getNV(id1));
		datban.setNgayDat(new Date());

		Integer temp = this.themDatBan(datban,id);
		if(temp != 0) {
			model.addAttribute("message","Thêm mới thành công");
			
		}else {
			model.addAttribute("message","Thêm mới thất bại");
		}
		return "web/datban2";
	};
	
	
	public Integer themDatBan(DatBanEntity datban, Long id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			/*ChiTietDatEntity chiTietDat = null;
			chiTietDat.setBans(getBan(id));
			chiTietDat.setDatBan(datban);*/
			
			session.save(datban);
			/*session.save(chiTietDat);*/
			t.commit();
		}
		catch (Exception e) {
			System.out.println("loi");
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
	public BanEntity getBan(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where id =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BanEntity list = (BanEntity) query.list().get(0);
		return list;
	}
}