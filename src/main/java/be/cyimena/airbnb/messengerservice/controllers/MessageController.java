package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class MessageController {

    private final IMessageService messageService;

    // CONSTRUCTOR

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    // METHODS

    @PostMapping("/messages")
    public ResponseEntity<Message> addMessage(
            @RequestBody Message message,
            @RequestParam(value = "conversationId", required = false) Integer conversationId) {
        return new ResponseEntity<>(messageService.addMessage(message, conversationId), HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public Page<Message> getMessage(@PathVariable Integer id, Pageable pageable) {
        return this.messageService.getMessageById(id, pageable);
    }
//
//    @GetMapping("/conversation/{id}")
//    public void deleteMessage(@PathVariable Integer id) {
//        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
//    }

}
