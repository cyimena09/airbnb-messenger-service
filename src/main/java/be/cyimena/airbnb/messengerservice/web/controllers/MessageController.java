package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.web.models.MessageDto;
import be.cyimena.airbnb.messengerservice.services.IMessageService;
import be.cyimena.airbnb.messengerservice.web.models.ParticipationDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class MessageController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping("/messages/by/conversations/{id}")
    public ResponseEntity<Page<MessageDto>> getMessagesByConversationId(@PathVariable UUID id, Pageable pageable) {
        try {
            Page<MessageDto> messages = this.messageService.getMessagesByConversationId(id, pageable);

            if (messages == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(messages, HttpStatus.OK);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method used when you do not know the ID of the conversation but have the ID of the participant (s)
     *
     * @param participantsIds
     * @param pageable
     * @return
     */
    @GetMapping("/messages/by/participations")
    public ResponseEntity<Page<MessageDto>> getMessagesByParticipations(
            @RequestParam(value = "participantsIds") List<UUID> participantsIds, Pageable pageable) {

        try {
            //return new ResponseEntity<>(this.messageService.getMessagesByParticipations(participantsIds, pageable), HttpStatus.OK);
            return null;
        } catch (ServiceException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/messages")
    public void createMessage(@RequestBody MessageDto message) {
        String WEB_SOCKET_URL = "/queue/messages";
        try {
            // save message
            if (message != null && message.getConversation().getParticipations() != null) {
                this.messageService.addPrivateMessage(message);
                // we send the message to all participants except the one who created the message
                for (ParticipationDto p : message.getConversation().getParticipations()) {
                    if (!message.getSenderId().equals(p.getParticipantId())) {
                        this.messagingTemplate.convertAndSendToUser(p.getParticipantId().toString(), WEB_SOCKET_URL, message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
