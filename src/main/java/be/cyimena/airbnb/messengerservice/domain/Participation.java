package be.cyimena.airbnb.messengerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "participations", uniqueConstraints={@UniqueConstraint(columnNames = {"participantId" , "conversation_id"})})
public class Participation {

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

}
