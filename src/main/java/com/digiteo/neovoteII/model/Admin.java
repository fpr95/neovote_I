package com.digiteo.neovoteII.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity(name = "admin")
@Table(
        name = "admins",
        uniqueConstraints = {
                @UniqueConstraint(name = "admin_field_unique", columnNames = {"a_userName", "a_rut", "a_phone", "a_email"})
        }
)
public class Admin extends BaseEntity {

    @Column(
            name = "a_name",
            nullable = false
    )
    private String a_name;

    @Column(
            name = "a_lastname",
            nullable = false
    )
    private String a_lastName;

    @Column(
            name = "a_userName",
            nullable = false
    )
    private String a_userName;

    @Column(
            name = "a_key",
            nullable = false
    )
    private String a_key;

    // persisting of this field is managed by AttributeConverter
    private GenderValues gender;

    @Column(
            name = "a_rut",
            nullable = false
    )
    private String a_rut;

    @Column(
            name = "a_phone",
            nullable = false
    )
    private String a_phone;

    @Column(
            name = "a_email",
            nullable = false
    )
    private String a_email;

    @Column(
            name = "enabled",
            nullable = false,
            columnDefinition = "TINYINT DEFAULT (0)"
    )
    private boolean a_enabled = false; //admin will not be enabled for vote until configure cases 1 and 2 from the neovoteUseCase.txt

    @Column(
            name = "register_date_time",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime timestamp;

    // ORDER:chronological only, UNIQUENESS:yep, UPDATES:few, SIZE:3 elements max, OWNING:no, ACCESS:field/property(?)
    @OneToMany(mappedBy = "creatorAdmin", fetch = FetchType.LAZY)
    private List<Election> elections = new ArrayList<Election>();

    public Admin(
            //Long id,
            String a_name,
            String a_lastname,
            String a_userName,
            String a_key,
            String a_rut,
            String a_phone,
            String a_email) {
        //super(id);
        this.a_name = a_name;
        this.a_lastName = a_lastname;
        this.a_userName = a_userName;
        this.a_key = a_key;
        this.a_rut = a_rut;
        this.a_phone = a_phone;
        this.a_email = a_email;
        this.timestamp = OffsetDateTime.now();
    }

    public Admin(
            String a_name,
            String a_lastName,
            String a_userName,
            String a_key,
            GenderValues genderConstant,
            String a_rut,
            String a_phone,
            String a_email) {
        this.a_name = a_name;
        this.a_lastName = a_lastName;
        this.a_userName = a_userName;
        this.a_key = a_key;
        this.gender = genderConstant;
        this.a_rut = a_rut;
        this.a_phone = a_phone;
        this.a_email = a_email;
        this.timestamp = OffsetDateTime.now();
    }

    @Override
    public Long getId() { return super.id; }

    @Override
    public void setId(Long id) { super.setId(id); }

    public void addElection(Election e){
        elections.add(e);
        e.setCreatorAdmin(this);
    }

    public void removeElection(Election e){
        elections.remove(e);
        e.setCreatorAdmin(null);
    }
}
