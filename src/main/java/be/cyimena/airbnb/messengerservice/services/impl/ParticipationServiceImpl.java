package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.mappers.IParticipationMapper;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationServiceImpl implements IParticipationService {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ParticipationRepository participationRepository;
    private IParticipationMapper participationMapper;

    @Override
    public Page<ParticipationDto> getParticipationsByConversationId(UUID conversationId, Pageable pageable) {
        //return participationMapper.mapToParticipationDto()  ;

        return participationRepository.findByConversationId(conversationId, pageable).map(participationMapper.INSTANCE::mapToParticipationDto);
//        return participationMapper.mapToParticipationDto(participationRepository.findByConversationId(conversationId, pageable))  ;
//        return this.conversationRepository.findConversationsByParticipantId(id, pageable).map(conversationMapper.INSTANCE::mapToConversationDto);
    }

    @Override
    public void addParticipation(ParticipationDto participationDto) {
        UUID conversationId = participationDto.getConversation().getId();
        Participation participation = this.participationMapper.INSTANCE.mapToParticipation(participationDto);
        this.conversationRepository.findById(conversationId).map(conv -> {
            participation.setConversation(conv);
            return participationRepository.save(participation);
        });
    }

}
