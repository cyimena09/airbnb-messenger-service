package be.cyimena.airbnb.messengerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "participations")
public class Participation {

    // ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id", updatable = false, nullable = false)
    private Integer id;

    private Integer participantId; // id du participant

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    @JsonIgnore
    private Conversation conversation;

    // METHODS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}
