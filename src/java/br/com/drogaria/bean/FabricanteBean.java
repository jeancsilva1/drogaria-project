 package br.com.drogaria.bean;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {

    private Fabricante fabricante;
    private ListDataModel<Fabricante> itens;
   // private ArrayList<Fabricante> itens;
    //private ArrayList<Fabricante> itensFiltrados;

    public ListDataModel<Fabricante> getItens() {
        return itens;
    }
    public void setItens(ListDataModel<Fabricante> itens) {
        this.itens = itens;
    }
    
   /* public ArrayList<Fabricante> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Fabricante> itens) {
        this.itens = itens;
    }

    public ArrayList<Fabricante> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }
*/
    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @PostConstruct
    public void prepararPesquisa() {
        try {
            FabricanteDAO dao = new FabricanteDAO();
           ArrayList<Fabricante> lista = dao.listar();
           itens = new ListDataModel<Fabricante>(lista);
            //itens = dao.listar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }
    }

    public void prepararNovo() {
        fabricante = new Fabricante();
    }

    public void novo() {
        try {
            FabricanteDAO dao = new FabricanteDAO();
            ArrayList<Fabricante> lista = dao.listar();
           itens = new ListDataModel<Fabricante>(lista);
            dao.salvar(fabricante);
            //itens = dao.listar();
            JSFUtil.adicionarMensagemSucesso("Fabricante salvo com Sucesso !!!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }
    }

    public void excluir() {
        try {
            FabricanteDAO dao = new FabricanteDAO();
            dao.excluir(fabricante);
            ArrayList<Fabricante> lista = dao.listar();
            itens = new ListDataModel<Fabricante> (lista);
            //itens = dao.listar();
            JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }

    }

    public void prepararExcluir(){
        fabricante = itens.getRowData();
    }
    
      public void prepararEditar(){
        fabricante = itens.getRowData();
    }
    
    public void editar() {
        try {
            
            FabricanteDAO dao = new FabricanteDAO();
            dao.editar(fabricante);
             ArrayList<Fabricante> lista = dao.listar();
            itens = new ListDataModel<Fabricante> (lista);
            //itens = dao.listar();
            JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso. ");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JSFUtil.adicionarMensagemErro(ex.getMessage());
        }
    }
    
    
    
}
