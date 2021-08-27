package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface IMessageService {

    Page<MessageDto> getMessagesByConversationId(UUID id, Pageable pageable) throws Exception;

    Page<MessageDto> getMessagesByParticipations(Set<ParticipationDto> participations, Pageable pageable) throws Exception;

    Page<MessageDto> getMessagesByParticipantId(UUID id, Pageable pageable) throws Exception;

    MessageDto addPrivateMessage(MessageDto message) throws Exception;

    void updateMessage(UUID conversationId, MessageDto message) throws Exception;

    void deleteMessage(UUID userId) throws Exception;

}
