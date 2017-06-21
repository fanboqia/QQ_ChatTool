package com.boqian.view.service;
/**
 *通讯服务 与服务器保持链接状态
 *1.处理好友在线实时更新
 *2.更新好友状态，5秒钟一次
 *3.登陆验证
 *4.退出账户
 * @author boqianfan
 *
 */

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.boqian.view.util.Config;

import net.sf.json.JSONObject;

public class NetService implements Runnable {

	// 饿汉单例
	private static NetService netService = new NetService();

	private NetService() {

	}

	public static NetService getNetService() {
		return netService;
	}

	// 准备与服务器保持长时间的通讯
	@Override
	public void run() {

		try {
			
			System.out.println("成功登陆客户端");
			//////////////好友信息获得
			//*******O to 0******
			output.write("U0001".getBytes());
			output.flush();
			System.out.println("U0001 code has been send!");
			byte[] bytes = new byte[10240];
			int len = input.read(bytes);
			System.out.println("客户端开始接收服务器数据U0001");
			String json_str = new String(bytes, 0, len);			
			System.out.println("好友信息:"+json_str);
			///////////////
			
			//解析好友列表
			Config.jiexi_haoyou_json_data(json_str);
			
			System.out.println("start to get 个人资料");
			///////////////////个人资料获得
			output.write("U0003".getBytes());
			output.flush();
			len = input.read(bytes);
			Config.geren_json_data  = new String(bytes, 0, len);
			System.out.println("个人资料:"+Config.geren_json_data);
			///////////////////
			
			
			//好友在线的实时更新
			while (run) {
				
				output.write("U0002".getBytes());
				output.flush();
				input.read();
				output.write(Config.haoyou_liebiao_data.getBytes());
				output.flush();
				
				
				len = input.read(bytes);
				String online = new String(bytes, 0, len);
				System.out.println("在线账户:"+online);
				Config.haoyou_online = online;
				try{
				Config.haoYouListJpanel.haoyouOnline();
				}catch (Exception e) {
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			run = false;
		}

	}

	private Socket socket = null;
	private InputStream input = null;
	private OutputStream output = null;
	private Thread thread = null;
	private boolean run = false;// *******迷

	public JSONObject login() throws UnknownHostException, IOException {
		socket = new Socket(Config.IP, Config.LOGIN_PORT);
		input = socket.getInputStream();
		output = socket.getOutputStream();
		// *************config.password***************
		String json_str = "{\"username\":" + "\"" + Config.username + "\"" + "," + "\"password\":" + "\""
				+ Config.password + "\"" + "}";

		// 开始与服务器传递消息
		output.write(json_str.getBytes());
		output.flush();

		// 等待服务器回执消息
		byte[] bytes = new byte[1024];
		int len = input.read(bytes);

		json_str = new String(bytes, 0, len);
		JSONObject jsonObject = JSONObject.fromObject(json_str);

		// 如果是0就是成功
		if (jsonObject.getInt("status") == 0) {
			// 开始持续网络服务
			if (thread != null) {
				// 询问线程是否活着
				if (thread.getState() == Thread.State.RUNNABLE) {
					run = false;// 终止线程
					try {
						thread.stop();
					} catch (Exception e) {
					}
				}

			}
			// 重新开启线程服务器保持链接
			thread = new Thread(this);
			run = true;
			thread.start();
		}
		return jsonObject;
	}
}
