package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConversationService {

    ConversationDto getConversationById(Integer id) throws ServiceException;

    Page<ConversationDto> getConversationsByParticipantId(Integer id, Pageable pageable) throws ServiceException;

    Integer getConversationIdByParticipantsIds(List<Integer> ids);

    ConversationDto getConversationByParticipantsIds(List<Integer> participantsIds);

    ConversationDto createConversation(Conversation conversation) throws ServiceException;

}
