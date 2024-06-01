package email;


import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,"UTF-8");

        messageHelper.setFrom(dotenv.get("EMAIL_USERNAME"));
        messageHelper.setTo(email);
        messageHelper.setSubject("Registration Confirmation");

        // todo change company name
        String content = "<p>Welcome, " + username + "!</p>"
                + "<p>Thank you for registering with us. We are excited to have you on board.</p>"
                + "<p>Best regards,<br>Your Company</p>";

        log.info("Sending email to: " + email);
        mailSender.send(mimeMessage);
        log.info("Email sent successfully");
    }
}
