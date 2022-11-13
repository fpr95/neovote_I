package com.digiteo.neovoteII.mapstruct.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data // (@Getter, @Setter, @ToString, @EqualsAndHashCode y @RequiredArgsConstructor)
@NoArgsConstructor
@AllArgsConstructor
public class AdminPatchDTO {

    @Size(min = 2, message = "Su nombre de usuario debe contener como mínimo 2 caracteres")
    private String userName;

    @Size(min = 8, message = "La contraseña debe tener como mínimo 8 caracteres y como máximo 20")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
            message = "La contraseña debe contener al menos una minúscula, una mayúscula, un número y un símbolo"
    )
    private String key;

    @Size(min = 8, message = "Ingrese un número válido (Mínimo 8 dígitos)")
    @Pattern(
            regexp = "^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$",
            message = "Ingrese un número válido (Formato incompatible)"
    )
    private String phone;

    @Email(message = "Ingrese un correo válido (@Email)")
    @Pattern(
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Ingrese un correo válido (regex)"
    )
    private String email;
}
