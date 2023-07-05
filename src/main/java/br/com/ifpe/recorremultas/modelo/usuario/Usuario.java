package br.com.ifpe.recorremultas.modelo.usuario;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.ifpe.recorremultas.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Usuario")
@Where(clause = "habilitado = true")

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends EntidadeAuditavel {
    {/*     @OneToMany(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Peticao> peticoes; 
    */}

    @Column
    private String nomeCompleto;
 
    @Column
    private LocalDate dataNascimento;
 
    @Column
    private String cpf;
 
    @Column
    private String email;
    
    @Column
    private String senha;
}
