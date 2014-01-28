package org.jakerunzer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class AmaGUI {

	private Redditama r_ama;

	private JFrame frmRedditAmaViewer;
	private JTextField txtUrl;
	private JComboBox comSort;
	private JPanel pnlBottom;
	private JPanel pnlContent;
	private JScrollPane scrollPane;
	private JPanel test;
	private JLabel lblError;
	private JLabel lblAuthor;
	private JTextArea txtTitle;
	private JLabel lblReddit;
	private JPanel pnlAuthor;
	private JPanel pnlReddit;
	private JPanel pnlTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AmaGUI window = new AmaGUI();
					window.frmRedditAmaViewer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AmaGUI() {
		initComponents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {
		frmRedditAmaViewer = new JFrame();
		frmRedditAmaViewer.setTitle("Reddit AMA Viewer");
		frmRedditAmaViewer.setBounds(100, 100, 1000, 800);
		frmRedditAmaViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmRedditAmaViewer.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmRedditAmaViewer.dispose();
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		JPanel pnlUrl = new JPanel();
		pnlUrl.setBackground(Color.WHITE);
		pnlUrl.setForeground(Color.WHITE);
		pnlUrl.setBorder(new EmptyBorder(10, 5, 5, 5));
		frmRedditAmaViewer.getContentPane().add(pnlUrl, BorderLayout.NORTH);
		GridBagLayout gbl_pnlUrl = new GridBagLayout();
		gbl_pnlUrl.columnWidths = new int[] { 84, 124, 84, 85, 0 };
		gbl_pnlUrl.rowHeights = new int[] { 29, 0, 0 };
		gbl_pnlUrl.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_pnlUrl.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnlUrl.setLayout(gbl_pnlUrl);

		JLabel lblAmaUrl = new JLabel("AMA URL");
		lblAmaUrl.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblAmaUrl = new GridBagConstraints();
		gbc_lblAmaUrl.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmaUrl.gridx = 0;
		gbc_lblAmaUrl.gridy = 0;
		pnlUrl.add(lblAmaUrl, gbc_lblAmaUrl);

		txtUrl = new JTextField();
		GridBagConstraints gbc_txtUrl = new GridBagConstraints();
		gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUrl.insets = new Insets(0, 0, 5, 5);
		gbc_txtUrl.gridx = 1;
		gbc_txtUrl.gridy = 0;
		pnlUrl.add(txtUrl, gbc_txtUrl);
		txtUrl.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtUrl.setColumns(10);
		comSort = new JComboBox();
		GridBagConstraints gbc_comSort = new GridBagConstraints();
		gbc_comSort.anchor = GridBagConstraints.WEST;
		gbc_comSort.insets = new Insets(0, 0, 5, 5);
		gbc_comSort.gridx = 2;
		gbc_comSort.gridy = 0;
		pnlUrl.add(comSort, gbc_comSort);
		comSort.setToolTipText("How to sort the comments");
		comSort.setModel(new DefaultComboBoxModel(new String[] { "top", "new",
				"hot", "cont", "old", "best" }));

		JButton btnLoad = new JButton("Get AMA");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoad.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLoad.gridx = 3;
		gbc_btnLoad.gridy = 0;
		pnlUrl.add(btnLoad, gbc_btnLoad);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblError.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.insets = new Insets(0, 0, 0, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 1;
		pnlUrl.add(lblError, gbc_lblError);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getAMA();
			}
		});

		pnlBottom = new JPanel();
		pnlBottom.setBackground(Color.WHITE);
		frmRedditAmaViewer.getContentPane().add(pnlBottom, BorderLayout.CENTER);
		pnlBottom.setLayout(new BorderLayout(0, 0));

		JPanel pnlPostInfo = new JPanel();
		pnlPostInfo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null,
				null, null, null));
		pnlPostInfo.setBackground(Color.WHITE);
		pnlBottom.add(pnlPostInfo, BorderLayout.NORTH);
		pnlPostInfo.setLayout(new BorderLayout(0, 0));

		pnlReddit = new JPanel();
		pnlReddit.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) pnlReddit.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlPostInfo.add(pnlReddit, BorderLayout.NORTH);

		lblReddit = new JLabel("Reddit AMA");
		lblReddit.setBackground(Color.WHITE);
		pnlReddit.add(lblReddit);
		lblReddit.setFont(new Font("Lucida Fax", Font.PLAIN, 20));

		pnlTitle = new JPanel();
		pnlTitle.setBorder(new LineBorder(Color.LIGHT_GRAY));
		pnlPostInfo.add(pnlTitle, BorderLayout.CENTER);
		pnlTitle.setLayout(new BorderLayout(0, 0));

		txtTitle = new JTextArea();
		pnlTitle.add(txtTitle);
		txtTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		txtTitle.setText("TestTestTestvTestTestTestTest");
		txtTitle.setBorder(new EmptyBorder(2, 5, 2, 2));
		txtTitle.setLineWrap(true);
		txtTitle.setWrapStyleWord(true);

		pnlAuthor = new JPanel();
		pnlAuthor.setBorder(new EmptyBorder(1, 20, 0, 0));
		pnlAuthor.setBackground(Color.WHITE);
		pnlPostInfo.add(pnlAuthor, BorderLayout.SOUTH);
		pnlAuthor.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblAuthor = new JLabel("Author");
		pnlAuthor.add(lblAuthor);
		lblAuthor.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblAuthor.setBackground(Color.WHITE);

		pnlContent = new JPanel();
		pnlContent.setBackground(Color.WHITE);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setAutoscrolls(true);
		pnlBottom.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(pnlContent);
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));

		/*
		 * COMMENTED OUT CODE IS USED FOR TESTING OF THE QUESTION/ANSWER LOOK
		 */

		// test = new JPanel();
		// test.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		// pnlContent.add(test);
		// test.setLayout(new BorderLayout(0, 0));
		//
		// JPanel quesPnl = new JPanel();
		// quesPnl.setBorder(new LineBorder(Color.LIGHT_GRAY));
		// quesPnl.setBackground(Color.WHITE);
		// test.add(quesPnl, BorderLayout.NORTH);
		// quesPnl.setLayout(new BorderLayout(0, 0));
		//
		// JPanel pnlQ = new JPanel();
		// pnlQ.setBackground(Color.WHITE);
		// pnlQ.setBorder(new EmptyBorder(0, 5, 0, 5));
		// quesPnl.add(pnlQ, BorderLayout.WEST);
		// pnlQ.setLayout(new BorderLayout(0, 0));
		//
		// JLabel lblQ = new JLabel("Q");
		// lblQ.setBackground(Color.WHITE);
		// lblQ.setAlignmentX(Component.CENTER_ALIGNMENT);
		// pnlQ.add(lblQ);
		// lblQ.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		//
		// JPanel pnlQuesBody = new JPanel();
		// pnlQuesBody.setBackground(Color.WHITE);
		// pnlQuesBody.setBorder(new EmptyBorder(5, 5, 5, 5));
		// quesPnl.add(pnlQuesBody);
		// pnlQuesBody.setLayout(new BorderLayout(0, 0));
		//
		// JTextArea txtQues = new JTextArea();
		// txtQues.setAlignmentX(Component.LEFT_ALIGNMENT);
		//
		// txtQues.setEditable(false);
		// pnlQuesBody.add(txtQues);
		// txtQues.setWrapStyleWord(true);
		// txtQues.setLineWrap(true);
		// txtQues.setBorder(new EmptyBorder(4, 4, 4, 4));
		// txtQues.setText("PostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPostPost");
		//
		// JSeparator separator = new JSeparator();
		// separator.setBorder(new EmptyBorder(0, 0, 0, 0));
		// separator.setMinimumSize(new Dimension(0, 10));
		// separator.setBackground(Color.WHITE);
		// separator.setForeground(Color.GREEN);
		// test.add(separator, BorderLayout.CENTER);
		//
		// JPanel ansPnl = new JPanel();
		// ansPnl.setBorder(new EmptyBorder(0, 20, 0, 0));
		// ansPnl.setBackground(Color.WHITE);
		// test.add(ansPnl, BorderLayout.SOUTH);
		// ansPnl.setLayout(new BorderLayout(0, 0));
		//
		// JPanel pnlA = new JPanel();
		// pnlA.setBackground(Color.WHITE);
		// ansPnl.add(pnlA, BorderLayout.WEST);
		//
		// JLabel lblA = new JLabel("A");
		// lblA.setBorder(null);
		// lblA.setBackground(Color.WHITE);
		// lblA.setAlignmentX(Component.CENTER_ALIGNMENT);
		// pnlA.add(lblA);
		// lblA.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		//
		// JPanel pnlAnsBody = new JPanel();
		// pnlAnsBody.setBackground(Color.WHITE);
		// ansPnl.add(pnlAnsBody);
		// pnlAnsBody.setLayout(new BorderLayout(0, 0));
		//
		// JTextArea txtAns = new JTextArea();
		// txtAns.setBorder(new EmptyBorder(4, 4, 4, 4));
		// txtAns.setEditable(false);
		// pnlAnsBody.add(txtAns);
		// txtAns.setLineWrap(true);
		// txtAns.setWrapStyleWord(true);
		// txtAns.setText("PostPostPostPostPostPost");

		resetContent();
	}

	/**
	 * Reset the GUI removing AMA details
	 */
	private void resetContent() {
		lblAuthor.setText("");
		txtTitle.setText("");
		lblError.setText("");
	}

	/**
	 * Sets the error message in the GUI
	 */
	private void errorContent() {
		resetContent();
		lblError.setText("Error Getting Reddit AMA");
	}

	/**
	 * Gets the AMA and updates the GUI
	 */
	private void getAMA() {
		Runnable getAma = new Runnable() {
			public void run() {
				String url = txtUrl.getText();
				String sort = (String) comSort.getSelectedItem();
				r_ama = new Redditama(url, sort);
				if (r_ama.fetchAMA()) {
					r_ama.addToGUI(pnlContent);
					lblError.setText("");
					lblAuthor.setText(r_ama.getOp_author());
					txtTitle.setText(r_ama.getOp_post());
				} else {
					errorContent();
					pnlContent.removeAll();
				}
			}
		};
		getAma.run();
	}
}
