package com.todo.ui;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BoxLayoutPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1228723752814520577L;
	
	@SuppressWarnings("rawtypes")
	private List panelComponents;
	private int axis;
	
	@SuppressWarnings("rawtypes")
	public void setPanelComponents(List panelComponents) {
		this.panelComponents = panelComponents;
	}
	public void setAxis(int axis) {
		this.axis = axis;
	}
	
	@SuppressWarnings("rawtypes")
	public void init(){
		setLayout(new BoxLayout(this, axis));
		for(Iterator iter = panelComponents.iterator();iter.hasNext();){
			Component component = (Component)iter.next();
			add(component);
		}
	}

	
}
