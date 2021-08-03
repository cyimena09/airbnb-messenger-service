package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.domain.Notification;
import be.cyimena.airbnb.messengerservice.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/messenger")
@RequiredArgsConstructor
public class NotificationController {

    private final INotificationService notificationService;

    @GetMapping("/notifications/by/users/{id}")
    public Page<Notification> getNotificationsByUserId(@PathVariable UUID id, Pageable pageable) {
        return this.notificationService.getNotificationsByUserId(id, pageable);
    }

    @PostMapping("/notifications")
    public void updateNotification(@RequestBody Notification notification) {
        this.notificationService.updateNotification(notification);
    }

    @DeleteMapping("/notifications")
    public void deleteNotification(@RequestBody Notification notification) {
    }

}
