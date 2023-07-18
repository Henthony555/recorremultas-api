package br.com.ifpe.recorremultas.api.multa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ifpe.recorremultas.modelo.multa.Multa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultaRequest {

    @NotNull(message = "O código é de preenchimento obrigatório")
    @NotBlank(message = "O código é de preenchimento obrigatório")
    private String codigo;
 
    @NotNull(message = "A infração é de preenchimento obrigatório")
    @NotBlank(message = "A infração é de preenchimento obrigatório")
    private String infracao;
 
    @NotNull(message = "O valor da multa é de preenchimento obrigatório")
    private Double valorMulta;
 
    @NotNull(message = "Os pontos descontados são de preenchimento obrigatório")
    private Integer pontosDescontados;
 
    @NotNull(message = "O grau da multa é de preenchimento obrigatório")
    @NotBlank(message = "O grau da multa é de preenchimento obrigatório")
    private String grauMulta;
 

   public Multa build() {

       return Multa.builder()
               .codigo(codigo)
               .infracao(infracao)
               .valorMulta(valorMulta)
               .pontosDescontados(pontosDescontados)
               .grauMulta(grauMulta)
               .build();
   }
}
