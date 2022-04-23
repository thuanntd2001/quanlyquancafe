package spring.controller.admin;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin-home/")
public class ThongKe {

	@RequestMapping(value = "thong-ke" , method = RequestMethod.GET)
	public String thongKe() {
		
		
		return "admin/thongke";
	}


}