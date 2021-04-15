package be.cyimena.airbnb.messengerservice.mappers;

import java.util.List;

import be.cyimena.airbnb.messengerservice.models.Message;
import be.cyimena.airbnb.messengerservice.models.MessageDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(uses = {IConversationMapper.class})
public interface IMessageMapper {

    IMessageMapper INSTANCE = Mappers.getMapper(IMessageMapper.class);

    Message mapToMessage(MessageDto source);
    MessageDto mapToMessageDto(Message source);

    @InheritConfiguration
    Page<MessageDto> mapToPageMessageDto(Page<Message> source);

    @AfterMapping
    default void changeMessageTextIfDeleted(@MappingTarget MessageDto target) {
        if (!target.isSwDisplay()) {
            target.setText("Ce message a été supprimé");
        }
    }

}

