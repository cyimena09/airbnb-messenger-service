package be.cyimena.airbnb.messengerservice.domain;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "notifications")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "notification_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    private NotificationType type; // Message, Notification
    private String text;
    private Boolean isSeen;
    private UUID messageId;
    private UUID userId;

}
