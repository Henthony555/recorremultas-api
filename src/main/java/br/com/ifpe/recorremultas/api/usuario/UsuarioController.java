package br.com.ifpe.recorremultas.api.usuario;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.recorremultas.api.peticao.PeticaoRequest;
import br.com.ifpe.recorremultas.modelo.peticao.Peticao;
import br.com.ifpe.recorremultas.modelo.peticao.PeticaoService;
import br.com.ifpe.recorremultas.modelo.usuario.Usuario;
import br.com.ifpe.recorremultas.modelo.usuario.UsuarioService;
import br.com.ifpe.recorremultas.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends GenericController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PeticaoService peticaoService;


    @ApiOperation(value = "Serviço responsável por salvar um usuario no sistema.")
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioRequest request) {

        Usuario usuario = usuarioService.save(request.build());
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por listar todos os usuarios do sistema.")
    @GetMapping
    public List<Usuario> listarTodos() {

        return usuarioService.listarTodos();
    }

    @ApiOperation(value = "Serviço responsável por obter um usuario referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna  o usuario."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
   })
    @GetMapping("/{id}")
    public Usuario obterPorID(@PathVariable Long id) {

        return usuarioService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody UsuarioRequest request) {

        usuarioService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/peticao/{usuarioId}")
    public ResponseEntity<Peticao> adicionarPeticao(@PathVariable("usuarioId") Long usuarioId,
            @RequestBody @Valid PeticaoRequest request) {

        Peticao peticao = usuarioService.adicionarPeticao(usuarioId, request.build());
        return new ResponseEntity<Peticao>(peticao, HttpStatus.CREATED);
    }

    @PutMapping("/peticao/{peticaoId}")
    public ResponseEntity<Peticao> atualizarPeticao(@PathVariable("peticaoId") Long peticaoId,
            @RequestBody PeticaoRequest request) {

        Peticao peticao = usuarioService.atualizarPeticao(peticaoId, request.build());
        return new ResponseEntity<Peticao>(peticao, HttpStatus.OK);
    }

    @DeleteMapping("/peticao/{peticaoId}")
    public ResponseEntity<Void> removerPeticao(@PathVariable("peticaoId") Long peticaoId) {

        usuarioService.removerPeticao(peticaoId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioRequest request) {
        // Lógica de autenticação do usuário
        if (usuarioService.isAuthenticated(request.getIdToken())) {
            // Usuário autenticado com sucesso
            return ResponseEntity.ok("Usuário autenticado.");
        } else {
            // Falha na autenticação do usuário
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação do usuário.");
        }
    }
}