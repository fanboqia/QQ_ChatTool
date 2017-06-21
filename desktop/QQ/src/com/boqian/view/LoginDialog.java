package com.boqian.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import com.boqian.view.service.NetService;
import com.boqian.view.util.Config;

import net.sf.json.JSONObject;
import javax.swing.JPasswordField;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField username;
	private JTextField rtf_registerName;
	private JTextField rtf_code;
	private JTextField rtf_password;
	private JTextField rtf_passwordAgain;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// System.out.println("{\"type\":\"code\",\"username\":\""+config.+"\"}");
		try {
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setBounds(100, 100, 450, 300);
		setTitle("通讯软件");
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);// 一直显示在最上面
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 29);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JLabel lb_phone = new JLabel("手机号:");
			lb_phone.setBounds(25, 103, 61, 16);
			getContentPane().add(lb_phone);
		}

		JLabel lb_email = new JLabel("Email:");
		lb_email.setBounds(25, 131, 61, 16);
		getContentPane().add(lb_email);

		username = new JTextField();
		username.setBounds(80, 98, 313, 55);
		getContentPane().add(username);
		username.setColumns(10);

		JLabel lb_password = new JLabel("密码：");
		lb_password.setBounds(25, 185, 61, 16);
		getContentPane().add(lb_password);

		JButton btn_register = new JButton("注 册");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (LoginDialog.this.getHeight() == 300)
					setBounds(100, 100, 450, 600);
				else if (LoginDialog.this.getHeight() == 600)
					setBounds(100, 100, 450, 300);
				setLocationRelativeTo(null);
			}
		});
		btn_register.setBounds(25, 229, 117, 43);
		getContentPane().add(btn_register);

		JButton loginbutton = new JButton("登 陆");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 用户名和密码
				String username_str = username.getText();
				String password_str = password.getText();

				if (username_str.trim().equals("") || password_str.trim().equals("")) {
					JOptionPane.showMessageDialog(LoginDialog.this, "用户名和密码必须填写!");
					return;
				}
				Config.username = username_str;
				Config.password = password_str;

				try {
					JSONObject json = NetService.getNetService().login();

					// *****STATE to STATUS******//
					if (json.getInt("status") == 0) {
						// JOptionPane.showMessageDialog(LoginDialog.this,
						// "登陆成功！");
						// 登陆成功后显示好友列表，同时要关闭当前窗口
						new FriendList().setVisible(true);
						LoginDialog.this.setVisible(false);
						LoginDialog.this.dispose();
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, json.getString("msg"));
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(LoginDialog.this, "网络链接失败！");
				}
			}
		});
		loginbutton.setBounds(276, 229, 117, 43);
		getContentPane().add(loginbutton);

		JPanel pn_register = new JPanel();
		pn_register.setBorder(
				new TitledBorder(null, "\u6CE8\u518C\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_register.setBounds(25, 284, 377, 239);
		getContentPane().add(pn_register);
		pn_register.setLayout(null);

		JLabel rlb_phone = new JLabel("手机号:");
		rlb_phone.setBounds(6, 39, 61, 16);
		pn_register.add(rlb_phone);

		JLabel rlb_email = new JLabel("Email:");
		rlb_email.setBounds(6, 67, 61, 16);
		pn_register.add(rlb_email);

		rtf_registerName = new JTextField();
		rtf_registerName.setColumns(10);
		rtf_registerName.setBounds(58, 39, 275, 54);
		pn_register.add(rtf_registerName);

		JButton rbtn_send_code = new JButton("发送验证");
		rbtn_send_code.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rtf_registerName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(LoginDialog.this, "注册账号不能为空！");
					return;
				}

				try {
					Socket socket = new Socket(Config.IP, Config.REG_PORT);
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();

					outputStream.write(
							("{\"type\":\"code\",\"username\":\"" + rtf_registerName.getText() + "\"}").getBytes());
					outputStream.flush();
					byte[] bytes = new byte[1024];
					int len = inputStream.read(bytes);
					String json_str = new String(bytes, 0, len);
					System.out.println(json_str);
					JSONObject json = JSONObject.fromObject(json_str);
					if (json.getInt("status") == 0) {
						JOptionPane.showMessageDialog(LoginDialog.this, "验证码发送成功");
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, "验证码发送失败，可能手机号码，email写错了");
					}

					inputStream.close();
					outputStream.close();
					socket.close();

				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		rbtn_send_code.setBounds(216, 99, 117, 29);
		pn_register.add(rbtn_send_code);

		rtf_code = new JTextField();
		rtf_code.setBounds(82, 128, 130, 26);
		pn_register.add(rtf_code);
		rtf_code.setColumns(10);

		JLabel rlb_code = new JLabel("验 证 码：");
		rlb_code.setBounds(6, 133, 61, 16);
		pn_register.add(rlb_code);

		JLabel rlb_password = new JLabel("密 码：");
		rlb_password.setBounds(6, 171, 61, 16);
		pn_register.add(rlb_password);

		JLabel rlb_passwordAgain = new JLabel("确认密码：");
		rlb_passwordAgain.setBounds(6, 204, 80, 16);
		pn_register.add(rlb_passwordAgain);

		rtf_password = new JTextField();
		rtf_password.setColumns(10);
		rtf_password.setBounds(82, 163, 238, 29);
		pn_register.add(rtf_password);

		rtf_passwordAgain = new JTextField();
		rtf_passwordAgain.setColumns(10);
		rtf_passwordAgain.setBounds(82, 196, 238, 29);
		pn_register.add(rtf_passwordAgain);

		JButton rbtn_giveUp = new JButton("放弃");
		rbtn_giveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		rbtn_giveUp.setBounds(35, 535, 117, 37);
		getContentPane().add(rbtn_giveUp);

		JButton rbtn_registerName = new JButton("注册用户");
		rbtn_registerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rtf_registerName.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(LoginDialog.this, "注册账号不能为空！");
					return;
				}
				if (rtf_password.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(LoginDialog.this, "注册密码不能为空！");
					return;
				}
				if (rtf_passwordAgain.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(LoginDialog.this, "注册密码不能为空！");
					return;
				}
				if (rtf_code.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(LoginDialog.this, "验证码不能为空！");
					return;
				}

				if (!(rtf_password.getText().equals(rtf_passwordAgain.getText()))) {
					JOptionPane.showMessageDialog(LoginDialog.this, "两次密码不相同!");
					return;
				}

				try {
					Socket socket = new Socket(Config.IP, Config.REG_PORT);
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();

					outputStream.write(
							("{\"type\":\"reg\",\"username\":\"" + rtf_registerName.getText() + "\",\"password\":\""
									+ rtf_password.getText() + "\",\"code\":\"" + rtf_code.getText() + "\"}")
											.getBytes());
					outputStream.flush();
					byte[] bytes = new byte[1024];
					int len = inputStream.read(bytes);
					String json_str = new String(bytes, 0, len);
					System.out.println(json_str);
					JSONObject json = JSONObject.fromObject(json_str);
					if (json.getInt("status") == 0) {
						JOptionPane.showMessageDialog(LoginDialog.this, "注册成功");
						rtf_registerName.setText("");
						rtf_code.setText("");
						rtf_passwordAgain.setText("");
						rtf_password.setText("");
					} else if (json.getInt("status") == 1) {
						JOptionPane.showMessageDialog(LoginDialog.this, "用户名已存在");
					} else if (json.getInt("status") == 2) {
						JOptionPane.showMessageDialog(LoginDialog.this, "验证码错误请重新获得");
					} else if (json.getInt("status") == 3) {
						JOptionPane.showMessageDialog(LoginDialog.this, "未知错误");
					}

					inputStream.close();
					outputStream.close();
					socket.close();

				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		rbtn_registerName.setBounds(255, 535, 117, 37);
		getContentPane().add(rbtn_registerName);

		password = new JPasswordField();
		password.setBounds(80, 180, 313, 26);
		getContentPane().add(password);
	}
}
