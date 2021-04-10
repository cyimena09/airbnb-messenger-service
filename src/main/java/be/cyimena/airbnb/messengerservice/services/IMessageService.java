package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.models.Message;

import java.util.List;

public interface IMessageService {

    List<Message> getMessages();

    Message getMessageById(Integer messageId);

    Message createMessage(Message message);

    Message updateMessage(Integer conversationId, Message message);

    void deleteMessage(Integer userId);

}
