package com.digiteo.neovoteII.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity(name = "voter")
@Table(
        name = "voter",
        uniqueConstraints = {
                @UniqueConstraint(name = "voter_field_unique", columnNames = {"v_phone", "v_email"})
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
            name = "v_key",
            nullable = false
    )
    private String v_key;

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
    private OffsetDateTime odt;


    public Voter(
            Long id,
            String v_name,
            String v_lastname,
            String v_key,
            String v_phone,
            String v_email) {
        super(id);
        this.v_name = v_name;
        this.v_lastname = v_lastname;
        this.v_key = v_key;
        this.v_phone = v_phone;
        this.v_email = v_email;
        this.odt = OffsetDateTime.now();
    }

    public Voter(
            String v_name,
            String v_lastname,
            String v_key,
            String v_phone,
            String v_email) {
        this.v_name = v_name;
        this.v_lastname = v_lastname;
        this.v_key = v_key;
        this.v_phone = v_phone;
        this.v_email = v_email;
        this.odt = OffsetDateTime.now();
    }

    @Override
    public Long getId() { return super.id; }
    //  -> 16/06/22 Change @Id setter modifier to PRIVATE
//	-> 05/07/22 Change modifier to PUBLIC as Mapstruct need it that way to achieve the DTO-entity mapping.
    @Override
    public void setId(Long id) { super.setId(id); }

}
