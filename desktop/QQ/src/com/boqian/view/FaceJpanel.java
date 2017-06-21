package com.boqian.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.boqian.view.util.Config;

public class FaceJpanel extends JPanel implements Comparable<FaceJpanel>{
	
	private String image;
	private String netname;
	private String info;
	private String uid;
	private JLabel lb_face = null;
	private JLabel lb_info;
	private JLabel lb_netname;
	
	public FaceJpanel(String image,String netname,String info, String uid){
		this.image = image;
		this.info = info;
		this.uid = uid;
		this.netname = netname;
		setLayout(null);

		lb_face.setBounds(6, 6, 48, 48);
		add(lb_face);
		//lb_face.setText("face");
		setImage(image);
		
		lb_netname = new JLabel(netname);
		lb_netname.setBounds(66, 6, 356, 16);
		add(lb_netname);
		lb_netname.setFont(new Font("Bangla Sangam MN", Font.BOLD, 13));
		
		lb_info = new JLabel(info);
		lb_info.setBounds(64, 34, 358, 16);
		add(lb_info);
		
	}
	
	public void setImage(String image){
		if(image.equals("def")){
			image = "0";
		}
		
		if(online){//在线为彩色
			lb_face.setIcon(new ImageIcon("face0/"+image+".png"));
		}else{//离线为黑白
			lb_face.setIcon(new ImageIcon("face1/"+image+".png"));
		}
		
		this.image = image;
	}
	
	public void setNetName(String netname){
//		if(netname.equals(""))
//			netname = "无";
		
		lb_netname.setText(netname);
		this.netname = netname;
	}
	
	public void setInfo(String info){
		lb_info.setText(info);
		this.info = info;
	}
	
	private boolean online = false;
	
	public void setOnline(boolean online){
		this.online = online;
		
		if(online){//在线为彩色
			lb_face.setIcon(new ImageIcon("face0/"+image+".png"));
		}else{//离线为黑白
			lb_face.setIcon(new ImageIcon("face1/"+image+".png"));
		}
	}
	
	public int compareTo(FaceJpanel online){
		if(online.online){
			return 1;
		}else if(this.online){
			return -1;
		}
		return 0;
	}
}
