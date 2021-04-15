package be.cyimena.airbnb.messengerservice.mappers;

import be.cyimena.airbnb.messengerservice.models.Conversation;
import be.cyimena.airbnb.messengerservice.models.ConversationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;


@Mapper(uses = {IMessageMapper.class})
public interface IConversationMapper {

    IConversationMapper INSTANCE = Mappers.getMapper(IConversationMapper.class);

    Conversation mapToConversation(ConversationDto source);
    ConversationDto mapToConversationDto(Conversation source);
    Page<ConversationDto> mapToPageConversationDto(Page<Conversation> source);

}
