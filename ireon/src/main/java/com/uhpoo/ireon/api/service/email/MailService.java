package com.uhpoo.ireon.api.service.email;

import com.uhpoo.ireon.global.excpetion.MailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;

    public void sendMail(String toEmail,
                         String title,
                         String text) {
        SimpleMailMessage emailFrom = createEmailForm(toEmail, title, text);

        try {
            emailSender.send(emailFrom);
        }catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}," +
                    "title: {}, text: {}", toEmail, title, text);

            throw new MailException("이메일 전송 시 문제가 발생하였습니다.");
        }
    }

    private SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
