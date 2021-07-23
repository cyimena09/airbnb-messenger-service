package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.mappers.IParticipationMapper;
import be.cyimena.airbnb.messengerservice.repositories.ConversationRepository;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements IParticipationService {

    private final ConversationRepository conversationRepository;
    private final ParticipationRepository participationRepository;
    private final IParticipationMapper participationMapper;

    @Override
    public Page<ParticipationDto> getParticipationsByConversationId(UUID conversationId, Pageable pageable) {
        return participationRepository.findByConversationId(conversationId, pageable).map(participationMapper::mapToParticipationDto);
    }

    @Override
    public void addParticipation(ParticipationDto participationDto) {
        UUID conversationId = participationDto.getConversation().getId();
        Participation participation = this.participationMapper.mapToParticipation(participationDto);
        this.conversationRepository.findById(conversationId).map(conv -> {
            participation.setConversation(conv);
            return participationRepository.save(participation);
        });
    }

}
