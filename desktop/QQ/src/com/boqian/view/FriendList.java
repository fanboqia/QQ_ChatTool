package com.boqian.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

/**
 * 好友信息列表
 * 
 * @author boqianfan
 *
 */
public class FriendList extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FriendList dialog = new FriendList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FriendList() {
		setBounds(100, 100, 200, 600);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 200, 578);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 188, 52);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(5, 5));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel(new ImageIcon("img/arrow.png"));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(5, 5));

		JLabel lblNewLabel_1 = new JLabel("   纤 尘～");
		panel_2.add(lblNewLabel_1, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("   一生之际在于勤！");
		panel_2.add(lblNewLabel_2, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(6, 538, 188, 34);
		contentPanel.add(panel_4);
		panel_4.setLayout(null);

		JButton btnNewButton = new JButton("设置");
		btnNewButton.setBounds(0, 6, 51, 29);
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("查找");
		btnNewButton_1.setBounds(49, 6, 51, 29);
		panel_4.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("退出");
		btnNewButton_2.setBounds(118, 6, 64, 29);
		panel_4.add(btnNewButton_2);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 70, 188, 462);
		contentPanel.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);

		scrollPane.getViewport().add(new HaoYouListJpanel());

		// JPanel panel_5 = new JPanel();
		// tabbedPane.addTab("New tab", null, panel_5, null);
	}
}
