package be.cyimena.airbnb.messengerservice.exceptions;

import java.util.UUID;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(UUID id) {
        super("Impossible de trouver un message avec l'id : " + id);
    }
}
