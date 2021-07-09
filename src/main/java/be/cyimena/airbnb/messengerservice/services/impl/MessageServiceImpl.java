package be.cyimena.airbnb.messengerservice.services.impl;

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
import java.util.List;
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
    public void addPrivateMessage(MessageDto messageDto) throws ServiceException {
        if (messageDto == null || messageDto.getConversation() == null || messageDto.getConversation().getParticipations() == null) {
            return;
        }
        // At least two participants in a private conversation are required
        if (messageDto.getConversation().getParticipations().size() < 2) {
            return;
        }
        // If the conversation already exist
        if (messageDto.getConversation().getId() != null && !StringUtils.isBlank(messageDto.getConversation().getId().toString())) {
            this.addMessage(messageDto.getConversation().getId(), messageDto);
            return;
        }
        // If the conversation (conversationId) is null, we create a new conversation and add participants
        if (messageDto.getConversation().getId() == null) {
            ConversationDto conversationDto = new ConversationDto();
            conversationDto = this.conversationService.createConversation(conversationDto);
            for (ParticipationDto p : messageDto.getConversation().getParticipations()) {
                p.setConversation(conversationDto);
                this.participationService.addParticipation(p);
            }
            this.addMessage(conversationDto.getId(), messageDto);
        }
    }

    /**
     * Private method which simply recording a message.
     *
     * @param conversationId
     * @param messageDto
     */
    private void addMessage(UUID conversationId, MessageDto messageDto) throws ServiceException {
        try {
            Conversation conversation = new Conversation();
            conversation.setId(conversationId);
            Message message = this.messageMapper.INSTANCE.mapToMessage(messageDto);
            message.setConversation(conversation);
            messageRepository.save(message);
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
