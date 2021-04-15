package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class ParticipationController {

    @Autowired
    private ParticipationRepository participationRepository;

    @PostMapping("/participations")
    public ResponseEntity<Participation> addParticipation(@RequestBody Participation participation) {
        try {
            return new ResponseEntity<>(this.participationRepository.save(participation), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
