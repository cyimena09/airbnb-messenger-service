package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    Page<Message> findMessagesByConversationId(Integer id, Pageable pageable) throws SQLException;

}
