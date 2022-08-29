package com.digiteo.neovoteII.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity(name = "admin")
@Table(
        name = "admin",
        uniqueConstraints = {
                @UniqueConstraint(name = "admin_field_unique", columnNames = {"a_phone", "a_email"})
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
    private String a_lastname;

    @Column(
            name = "a_key",
            nullable = false
    )
    private String a_key;

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
            name = "a_enabled",
            nullable = false,
            columnDefinition = "TINYINT DEFAULT (1)"
    )
    private boolean a_enabled;

    @Column(
            name = "register_date_time",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime odt;

    public Admin(
            Long id,
            String a_name,
            String a_lastname,
            String a_key,
            String a_phone,
            String a_email) {
        super(id);
        this.a_name = a_name;
        this.a_lastname = a_lastname;
        this.a_key = a_key;
        this.a_phone = a_phone;
        this.a_email = a_email;
        this.odt = OffsetDateTime.now();
    }

    public Admin(
            String a_name,
            String a_lastname,
            String a_key,
            String a_phone,
            String a_email) {
        this.a_name = a_name;
        this.a_lastname = a_lastname;
        this.a_key = a_key;
        this.a_phone = a_phone;
        this.a_email = a_email;
        this.odt = OffsetDateTime.now();
    }

    @Override
    public Long getId() { return super.id; }
    @Override
    public void setId(Long id) { super.setId(id); }
}
