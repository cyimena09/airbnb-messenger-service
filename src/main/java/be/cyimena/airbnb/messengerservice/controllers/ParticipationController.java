package be.cyimena.airbnb.messengerservice.controllers;

import be.cyimena.airbnb.assetsservice.models.RealEstate;
import be.cyimena.airbnb.messengerservice.exceptions.MessageNotFoundException;
import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.Participation;
import be.cyimena.airbnb.messengerservice.repositories.MessageRepository;
import be.cyimena.airbnb.messengerservice.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class ParticipationController {

    @Autowired
    private ParticipationRepository participationRepository;

    @PostMapping("/participations")
    public Participation createParticipation(@RequestBody Participation participation) {
        return this.participationRepository.save(participation);
    }

    @GetMapping("/participations/{id}")
    public Page<Participation> getParticipationsByUserId(@PathVariable Integer id, Pageable pageable) {
        return participationRepository.findParticipationsByUserId(id, pageable);
    }

//    @GetMapping("/conversation/{id}")
//    public Message updateMessage(@PathVariable Integer id) {
//        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
//    }
//
//    @GetMapping("/conversation/{id}")
//    public void deleteMessage(@PathVariable Integer id) {
//        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
//    }

}
