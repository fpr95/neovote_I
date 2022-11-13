package com.digiteo.neovoteII.model;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Entity(name = "proposal")
@Table(
        name = "proposals",
        uniqueConstraints = {
                @UniqueConstraint(name = "proposal_field_unique", columnNames = {"p_name"})
        }
)
public class Proposal extends BaseEntity {

    @Column(
            name = "p_name",
            nullable = false
    )
    private String p_name;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "election_id")
    private Election election;

    //ORDER:chronological, UNIQUENESS:yep, UPDATES:for adding, SIZE:undefined, OWNING:no, ACCESS:field(@Id on BaseEntity.id)
    @ToString.Exclude
    @OneToMany(
            mappedBy = "targetProposal",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Vote> votes;

    @Column(
            name = "creationTimestamp",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Setter(AccessLevel.PRIVATE)
    private OffsetDateTime creationTimestamp;

    public Proposal(
            String p_name,
            String description,
            Election election){
        this.p_name = p_name;
        this.description = description;
        this.election = election;
        this.creationTimestamp = OffsetDateTime.now();
    }

    @Override
    public Long getId() { return super.id; }

    @Override
    public void setId(Long id) { super.setId(id); }

    public void addVote(Vote v){
        votes.add(v);
        v.setTargetProposal(this);
    }
}
