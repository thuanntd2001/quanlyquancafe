package spring.controller.admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.entity.NhanVienEntity;


@Controller
public class test {
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String index( @ModelAttribute("test") String test) {
		Date date1;
		try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(test);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "admin/test";
		
	}
	
	@RequestMapping(value = "test", method = RequestMethod.POST)
	public String index1( @ModelAttribute("test") String test) {
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
		try {
			Date startDate = df.parse(test);
			System.out.print(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/test";
		
	}

}
