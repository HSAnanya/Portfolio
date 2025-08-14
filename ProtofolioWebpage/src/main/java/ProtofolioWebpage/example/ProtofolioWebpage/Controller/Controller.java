package ProtofolioWebpage.example.ProtofolioWebpage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendEmail")
    public String sendEmail(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("ananyahsananya@gmail.com"); // your Gmail
            mailMessage.setTo(email); // recipient from form
            mailMessage.setSubject("Message from " + name); // subject using name
            mailMessage.setText(message); // body from form

            mailSender.send(mailMessage);
            return "Email sent successfully to " + email;

        } catch (MailException e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
