package tr.com.obss.jifinalproject.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotNull
    private long id;
    @NotBlank
    @Size(max = 255, min = 3, message = "Please enter a valid username")
    @Email
    private String username;
    @NotBlank
    @Size(max = 255, min = 3, message = "Please enter a valid password")
    private String password;

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}

