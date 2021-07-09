package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

}
