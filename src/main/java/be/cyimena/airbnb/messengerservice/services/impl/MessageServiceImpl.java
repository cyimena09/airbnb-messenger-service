package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.mappers.IConversationMapper;
import be.cyimena.airbnb.messengerservice.mappers.IMessageMapper;
import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.domain.Message;
import be.cyimena.airbnb.messengerservice.mappers.IParticipationMapper;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;
    private IMessageMapper messageMapper;

    @Autowired
    private IConversationService conversationService;
    private IConversationMapper conversationMapper;

    @Autowired
    private IParticipationService participationService;
    private IParticipationMapper participationMapper;

    @Override
    public Page<MessageDto> getMessagesByConversationId(UUID id, Pageable pageable) {
        Page<Message> messages;
        try {
            messages = messageRepository.findMessagesByConversationId(id, pageable);

            if (messages.isEmpty()) {
                return null;
            } else {
                return messages.map(messageMapper.INSTANCE::mapToMessageDto);
            }
        } catch (SQLException e) {
            throw new ServiceException("Impossible de récupérer les messages avec la conversation " + id);
        }
    }

    public Page<MessageDto> getMessagesByParticipationsIds(List<UUID> participantsIds, Pageable pageable) {
        UUID conversationId = this.conversationService.getConversationIdByParticipantsIds(participantsIds);
        return this.getMessagesByConversationId(conversationId, pageable);
    }

    @Override
    public void addMessage(Message message) throws ServiceException {
        UUID conversationId;
        // todo vérifier qu'il existe une liste de participant

        // --------------------------------------------------------------------------
        // 1. Si la conversation est nulle, on crée une nouvelle conversation et on ajoute les deux premiers participations
        // --------------------------------------------------------------------------

        if (message.getConversation() == null || message.getConversation().getId() == null) {
            Conversation conversation = new Conversation();
            conversation = this.conversationMapper.mapToConversation(this.conversationService.createConversation(conversation));
            conversationId = conversation.getId();
            Set<Participation> liste = message.getConversation().getParticipations();

            for (Participation p : liste) {
                p.setConversation(conversation);
                this.participationService.addParticipation(participationMapper.mapToParticipationDto(p));
            }
        } else {
            conversationId = message.getConversation().getId();
        }

        // --------------------------------------------------------------------------
        // 2. Dans tous les cas on enregistre le message soit dans une conversation existante ou dans une nouvelle
        // --------------------------------------------------------------------------

        try {
            Conversation conversation = this.conversationMapper.mapToConversation(this.conversationService.getConversationById(conversationId));
            message.setConversation(conversation);
            this.messageMapper.mapToMessageDto(messageRepository.save(message));
        } catch (ServiceException | ConversationNotFoundException e) {
            throw new ServiceException("Impossible de sauvegarder les messages");
        }
    }

    @Override
    public void updateMessage(UUID conversationId, Message message) {
    }

    @Override
    public void deleteMessage(UUID userId) {

    }

}
