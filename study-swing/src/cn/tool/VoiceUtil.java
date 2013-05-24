package cn.tool;

import java.applet.Applet;
import java.awt.Toolkit;

public class VoiceUtil extends Applet{

	public static void main(String[] args) {
		try {
//			Toolkit.getDefaultToolkit().beep();
//			InputStream in = new FileInputStream ("alert_msg.au"); // 打 开 一 个 声 音 文 件 流 作 为 输 入
//			AudioStream as = new AudioStream (in); // 用 输 入 流 创 建 一 个AudioStream 对 象 
//			AudioPlayer.player.start (as); //“player” 是AudioPlayer 中 一 静 态 成 员 用 于 控 制 播 放 
//			AudioPlayer.player.stop (as);
//			FileInputStream fileau = new FileInputStream("alert_msg.ogg");
//			AudioStream as = new AudioStream(fileau);
//			AudioPlayer.player.start(as);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void singSong(){
		Toolkit.getDefaultToolkit().beep();
	}

}
