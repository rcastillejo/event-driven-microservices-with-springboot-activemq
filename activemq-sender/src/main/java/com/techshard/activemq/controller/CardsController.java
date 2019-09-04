package com.techshard.activemq.controller;

import com.techshard.activemq.domain.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
@RestController
@RequestMapping("/cards")
public class CardsController {
    private final Logger logger = LoggerFactory.getLogger(CardsController.class);

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;


    private List<Card> cards;

    @PostConstruct
    public void init(){
        cards = new ArrayList<>();
    }

    @PostMapping("{card-id}")
    public ResponseEntity<String> createCard(@PathVariable("card-id") final String cardId,
                                             @RequestBody Card body){
        jmsTemplate.convertAndSend(queue, cardId);
        body.setId(cardId);
        cards.add(body);
        logger.info("Card received {}={} ", cardId, body);
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> listCards(){
        return new ResponseEntity(cards, HttpStatus.OK);
    }

}
