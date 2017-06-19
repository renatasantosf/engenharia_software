/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import model.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import model.Devolucao;
import rn.ClienteRN;
import rn.DevolucaoRN;

/**
 *
 * @author Francke
 */
@Named(value = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable {

    private Cliente clienteSelecionado;
    private String matricula;
    private String nomeBusca;
    private List<Cliente> pesquisaNome;
    private List<Cliente> maisRetiram;
    private List<Cliente> maisAtrasam;
    @Inject
    private ClienteRN clienteRN;
    @Inject
    private DevolucaoRN devolucaoRN;
    @Inject
    LoginMB loginMB;

    public ClienteMB() {
        clienteSelecionado = new Cliente();

    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente usuarioSelecionado) {
        this.clienteSelecionado = usuarioSelecionado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Cliente> getListaClientes(){
        return clienteRN.listar();
    }

    public List<Cliente> getPesquisaNome() {
        return pesquisaNome;
    }

    public void setPesquisaNome(List<Cliente> pesquisaNome) {
        this.pesquisaNome = pesquisaNome;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public List<Cliente> getMaisRetiram() {
        return maisRetiram;
    }

    public void setMaisRetiram(List<Cliente> maisRetiram) {
        this.maisRetiram = maisRetiram;
    }

    public List<Cliente> getMaisAtrasam() {
        return maisAtrasam;
    }

    public void setMaisAtrasam(List<Cliente> maisAtrasam) {
        this.maisAtrasam = maisAtrasam;
    }

    public String novoCliente(){
        clienteSelecionado = new Cliente();
        return("/admin/clientes/cadastroClientes?faces-redirect=true");
    }

    public String adicionarCliente(){
        clienteRN.salvar(clienteSelecionado);
        this.novoCliente();
        return("/admin/clientes/confirmaCadastroCliente?faces-redirect=true");
    }

    public String mostrarClientes(){
        if(loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return("/admin/clientes/listaClientes?faces-redirect=true");
        return("/usuario/clientes/listaClientes?faces-redirect=true");
    }

    public String editarCliente(Cliente c){
        clienteSelecionado = c;
        return("/admin/clientes/edicaoClientes?faces-redirect=true");
    }

    public String atualizarCliente(){
        clienteRN.salvar(clienteSelecionado);
        return("/admin/clientes/listaClientes?faces-redirect=true");
    }

    public String adicionarPesquisa() {
        pesquisaNome = buscarCliente(this.nomeBusca);
        if(loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return ("/admin/clientes/buscaCliente?faces-redirect=true");
        return ("/usuario/clientes/buscaCliente?faces-redirect=true");        
    }

    public String adicionarPesquisaRetirada() {
        pesquisaNome = buscarCliente(this.nomeBusca); 
        if(loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return ("/admin/retiradas/buscaCliente?faces-redirect=true");
        return ("/usuario/retiradas/buscaCliente?faces-redirect=true");
    }

    public void removerCliente(Cliente cliente){// verifica se este cliente está relacionado a tabela devolução
        List<Devolucao> pesquisaCascade = devolucaoRN.buscarClienteExclusao(cliente.getMatricula());
        for (Devolucao devolucao : pesquisaCascade) {
            devolucaoRN.remover(devolucao);      
        }
        clienteRN.remover(cliente);
    }

    public List<Cliente> buscarCliente(String nome){
        return clienteRN.buscarPorNome(nome);
    }

    public String clientesMaisRetiram(){
        maisRetiram = this.clienteRN.topQueRetiram();
        if(loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return("/admin/relatorios/clientesMaisRetiram?faces-redirect=true");
        return("/usuario/relatorios/clientesMaisRetiram?faces-redirect=true");   

    }

    public String clientesMaisAtrasam(){
        maisAtrasam = this.clienteRN.topQueAtrasam();
        if(loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return("/admin/relatorios/clientesMaisAtrasam?faces-redirect=true");
        return("/usuario/relatorios/clientesMaisAtrasam?faces-redirect=true");

    }
}
