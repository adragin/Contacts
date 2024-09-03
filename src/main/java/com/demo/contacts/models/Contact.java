package com.demo.contacts.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "contact")
    private List<Phone> phones;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
}
