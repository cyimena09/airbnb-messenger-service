package be.cyimena.airbnb.messengerservice.web.models;

import java.sql.Timestamp;

public class MessageDto {

    // ATTRIBUTES

    private Integer id;
    private String text;
    private Integer senderId;
    private Timestamp createAt;
    private Timestamp updateAt;
    private boolean swDisplay;
    private ConversationDto conversationDto;

    // METHODS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isSwDisplay() {
        return swDisplay;
    }

    public void setSwDisplay(boolean swDisplay) {
        this.swDisplay = swDisplay;
    }

    public ConversationDto getConversationDto() {
        return conversationDto;
    }

    public void setConversationDto(ConversationDto conversationDto) {
        this.conversationDto = conversationDto;
    }

}
