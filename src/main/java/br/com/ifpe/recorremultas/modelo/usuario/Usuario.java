package br.com.ifpe.recorremultas.modelo.usuario;

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
    {/* 
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usu usu;
    

    @OneToMany(mappedBy = "usuario", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Peticao> peticoes; 
    */}

    {/* 
    @Column(nullable = false, length = 100)
    private String nomeCompleto;
 
    @Column
    private LocalDate dataNascimento;
 
    @Column(unique = true)
    private String cpf;
    
    */} 
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String senha;

    
}
   