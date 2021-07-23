package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.domain.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    @Query("SELECT c FROM Conversation c INNER JOIN Participation p ON p.conversation.id = c.id WHERE p.participantId = :id")
    Page<Conversation> findConversationsByParticipantId(UUID id, Pageable pageable) throws SQLException;

    @Query("SELECT c FROM Conversation c INNER JOIN Participation p ON p.conversation.id = c.id WHERE c.id = :id")
    Conversation findByConversationId(UUID id);

}
