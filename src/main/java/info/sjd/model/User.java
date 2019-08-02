package info.sjd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Integer userId;

    @NotNull(message = "name must be set")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "incorrectly name")
    private String name;

    @NotNull(message = "email must be set")
    @Email(message = "email does not exist")
    private String email;

    @NotNull(message = "password must be set")
    private String password;

    public User(String name, String email, String password) {
        this.userId = null;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
