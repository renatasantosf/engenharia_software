package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francke
 */
@Entity
@XmlRootElement // elemento xml para web service
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matricula;
    private String nome, telefone, email, cpf;
    private int retiradas, atrasos;

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    
    public Cliente() { 
        
    }
    /**
     * Construtor para inicializar cliente
     *
     * @param matricula identifica a matrícula de uma pessoa.
     * @param nome identifica o nome de uma pessoa.
     * @param telefone identifica telefone de uma pessoa.
     *
     */
    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }   

    /**
     * Retorna o nome
     *
     * @return nome de uma pessoa
     */
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o telefone
     *
     * @return telefone de uma pessoa
     */
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmailCliente() {
        return email;
    }
    
    public void setEmailCliente(String email) {
        this.email = email;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(int retiradas) {
        this.retiradas = retiradas;
    }

    public int getAtrasos() {
        return atrasos;
    }

    public void setAtrasos(int atrasos) {
        this.atrasos = atrasos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the matricula fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cliente[ id=" + matricula + " ]";
    }
    
}