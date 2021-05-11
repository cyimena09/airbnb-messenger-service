package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.domain.Message;
import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @GetMapping("/messages/by/conversations/{id}")
    public ResponseEntity<Page<MessageDto>> getMessagesByConversationId(@PathVariable Integer id, Pageable pageable) {
        try {
            Page<MessageDto> messages =  this.messageService.getMessagesByConversationId(id, pageable);

            if (messages == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(messages, HttpStatus.OK);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Méthode utilisée lorsqu'on ne connait pas l'id de la conversation mais qu'on dispose de l'id du/des participant(s)
    @GetMapping("/messages/by/participations")
    public ResponseEntity<Page<MessageDto>> getMessagesByParticipations(
            @RequestParam(value = "participantsIds") List<Integer> participantsIds, Pageable pageable) {

        try {
            return new ResponseEntity<>(this.messageService.getMessagesByParticipations(participantsIds, pageable), HttpStatus.OK);
        } catch (ServiceException e)  {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageDto> addMessage(@RequestBody Message message) {
        try {
            return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.BAD_REQUEST);
        }
    }

}
