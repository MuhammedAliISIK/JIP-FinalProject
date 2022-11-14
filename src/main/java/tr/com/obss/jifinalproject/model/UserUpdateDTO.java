package tr.com.obss.jifinalproject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateDTO {
    @NotBlank
    @Size(max = 255, min = 3, message = "Please enter a valid username")
    private String username;
    @NotBlank
    @Size(max = 255, min = 3, message = "Please enter a valid password")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
