package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
import model.Livro;
import model.Devolucao;
import model.Retiradas;
import rn.ClienteRN;
import rn.LivroRN;
import rn.DevolucaoRN;
import rn.RetiradasRN;
import static util.DateUtil.dateToString;

@Named
@ApplicationScoped
public class DevolucaoMB implements Serializable {

    @Inject
    DevolucaoRN devolucaoRN;
    @Inject
    RetiradasRN retiradaRN;
    @Inject
    LivroRN livroRN;
    @Inject
    ClienteRN clienteRN;
    @Inject
    ClienteMB clienteMB;
    @Inject
    LivroMB livroMB;
    @Inject
    RetiradasMB RetiradasMB;
    @Inject
    LoginMB loginMB;

    private long id;
    private long matriculaCliente;
    private long idLivro;
    private String dtFormatada;
    long DAY_IN_MS = 1000 * 60 * 60 * 24; // formatar data entrega
    private Devolucao devolucaoSelecionada;
      private Date dataAtual = new Date(System.currentTimeMillis());

    public DevolucaoMB() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMatriculaCliente() {
        return matriculaCliente;
    }

    public void setMatriculaCliente(long matriculaCliente) {
        this.matriculaCliente = matriculaCliente;
    }

    public long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(long idLivro) {
        this.idLivro = idLivro;
    }

    public String getDtFormatada() {
        return dtFormatada;
    }

    public void setDtFormatada(String dtFormatada) {
        this.dtFormatada = dtFormatada;
    }

    public Devolucao getDevolucaoSelecionada() {
        return devolucaoSelecionada;
    }

    public void setDevolucaoSelecionada(Devolucao devolucaoSelecionada) {
        this.devolucaoSelecionada = devolucaoSelecionada;
    }

    public List<Devolucao> getListaDevolucao() {
        return devolucaoRN.listar();
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public void devolverLivro(Retiradas retirada){
        Livro l = retirada.getLivro();
        Cliente c = retirada.getCliente();
        atualizaLivroDevolucao(l);
        atrasoCliente(c, retirada);
        adicionarDevolucao(retirada);
        retiradaRN.remover(retirada);
    }
    
    public void atualizaLivroDevolucao(Livro l){
        l.setDisponivel(true);
        l.setDataLiberacao(dataAtual);
        livroRN.salvar(l);
    }

    public void atrasoCliente(Cliente c, Retiradas r){
        if (r.getDataDevolucao().before(getDataAtual()))
            c.setAtrasos(c.getAtrasos() + 1);

        clienteRN.salvar(c);
    }

    public String novaDevolucao() {
        devolucaoSelecionada = new Devolucao();
        if (loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return ("/admin/devolucao/devolucao?faces-redirect=true");
        return ("/usuario/devolucao/devolucao?faces-redirect=true");
    }

    public String adicionarDevolucao(Retiradas retirada) {
        devolucaoSelecionada = new Devolucao(retirada.getCliente(), retirada.getLivro(), retirada.getDataRetirada(), retirada.getDataDevolucao(), dataAtual);
        devolucaoRN.salvar(devolucaoSelecionada);
        return (this.novaDevolucao());
    }

   public String formataData(Date data) {
        this.dtFormatada = dateToString(data);
        return this.dtFormatada;
    }

    public String mostrarDevolucao(){
        if (loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return("/admin/relatorios/listaDevolucao?faces-redirect=true");
        return("/usuario/relatorios/listaDevolucao?faces-redirect=true");
    }

    public void removerDevolucao(Devolucao devolucao) {
        devolucaoRN.remover(devolucao);
    }

    public String getAtrasadoString(Devolucao d){
        if(d.getDataDevolucao().before(d.getDataDevolvido())) return "Sim";
        else return "Não";
    }

    public String getLabel(Devolucao d){
        if(d.getDataDevolucao().before(d.getDataDevolvido())) return "label-danger";
        else return "label-success";
    }
}