package br.com.ifpe.recorremultas.api.usuario;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.recorremultas.modelo.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    
    @NotNull(message = "O Nome é de preenchimento obrigatório")
    @NotBlank(message = "O Nome é de preenchimento obrigatório")
    @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
    private String nomeCompleto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
 
    @NotNull(message = "O CPF é de preenchimento obrigatório")
    @NotBlank(message = "O CPF é de preenchimento obrigatório")
    @CPF
    private String cpf;
 
    @NotNull(message = "O email é de preenchimento obrigatório")
    @NotBlank(message = "O email é de preenchimento obrigatório")
    @Email
    private String email;
 
    @NotNull(message = "A senha é de preenchimento obrigatório")
    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String senha;
 
    public Usuario build() {
 
        return Usuario.builder()
            .email(email)
            .senha(senha)
            .build();
        }

        
   {/*  public Usu buildUsu() {
	
	return Usu.builder()
		.username(email)
		.password(password)
		.roles(Arrays.asList(Usu.ROLE_CLIENTE))
		.build();
    }
  */}


public String getIdToken() {
    return null;
}  
}

