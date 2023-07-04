package br.com.ifpe.recorremultas.api.multa;

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

import br.com.ifpe.recorremultas.modelo.multa.Multa;
import br.com.ifpe.recorremultas.modelo.multa.MultaService;
import br.com.ifpe.recorremultas.util.entity.GenericController;

@RestController
@RequestMapping("/api/multa")
public class MultaController extends GenericController {

   @Autowired
   private MultaService multaService;

   @PostMapping
   public ResponseEntity<Multa> save(@RequestBody @Valid MultaRequest request) {

       Multa Multa = multaService.save(request.build());
       return new ResponseEntity<Multa>(Multa, HttpStatus.CREATED);
   }

   @GetMapping
   public List<Multa> listAll() {
    return multaService.listAll();       
       
   }

   @GetMapping ("/{id}")
   public Multa listById(@PathVariable Long id) {
    return multaService.listById(id);       
       
   }
   
   @PutMapping("/{id}")
   public ResponseEntity<Multa> update(@PathVariable("id") Long id, @RequestBody MultaRequest request) {

       multaService.update(id, request.build());
       return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

      multaService.delete(id);
      return ResponseEntity.ok().build();
  }

}
