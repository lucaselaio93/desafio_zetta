package com.github.lucaselaio93.desafiozetta.model.api.rest;

import com.github.lucaselaio93.desafiozetta.model.entity.Cargo;
import com.github.lucaselaio93.desafiozetta.model.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoController {

    private final CargoRepository cargoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cargo save(@RequestBody @Valid Cargo cargo){
        return cargoRepository.save(cargo);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        cargoRepository.findById(id)
                .map( cargo -> {
                    cargoRepository.delete(cargo);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{id}")
    public Cargo searchById(@PathVariable  Integer id){
        return cargoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Cargo> list(){
        return cargoRepository.findAll();
    }
}
