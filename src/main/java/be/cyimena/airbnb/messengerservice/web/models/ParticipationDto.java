package be.cyimena.airbnb.messengerservice.web.models;

import lombok.Data;

import java.util.UUID;

@Data
public class ParticipationDto {

    private UUID id;
    private UUID participantId;
    private ConversationDto conversationDto;

}
