package br.ufrrj.projeto.oficinatoretto.model;

import javax.swing.event.ListDataListener;

public class ComboBoxModel<T> implements javax.swing.ComboBoxModel<T> {
	
	private T[] lista;
	private T item;
	
	public ComboBoxModel(T[] lista) {
		this.lista = lista;
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		
	}

	@Override
	public T getElementAt(int arg0) {
		return this.lista[arg0];
	}

	@Override
	public int getSize() {
		return this.lista.length;
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		
	}

	@Override
	public Object getSelectedItem() {
		return item;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.item = (T)anItem;
	}

}