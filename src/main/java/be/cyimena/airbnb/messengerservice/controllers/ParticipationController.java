package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class ParticipationController {

    @Autowired
    private ParticipationRepository participationRepository;

    @PostMapping("/participations")
    public Participation addParticipation(@RequestBody Participation participation) {
        return this.participationRepository.save(participation);
    }

}
