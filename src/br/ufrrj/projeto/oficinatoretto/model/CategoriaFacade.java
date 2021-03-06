package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.CategoriaDAO;

public class CategoriaFacade {
	
	private Categoria categoria = new Categoria();
	
	public void registraCategoria(String nome, Integer superCategoria) {
		if (superCategoria != null ){
			Categoria superCat = new Categoria();
			superCat.setIdCategoria(superCategoria);
			categoria.setCategoria(superCat);
		}
		categoria.setNome(nome);
		
	}

    public void salvar() throws Exception {
        new CategoriaDAO().salvar(categoria);
        this.categoria = new Categoria();
    }

    public void alterar(Categoria categoria) throws Exception {
        new CategoriaDAO().alterar(categoria);
    }

    public List<Categoria> listaCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar categoria" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new CategoriaDAO().excluir(id);
    }

}