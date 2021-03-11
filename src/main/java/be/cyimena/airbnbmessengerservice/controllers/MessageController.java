package be.cyimena.airbnbmessengerservice.controllers;

import be.cyimena.airbnbmessengerservice.exceptions.MessageNotFoundException;
import be.cyimena.airbnbmessengerservice.models.Message;
import be.cyimena.airbnbmessengerservice.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/conversation/{id}")
    public Message updateMessage(@PathVariable Integer id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
    }

    @GetMapping("/conversation/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
    }

}
