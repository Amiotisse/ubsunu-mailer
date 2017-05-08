package com.amiotisse.ubsunu.mailer.marks.controller;

import com.amiotisse.ubsunu.mailer.marks.model.MarkList;
import com.amiotisse.ubsunu.mailer.marks.service.MarksListPublisher;
import com.amiotisse.ubsunu.mailer.marks.service.MarksListPublisherSmtpGmailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author himna
 * @since 5/8/2017.
 */
@RestController
@RequestMapping("/marks")
public class MarksListController {

    private MarksListPublisher publisher ;

    private static final Logger log = LoggerFactory.getLogger(MarksListController.class);
    public MarksListController(MarksListPublisher publisher) {
        this.publisher = publisher;
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity publish (
            @RequestBody MarkList markList
            ){
        log.info("POST /marks/publish | body ={}" , markList);
        publisher.publish(markList);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
