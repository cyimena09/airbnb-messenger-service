package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.mappers.IConversationMapper;
import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ConversationServiceImpl implements IConversationService {

    private final ConversationRepository conversationRepository;
    private final IConversationMapper conversationMapper;

    // CONSTRUCTORS

    public ConversationServiceImpl(ConversationRepository conversationRepository, IConversationMapper conversationMapper) {
        this.conversationRepository = conversationRepository;
        this.conversationMapper = conversationMapper;
    }

    // METHODS

    @Override
    public ConversationDto getConversationById(Integer id) {
        try {
            Conversation c = this.conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(id));
            return this.conversationMapper.mapToConversationDto(c);
        } catch (ConversationNotFoundException e) {
            throw new ServiceException(e.getMessage());
        } catch (ServiceException e) {
            throw new ServiceException("Impossible de récupérer la conversation avec l'id" + id);
        }
    }

    @Override
    public Page<ConversationDto> getConversationsByParticipantId(Integer id, Pageable pageable) {
        try {
            return this.conversationMapper.mapToPageConversationDto(this.conversationRepository.findConversationsByParticipantId(id, pageable));
        } catch (SQLException e) {
            throw new ServiceException("Impossible de récupérer la conversation");
        }
    }

    @Override
    public ConversationDto createConversation(Conversation conversation) {
        try {
            return this.conversationMapper.mapToConversationDto(this.conversationRepository.save(conversation));
        } catch (ServiceException e) {
            throw new ServiceException("Impossible de créer la conversation");
        }
    }

}
