package be.cyimena.airbnbmessengerservice.controllers;

import be.cyimena.airbnbmessengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnbmessengerservice.models.Conversation;
import be.cyimena.airbnbmessengerservice.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class ConversationController {

    @Autowired
    private ConversationRepository conversationRepository;

    @GetMapping("/conversation/{id}")
    public Conversation getConversationsByUserId(@PathVariable Integer id) {
        return conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
    }

    @GetMapping("/conversation/{id}")
    public Conversation getConversationById(@PathVariable Integer id) {
        return conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
    }

}
