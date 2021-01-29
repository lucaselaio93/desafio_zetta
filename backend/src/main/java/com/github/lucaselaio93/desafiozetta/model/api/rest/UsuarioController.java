package com.github.lucaselaio93.desafiozetta.model.api.rest;

import com.github.lucaselaio93.desafiozetta.model.api.rest.dto.UsuarioDTO.UsuarioDTO;
import com.github.lucaselaio93.desafiozetta.model.entity.Cargo;
import com.github.lucaselaio93.desafiozetta.model.entity.Usuario;
import com.github.lucaselaio93.desafiozetta.model.repository.CargoRepository;
import com.github.lucaselaio93.desafiozetta.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Usuario save(@RequestBody @Valid UsuarioDTO dto){

        LocalDate data = LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCargo = dto.getCargo();

        Cargo cargoOptional = cargoRepository.findById(idCargo).orElse(new Cargo());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setDataNascimento(data);
        usuario.setCargo(cargoOptional);
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        usuarioRepository.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void update(@PathVariable Integer id,@RequestBody Usuario novoUsuario){
        usuarioRepository
                .findById(id)
                .map(usuario -> {
                    novoUsuario.setId(usuario.getId());
                    return usuarioRepository.save(novoUsuario);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

}
