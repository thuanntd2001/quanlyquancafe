package spring.controller.chung;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class IconController {

	@Autowired
	ServletContext context;
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "user-avt", method = RequestMethod.POST)
	public String Avt(ModelMap model, @RequestParam("avt") MultipartFile avt, HttpServletRequest request) {

		try {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			String photoPath = context.getRealPath("/files/" + user.getUserName() + avt.getOriginalFilename());
			avt.transferTo(new File(photoPath));
			//tao ten file icon de lat ghi vo csdl
			user.setIcon(user.getUserName() + avt.getOriginalFilename());
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();

			try {

				UserTBEntity userUpdate = (UserTBEntity) session.merge((UserTBEntity)user);

				t.commit();
				model.addAttribute("message", "cập nhật thành công");

				Thread.sleep(5000);
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "cập nhật thất bại");
				e.printStackTrace();
			} finally {
				session.close();
			}
			

			return "web/user";
		} catch (Exception e) {
			model.addAttribute("message", "lỗi lưu file!");
		}
		return "web/user";
	}

}
