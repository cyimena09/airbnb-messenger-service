package be.cyimena.airbnbmessengerservice.repositories;

import be.cyimena.airbnbmessengerservice.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

}
