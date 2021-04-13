package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class MessageController {

    private final IMessageService messageService;

    // CONSTRUCTOR

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    // METHODS

    @GetMapping("/messages/by/conversations/{id}")
    public Page<Message> getMessagesByConversationId(@PathVariable Integer id, Pageable pageable) {
        return this.messageService.getMessagesByConversationId(id, pageable);
    }

    // Méthode utilisé lorsqu'on ne connait pas l'id de la conversation mais qu'on dispose de l'id du/des participant(s)
    @GetMapping("/messages/by/participations")
    public Page<Message> getMessagesByParticipations(
            @RequestParam(value = "participantsIds") List<Integer> participantsIds, Pageable pageable) {

        return this.messageService.getMessagesByParticipations(participantsIds, pageable);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.OK);
    }

}
