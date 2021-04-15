package be.cyimena.airbnb.messengerservice.models;

import java.util.Set;

public class ConversationDto {

    // ATTRIBUTES

    private Integer id;
    Set<MessageDto> messagesDto;
    Set<ParticipationDto> participationsDto;

    // METHODS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<MessageDto> getMessagesDto() {
        return messagesDto;
    }

    public void setMessagesDto(Set<MessageDto> messagesDto) {
        this.messagesDto = messagesDto;
    }

    public Set<ParticipationDto> getParticipationsDto() {
        return participationsDto;
    }

    public void setParticipationsDto(Set<ParticipationDto> participationsDto) {
        this.participationsDto = participationsDto;
    }

}
