package be.cyimena.airbnb.messengerservice.exceptions;

import java.util.UUID;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(UUID id) {
        super("Could not find a message with id : " + id);
    }
}
