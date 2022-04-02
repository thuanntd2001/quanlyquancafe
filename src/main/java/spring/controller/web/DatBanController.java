package spring.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DatBanController {
	@RequestMapping("datban2")
	public String DatBan2() {
		return "web/datban2";
	}
}
