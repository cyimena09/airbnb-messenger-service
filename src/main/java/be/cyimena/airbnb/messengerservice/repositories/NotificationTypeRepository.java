package be.cyimena.airbnb.messengerservice.repositories;

import be.cyimena.airbnb.messengerservice.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, UUID> {

    NotificationType findByName(String name);
}

