package be.cyimena.airbnb.messengerservice.exceptions;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(Integer id) {
        super("Aucune conversation avec l'id : " + id);
    }
}
