package br.com.ifpe.recorremultas.api.peticao;

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

import br.com.ifpe.recorremultas.modelo.peticao.Peticao;
import br.com.ifpe.recorremultas.modelo.peticao.PeticaoService;
import br.com.ifpe.recorremultas.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/peticao")
public class PeticaoController extends GenericController {

   @Autowired
   private PeticaoService peticaoService;

   @ApiOperation(value = "Serviço responsável por salvar uma petição no sistema.")
   @PostMapping
   public ResponseEntity<Peticao> save(@RequestBody @Valid PeticaoRequest request) {

      Peticao peticao = peticaoService.save(request.build());
       return new ResponseEntity<Peticao>(peticao, HttpStatus.CREATED);
   }

   @ApiOperation(value = "Serviço responsável por listar todas as petições do sistema.")
   @GetMapping
   public List<Peticao> listarTodos() {
  
       return peticaoService.listarTodos();
   }

    @ApiOperation(value = "Serviço responsável por obter uma petição referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a petição."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
   })
   @GetMapping("/{id}")
   public Peticao obterPorID(@PathVariable Long id) {

       return peticaoService.obterPorID(id);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Peticao>update(@PathVariable("id") Long id, @RequestBody PeticaoRequest request) {

      peticaoService.update(id, request.build());
       return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

        peticaoService.delete(id);
       return ResponseEntity.ok().build();
   }

}

