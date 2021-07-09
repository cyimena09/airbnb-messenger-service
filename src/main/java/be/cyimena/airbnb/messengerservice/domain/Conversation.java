package be.cyimena.airbnb.messengerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "conversations")
public class Conversation implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "conversation_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation")
    Set<Message> messages;

    @OneToMany(mappedBy = "conversation")
    Set<Participation> participations;

}
