package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.assetsservice.models.RealEstate;
import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.Participation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    Page<Participation> findParticipationsByUserId(Integer id, Pageable pageable);

}
