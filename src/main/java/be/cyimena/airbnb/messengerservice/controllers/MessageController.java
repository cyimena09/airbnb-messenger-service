package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.assetsservice.models.RealEstate;
import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.exceptions.MessageNotFoundException;
import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @PostMapping("/messages")
    public Message addMessage(
            @RequestBody Message message,
            @RequestParam(value = "conversationId", required = false) Integer conversationId) {
        // Todo créer des services et ajouter des exceptions
        Conversation conversation = new Conversation();

        // 1. Cas ou la conversation n'existe pas => on crée une nouvelle conversation et une nouvelle participation
        if (conversationId == null) {
            // On crée une nouvelle conversation
            conversation = this.conversationRepository.save(conversation);

            // On crée la participation
            Participation participation = new Participation();
            participation.setUserId(message.getSenderId());
            participation.setConversation(conversation);
            this.participationRepository.save(participation);
        }
        // 2. Ca ou la conversation existe
        else {
            conversation.setId(conversationId);
        }
        // On ajoute la nouvelle conversation au message avant de l'enregistrer
        message.setConversation(conversation);
        // 3. On enregistre le message en fonction de l'id de la conversation
        return this.messageRepository.save(message);
    }

    @GetMapping("/messages/{id}")
    public Page<Message> getMessage(@PathVariable Integer id, Pageable pageable) {
        //return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        return messageRepository.findByConversationId(id, pageable);
    }
//
//    @GetMapping("/conversation/{id}")
//    public void deleteMessage(@PathVariable Integer id) {
//        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
//    }

}
