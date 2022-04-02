package spring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HoaDonController {

	@RequestMapping(value = "hoa-don" , method = RequestMethod.GET)
	public String showMenu() {
		return "web/bill";
	}

	@RequestMapping("hoa-don-2")
	public String Bill2() {
		return "web/bill2";
	}
	
}