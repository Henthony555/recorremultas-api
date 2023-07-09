package br.com.ifpe.recorremultas.modelo.agencias;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.recorremultas.util.entity.GenericService;
import br.com.ifpe.recorremultas.util.exception.EntidadeNaoEncontradaException;

@Service
public class AgenciasService extends GenericService {

    @Autowired
    private AgenciasRepository repository;

    @Transactional
    public Agencias save(Agencias agencias) {

        super.preencherCamposAuditoria(agencias);
        return repository.save(agencias);
    }

    public List<Agencias> listarTodos() {

        return repository.findAll();
    }

    public Agencias obterPorID(Long id) {

         Optional<Agencias> consulta = repository.findById(id);
    
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Agencias", id);
        }

    }

    @Transactional
    public void update(Long id, Agencias agenciasAlterado) {

        Agencias agencias = repository.findById(id).get();
        agencias.setNomeAgencia(agenciasAlterado.getNomeAgencia());
        agencias.setLatitude(agenciasAlterado.getLatitude());
        agencias.setLongitude(agenciasAlterado.getLongitude());

        super.preencherCamposAuditoria(agencias);
        repository.save(agencias);
    }

    @Transactional
    public void delete(Long id) {

        Agencias agencias = repository.findById(id).get();
        agencias.setHabilitado(Boolean.FALSE);
        super.preencherCamposAuditoria(agencias);

        repository.save(agencias);
    }

}
