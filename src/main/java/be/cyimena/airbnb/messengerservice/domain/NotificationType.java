package be.cyimena.airbnb.messengerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "types")
public class NotificationType {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "type_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String name;

}
