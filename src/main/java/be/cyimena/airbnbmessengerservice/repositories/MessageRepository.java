package be.cyimena.airbnbmessengerservice.repositories;

import be.cyimena.airbnbmessengerservice.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
