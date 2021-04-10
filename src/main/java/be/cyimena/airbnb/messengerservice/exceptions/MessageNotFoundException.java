package be.cyimena.airbnb.messengerservice.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Integer id) {
        super("Impossible de trouver une conversation avec l'id : " + id);
    }
}
