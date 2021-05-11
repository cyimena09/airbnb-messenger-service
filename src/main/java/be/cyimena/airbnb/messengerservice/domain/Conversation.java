package be.cyimena.airbnb.messengerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "conversations")
public class Conversation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id", updatable = false, nullable = false)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation")
    Set<Message> messages;

    @OneToMany(mappedBy = "conversation")
    Set<Participation> participations;

}
