package be.cyimena.airbnb.messengerservice.services.impl;

import be.cyimena.airbnb.messengerservice.domain.Notification;
import be.cyimena.airbnb.messengerservice.domain.NotificationType;
import be.cyimena.airbnb.messengerservice.exceptions.NotificationNotFoundException;
import be.cyimena.airbnb.messengerservice.repositories.NotificationRepository;
import be.cyimena.airbnb.messengerservice.repositories.NotificationTypeRepository;
import be.cyimena.airbnb.messengerservice.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTypeRepository typeRepository;

    @Override
    public Page<Notification> getNotificationsByUserId(UUID id, Pageable pageable) {
        return this.notificationRepository.findNotificationsByUserId(id, pageable);
    }

    @Override
    public Notification createNotification(Notification notification) throws ServiceException {
        String typeName = notification.getType().getName();
        NotificationType typeFound = this.typeRepository.findByName(typeName);
        notification.setType(typeFound);
        notification.setIsSeen(false);
        notification.setIsRead(false);
        return this.notificationRepository.save(notification);
    }

    @Override
    public void setIsSeen(UUID userId) {
        this.notificationRepository.setIsSeen(userId);
    }

    @Override
    public void deleteNotification(UUID notificationId) {

    }

}
