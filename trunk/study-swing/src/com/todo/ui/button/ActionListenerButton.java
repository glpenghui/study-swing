package com.todo.ui.button;

import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ActionListenerButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8890221509984669304L;
	
	private ActionListener actionListener;

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void init() {
        this.addActionListener(actionListener);
    }
} 


