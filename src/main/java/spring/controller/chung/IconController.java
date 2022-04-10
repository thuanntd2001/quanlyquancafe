package spring.controller.chung;
import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IconController {
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="user-avt", method = RequestMethod.POST)
	public String Avt(ModelMap model, @RequestParam("avt") MultipartFile avt) {
		try {
			String photoPath = context.getRealPath("/files/"+avt.getOriginalFilename());
			avt.transferTo(new File(photoPath));
			
              model.addAttribute("avt_name", avt.getOriginalFilename());
              
              Thread.sleep(1500);
              return "job/applys";
		}
		catch(Exception e) {
			model.addAttribute("message", "lỗi lưu file!");
		}
		return "web/user";
	}
}
	
