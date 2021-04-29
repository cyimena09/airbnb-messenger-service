package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.domain.Participation;
import org.hibernate.service.spi.ServiceException;

public interface IParticipationService {

    Participation createParticipation(Participation participation) throws ServiceException;

}
