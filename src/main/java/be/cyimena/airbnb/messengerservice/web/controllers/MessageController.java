package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.domain.Notification;
import be.cyimena.airbnb.messengerservice.domain.NotificationType;
import be.cyimena.airbnb.messengerservice.enumerations.TypeEnum;
import be.cyimena.airbnb.messengerservice.services.INotificationService;
import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/messenger")
@RequiredArgsConstructor
public class MessageController {

    private final IMessageService messageService;
    private final INotificationService notificationService;
    private final SimpMessageSendingOperations messagingTemplate;

    @GetMapping("/messages/by/conversations/{id}")
    public ResponseEntity<Page<MessageDto>> getMessagesByConversationId(@PathVariable UUID id, Pageable pageable) {
        try {
            Page<MessageDto> messages = this.messageService.getMessagesByConversationId(id, pageable);
            if (messages == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(messages, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages/by/participations/{id}")
    public Page<MessageDto> getMessagesByParticipantId(@PathVariable UUID id, Pageable pageable) throws Exception {
        return this.messageService.getMessagesByParticipantId(id, pageable);
    }

    /**
     * Method used when you do not know the ID of the conversation but have the ID of the participant (s)
     *
     * @param participations list of participants
     * @param pageable pageable
     * @return page of messageDto
     */
    @PostMapping("/messages/by/participations")
    public ResponseEntity<Page<MessageDto>> getMessagesByParticipations(@RequestBody Set<ParticipationDto> participations, Pageable pageable) throws Exception {
        try {
            return new ResponseEntity<>(this.messageService.getMessagesByParticipations(participations, pageable), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/messages")
    public MessageDto createMessage(@RequestBody MessageDto message) {
        String WEB_SOCKET_URL = "/queue/messages";
        MessageDto messageDto;
        try {
            if (message != null && message.getConversation() != null) {
                if (message.getConversation().getId() != null || message.getConversation().getParticipations() != null) {
                    messageDto = this.messageService.addPrivateMessage(message);
                    // we send the message and notification to all participants except the one who created the message
                    for (ParticipationDto p : messageDto.getConversation().getParticipations()) {
                        if (!messageDto.getSenderId().equals(p.getParticipantId())) {
                            // send message with websocket
                            this.messagingTemplate.convertAndSendToUser(p.getParticipantId().toString(), WEB_SOCKET_URL, messageDto);
                            // save and send notification with websocket
                            Notification notification = new Notification();
                            NotificationType type = new NotificationType();
                            type.setName(TypeEnum.MESSAGE.toString());
                            notification.setType(type);
                            notification.setMessageId(messageDto.getId());
                            notification.setUserId(p.getParticipantId());
                            this.notificationService.createNotification(notification);
                        }
                    }
                    return messageDto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
