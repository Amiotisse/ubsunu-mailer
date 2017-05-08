package com.amiotisse.ubsunu.mailer.marks.service;

import com.amiotisse.ubsunu.mailer.marks.model.Mark;
import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author himna
 * @since 5/8/2017.
 */
public class MarksListPublisherSparkPostImpl implements MarksListPublisher {

    private static Logger log = LoggerFactory.getLogger(MarksListPublisherSparkPostImpl.class);

    private String markListPublishUrlFormat;
    private Client mailSender;
    private String fromEmail;
    private String messageSubject;
    // api key : aac4ac5860131c7ec11662b9eb705ac2ce1401f6


    public MarksListPublisherSparkPostImpl(String markListPublishUrlFormat, Client mailSender, String fromEmail, String messageSubject) {
        this.markListPublishUrlFormat = markListPublishUrlFormat;
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
        this.messageSubject = messageSubject;
    }

    @Override
    public void publishOne(String title, Mark mark) {
        try {
            mailSender.sendMessage(
                    fromEmail,
                    mark.getStudent().getEmail(),
                    messageSubject,
                    messageText(mark.getStudent(),title,mark.getValue()),
                    null
            );
        } catch (SparkPostException e) {
            log.error("can not sent mail" , e);
        }
    }

    @Override
    public String markListPublishUrlFormat() {
        return markListPublishUrlFormat;
    }
}
