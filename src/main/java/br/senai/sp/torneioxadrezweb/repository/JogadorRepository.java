package br.senai.sp.torneioxadrezweb.repository;

import br.senai.sp.torneioxadrezweb.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
