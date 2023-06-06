package br.com.ifpe.recorremultas.modelo.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.recorremultas.util.entity.GenericService;

@Service
public class UsuarioService extends GenericService {
    
    @Autowired
    private UsuarioRepository repository;
 
    @Transactional
    public Usuario save(Usuario usuario) {
 
        super.preencherCamposAuditoria(usuario);
        return repository.save(usuario);
    }
 
    public List<Usuario> listarTodos() {
   
     return repository.findAll();
 }
 
 public Usuario obterPorID(Long id) {
 
     return repository.findById(id).get();
 }

 @Transactional
    public void update(Long id, Usuario usuarioAlterado) {

        Usuario usuario = repository.findById(id).get();
        usuario.setNomeCompleto(usuarioAlterado.getNomeCompleto());
        usuario.setDataNascimento(usuarioAlterado.getDataNascimento());
        usuario.setCpf(usuarioAlterado.getCpf());
        usuario.setEmail(usuarioAlterado.getEmail());
        usuario.setSenha(usuarioAlterado.getSenha());
     
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

}

