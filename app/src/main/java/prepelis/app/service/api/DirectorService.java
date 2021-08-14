package prepelis.app.service.api;

import java.util.List;
import prepelis.app.dto.DirectorDto;

public interface DirectorService {

    public DirectorDto addDirector(DirectorDto directorDto);

    public List<DirectorDto> getAllDirectors();

    public DirectorDto updateDirector(Long id, DirectorDto director);

    public String deleteDirector(Long id);

}