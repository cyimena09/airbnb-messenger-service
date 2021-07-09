package be.cyimena.airbnb.messengerservice.mappers;

import be.cyimena.airbnb.messengerservice.domain.Message;
import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMessageMapper {

    IMessageMapper INSTANCE = Mappers.getMapper(IMessageMapper.class);

    @Mapping(target = "conversation", ignore = true)
    Message mapToMessage(MessageDto source);
    @Mapping(target = "conversation", ignore = true)
    MessageDto mapToMessageDto(Message source);

}
