package com.digiteo.neovoteII.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.*;

@Data // (@Getter, @Setter, @ToString, @EqualsAndHashCode y @RequiredArgsConstructor)
@NoArgsConstructor
@AllArgsConstructor
// The jackson annotation below is for serializing only so it's not useful for the PATCH's issue
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoterDTO {

    @NotBlank(message = "El campo Nombre es obligatorio")
    @Size(min = 2, message = "Ingrese su nombre")
    private String name;

    @NotBlank(message = "El campo Apellido es obligatorio")
    @Size(min = 2, message = "Ingrese su apellido")
    private String lastname;

    @NotEmpty
    @Size(min = 8, message = "La contraseña debe tener como mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$",
            message = "La contraseña debe contener al menos una minúscula, una mayúscula, un número y un símbolo"
    )
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


    // check if @Past and @AssertTrue should be in entity

}
