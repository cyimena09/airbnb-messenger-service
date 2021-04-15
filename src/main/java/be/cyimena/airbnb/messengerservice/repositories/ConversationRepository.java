package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.exceptions.ConversationNotFoundException;
import be.cyimena.airbnb.messengerservice.models.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.SQLException;
import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    @Query("SELECT c FROM Conversation c INNER JOIN Participation p ON p.conversation.id = c.id WHERE p.participantId = :id")
    Page<Conversation> findConversationsByParticipantId(Integer id, Pageable pageable) throws SQLException;

    @Query("SELECT p.conversation.id FROM Participation p " +
            "WHERE p.participantId IN :participantsIds " +
            "GROUP BY p.conversation.id " +
            "HAVING COUNT(p.conversation.id) = 2") // "2" uniquement lorsqu'une conversation est avec une autre personne < 2 lorsque groupe ...
    Integer findConversationIdByParticipations(List<Integer> participantsIds) throws SQLException;

}
