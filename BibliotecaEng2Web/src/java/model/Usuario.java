package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Francke
 */
@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public final static int MASCULINO = 1;
    public final static int FEMININO = 2;
    private String nome;
    private String telefone;
    private String email;
    private String login;
    private String senha;
    private int sexo;
    private String estado;
    private boolean admin;
    
    public Usuario() {
    }
    
    public Usuario(String nome, String login, String senha) {
        this.nome = nome;        
        this.login = login;
        this.senha = senha;
        this.admin = true;
        this.sexo = 1;
        this.estado="RS";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getSexo() {
        return sexo;
    }
    
    public String getSexoString() {
        if(sexo==1) return "Masculino";
        else return "Feminino";
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    public String getAdminString(){
        if(admin) return "Sim";
        else return "Não";
    }
    
    public String getLabel(){
        if(admin)return "label-success";
        else return "label-danger";
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public boolean verificaLogin(String login, String senha){
        return(this.login.equals(login) && this.senha.equals(senha));
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Usuario[ id=" + id + " ]";
    }
    
}
