package be.cyimena.airbnb.messengerservice.web.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ConversationDto {

    private UUID id;
    Set<MessageDto> messagesDto;
    Set<ParticipationDto> participationsDto;

}
