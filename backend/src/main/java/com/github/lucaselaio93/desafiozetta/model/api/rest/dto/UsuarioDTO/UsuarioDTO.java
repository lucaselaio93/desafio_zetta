package com.github.lucaselaio93.desafiozetta.model.api.rest.dto.UsuarioDTO;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private Integer cargo;
}
