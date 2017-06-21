package com.boqian.view;

import javax.swing.JPanel;

import com.boqian.view.util.Config;
import com.mysql.fabric.xmlrpc.base.Value;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.Icon;
import java.util.List;

public class HaoYouListJpanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HaoYouListJpanel() {
		setLayout(null);

		// this.setPreferredSize(new Dimension(0, 61 * 40));

		gengxin();

		Config.haoYouListJpanel = this;
	}

	private Hashtable<String, FaceJpanel> list = new Hashtable<>();

	public void haoyouOnline() {
		String zaixianliebiao = Config.haoyou_online;

		String[] uids = zaixianliebiao.split(",");

		Set<String> keys = list.keySet();

		// 全部下线
		for (String string : keys) {
			list.get(string).setOnline(false);
		}

		if (!zaixianliebiao.trim().equals("notFound") && !zaixianliebiao.trim().equals("")) {
			// 选择上线
			for (String uid : uids) {
				if (!uid.trim().equals("")) {
					FaceJpanel faceJpanel = list.get(uid);
					faceJpanel.setOnline(true);
				}
			}
		}

		Collection<FaceJpanel> faceJpanels = list.values();
		List<FaceJpanel> list1 = new ArrayList<FaceJpanel>(faceJpanels);
		Collections.sort(list1);

		this.removeAll();

//		int i = 0;
//		for (FaceJpanel faceJpanel : list1) {
//			faceJpanel.setBounds(0, i++ * 61, 546, 63);
//			this.add(faceJpanel);
//		}

		this.setPreferredSize(new Dimension(0, 61 * list.size()));
		this.updateUI();
	}

	public void gengxin() {
		// 好友列表
		String haoyouliebiao = Config.haoyou_json_data;
		// 在线列表
		String zaixianliebiao = Config.haoyou_online;
		
		System.out.println("好友信息(gengxin()):" + Config.haoyou_json_data);
		
		JSONArray jsonArray = JSONArray.fromObject(haoyouliebiao);

		if (list.size() == 0) {// 第一次加载列表

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				list.put(jsonObject.getString("uid"), new FaceJpanel(jsonObject.getString("img"),
						jsonObject.getString("netname"), jsonObject.getString("info"), jsonObject.getString("uid")));
			}

		} else {// 已有列表数据

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String uid = jsonObject.getString("uid");

				FaceJpanel faceJpanel = (FaceJpanel)list.get(uid);
				if (faceJpanel != null) {// 已经存在

					faceJpanel.setImage(jsonObject.getString("img"));
					faceJpanel.setInfo(jsonObject.getString("info"));
					faceJpanel.setNetName(jsonObject.getString("netname"));

				} else { // 不存在
					list.put(jsonObject.getString("uid"),
							new FaceJpanel(jsonObject.getString("img"), jsonObject.getString("netname"),
									jsonObject.getString("info"), jsonObject.getString("uid")));
				}
			}
		}

		// 好友在线更新
		haoyouOnline();
	}

}
