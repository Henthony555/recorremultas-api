package br.com.ifpe.recorremultas.modelo.multa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.ifpe.recorremultas.util.entity.GenericService;
import br.com.ifpe.recorremultas.util.exception.EntidadeNaoEncontradaException;

@Service
public class MultaService extends GenericService {

    @Autowired
    private MultaRepository repository;

    @Transactional
    public Multa save(Multa multa) {

        super.preencherCamposAuditoria(multa);
        return repository.save(multa);
    }

    public List<Multa> listAll() {

        return repository.findAll();

    }

    public Multa listById(Long id) {

      Optional<Multa> consulta = repository.findById(id);
    
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Multa", id);
        }

    }

    @Transactional
    public void update(Long id, Multa multaAlterado) {

        Multa multa = repository.findById(id).get();
        multa.setCodigo(multaAlterado.getCodigo());
        multa.setInfracao(multaAlterado.getInfracao());
        multa.setValorMulta(multaAlterado.getValorMulta());
        multa.setPontosDescontados(multaAlterado.getPontosDescontados());
        multa.setGrauMulta(multaAlterado.getGrauMulta());

        super.preencherCamposAuditoria(multa);
        repository.save(multa);

   }

   @Transactional
   public void delete(Long id) {

       Multa multa = repository.findById(id).get();
       multa.setHabilitado(Boolean.FALSE);
       super.preencherCamposAuditoria(multa);

       repository.save(multa);
   }

}
