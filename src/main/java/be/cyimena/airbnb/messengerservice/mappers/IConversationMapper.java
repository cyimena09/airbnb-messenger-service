package be.cyimena.airbnb.messengerservice.mappers;

import be.cyimena.airbnb.messengerservice.domain.Conversation;
import be.cyimena.airbnb.messengerservice.web.models.ConversationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {IParticipationMapper.class})
public interface IConversationMapper {

    IConversationMapper INSTANCE = Mappers.getMapper(IConversationMapper.class);

    @Mapping(target = "messages", ignore = true)
    Conversation mapToConversation(ConversationDto source);
    @Mapping(target = "messages", ignore = true)
    ConversationDto mapToConversationDto(Conversation source);

}
