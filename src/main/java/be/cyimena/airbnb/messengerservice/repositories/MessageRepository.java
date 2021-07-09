package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    Page<Message> findMessagesByConversationId(UUID id, Pageable pageable) throws SQLException;

}
