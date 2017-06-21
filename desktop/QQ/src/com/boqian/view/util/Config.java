package com.boqian.view.util;

import com.boqian.view.HaoYouListJpanel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Config {

	//服务器地址
	public static final String IP = "192.168.1.100";
	//登陆端口
	public static final int LOGIN_PORT = 4001;
	//注册端口
	public static final int REG_PORT = 4002;
	
	//用户名和密码验证
	public static String username;
	public static String password;
	
	//好友列表 Jpanel
	public static HaoYouListJpanel haoYouListJpanel;
	
	//好友列表信息 JSON
	public static String haoyou_json_data = "[]";
	//个人列表信息 JSON
	public static String geren_json_data = "";
	
	//好友在线列表信息 JSON 
	public static String haoyou_online = "";
	
	//好友信息列表 (uid)
	public static String haoyou_liebiao_data = ""; 
	
	//好友列表分析，取出好友列表值
	public static void jiexi_haoyou_json_data(String haoyou_json_data){
		Config.haoyou_json_data = haoyou_json_data;
		System.out.println("*****haoyou_json_data***:"+ Config.haoyou_json_data);
		JSONArray jsonArray = JSONArray.fromObject(haoyou_json_data);
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			stringBuffer.append(jsonObject.getString("uid"));
			stringBuffer.append(",");
		}
		//attention
		haoyou_liebiao_data = stringBuffer.toString();
	}
}
