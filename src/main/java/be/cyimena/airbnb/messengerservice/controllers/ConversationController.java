package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Conversation> getConversationsByParticipantId(@PathVariable Integer id, Pageable pageable) {
        return this.conversationService.getConversationsByParticipantId(id, pageable);
    }

}
