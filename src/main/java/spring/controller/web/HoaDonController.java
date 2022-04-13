package spring.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.ChiTietDatEntity;
import spring.entity.ChiTietHDEntity;
import spring.entity.HoaDonEntity;

@Transactional
@Controller
public class HoaDonController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "hoa-don" , method = RequestMethod.GET)
	public String showMenu(ModelMap model,HttpServletRequest request) {
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		Long id = user.getiD();
		List<HoaDonEntity> hoaDon = this.getHoaDon(id);
		model.addAttribute("hoaDon", hoaDon);
		return "web/bill";
	}

	@RequestMapping(value = "hoa-don/{id}.htm", params = "linkView")
	public String xemChiTietHD(HttpServletRequest request, ModelMap model,
			@PathVariable("id") Long id) {
		List<ChiTietHDEntity> chiTietHD = this.getChiTietHD(id);
		model.addAttribute("chiTietHD", chiTietHD);
		return "web/bill2";
	}
	
	
	public List<HoaDonEntity> getHoaDon(Long id){
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDonEntity where hdnv.maNV = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		List<HoaDonEntity> list = query.list();
		return list;
	}
	
	public List<ChiTietHDEntity> getChiTietHD(Long id){
		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietHDEntity where hoaDon.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);	
		List<ChiTietHDEntity> list = query.list();
		return list;
	}
}