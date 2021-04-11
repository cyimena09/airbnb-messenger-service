package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMessageService {

    List<Message> getMessages();

    Page<Message> getMessageById(Integer messageId, Pageable pageable);

    Message addMessage(Message message, Integer conversationId);

    Message updateMessage(Integer conversationId, Message message);

    void deleteMessage(Integer userId);

}
