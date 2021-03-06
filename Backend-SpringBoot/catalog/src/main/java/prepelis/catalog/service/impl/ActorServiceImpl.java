package prepelis.catalog.service.impl;

import org.springframework.stereotype.Service;
import prepelis.catalog.dto.ActorDto;
import prepelis.catalog.model.Actor;
import prepelis.catalog.model.Movie;
import prepelis.catalog.repository.ActorRepository;
import prepelis.catalog.repository.MovieRepository;
import prepelis.catalog.service.api.ActorService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private ActorRepository actorRepository;

    @Override
    public String addActor(ActorDto actorDto) {
        if (actorRepository.findByName(actorDto.getName()) == null){
            Actor actor = new Actor();
            mapDtoToEntity(actorDto, actor);
            actorRepository.save(actor);
            return "Actor successfully created";
        } else {
            return "Actor name already register";
        }
    }

    @Override
    public ActorDto getActorById(Long actorId){
        Actor actor = actorRepository.findActorById(actorId);
        return mapEntityToDto(actor);
    }

    @Override
    public ActorDto getActorByName(String name) {
        Actor actor = actorRepository.findByName(name);
        return mapEntityToDto(actor);
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<ActorDto> actorDtos = new ArrayList<>();
        List<Actor> actors = actorRepository.findAll();
        actors.forEach(actor -> {
            ActorDto actorDto = mapEntityToDto(actor);
            actorDtos.add(actorDto);
        });
        return actorDtos;
    }

    @Override
    public String updateActor(Long actorId, ActorDto actorDto) {
        Actor actor = actorRepository.findActorById(actorId);
        if (actorRepository.findByName(actorDto.getName()) == null || actorDto.getName().equals(actor.getName())) {
            mapDtoToEntity(actorDto, actor);
            actorRepository.save(actor);
            return "Actor successfully edited!";
        } else {
            return "Actor name already register";
        }
    }

    @Override
    public String deleteActor(Long actorId) {
        Optional<Actor> actor = actorRepository.findById(actorId);
        if(actor.isPresent()) {
            actorRepository.deleteById(actor.get().getId());
            return "Actor with id: " + actorId + " deleted successfully!";
        } else return "Actor not found in database";
    }

    private String mapDtoToEntity(ActorDto actorDto, Actor actor) {
        actor.setName(actorDto.getName());
        return null;
    }

    private ActorDto mapEntityToDto(Actor actor) {
        ActorDto responseDto = new ActorDto();
        responseDto.setId(actor.getId());
        responseDto.setName(actor.getName());
        responseDto.setMovies(actor.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet()));
        return responseDto;
    }
}
