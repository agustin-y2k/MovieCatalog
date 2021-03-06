package prepelis.catalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ActorDto {

    private Long id;

    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Movies")
    Set<String> movies = new HashSet<>();

}
