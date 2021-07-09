package be.cyimena.airbnb.messengerservice.web.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class MessageDto {

    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private String text;
    private Timestamp createAt;
    private Timestamp updateAt;
    private boolean swDisplay;
    private ConversationDto conversation;

}
