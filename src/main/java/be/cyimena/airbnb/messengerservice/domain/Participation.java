package be.cyimena.airbnb.messengerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "participations", uniqueConstraints={@UniqueConstraint(columnNames = {"participantId" , "conversation_id"})})
public class Participation {

    // ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id", updatable = false, nullable = false)
    private Integer id;

    @NotNull
    private Integer participantId;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    @JsonIgnore
    @NotNull
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
