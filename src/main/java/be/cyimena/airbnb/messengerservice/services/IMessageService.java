package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.MessageDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMessageService {

    Page<MessageDto> getMessagesByConversationId(Integer id, Pageable pageable) throws ServiceException;

    Page<MessageDto> getMessagesByParticipations(List<Integer> participantsIds, Pageable pageable) throws ServiceException;

    MessageDto addMessage(Message message) throws ServiceException;

    MessageDto updateMessage(Integer conversationId, Message message) throws ServiceException;

    void deleteMessage(Integer userId) throws ServiceException;

}
