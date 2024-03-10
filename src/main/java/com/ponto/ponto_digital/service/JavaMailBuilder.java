package com.ponto.ponto_digital.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.ponto.ponto_digital.exception.PontoException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.internet.MimeMessage;

@Component
public class JavaMailBuilder {
    
    private final static String NOREPLY = "gfpoint.noreply@gmail.com";

    private final Configuration configuration;

    private final JavaMailSender javaMailSender;

    private String subject;

    private String to;

    @Autowired
    public JavaMailBuilder(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    private String template(final String name, final Map<String, Object> params){
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(name), params);
        } catch (TemplateException | IOException e) {
            throw new PontoException("Erro ao gerar o template", HttpStatus.BAD_REQUEST);
        }
    }

    public JavaMailBuilder subject(String assunto) {
        subject = assunto;

        return this;
    }

    public JavaMailBuilder to(String destinatario) {
        to = destinatario;

        return this;
    }

    public void fire(String path, Map<String, Object> params){

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           
            String template = template(path, params);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setFrom(NOREPLY);

            mimeMessageHelper.setTo(to);

            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.setText(template, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new PontoException("Erro ao gerar o template", HttpStatus.BAD_REQUEST);
        }

    }
    
}
