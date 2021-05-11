package be.cyimena.airbnb.messengerservice.mappers;

import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {IMessageMapper.class})
public interface IConversationMapper {

    IConversationMapper INSTANCE = Mappers.getMapper(IConversationMapper.class);

    Conversation mapToConversation(ConversationDto source);
    ConversationDto mapToConversationDto(Conversation source);

}
