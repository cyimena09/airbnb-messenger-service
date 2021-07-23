package be.cyimena.airbnb.messengerservice.web.models;

import be.cyimena.airbnb.assetsservice.web.models.UserDto;
import lombok.Data;

import java.util.UUID;

@Data
public class ParticipationDto {

    private UUID id;
    private UUID participantId;
    private ConversationDto conversation;
    private UserDto user;

}
