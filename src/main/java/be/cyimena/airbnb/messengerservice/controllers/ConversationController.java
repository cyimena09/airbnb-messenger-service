package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.assetsservice.exceptions.RealEstateNotFoundException;
import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1")
public class ConversationController {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/conversations/{id}")
    public Conversation getConversationById(@PathVariable Integer id) {

        return this.conversationRepository.findById(id).orElseThrow();

    }

    @PostMapping("/conversations")
    public Conversation createConversation(@RequestBody Conversation conversation) {
        return this.conversationRepository.save(conversation);
    }
//    @Autowired
//    private ConversationRepository conversationRepository;
//
//    @GetMapping("/conversation/{id}")
//    public Conversation getConversationsByUserId(@PathVariable Integer id) {
//        return conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
//    }
//
//    @GetMapping("/conversation/{id}")
//    public Conversation getConversationById(@PathVariable Integer id) {
//        return conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
//    }

}
