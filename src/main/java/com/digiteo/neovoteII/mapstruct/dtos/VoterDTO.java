package com.digiteo.neovoteII.mapstruct.dtos;

import lombok.*;

import javax.validation.constraints.*;

@Data // (@Getter, @Setter, @ToString, @EqualsAndHashCode y @RequiredArgsConstructor)
@NoArgsConstructor
@AllArgsConstructor
public class VoterDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotBlank(message = "El campo apellido es obligatorio")
    private String lastname;

    @NotBlank
    @Size(min = 8, message = "La contraseña debe tener como mínimo 8 caracteres")
    private String key;

    @Size(min = 8, message = "Ingrese un número válido (@Size)")
    @Pattern(
            regexp = "^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$",
            message = "Ingrese un número válido (@Pattern)"
    )
    private String phone;

    @Email(message = "Ingrese un correo válido (@Email)")
    @Pattern(
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Ingrese un correo válido (@Pattern)"
    )
    private String email;

    //private boolean enabled;

    //Try to add validation provider that implements Bean Validation 2.0 framework i.e. Hibernate Validator
    //Try to add validation annotations @NotNull/@NotEmpty/@NotBlank @Size, @Min, @Max, @Email,
    // @Past and @AssertTrue(check if these 2 should be in entity)

}
