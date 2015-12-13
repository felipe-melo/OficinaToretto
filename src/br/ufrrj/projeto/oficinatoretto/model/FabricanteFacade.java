package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.FabricanteDAO;

public class FabricanteFacade {
	
	private Fabricante fabricante = new Fabricante();
	
	public void regristraFabricante(String nome, String telefone) {
		fabricante.setNome(nome);
		fabricante.setTelefone(telefone);
	}

    public void salvar() throws Exception {
        new FabricanteDAO().salvar(fabricante);
    }

    public void alterar() throws Exception {
        new FabricanteDAO().alterar(fabricante);
    }

    public List<Fabricante> listaFabricantes() {
        FabricanteDAO dao = new FabricanteDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar fabricante" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new FabricanteDAO().excluir(id);
    }

}