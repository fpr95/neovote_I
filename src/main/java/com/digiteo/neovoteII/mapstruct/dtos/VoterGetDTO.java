package com.digiteo.neovoteII.mapstruct.dtos;

import lombok.*;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode y @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class VoterGetDTO {

    @Setter(value = AccessLevel.NONE)
    public Long id;
    private String name;
    private String lastname;

//	public Long getId() { return id; }
//
//	public void setId(Long id) { this.id = id; }
//
//	public String getV_name() { return v_name; }
//
//	public void setV_name(String v_name) { this.v_name = v_name; }
//
//	public String getV_lastname() { return v_lastname; }
//
//	public void setV_lastname(String v_lastname) { this.v_lastname = v_lastname; }

}
