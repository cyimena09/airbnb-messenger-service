package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.models.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

}
