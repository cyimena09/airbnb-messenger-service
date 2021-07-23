package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.services.IParticipationService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messenger")
@RequiredArgsConstructor
public class ParticipationController {

    private final IParticipationService participationService;

    @PostMapping("/participations")
    public ResponseEntity<?> addParticipation(@RequestBody ParticipationDto participation) {
        try {
            //return new ResponseEntity<>(this.participationService.addParticipation(participation), HttpStatus.OK);
            return null;
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
