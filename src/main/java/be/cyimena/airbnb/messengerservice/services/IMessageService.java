package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.domain.Message;
import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    Page<MessageDto> getMessagesByConversationId(UUID id, Pageable pageable) throws ServiceException;

    Page<MessageDto> getMessagesByParticipationsIds(List<UUID> participantsIds, Pageable pageable) throws ServiceException;

    void addPrivateMessage(MessageDto message) throws ServiceException;


    void updateMessage(UUID conversationId, MessageDto message) throws ServiceException;

    void deleteMessage(UUID userId) throws ServiceException;

}
