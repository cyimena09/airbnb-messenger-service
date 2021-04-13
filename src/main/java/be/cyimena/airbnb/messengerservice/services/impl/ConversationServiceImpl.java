package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.services.IConversationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl implements IConversationService {

    private final ConversationRepository conversationRepository;

    // CONSTRUCTORS

    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    // METHODS

    @Override
    public Conversation getConversationById(Integer id) {

        try {
            return this.conversationRepository.findById(id).orElseThrow(() -> new ConversationNotFoundException(null));
        } catch (ConversationNotFoundException e) {
            return null;
        }
    }

    @Override
    public Page<Conversation> getConversationsByParticipantId(Integer id, Pageable pageable) {
        return this.conversationRepository.getConversationsByParticipantId(id, pageable);
    }

    @Override
    public Conversation createConversation(Conversation conversation) {
        return this.conversationRepository.save(conversation);
    }

}
