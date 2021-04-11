package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ParticipationRepository participationRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ConversationRepository conversationRepository, ParticipationRepository participationRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.participationRepository = participationRepository;
    }

    @Override
    public List<Message> getMessages() {
        return null;
    }

    @Override
    public Page<Message> getMessageById(Integer id, Pageable pageable) {
        return messageRepository.findByConversationId(id, pageable);
    }

    @Override
    public Message addMessage(Message message, Integer conversationId) {
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

    @Override
    public Message updateMessage(Integer conversationId, Message message) {
        return null;
    }

    @Override
    public void deleteMessage(Integer userId) {

    }

}
