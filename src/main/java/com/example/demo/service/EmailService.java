package com.example.demo.service;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j

@Service
@RequiredArgsConstructor
public class EmailService {
     private final JavaMailSender mailSender;
    private final String FROM_EMAIL = "pruebag257@gmail.com";

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(FROM_EMAIL);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(buildEmailTemplate(text), true);

            log.info("Enviando email a: {}", to);
            mailSender.send(message);
            log.info("Email enviado exitosamente a: {}", to);

        } catch (MessagingException e) {
            log.error("Error enviando email a: {} - Error: {}", to, e.getMessage());
            throw new MailSendException("Error al enviar el correo electrónico", e);
        }
    }

    private String buildEmailTemplate(String text) {
        return """
            <html>
                <body style='font-family: Arial, sans-serif;'>
                    <div style='padding: 20px; background: #f5f5f5;'>
                        <h2 style='color: #2c3e50;'>Bienvenido al Sistema</h2>
                        <p>%s</p>
                        <p style='color: #7f8c8d; font-size: 12px;'>
                            Este es un correo automático, por favor no responder.
                        </p>
                    </div>
                </body>
            </html>
            """.formatted(text);
    }
}



