package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements IMessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final IParticipationService participationService;
    private final IConversationService conversationService;

    // todo UTILISER UNIQUEMENT LES SERVICES
    public MessageServiceImpl(
            MessageRepository messageRepository,
            ConversationRepository conversationRepository,
            IParticipationService participationService,
            IConversationService conversationService) {

        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.conversationService = conversationService;
        this.participationService = participationService;
    }

    @Override
    public Page<Message> getMessagesByConversationId(Integer id, Pageable pageable) {
        if (messageRepository.findByConversationId(id, pageable).isEmpty()) {
            return null;
        } else {
            return messageRepository.findByConversationId(id, pageable);
        }
    }

    public Page<Message> getMessagesByParticipations(List<Integer> participantsIds, Pageable pageable) {
        // On récupère l'id de la conversation en effectuant une requete qui match avec les participations fournie.
        Integer conversationid = this.conversationRepository.findConversationIdByParticipations(participantsIds);
        return this.getMessagesByConversationId(conversationid, pageable);
    }

    @Override
    public Message addMessage(Message message) throws ServiceException {
        Integer conversationId;
        // todo vérifier qu'il existe une liste de participant

        // --------------------------------------------------------------------------
        // 1. Si la conversation est nulle, on crée une nouvelle conversation et on ajoute les deux premiers participations
        // --------------------------------------------------------------------------

        if (message.getConversation() == null || message.getConversation().getId() == null) {
            Conversation conversation = new Conversation();
            conversation = this.conversationRepository.save(conversation);
            conversationId = conversation.getId();
            Set<Participation> liste = message.getConversation().getParticipations();

            for (Participation p : liste) {
                p.setConversation(conversation);
                this.participationService.createParticipation(p);
            }
        } else {
            conversationId = message.getConversation().getId();
        }

        // --------------------------------------------------------------------------
        // 2. Dans tous les cas on enregistre le message soit dans une conversation existante ou dans une nouvelle
        // --------------------------------------------------------------------------

        Conversation conversation = this.conversationService.getConversationById(conversationId);
        message.setConversation(conversation);

        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Integer conversationId, Message message) {
        return null;
    }

    @Override
    public void deleteMessage(Integer userId) {

    }

}
