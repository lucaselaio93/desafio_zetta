package com.github.lucaselaio93.desafiozetta.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    @NotEmpty(message = "Este campo não pode estar vazio.")
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    @NotEmpty(message = "Este campo não pode estar vazio.")
    private String cpf;

    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
