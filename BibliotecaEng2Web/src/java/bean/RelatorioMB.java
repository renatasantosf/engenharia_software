package bean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped //Application, pois os usuários cadastrados deverão permanecer mesmo se fizer logout.
public class RelatorioMB {

    @Inject
    LoginMB loginMB;

    public RelatorioMB() {

    }

    public String mostrarRelatorios() {
        if (loginMB!=null && loginMB.estaLogado() && loginMB.eAdmin())
            return ("/admin/relatorios/relatorios?faces-redirect=true");
        return ("/usuario/relatorios/relatorios?faces-redirect=true");
    }
}
