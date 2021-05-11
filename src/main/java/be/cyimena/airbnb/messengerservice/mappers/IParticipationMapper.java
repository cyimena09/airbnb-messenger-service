package be.cyimena.airbnb.messengerservice.mappers;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IParticipationMapper {
    IParticipationMapper INSTANCE = Mappers.getMapper(IParticipationMapper.class);

    Participation mapToParticipation(ParticipationDto source);
    ParticipationDto mapToParticipationDto(Participation source);
}
