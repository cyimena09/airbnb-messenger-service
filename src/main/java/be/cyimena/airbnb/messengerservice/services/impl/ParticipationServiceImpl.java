package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import org.springframework.stereotype.Service;

@Service
public class ParticipationServiceImpl implements IParticipationService {

    private final ParticipationRepository participationRepository;

    // CONSTRUCTORS

    public ParticipationServiceImpl(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @Override
    public Participation createParticipation(Participation participation) {
        return this.participationRepository.save(participation);
    }

}
