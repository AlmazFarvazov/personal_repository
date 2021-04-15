package ru.itis.javalab.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class EmailUtilImpl implements EmailUtil {

    private final JavaMailSender javaMailSender;
    private final ExecutorService executorService;

    public EmailUtilImpl(JavaMailSender javaMailSender, ExecutorService executorService) {
        this.javaMailSender = javaMailSender;
        this.executorService = executorService;
    }

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        executorService.submit(() -> javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
        }));
    }
}
