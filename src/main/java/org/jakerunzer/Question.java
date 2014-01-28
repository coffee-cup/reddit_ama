package org.jakerunzer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Question {
	private String q_author;
	private String q_body;
	private String a_author;
	private String a_body;

	private JPanel content;
	private GridBagConstraints gbc_content;

	public Question(String q_author, String q_body, String a_author,
			String a_body) {
		this.q_author = q_author;
		this.q_body = q_body;
		this.a_author = a_author;
		this.a_body = a_body;

		initComponents();
	}

	/**
	 * Creates a JPanel containing questions and answers
	 */
	private void initComponents() {
		content = new JPanel();
		content.setLayout(new GridLayout(0, 1, 0, 0));

		content = new JPanel();
		content.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		content.setLayout(new BorderLayout(0, 0));

		JPanel quesPnl = new JPanel();
		quesPnl.setBackground(Color.WHITE);
		content.add(quesPnl, BorderLayout.NORTH);
		quesPnl.setBorder(new LineBorder(Color.LIGHT_GRAY));
		quesPnl.setLayout(new BorderLayout(0, 0));

		JPanel pnlQ = new JPanel();
		pnlQ.setBackground(Color.WHITE);
		pnlQ.setBorder(new EmptyBorder(0, 5, 0, 5));
		quesPnl.add(pnlQ, BorderLayout.WEST);
		pnlQ.setLayout(new BorderLayout(0, 0));

		JLabel lblQ = new JLabel("Q");
		lblQ.setBackground(Color.WHITE);
		lblQ.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlQ.add(lblQ);
		lblQ.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		JPanel pnlQuesBody = new JPanel();
		pnlQuesBody.setBackground(Color.WHITE);
		pnlQuesBody.setBorder(new EmptyBorder(5, 5, 5, 5));
		quesPnl.add(pnlQuesBody);
		pnlQuesBody.setLayout(new BorderLayout(0, 0));

		JTextArea txtQues = new JTextArea();
		txtQues.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtQues.setEditable(false);
		pnlQuesBody.add(txtQues);
		txtQues.setWrapStyleWord(true);
		txtQues.setLineWrap(true);
		txtQues.setBorder(new EmptyBorder(4, 4, 4, 4));
		txtQues.setText(q_body);

		JPanel ansPnl = new JPanel();
		ansPnl.setBorder(new EmptyBorder(0, 20, 0, 0));
		ansPnl.setBackground(Color.WHITE);
		content.add(ansPnl, BorderLayout.SOUTH);
		ansPnl.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlA = new JPanel();
		pnlA.setBackground(Color.WHITE);
		ansPnl.add(pnlA, BorderLayout.WEST);

		JLabel lblA = new JLabel("A");
		lblA.setBorder(null);
		lblA.setBackground(Color.WHITE);
		lblA.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlA.add(lblA);
		lblA.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		JPanel pnlAnsBody = new JPanel();
		pnlAnsBody.setBackground(Color.WHITE);
		ansPnl.add(pnlAnsBody);
		pnlAnsBody.setLayout(new BorderLayout(0, 0));

		JTextArea txtAns = new JTextArea();
		txtAns.setBorder(new EmptyBorder(4, 4, 4, 4));
		txtAns.setEditable(false);
		pnlAnsBody.add(txtAns);
		txtAns.setLineWrap(true);
		txtAns.setWrapStyleWord(true);
		txtAns.setText(a_body);
	}

	public String getQ_author() {
		return q_author;
	}

	public void setQ_author(String q_author) {
		this.q_author = q_author;
	}

	public String getQ_body() {
		return q_body;
	}

	public void setQ_body(String q_body) {
		this.q_body = q_body;
	}

	public String getA_author() {
		return a_author;
	}

	public void setA_author(String a_author) {
		this.a_author = a_author;
	}

	public String getA_body() {
		return a_body;
	}

	public void setA_body(String a_body) {
		this.a_body = a_body;
	}

	public JPanel getPanel() {
		return content;
	}

	public GridBagConstraints getGridBag() {
		return gbc_content;
	}
}
