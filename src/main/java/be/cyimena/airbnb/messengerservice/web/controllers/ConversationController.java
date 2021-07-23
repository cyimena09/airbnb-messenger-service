package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/messenger")
@RequiredArgsConstructor
public class ConversationController {

    private final IConversationService conversationService;

    @GetMapping("/conversations/{id}")
    public ResponseEntity<ConversationDto> getConversationById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(this.conversationService.getConversationById(id), HttpStatus.OK);
        } catch (ConversationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/conversations/by/participations/{id}")
    public ResponseEntity<Page<ConversationDto>> getConversationsByParticipantId(@PathVariable UUID id, Pageable pageable) {
        try {
            return new ResponseEntity<>(this.conversationService.getConversationsByParticipantId(id, pageable), HttpStatus.OK);
        } catch (ConversationNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
