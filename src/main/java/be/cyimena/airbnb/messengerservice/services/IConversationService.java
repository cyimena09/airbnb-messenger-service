package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IConversationService {

    ConversationDto getConversationById(UUID id) throws ServiceException;

    Page<ConversationDto> getConversationsByParticipantId(UUID id, Pageable pageable) throws ServiceException;

    UUID getConversationIdByParticipantsIds(List<UUID> ids);

    ConversationDto getConversationByParticipantsIds(List<UUID> participantsIds);

    ConversationDto createConversation(Conversation conversation) throws ServiceException;

}
