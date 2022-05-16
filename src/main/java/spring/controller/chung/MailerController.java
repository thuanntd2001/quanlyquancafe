package spring.controller.chung;

import java.io.IOException;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quancafehighland.model.UserModel;
import com.quancafehighland.service.impl.UserService;

import spring.Recaptcha.RecaptchaVerification;

@Controller
@RequestMapping("/mailer/")
public class MailerController {
	@RequestMapping("form")
	public String index() {
		return "mailer/form";
	}

	@Autowired
	JavaMailSender mailer;

	@RequestMapping("send")
	public String send(ModelMap model, @RequestParam("from") String from, @RequestParam("to") String to,
			HttpServletRequest request) throws IOException {
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = RecaptchaVerification.verify(gRecaptchaResponse);
		UserService usv = new UserService();
		UserModel user = usv.findByEmail(to);
		if (user == null || !verify) {
			if (!verify) {
				model.addAttribute("reCaptra", "Vui lòng nhập reCaptra");
			} else model.addAttribute("message", "user có email này không tồn tại trong hệ thống");			
		} else
			try {
				// Táº¡o mail
//        	   from = "nhonamstg@gmail.com";
				MimeMessage mail = mailer.createMimeMessage();
				// Sá»­ dá»¥ng lá»›p trá»£ giÃºp
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				helper.setFrom(from, from);

				helper.setTo(to);
				helper.setReplyTo(from, from);
				helper.setSubject("QUÁN CAFE HIGHLAND KÍNH GỬI");
				helper.setText("PASSWORD: " + user.getPasswd(), true);
				// Gá»­i mail
				mailer.send(mail);
				model.addAttribute("message", "truy cập gmail để nhận mã!");
			} catch (Exception ex) {
				model.addAttribute("message", "Lỗi gửi mail !");
			}
		return "mailer/form";
	}
}