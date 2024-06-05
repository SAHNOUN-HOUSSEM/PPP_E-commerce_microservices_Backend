package email;


import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendRegistrationEmail(String email, String username) throws MessagingException {
        Dotenv dotenv = Dotenv.load();
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(dotenv.get("EMAIL_USERNAME"));
//        message.
//        System.out.println(dotenv.get("EMAIL_USERNAME"))
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");
        message.setTo(email);
        message.setSubject("Registration Confirmation");
        message.setText("Hello " + username + ",\n\n" +
                "Thank you for registering with us. We are excited to have you on board.\n\n" +
                "Best Regards,\n" +
                "The Team");
        mailSender.send(message);
        log.info("Registration email sent to {}", email);
    }
}
