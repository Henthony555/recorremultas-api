package br.com.ifpe.recorremultas.api.peticao;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.recorremultas.modelo.peticao.Peticao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeticaoRequest {

   @NotNull(message = "O Nome é de preenchimento obrigatório")
   @NotBlank(message = "O Nome é de preenchimento obrigatório")
   private String nomeCompleto;

   private String nacionalidade;

   private String estadoCivil;

   private String profissao;

   private String cnh;

   private String orgaoExpeditor;

   @NotNull(message = "O CPF é de preenchimento obrigatório")
   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   private String cpf;

   private String telefone;

   private String enderecoCompleto;

   @NotNull(message = "A Marca/Modelo é de preenchimento obrigatório")
   @NotBlank(message = "A Marca/Modelo é de preenchimento obrigatório")
   private String marcaModelo;

   @NotNull(message = "A placa é de preenchimento obrigatório")
   @NotBlank(message = "A placa é de preenchimento obrigatório")
   private String placa;

   private String renavam;

   private String chassi;

   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataMulta;

   private String orgaoEmissor;

   private String notificacao;

   @NotNull(message = "A justificativa é de preenchimento obrigatório")
   @NotBlank(message = "A justificativa é de preenchimento obrigatório")
   private String justificativaCancelamento;

   private String id_usuario;

   public Peticao build() {

       return Peticao.builder()
               .nomeCompleto(nomeCompleto)
               .nacionalidade(nacionalidade)
               .estadoCivil(estadoCivil)
               .profissao(profissao)
               .cnh(cnh)
               .cpf(cpf)
               .orgaoExpeditor(orgaoExpeditor)
               .telefone(telefone)
               .enderecoCompleto(enderecoCompleto)
               .marcaModelo(marcaModelo)
               .placa(placa)
               .renavam(renavam)
               .chassi(chassi)
               .dataMulta(dataMulta)
               .orgaoEmissor(orgaoEmissor)
               .notificacao(notificacao)
               .justificativaCancelamento(justificativaCancelamento)
               .id_usuario(id_usuario)
               .build();
              
   }
}
