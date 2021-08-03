package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface IMessageService {

    Page<MessageDto> getMessagesByConversationId(UUID id, Pageable pageable) throws ServiceException;

    Page<MessageDto> getMessagesByParticipations(Set<ParticipationDto> participations, Pageable pageable) throws ServiceException;

    Page<MessageDto> getMessagesByParticipantId(UUID id, Pageable pageable) throws ServiceException;

    MessageDto addPrivateMessage(MessageDto message) throws ServiceException;

    void updateMessage(UUID conversationId, MessageDto message) throws ServiceException;

    void deleteMessage(UUID userId) throws ServiceException;

}
