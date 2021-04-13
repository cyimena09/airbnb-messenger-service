package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Page<Message> findByConversationId(Integer id, Pageable pageable);

}
