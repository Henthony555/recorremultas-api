package br.com.ifpe.recorremultas.modelo.multa;

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
@Table(name = "Multa")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Multa extends EntidadeAuditavel  {

   @Column(nullable = false, length = 10)
   private String codigo;

   @Column(nullable = false, length = 100)
   private String infracao;

   @Column (nullable = false, length = 20)
   private Double valorMulta;

   @Column(nullable = false, length = 4)
   private Integer pontosDescontados;

   @Column(nullable = false, length = 20)
   private String grauMulta;

}
