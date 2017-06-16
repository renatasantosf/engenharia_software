package bean;

import model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import rn.UsuarioRN;

/**
 *
 * @author Francke
 */
@Named(value = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {
    
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;
    @Inject
    private UsuarioRN usuarioRN;

    public UsuarioMB() {
        usuarioSelecionado = new Usuario();
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
    public List<Usuario> getListaUsuarios(){
        return usuarioRN.listar();
    }
    
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public String novoUsuario(){
        usuarioSelecionado = new Usuario();
        return("/admin/usuarios/cadastroUsuarios?faces-redirect=true");
    }
    
    public String adicionarUsuario(){
        usuarioRN.salvar(usuarioSelecionado);
        this.novoUsuario();
        return("/admin/usuarios/confirmaCadastroUsuario?faces-redirect=true");
    }
    
    public String mostrarUsuarios(){        
        return("/admin/usuarios/listaUsuarios?faces-redirect=true");
    }
    
    public String editarUsuario(Usuario u){
        usuarioSelecionado = u;
        return("/admin/usuarios/edicaoUsuarios?faces-redirect=true");        
    }
    
    public String editarPerfil(Usuario u){
        usuarioSelecionado = u;
        return("/usuario/editarPerfil?faces-redirect=true");
    }
    
    public String atualizarUsuario(){
        usuarioRN.salvar(usuarioSelecionado);
        return("/admin/usuarios/listaUsuarios?faces-redirect=true");
    }
    
    public void removerUsuario(Usuario usuario){
        usuarioRN.remover(usuario);
    }
    
}
