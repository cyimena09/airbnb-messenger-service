package be.cyimena.airbnb.messengerservice.exceptions;

import java.util.UUID;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(UUID id) {
        super("Aucune conversation avec l'id : " + id);
    }
}
