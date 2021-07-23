package be.cyimena.airbnb.messengerservice.web.controllers;

import be.cyimena.airbnb.messengerservice.services.IConversationService;
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

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/messenger")
public class MessageController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private IConversationService conversationService;
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
     * @param participations
     * @param pageable
     * @return
     */
    @PostMapping("/messages/by/participations")
    public ResponseEntity<Page<MessageDto>> getMessagesByParticipations(@RequestBody Set<ParticipationDto> participations, Pageable pageable) {
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
            // save message
            if (message != null && message.getConversation() != null) {
                if (message.getConversation().getId() != null || message.getConversation().getParticipations() != null) {
                    messageDto = this.messageService.addPrivateMessage(message);
                    // we send the message to all participants except the one who created the message
                    for (ParticipationDto p : messageDto.getConversation().getParticipations()) {
                        if (!messageDto.getSenderId().equals(p.getParticipantId())) {
                            this.messagingTemplate.convertAndSendToUser(p.getParticipantId().toString(), WEB_SOCKET_URL, messageDto);
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
