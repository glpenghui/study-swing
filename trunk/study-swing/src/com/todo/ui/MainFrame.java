package com.todo.ui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6526854054846237364L;

	@SuppressWarnings("deprecation")
	public void init(){
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(600,400));
		setVisible(true);
		setState(Frame.NORMAL);
		show();
	}
}
