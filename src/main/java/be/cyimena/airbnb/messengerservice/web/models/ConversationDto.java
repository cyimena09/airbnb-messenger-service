package be.cyimena.airbnb.messengerservice.web.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ConversationDto {

    private UUID id;
    Set<MessageDto> messages;
    Set<ParticipationDto> participations;

}
