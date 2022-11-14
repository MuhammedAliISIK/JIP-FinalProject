package tr.com.obss.jifinalproject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDTO {
    @NotNull
    private long id;
    @NotBlank
    @Size(max = 255, min = 3)
    private String name;
    @NotBlank
    @Size(max = 255, min = 3)
    private String author;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}