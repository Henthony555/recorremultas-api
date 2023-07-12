package br.com.ifpe.recorremultas.modelo.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import br.com.ifpe.recorremultas.modelo.peticao.Peticao;
import br.com.ifpe.recorremultas.modelo.peticao.PeticaoRepository;
import br.com.ifpe.recorremultas.util.entity.GenericService;
import br.com.ifpe.recorremultas.util.exception.EntidadeNaoEncontradaException;


@Service
public class UsuarioService extends GenericService {
    
    @Autowired
    private UsuarioRepository repository;

    
    @Autowired
    private PeticaoRepository peticaoRepository;
    
    {/* 
    @Autowired
    private UsuService usuService;
    */}
    
    @Transactional
    public Usuario save(Usuario usuario) {

        //usuService.save(usuario.getUsu());

 
        super.preencherCamposAuditoria(usuario);
        return repository.save(usuario);
    }
 
    public List<Usuario> listarTodos() {
   
     return repository.findAll();
    }
 
    public Usuario obterPorID(Long id) {
    
        Optional<Usuario> consulta = repository.findById(id);
    
        if (consulta.isPresent()) {
            return consulta.get();
        } else {
            throw new EntidadeNaoEncontradaException("Usuario", id);
        }
    }

    @Transactional
    public void update(Long id, Usuario usuarioAlterado) {

        Usuario usuario = repository.findById(id).get();
        usuario.setNomeCompleto(usuarioAlterado.getNomeCompleto());
        usuario.setDataNascimento(usuarioAlterado.getDataNascimento());
        usuario.setCpf(usuarioAlterado.getCpf());
        //usuario.setEmail(usuarioAlterado.getEmail());
        //usuario.setSenha(usuarioAlterado.getSenha());
     
        super.preencherCamposAuditoria(usuario);
        repository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {

        Usuario usuario = repository.findById(id).get();
        usuario.setHabilitado(Boolean.FALSE);
        super.preencherCamposAuditoria(usuario);

        repository.save(usuario);
    }



    @Transactional
    public Peticao adicionarPeticao(Long usuarioId, Peticao peticao) {

       Usuario usuario = this.obterPorID(usuarioId);
      
       //Primeiro salva o EnderecoCliente:

       peticao.setUsuario(usuario);
       peticao.setHabilitado(Boolean.TRUE);
       peticaoRepository.save(peticao);
      
       //Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

       List<Peticao> listaPeticao = usuario.getPeticoes();
      
       if (listaPeticao == null) {
           listaPeticao = new ArrayList<Peticao>();
       }
      
       listaPeticao.add(peticao);
       usuario.setPeticoes(listaPeticao);
       this.save(usuario);
      
       return peticao;
   }

   @Transactional
   public Peticao atualizarPeticao(Long id, Peticao peticaoAlterado) {

       Peticao peticao = peticaoRepository.findById(id).get();
       peticao.setNomeCompleto(peticaoAlterado.getNomeCompleto());
       peticao.setNacionalidade(peticaoAlterado.getNacionalidade());
       peticao.setEstadoCivil(peticaoAlterado.getEstadoCivil());
       peticao.setProfissao(peticaoAlterado.getProfissao());
       peticao.setCnh(peticaoAlterado.getCnh());
       peticao.setOrgaoExpeditor(peticaoAlterado.getOrgaoExpeditor());
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

       return peticaoRepository.save(peticao);
   }

    @Transactional
    public void removerPeticao(Long id) {

       Peticao peticao = peticaoRepository.findById(id).get();
       peticao.setHabilitado(Boolean.FALSE);
       peticaoRepository.save(peticao);

       Usuario usuario = this.obterPorID(peticao.getUsuario().getId());
       usuario.getPeticoes().remove(peticao);
       this.save(usuario);
   }

   
    public boolean isAuthenticated(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();

            // Verificar se o UID é válido ou fazer outras operações com o usuário autenticado
            return true;

        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return false;
        }
    }


}

