package com.digiteo.neovoteII.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity(name = "election")
@Table(
        name = "elections",
        uniqueConstraints = {
                @UniqueConstraint(name = "election_field_unique", columnNames = {"title"})
}
)
public class Election extends BaseEntity {

    @Column(
            name = "title",
            nullable = false
    )
    private String e_title;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private Admin creatorAdmin;

    // persisting of this field is managed by AttributeConverter
    private ElectionStatus electionStatus;

    //ORDER:no, UNIQUENESS:yep, UPDATES:few, SIZE:10 elements max, OWNING:no, ACCESS:field(@Id on model.BaseEntity.id)
    @ToString.Exclude
    @OneToMany(
            mappedBy = "election",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Proposal> proposals = new ArrayList<Proposal>();

    @Column(
            name = "creationTimestamp",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime creationTimestamp;

    @Column( name = "initTimestamp" )
    private LocalDateTime initTimestamp;

    @Column( name = "finishTimestamp" )
    private LocalDateTime finishTimestamp;

    public Election (
            String e_title,
            String description,
            Admin admin,
            LocalDateTime initTimestamp,
            LocalDateTime finishTimestamp
    ){
        this.e_title = e_title;
        this.description = description;
        this.creatorAdmin = admin;
        this.electionStatus = ElectionStatus.NOT_INIT;
        this.creationTimestamp = OffsetDateTime.now();
        this.initTimestamp = initTimestamp;
        this.finishTimestamp = finishTimestamp;
    }

    @Override
    public Long getId() { return super.id; }

    @Override
    public void setId(Long id) { super.setId(id); }

    public void addProposal(Proposal p){
        proposals.add(p);
        p.setElection(this);
    }

    public void removeProposal(Proposal p){
        proposals.remove(p);
        p.setElection(null);
    }
}
