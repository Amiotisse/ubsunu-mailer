package com.amiotisse.ubsunu.mailer.marks.service;

import com.amiotisse.ubsunu.mailer.marks.model.Mark;
import com.amiotisse.ubsunu.mailer.marks.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import com.amiotisse.ubsunu.mailer.marks.model.MarkList;
/**
 * @author himna
 * @since 5/8/2017.
 */
public class MarksListPublisherSmtpGmailImpl implements MarksListPublisher{

    private static Logger log = LoggerFactory.getLogger(MarksListPublisherSmtpGmailImpl.class);

    private MailSender mailSender ;
    private SimpleMailMessage messageTemplate;
    private String markListPublishUrlFormat;

    public MarksListPublisherSmtpGmailImpl(MailSender mailSender, SimpleMailMessage messageTemplate , String markListPublishUrlFormat ) {
        this.mailSender = mailSender;
        this.messageTemplate = messageTemplate;
        this.markListPublishUrlFormat = markListPublishUrlFormat;
    }

    public void publishOne (String title , Mark mark){
        SimpleMailMessage msg = new SimpleMailMessage(this.messageTemplate);
        msg.setTo(mark.getStudent().getEmail());
        msg.setText(messageText(mark.getStudent() , title , mark.getValue()));
        try{
            this.mailSender.send(msg);
        }
        catch (MailException e) {
            log.error("can not sent mail" , e);
        }
    }

    @Override
    public String markListPublishUrlFormat() {
        return markListPublishUrlFormat;
    }
}
