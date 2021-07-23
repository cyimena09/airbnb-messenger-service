package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IParticipationService {

    Page<ParticipationDto> getParticipationsByConversationId(UUID conversationId, Pageable pageable) throws ServiceException;

    void addParticipation(ParticipationDto participation) throws ServiceException;

}
