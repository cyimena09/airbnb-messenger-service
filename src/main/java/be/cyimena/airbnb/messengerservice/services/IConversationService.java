package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IConversationService {

    ConversationDto getConversationById(UUID id) throws ServiceException;

    Page<ConversationDto> getConversationsByParticipantId(UUID id, Pageable pageable) throws ServiceException;

    ConversationDto createConversation(ConversationDto conversation) throws ServiceException;

}
