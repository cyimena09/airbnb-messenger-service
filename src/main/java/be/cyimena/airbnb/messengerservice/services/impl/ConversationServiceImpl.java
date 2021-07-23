package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.mappers.IConversationMapper;
import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ConversationServiceImpl implements IConversationService {

    private static final String CONVERSATION_ERROR = "Impossible de récupérer la conversation";

    @Autowired
    private ConversationRepository conversationRepository;
    private IConversationMapper conversationMapper;

    @Override
    public ConversationDto getConversationById(UUID id) {
        try {
            return conversationMapper.INSTANCE.mapToConversationDto(conversationRepository.findById(id).orElseThrow(() -> {
                throw new ConversationNotFoundException(id);
            }));
        } catch (ConversationNotFoundException e) {
            throw new ServiceException(e.getMessage());
        } catch (ServiceException e) {
            throw new ServiceException(CONVERSATION_ERROR + " avec l'id " + id);
        }
    }

    /**
     * Récupère toutes les conversations d'un utilisateur
     *
     * @param id       identifiant de l'utilisateur
     * @param pageable mise en page
     * @return retourne une liste de conversation
     */
    @Override
    public Page<ConversationDto> getConversationsByParticipantId(UUID id, Pageable pageable) {
        try {
            return this.conversationRepository.findConversationsByParticipantId(id, pageable).map(conversationMapper.INSTANCE::mapToConversationDto);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

//    @Override
//    public ConversationDto getConversationByParticipantsIds(List<UUID> participantsIds) {
//        UUID conversationId = this.getConversationIdByParticipantsIds(participantsIds);
//        return this.getConversationById(conversationId);
//    }

//    /**
//     * Récupère la conversation dans laquelle ont participé tous les utlisateurs
//     *
//     * @param ids identifiants des utilisateurs
//     * @return
//     */
//    @Override
//    public UUID getConversationIdByParticipantsIds(List<UUID> ids) {
//        try {
//            // todo récupérer l'id de la conversation grace aux participants
//            // todo récupérer la conversation en fonction de cette id
//            return conversationRepository.findConversationIdByParticipantsIds(ids);
//        } catch (SQLException e) {
//            throw new ServiceException(CONVERSATION_ERROR);
//        }
//    }

    @Override
    public ConversationDto createConversation(ConversationDto conversationDto) {
        try {
            Conversation conversation = this.conversationMapper.INSTANCE.mapToConversation(conversationDto);
            return this.conversationMapper.INSTANCE.mapToConversationDto(this.conversationRepository.save(conversation));
        } catch (ServiceException e) {
            throw new ServiceException(CONVERSATION_ERROR);
        }
    }

}
