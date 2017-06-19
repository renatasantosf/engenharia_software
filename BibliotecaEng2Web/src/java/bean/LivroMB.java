package bean;

import model.Livro;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import model.Devolucao;
import rn.LivroRN;
import rn.DevolucaoRN;

/**
 *
 * @author Francke
 */
@Named(value = "livroMB")
@SessionScoped
public class LivroMB implements Serializable {

    private Livro livroSelecionado;
    private String id;
    private List<Livro> pesquisaTitulo;
    private List<Livro> maisRetirados;
    private List<Livro> disponiveis;
    private String tituloBusca;
    @Inject
    private LivroRN livroRN;
    @Inject
    private DevolucaoRN devolucaoRN;
    @Inject
    LoginMB loginMB;

    public LivroMB() {
        livroSelecionado = new Livro();
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro usuarioSelecionado) {
        this.livroSelecionado = usuarioSelecionado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Livro> getListaLivros(){
        return livroRN.listar();
    }

    public List<Livro> getPesquisaTitulo() {
        return pesquisaTitulo;
    }

    public void setPesquisaTitulo(List<Livro> pesquisaTitulo) {
        this.pesquisaTitulo = pesquisaTitulo;
    }

    public String getTituloBusca() {
        return tituloBusca;
    }

    public void setTituloBusca(String tituloBusca) {
        this.tituloBusca = tituloBusca;
    }

    public List<Livro> getMaisRetirados() {
        return maisRetirados;
    }

    public void setMaisRetirados(List<Livro> maisRetirados) {
        this.maisRetirados = maisRetirados;
    }

    public List<Livro> getDisponiveis() {
        return disponiveis;
    }

    public void setDisponiveis(List<Livro> disponiveis) {
        this.disponiveis = disponiveis;
    }

    public String novoLivro(){
        livroSelecionado = new Livro();
        return("/admin/livros/cadastroLivros?faces-redirect=true");
    }

    public String adicionarLivro(){
        livroRN.salvar(livroSelecionado);
        this.novoLivro();
        return("/admin/livros/confirmaCadastroLivro?faces-redirect=true");
    }

    public String mostrarLivros(){
        if(loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
                return("/admin/livros/listaLivros?faces-redirect=true");
        return("/usuario/livros/listaLivros?faces-redirect=true");
    }

    public String editarLivro(Livro c){
        livroSelecionado = c;
        return("/admin/livros/edicaoLivros?faces-redirect=true");        
    }

    public String atualizarLivro(){
        livroRN.salvar(livroSelecionado);
        return("/admin/livros/listaLivros?faces-redirect=true");
    }

    public void removerLivro(Livro livro){// verifica se este livro está relacionado a tabela devolução
        List<Devolucao> pesquisaCascade = devolucaoRN.buscarLivroExclusao(livro.getId());
        for (Devolucao devolucao : pesquisaCascade) {
            devolucaoRN.remover(devolucao);
        }
        livroRN.remover(livro);
    }

    public String adicionarPesquisa() {
        pesquisaTitulo = buscarLivroTitulo(this.tituloBusca);
        if (loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return ("/admin/livros/buscaLivros?faces-redirect=true");
        return ("/usuario/livros/buscaLivros?faces-redirect=true");
    }

    public List<Livro> buscarLivroTitulo(String titulo){
        return livroRN.buscarPorTitulo(titulo);
    }

    public String livrosMaisRetirados(){
        maisRetirados = this.livroRN.maisRetirados();
        if (loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return("/admin/relatorios/livrosMaisRetirados?faces-redirect=true");
        return("/usuario/relatorios/livrosMaisRetirados?faces-redirect=true");
    }

    public String livrosDisponiveis(){
        disponiveis = this.livroRN.disponiveis();
        if (loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return("/admin/relatorios/livrosDisponiveis?faces-redirect=true");
        return("/usuario/relatorios/livrosDisponiveis?faces-redirect=true");
    }

    //metodo para box option selec one
    public Livro buscarLivroPorNome(String nome){
        for(Livro l: getListaLivros())
            if(l.getNome().equals(nome))
                return l;
        return null;
    }
}
