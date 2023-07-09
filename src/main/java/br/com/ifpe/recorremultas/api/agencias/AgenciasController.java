package br.com.ifpe.recorremultas.api.agencias;

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

import br.com.ifpe.recorremultas.modelo.agencias.Agencias;
import br.com.ifpe.recorremultas.modelo.agencias.AgenciasService;
import br.com.ifpe.recorremultas.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/agencias")
public class AgenciasController extends GenericController {

    @Autowired
    private AgenciasService agenciasService;

    @ApiOperation(value = "Serviço responsável por salvar uma agência no sistema.")
    @PostMapping
    public ResponseEntity<Agencias> save(@RequestBody @Valid AgenciasRequest request) {

        Agencias agencias = agenciasService.save(request.build());
        return new ResponseEntity<Agencias>(agencias, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por listar todas as agências do sistema.")
    @GetMapping
    public List<Agencias> listarTodos() {

        return agenciasService.listarTodos();
    }

    @ApiOperation(value = "Serviço responsável por obter uma agência referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a agência."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
   })
    @GetMapping("/{id}")
    public Agencias obterPorID(@PathVariable Long id) {

        return agenciasService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agencias> update(@PathVariable("id") Long id, @RequestBody AgenciasRequest request) {

        agenciasService.update(id, request.build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        agenciasService.delete(id);
        return ResponseEntity.ok().build();
    }

}
