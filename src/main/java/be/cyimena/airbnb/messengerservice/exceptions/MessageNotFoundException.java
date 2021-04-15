package be.cyimena.airbnb.messengerservice.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Integer id) {
        super("Impossible de trouver un message avec l'id : " + id);
    }
}
