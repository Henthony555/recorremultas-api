package br.com.ifpe.recorremultas.modelo.peticao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeticaoRepository extends JpaRepository<Peticao, Long> {

    List<Peticao> findAllById(String id_usuario);

  
}