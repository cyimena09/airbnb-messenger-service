package be.cyimena.airbnb.messengerservice.services;

import be.cyimena.airbnb.messengerservice.domain.Notification;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface INotificationService {

    public Page<Notification> getNotificationsByUserId(UUID id, Pageable pageable) throws ServiceException;

    Notification createNotification(Notification notification) throws ServiceException;

    public void updateNotification(Notification notification) throws ServiceException;

    public void deleteNotification(UUID notificationId) throws ServiceException;

}
