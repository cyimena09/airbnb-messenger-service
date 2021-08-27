package be.cyimena.airbnb.messengerservice.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Timestamp createAt;

    @ManyToOne(cascade = CascadeType.ALL)
    private NotificationType type; // Message, Notification
    private String text;
    private Boolean isSeen;
    private Boolean isRead;
    private UUID messageId;
    private UUID userId;

}
