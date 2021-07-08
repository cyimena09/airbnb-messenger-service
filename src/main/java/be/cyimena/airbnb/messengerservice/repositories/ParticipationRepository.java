package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

}
