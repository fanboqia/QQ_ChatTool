package com.boqian.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class PersonalData extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalData frame = new PersonalData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonalData() {
		setTitle("个人资料");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 426, 63);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(6, 6, 41, 41);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(69, 6, 351, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(69, 32, 351, 26);
		panel.add(textField_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u4E2A\u4EBA\u8D44\u6599", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 81, 426, 191);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("真实姓名：");
		lblNewLabel_1.setBounds(6, 35, 74, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("出生年月：");
		label_1.setBounds(6, 75, 74, 16);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("备 注：");
		label_2.setBounds(6, 120, 61, 16);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("性别：");
		label_3.setBounds(217, 35, 61, 16);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("年");
		label_4.setBounds(128, 75, 61, 16);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("月");
		label_5.setBounds(217, 75, 61, 16);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("日");
		label_6.setBounds(321, 75, 61, 16);
		panel_1.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setBounds(75, 30, 130, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 118, 326, 55);
		panel_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		rdbtnNewRadioButton_1.setBounds(303, 31, 79, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(75, 71, 52, 27);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(164, 71, 52, 27);
		panel_1.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(266, 71, 52, 27);
		panel_1.add(comboBox_2);
		
		JRadioButton radioButton = new JRadioButton("男");
		radioButton.setBounds(254, 31, 79, 23);
		panel_1.add(radioButton);
	}
}
