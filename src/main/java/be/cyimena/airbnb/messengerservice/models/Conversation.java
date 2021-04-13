package be.cyimena.airbnb.messengerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "conversations")
public class Conversation implements Serializable {

    // ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id", updatable = false, nullable = false)
    private Integer id;

    @JsonIgnore
    @OneToMany(mappedBy = "conversation")
    Set<Message> messages;

    @OneToMany(mappedBy = "conversation")
    Set<Participation> participations;

    // METHODS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

}
