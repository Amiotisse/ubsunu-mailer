package com.amiotisse.ubsunu.mailer.marks.service;

import com.amiotisse.ubsunu.mailer.marks.model.Mark;
import com.amiotisse.ubsunu.mailer.marks.model.MarkList;
import com.amiotisse.ubsunu.mailer.marks.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author himna
 * @since 5/8/2017.
 */
public interface MarksListPublisher {

    default void publish (MarkList markList){
        markList.getList()
                .parallelStream()
                .forEach( mark -> publishOne(markList.getTitle() , mark))
        ;
    }

    void publishOne (String title , Mark mark);

    default String messageText(Student student, String title, float value){
        return"Bonjour "
                + student.getFirstName() + " "
                + student.getLastName() + "\n"
                + String.format("Votre note à l'évaluation \"%s\" est de %2$.2f", title , value) + "\n"
                + "Vous pouvez consulter la liste des notes sur l'url : "
                + String.format(markListPublishUrlFormat(), title).replace(" ","%20");
    }

    String markListPublishUrlFormat();
}
