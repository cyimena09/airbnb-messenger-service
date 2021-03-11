package be.cyimena.airbnbmessengerservice.exceptions;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(Integer id) {
        super("Impossible de trouver une conversation avec l'id : " + id);
    }
}