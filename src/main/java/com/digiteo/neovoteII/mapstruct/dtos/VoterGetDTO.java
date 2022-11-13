package com.digiteo.neovoteII.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode y @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoterGetDTO {

    @Setter(value = AccessLevel.NONE)
    public Long id;
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    public String name;
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    public String lastname;

//	public Long getId() { return id; }
//
//	public void setId(Long id) { this.id = id; }
//
//	public String getName() { return name; }
//
//	public void setName(String name) { this.name = name; }
//
//	public String getLastname() { return lastname; }
//
//	public void setLastname(String lastname) { this.lastname = lastname; }

}
