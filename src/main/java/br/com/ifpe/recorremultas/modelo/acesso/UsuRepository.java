package br.com.ifpe.recorremultas.modelo.acesso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuRepository extends JpaRepository<Usu, Long> {

    Usu findByUsername(String username);

}
