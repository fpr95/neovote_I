package com.digiteo.neovoteII.model;

import java.io.Serializable;

//import javax.persistence.Access;
//import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
/*
About the relation between the type access by FIELD and the getters/setters for the @Id:
https://stackoverflow.com/questions/12369641/is-there-any-reason-to-not-generate-setters-and-getters-for-id-fields-in-jpa
 */
//@Access(AccessType.PROPERTY)
public abstract class BaseEntity implements Serializable {
    /**
     * check implementation of serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    public static final int INIT_SEQ = 100;
    @Id
    @SequenceGenerator(
            name = "seq_global",
            sequenceName = "seq_global",
            allocationSize = 1,
            initialValue = INIT_SEQ
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_global"
    )
    @Column(
            updatable = false,
            nullable = false,
            columnDefinition = "INT"
    )
    @Getter
    @Setter
    // In first instance this mutator was set as PRIVATE, but Mapstruct needs PUBLIC accessor to map (DTO -> Entity)
    @EqualsAndHashCode.Include
    protected Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

}
