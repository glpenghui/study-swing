package cn;

import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTabbedPane;

import javax.swing.JTable;

import javax.swing.border.TitledBorder;

public class Yezx extends JFrame implements ActionListener {

	JLabel lb1 = new JLabel("��ӭʹ��ѧ��ѧϰ����С����");

	JTabbedPane tab1 = new JTabbedPane();

	JButton bt2_1 = new JButton("���");

	JButton bt2_2 = new JButton("�޸�");

	JButton bt2_3 = new JButton("��ѯ");

	JButton bt2_4 = new JButton("ɾ��");

	JButton bt3 = new JButton("����������");

	String[] col2 = { "�ճ�����", "��ʼʱ��", "����ʱ��", "����", "��ע" };

	String row2[][] = new String[12][5];

	JTable table2 = new JTable(row2, col2);

	JScrollPane jsp2 = new JScrollPane(table2);

	String[] col3 = { "�ճ�����", "��ʼʱ��", "����ʱ��", "����", "��ע" };

	String row3[][] = new String[12][5];

	JTable table3 = new JTable(row3, col3);

	JScrollPane jsp3 = new JScrollPane(table3);

	public Yezx() {

		JPanel jp = (JPanel) this.getContentPane();

		JPanel jp1 = new JPanel();

		JPanel jp2 = new JPanel();

		JPanel jp2_2 = new JPanel();

		JPanel jp2_2_1 = new JPanel();

		JPanel jp2_2_2 = new JPanel();

		jp1.add(lb1);

		jp1.add(bt3);

		jp1.setBorder(new TitledBorder(""));

		GridLayout gl2 = new GridLayout(5, 1);

		gl2.setVgap(20);

		// ����JScrollPane�Ĵ�С

		jsp2.setPreferredSize(new Dimension(500, 200));

		jsp3.setPreferredSize(new Dimension(500, 200));

		// �������񲼾�

		jp2_2_2.setLayout(new GridLayout(2, 1));

		jp2_2_2.add(jsp2);

		jp2_2_2.add(jsp3);

		//

		jp2_2_1.setLayout(gl2);

		jp2_2_1.add(bt2_1);

		jp2_2_1.add(bt2_2);

		jp2_2_1.add(bt2_3);

		jp2_2_1.add(bt2_4);

		jp2_2.add(jp2_2_2);

		jp2_2.add(jp2_2_1);

		bt2_1.addActionListener(this);

		bt2_2.addActionListener(this);

		bt2_3.addActionListener(this);

		bt2_4.addActionListener(this);

		jp2.setLayout(new FlowLayout());

		tab1.addTab("������ϸ", jp2_2);

		jp2.add(tab1);

		jp.setLayout(new BorderLayout());

		jp.add(jp1, BorderLayout.NORTH);

		jp.add(jp2, BorderLayout.CENTER);

		this.pack();

		this.setResizable(false);

		this.setVisible(true);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {

		new Yezx();

	}

}
