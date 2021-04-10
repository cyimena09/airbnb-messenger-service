package be.cyimena.airbnb.messengerservice.models;

import be.cyimena.airbnb.assetsservice.models.Comment;
import be.cyimena.airbnb.assetsservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "participations")
public class Participation {

    // ATTRIBUTES

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id", updatable = false, nullable = false)
    private Integer id;

    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    // METHODS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}
