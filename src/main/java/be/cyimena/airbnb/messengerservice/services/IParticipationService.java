package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.hibernate.service.spi.ServiceException;

public interface IParticipationService {

    void addParticipation(ParticipationDto participation) throws ServiceException;

}
