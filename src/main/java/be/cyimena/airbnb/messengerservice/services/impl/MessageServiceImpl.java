package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.mappers.IConversationMapper;
import be.cyimena.airbnb.messengerservice.mappers.IMessageMapper;
import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.domain.Message;
import be.cyimena.airbnb.messengerservice.mappers.IParticipationMapper;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

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

    public Page<MessageDto> getMessagesByParticipations(Set<ParticipationDto> participations, Pageable pageable) {
        ConversationDto conversation = this.getMatchingConv(participations);

        if (conversation != null) {
            return this.getMessagesByConversationId(conversation.getId(), pageable);
        } else {
            return null;
        }
    }

    @Override
    public MessageDto addPrivateMessage(MessageDto messageDto) throws ServiceException {
        // todo return exception
        if (messageDto == null || messageDto.getConversation() == null || messageDto.getConversation().getParticipations() == null) {
            return null;
        }
        // If the conversation already exist
        if (messageDto.getConversation().getId() != null && !StringUtils.isBlank(messageDto.getConversation().getId().toString())) {
            // todo find conversation
            return this.addMessage(messageDto.getConversation().getId(), messageDto);
        }
        // If the conversation (conversationId) is null,
        if (messageDto.getConversation().getId() == null) {
            // First we try to find a matching conversation with two users supplied
            ConversationDto conversationDto = this.getMatchingConv(messageDto.getConversation().getParticipations());
            if (conversationDto == null) {
                // If no match we create a new conversation and add participants
                conversationDto = new ConversationDto();
                conversationDto = this.conversationService.createConversation(conversationDto);
                for (ParticipationDto p : messageDto.getConversation().getParticipations()) {
                    p.setConversation(conversationDto);
                    this.participationService.addParticipation(p);
                }
            }
            Set<ParticipationDto> savedParticipation = this.participationService.getParticipationsByConversationId(conversationDto.getId(), Pageable.unpaged()).toSet();
            MessageDto savedMessage = this.addMessage(conversationDto.getId(), messageDto);
            savedMessage.getConversation().setParticipations(savedParticipation);

            return savedMessage;
        }
        return null;
    }

    public ConversationDto getMatchingConv(Set<ParticipationDto> participations) {
        ParticipationDto firstParticipation = participations.stream().findFirst().get();
        ParticipationDto secondParticipation = participations.stream().skip(1).findFirst().get();
        List<ConversationDto> conversationsInDb = this.conversationService.getConversationsByParticipantId(firstParticipation.getParticipantId(), Pageable.unpaged()).toList();

        for (ConversationDto conversationInDb : conversationsInDb) {
            for (ParticipationDto participationInDb : conversationInDb.getParticipations()) {
                if (participationInDb.getParticipantId().equals(secondParticipation.getParticipantId())) {
                    return conversationInDb;
                }
            }
        }
        return null;
    }

    /**
     * Private method which simply save a message.
     *
     * @param conversationId
     * @param messageDto
     */
    private MessageDto addMessage(UUID conversationId, MessageDto messageDto) throws ServiceException {
        try {
            // sauvegarde du message
            Conversation conversation = new Conversation();
            conversation.setId(conversationId);
            Message message = this.messageMapper.INSTANCE.mapToMessage(messageDto);
            message.setConversation(conversation);
            message = messageRepository.save(message);
            // renvoie du message avec sa conversation
            ConversationDto conversationSavedDto = this.conversationService.getConversationById(message.getConversation().getId());
            MessageDto messageSavedDto = messageMapper.INSTANCE.mapToMessageDto(message);
            messageSavedDto.setConversation(conversationSavedDto);
            return messageSavedDto;
        } catch (ServiceException | ConversationNotFoundException e) {
            throw new ServiceException("Impossible de sauvegarder les messages");
        }
    }

    @Override
    public void updateMessage(UUID conversationId, MessageDto message) {
    }

    @Override
    public void deleteMessage(UUID userId) {

    }

}
