package be.cyimena.airbnb.messengerservice.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", updatable = false, nullable = false)
    private UUID id;

    private String text;

    private UUID senderId;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    private boolean swDisplay;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

}
