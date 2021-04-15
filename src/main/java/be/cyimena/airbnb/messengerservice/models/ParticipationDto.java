package be.cyimena.airbnb.messengerservice.models;

public class ParticipationDto {

    // ATTRIBUTES

    private Integer id;
    private Integer participantId;
    private ConversationDto conversationDto;

    // METHODS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public ConversationDto getConversationDto() {
        return conversationDto;
    }

    public void setConversationDto(ConversationDto conversationDto) {
        this.conversationDto = conversationDto;
    }

}
