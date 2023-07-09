package br.com.ifpe.recorremultas.api.agencias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.ifpe.recorremultas.modelo.agencias.Agencias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgenciasRequest {

    @NotNull(message = "O nome da agência é de preenchimento obrigatório")
    @NotBlank(message = "O nome da agência é de preenchimento obrigatório")
    private String nomeAgencia;

    @NotNull(message = "A latitude é de preenchimento obrigatório")
    @NotBlank(message = "A latitude é de preenchimento obrigatório")
    private String latitude;

    @NotNull(message = "A longitude é de preenchimento obrigatório")
    @NotBlank(message = "A longitude é de preenchimento obrigatório")
    private String longitude;

   public Agencias build() {

       return Agencias.builder()
               .nomeAgencia(nomeAgencia)
               .latitude(latitude)
               .longitude(longitude)
               .build();
   }
}

