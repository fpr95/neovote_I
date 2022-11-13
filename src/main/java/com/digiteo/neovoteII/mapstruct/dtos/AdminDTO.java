package com.digiteo.neovoteII.mapstruct.dtos;

import com.digiteo.neovoteII.model.GenderValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode & @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    @NotBlank(message = "El campo Nombre es obligatorio")
    @Size(min = 2, message = "Ingrese su nombre")
    private String name;

    @NotBlank(message = "El campo Apellido es obligatorio")
    @Size(min = 2, message = "Ingrese su apellido")
    private String lastname;

    @NotBlank(message = "El campo Nombre de Usuario es obligatorio, ¡Es el que usará para iniciar sesión!")
    @Size(min = 2, message = "Ingrese un nombre de usuario de al menos 2 caracteres, por favor")
    private String userName;

    @NotEmpty
    @Size(min = 8, message = "La contraseña debe tener como mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&/-_+=*()])(?=\\S+$).{8,20}$",
            message = "La contraseña debe contener al menos una minúscula, una mayúscula, un número y un símbolo"
    )
    private String key;

    private GenderValues genderCode;

    @NotBlank(message = "Ingrese su RUT por favor")
    @Pattern(
            regexp = "^(\\d{1,2}(?:[\\.]?\\d{3}){2}-?[\\dkK])$",
            message = "Ingrese un RUT válido por favor"
    )
    private String rut;

    @Size(min = 8, message = "Ingrese un número válido (@Size)")
    @Pattern(
            regexp = "^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$",
            message = "Ingrese un número válido (@Pattern)"
    )
    private String phone;

    @NotBlank(message = "Ingrese su correo por favor")
    @Email(message = "Ingrese un correo válido (@Email)")
    @Pattern(
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Ingrese un correo válido (@Pattern)"
    )
    private String email;
}
