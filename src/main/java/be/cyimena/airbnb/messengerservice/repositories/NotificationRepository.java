package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    Page<Notification> findNotificationsByUserId(UUID id, Pageable pageable);

}
