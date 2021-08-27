package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.mappers.IConversationMapper;
import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements IConversationService {

    private static final String CONVERSATION_ERROR = "Unable to retrieve conversation";
    private final ConversationRepository conversationRepository;
    private final IConversationMapper conversationMapper;

    @Override
    public ConversationDto getConversationById(UUID id) {
        try {
            return conversationMapper.mapToConversationDto(conversationRepository.findById(id).orElseThrow(() -> {
                throw new ConversationNotFoundException(id);
            }));
        } catch (ConversationNotFoundException e) {
            throw new ServiceException(e.getMessage());
        } catch (ServiceException e) {
            throw new ServiceException(CONVERSATION_ERROR + " with id : " + id);
        }
    }

    /**
     * Get all conversations of a user.
     *
     * @param id       user id
     * @param pageable pagination
     * @return return a conversation list
     */
    @Override
    public Page<ConversationDto> getConversationsByParticipantId(UUID id, Pageable pageable) {
        // todo manage no value present
        try {
            return this.conversationRepository.findConversationsByParticipantId(id, pageable).map(conversationMapper::mapToConversationDto);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public ConversationDto createConversation(ConversationDto conversationDto) {
        try {
            Conversation conversation = this.conversationMapper.mapToConversation(conversationDto);
            return this.conversationMapper.mapToConversationDto(this.conversationRepository.save(conversation));
        } catch (ServiceException e) {
            throw new ServiceException(CONVERSATION_ERROR);
        }
    }

}
