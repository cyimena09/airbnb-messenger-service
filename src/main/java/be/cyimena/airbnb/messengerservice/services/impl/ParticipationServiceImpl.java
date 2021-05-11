package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.mappers.IParticipationMapper;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipationServiceImpl implements IParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;
    private IParticipationMapper participationMapper;

    @Override
    public void addParticipation(ParticipationDto participation) {
        this.participationRepository.save(this.participationMapper.mapToParticipation(participation));
    }

}
