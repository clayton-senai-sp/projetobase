package br.senai.sp.torneioxadrezweb.model;

import br.senai.sp.torneioxadrezweb.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private SexoEnum genero;
    private String telefone;
    private String email;

}
