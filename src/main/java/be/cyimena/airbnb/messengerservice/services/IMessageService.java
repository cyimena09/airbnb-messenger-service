package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMessageService {

    Page<Message> getMessagesByConversationId(Integer id, Pageable pageable);

    Page<Message> getMessagesByParticipations(List<Integer> participantsIds, Pageable pageable);

    Message addMessage( Message message);

    Message updateMessage(Integer conversationId, Message message);

    void deleteMessage(Integer userId);

}
