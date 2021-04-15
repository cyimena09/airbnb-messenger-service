package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class ConversationController {

    private final IConversationService conversationService;

    // CONSTRUCTOR

    public ConversationController(IConversationService conversationService) {
        this.conversationService = conversationService;
    }

    // METHODS

    @GetMapping("/conversations/by/participations/{id}")
    public ResponseEntity<Page<ConversationDto>> getConversationsByParticipantId(@PathVariable Integer id, Pageable pageable) {

        try {
            return new ResponseEntity<>(this.conversationService.getConversationsByParticipantId(id, pageable), HttpStatus.OK);
        } catch (ConversationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
