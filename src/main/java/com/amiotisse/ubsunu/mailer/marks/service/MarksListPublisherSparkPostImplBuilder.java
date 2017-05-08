package com.amiotisse.ubsunu.mailer.marks.service;

import com.sparkpost.Client;

public class MarksListPublisherSparkPostImplBuilder {
    private String markListPublishUrlFormat;
    private Client mailSender;
    private String fromEmail;
    private String messageSubject;

    public MarksListPublisherSparkPostImplBuilder setMarkListPublishUrlFormat(String markListPublishUrlFormat) {
        this.markListPublishUrlFormat = markListPublishUrlFormat;
        return this;
    }

    public MarksListPublisherSparkPostImplBuilder setMailSender(Client mailSender) {
        this.mailSender = mailSender;
        return this;
    }

    public MarksListPublisherSparkPostImplBuilder setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
        return this;
    }

    public MarksListPublisherSparkPostImplBuilder setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
        return this;
    }

    public MarksListPublisherSparkPostImpl build() {
        return new MarksListPublisherSparkPostImpl(markListPublishUrlFormat, mailSender, fromEmail, messageSubject);
    }
}