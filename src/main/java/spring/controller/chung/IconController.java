package spring.controller.chung;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.utils.SessionUtil;

import spring.entity.UserTBEntity;

@Controller
@javax.transaction.Transactional
public class IconController {

	@Autowired
	ServletContext context;
	@Autowired
	SessionFactory factory;
	
	/*private UserTBEntity UserModelToUserEntity(UserModel model) {
		UserTBEntity entity=new UserTBEntity();
		entity.getChucVu().setId(model.getRoleID());
		entity.setIcon(model.getIcon());
		entity.setUserName(model.getUserName());
		entity.setPasswd(model.getPasswd());
		entity.setEmail(model.getEmail());
		entity.getUsernv().setMaNV(model.getID());
		return entity;
	}*/


	public UserTBEntity getUser(Long id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserTBEntity where usernv.maNV =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		UserTBEntity list = (UserTBEntity) query.list().get(0);
		return list;
	}
	@RequestMapping(value = "user-avt", method = RequestMethod.POST)
	public String Avt(ModelMap model, @RequestParam("avt") MultipartFile avt, HttpServletRequest request) {

		try {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			UserModel user1 = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");

			String photoPath = context.getRealPath("/files/" + user1.getUserName().trim() + avt.getOriginalFilename().trim());

			Long id = user1.getID();
			UserTBEntity user = this.getUser(id);
			avt.transferTo(new File(photoPath));
			user.setIcon(user.getUserName().trim() + avt.getOriginalFilename().trim());
			
			try {
				/*UserTBEntity userUpdate = (UserTBEntity) session.merge(getUser(user.getID()));
				//tao ten file icon de lat ghi vo csdl
				
				userUpdate.setIcon(user.getUserName() + avt.getOriginalFilename());*/
				session.update(user);
				t.commit();
				model.addAttribute("message", "cập nhật thành công!");

				Thread.sleep(5000);
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "cập nhật thất bại!");
				e.printStackTrace();
			} finally {
				session.close();
			}
			

			return "web/user";
		} catch (Exception e) {
			model.addAttribute("message", "lỗi lưu file!");
			e.printStackTrace();
		}
		return "web/user";
	}

}