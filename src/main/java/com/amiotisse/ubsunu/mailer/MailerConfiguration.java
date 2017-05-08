package com.amiotisse.ubsunu.mailer;

import com.amiotisse.ubsunu.mailer.marks.controller.MarksListController;
import com.amiotisse.ubsunu.mailer.marks.service.MarksListPublisher;

import com.amiotisse.ubsunu.mailer.marks.service.MarksListPublisherSparkPostImplBuilder;
import com.sparkpost.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author himna
 * @since 5/8/2017.
 */
@Configuration
public class MailerConfiguration {


    @Value("${ubsunu.mailer.from}")
    private String mailerFrom;
    @Value("${ubsunu.mailer.marks.list.publish.format.url}")
    private String markListPublishUrlFormat;
    @Value("${ubsunu.mailer.marks.list.publish.subject}")
    private String markListPublishSubject;
    @Value("${ubsunu.mailer.sparkpost.api.key}")
    private String sparkpostApiKey;

    @Bean
    public SimpleMailMessage messageTemplate (){
        SimpleMailMessage template = new SimpleMailMessage();
        template.setFrom(mailerFrom);
        template.setSubject(markListPublishSubject);
        return template;
    }
    /*
    @Bean
    public MailSender javaMailSender (){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername("himnabil@gmail.com");
        mailSender.setPassword("simNab31.");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        Properties mailSenderProperties = new Properties();
        //mailSenderProperties.put("mail.smtp.user","himnabil");
        //mailSenderProperties.put("mail.smtp.pwd","simNab31.");
        //mailSenderProperties.put("mail.smtp.host", "smtp.gmail.com");
        //mailSenderProperties.put("mail.smtp.port", "25");
        mailSenderProperties.put("mail.debug", "true");
        mailSenderProperties.put("mail.smtp.auth", "true");
        mailSenderProperties.put("mail.smtp.starttls.enable","true");
        //mailSenderProperties.put("mail.smtp.EnableSSL.enable","true");

        //mailSenderProperties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //mailSenderProperties.setProperty("mail.smtp.socketFactory.fallbac k", "false");
        //mailSenderProperties.setProperty("mail.smtp.port", "465");
        //mailSenderProperties.setProperty("mail.smtp.socketFactory.port", "465");


        mailSender.setJavaMailProperties(mailSenderProperties);
        return mailSender;
    }


    @Bean
    public MarksListPublisher marksListPublisherSmtpGmail (
             MailSender mailSender ,
             SimpleMailMessage messageTemplate
    ){
        return new MarksListPublisherSmtpGmailImpl(
                mailSender ,
                messageTemplate,
                markListPublishUrlFormat
        );
    }
    */

    @Bean
    public Client mailSenderSparkPost(){
        return new Client(sparkpostApiKey);
    }

    @Bean()
    public MarksListPublisher marksListPublisherSparkPost(Client mailSender){
        return new MarksListPublisherSparkPostImplBuilder()
                .setFromEmail(mailerFrom)
                .setMarkListPublishUrlFormat(markListPublishUrlFormat)
                .setMessageSubject(markListPublishSubject)
                .setMailSender(mailSender)
                .build();
    }

    @Bean
    public MarksListController marksListController (@Qualifier("marksListPublisherSparkPost") MarksListPublisher marksListPublisher){
        return new MarksListController(marksListPublisher);
    }
}
