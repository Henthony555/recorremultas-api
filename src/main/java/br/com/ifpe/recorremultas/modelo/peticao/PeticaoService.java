package br.com.ifpe.recorremultas.modelo.peticao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.recorremultas.modelo.usuario.Usuario;
import br.com.ifpe.recorremultas.util.entity.GenericService;
import br.com.ifpe.recorremultas.util.exception.EntidadeNaoEncontradaException;

@Service
public class PeticaoService extends GenericService {

    @Autowired
    private PeticaoRepository repository;

    @Transactional
    public Peticao save(Peticao peticao) {

        super.preencherCamposAuditoria(peticao);
        return repository.save(peticao);
    }

    public List<Peticao> listarTodos() {

        return repository.findAll();
    }

    public Peticao obterPorID(Long id) {

       Optional<Peticao> consulta = repository.findById(id);
    
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Peticao", id);
        }
    }

    @Transactional
    public void update(Long id, Peticao peticaoAlterado) {

        Peticao peticao = repository.findById(id).get();
        peticao.setNomeCompleto(peticaoAlterado.getNomeCompleto());
        peticao.setNacionalidade(peticaoAlterado.getNacionalidade());
        peticao.setEstadoCivil(peticaoAlterado.getEstadoCivil());
        peticao.setProfissao(peticaoAlterado.getProfissao());
        peticao.setCnh(peticaoAlterado.getCnh());
        peticao.setOrgaoExpeditor(peticaoAlterado.getOrgaoExpeditor());
        peticao.setCpf(peticaoAlterado.getCpf());
        peticao.setTelefone(peticaoAlterado.getTelefone());
        peticao.setEnderecoCompleto(peticaoAlterado.getEnderecoCompleto());
        peticao.setMarcaModelo(peticaoAlterado.getMarcaModelo());
        peticao.setPlaca(peticaoAlterado.getPlaca());
        peticao.setRenavam(peticaoAlterado.getRenavam());
        peticao.setChassi(peticaoAlterado.getChassi());
        peticao.setDataMulta(peticaoAlterado.getDataMulta());
        peticao.setOrgaoEmissor(peticaoAlterado.getOrgaoEmissor());
        peticao.setNotificacao(peticaoAlterado.getNotificacao());
        peticao.setJustificativaCancelamento(peticaoAlterado.getJustificativaCancelamento());

        super.preencherCamposAuditoria(peticao);
        repository.save(peticao);
    }

    @Transactional
    public void delete(Long id) {

        Peticao peticao = repository.findById(id).get();
        peticao.setHabilitado(Boolean.FALSE);
        super.preencherCamposAuditoria(peticao);

        repository.save(peticao);
    }

}