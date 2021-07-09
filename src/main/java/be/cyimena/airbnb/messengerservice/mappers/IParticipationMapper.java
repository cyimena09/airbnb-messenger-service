package be.cyimena.airbnb.messengerservice.mappers;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IParticipationMapper {
    IParticipationMapper INSTANCE = Mappers.getMapper(IParticipationMapper.class);

    @Mapping(target = "conversation", ignore = true)
    Participation mapToParticipation(ParticipationDto source);
    @Mapping(target = "conversation", ignore = true)
    ParticipationDto mapToParticipationDto(Participation source);
}
