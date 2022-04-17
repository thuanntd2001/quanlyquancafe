package spring.controller.admin;
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

import spring.entity.NhanVienEntity;
@Transactional
@Controller
@RequestMapping(value = "/admin-home/" )
public class QLNhanVienHome {

	/*@Autowired
	SessionFactory factory;
	@RequestMapping(value = "index" , method = RequestMethod.GET)
	public String showMenu(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		model.addAttribute("nhanvien", list);
		
		return "admin/QLNV";
	}
	
	@RequestMapping(value="form", method = RequestMethod.GET) 
    public String index(ModelMap model) {
		model.addAttribute("nhanvien",new NhanVienEntity());
          return "admin/form/updateNV";
    }*/

	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index( ModelMap model, @ModelAttribute("user") NhanVienEntity user) {
		List<NhanVienEntity> users = this.getUsers();				  
		model.addAttribute("nhanvien", users);
		return "admin/QLNV";
		
	}
	/*hiển thị form*/
	@RequestMapping(value="form", method = RequestMethod.GET) 
    public String index(ModelMap model) {
		model.addAttribute("user",new NhanVienEntity());
          return "admin/form/updateNV";
    }
	
	public List<NhanVienEntity> getUsers(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity";
		Query query = session.createQuery(hql);
		List<NhanVienEntity> list = query.list();
		return list;
	}
	/*thêm nhân viên*/
	@RequestMapping(value = "index", method = RequestMethod.POST )
	public String addUser(HttpServletRequest request, ModelMap model,@ModelAttribute("user") NhanVienEntity user) {
		Integer temp = this.insertUser(user);
		if(temp != 0) {
			/*model.addAttribute("message","them thanh cong");*/
//			user.setMaNV(null);
			user.setHoTen(null);
			user.setNgaySinh(null);
			user.setGioiTinh(null);
			user.setLuong(null);
			user.setSdt(null);
			user.setCmnd(null);
			user.setDiaChi(null);
			user.setNgayVaoLam(null);
			user.setDaNghi(false);
		}else {
			/*model.addAttribute("message","them that bai");*/
		}
		List<NhanVienEntity> users = this.getUsers();
		model.addAttribute("nhanvien", users);
		return"admin/QLNV";
	}
	
	public Integer insertUser(NhanVienEntity user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
		}catch(Exception e) {
			t.rollback();
			return 0;
		}finally {
			session.close();
		}
		return 1;
	} 
	
	/* phần chỉnh sửa */
	@RequestMapping(value = "form/{Username}", params = "linkEdit" )
	public String editUser (HttpServletRequest request, ModelMap model, @ModelAttribute("user") NhanVienEntity user, 
			@PathVariable("MANV") long maNV) {
		List<NhanVienEntity> users = this.getUsers();
		model.addAttribute("nhanvien", users);
		model.addAttribute("user", this.getUser(maNV));
		model.addAttribute("btnupdate","true");
		return "admin/form/updateNV";
	}
	public NhanVienEntity getUser (long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhanVienEntity where maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NhanVienEntity list = (NhanVienEntity) query.list().get(0);
		return list;
	}
}
