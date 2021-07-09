package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.mappers.IParticipationMapper;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationServiceImpl implements IParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;
    private IParticipationMapper participationMapper;

    @Override
    public void addParticipation(ParticipationDto participation) {
        UUID conversationId = participation.getConversation().getId();
        Participation p = this.participationMapper.INSTANCE.mapToParticipation(participation);
        Conversation conversation = new Conversation();
        conversation.setId(conversationId);
        p.setConversation(conversation);
        this.participationRepository.save(p);
    }

}
