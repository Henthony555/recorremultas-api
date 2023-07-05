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

import br.com.ifpe.recorremultas.modelo.usuario.Usuario;
import br.com.ifpe.recorremultas.modelo.usuario.UsuarioService;
import br.com.ifpe.recorremultas.util.entity.GenericController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController extends GenericController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioRequest request) {

        Usuario usuario = usuarioService.save(request.build());
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Usuario> listarTodos() {

        return usuarioService.listarTodos();
    }

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

{/* 

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
*/}
}