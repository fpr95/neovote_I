package com.digiteo.neovoteII.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity(name = "voter")
@Table(
        name = "voters",
        uniqueConstraints = {
                @UniqueConstraint(name = "voter_field_unique", columnNames = {"v_userName", "v_rut","v_phone", "v_email"})
        }
)
public class Voter extends BaseEntity {

    @Column(
            name = "v_name",
            nullable = false
    )
    private String v_name;

    @Column(
            name = "v_lastname",
            nullable = false
    )
    private String v_lastname;

    @Column(
            name = "v_userName",
            nullable = false
    )
    private String v_userName;

    @Column(
            name = "v_key",
            nullable = false
    )
    private String v_key;

    // persisting of this field is managed by AttributeConverter
    private GenderValues gender;

    @Column(
            name = "v_rut",
            nullable = false
    )
    private String v_rut;

    @Column(
            name = "v_phone",
            nullable = false
    )
    private String v_phone;

    @Column(
            name = "v_email",
            nullable = false
    )
    private String v_email;

    @Column(
            name = "enabled",
            nullable = false,
            columnDefinition = "TINYINT DEFAULT (1)"
    )
    private boolean v_enabled = true;

    @Column(
            name = "register_date_time",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Setter(AccessLevel.PRIVATE)
    //search the best type for performance(Date, LocalDateTime, primitive, etc)
    private OffsetDateTime creationTimestamp;

    @OneToMany(mappedBy = "voter", fetch = FetchType.LAZY) // <--- define cascade type
    private List<Vote> votes = new ArrayList<Vote>();


    public Voter(
            String v_name,
            String v_lastname,
            String v_userName,
            String v_key,
            String v_phone,
            String v_email) {
        this.v_name = v_name;
        this.v_lastname = v_lastname;
        this.v_userName = v_userName;
        this.v_key = v_key;
        this.v_phone = v_phone;
        this.v_email = v_email;
        this.creationTimestamp = OffsetDateTime.now();
    }

    public Voter(
            String v_name,
            String v_lastname,
            String v_userName,
            String v_key,
            GenderValues genderConstant,
            String v_rut,
            String v_phone,
            String v_email) {
        this.v_name = v_name;
        this.v_lastname = v_lastname;
        this.v_userName = v_userName;
        this.v_key = v_key;
        this.gender = genderConstant;
        this.v_rut = v_rut;
        this.v_phone = v_phone;
        this.v_email = v_email;
        this.creationTimestamp = OffsetDateTime.now();
    }

    @Override
    public Long getId() { return super.id; }
    //Change modifier to PUBLIC as Mapstruct need it that way to achieve the DTO-entity mapping.
    @Override
    public void setId(Long id) { super.setId(id); }

}
