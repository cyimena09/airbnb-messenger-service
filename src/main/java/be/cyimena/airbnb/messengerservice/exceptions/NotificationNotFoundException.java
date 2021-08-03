package be.cyimena.airbnb.messengerservice.exceptions;

import java.util.UUID;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(UUID id) {
        super("Could not find a notification with id : " + id);
    }
}
