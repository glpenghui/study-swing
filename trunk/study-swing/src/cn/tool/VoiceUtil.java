package cn.tool;

import java.applet.Applet;
import java.awt.Toolkit;

public class VoiceUtil extends Applet{

	public static void main(String[] args) {
		try {
//			Toolkit.getDefaultToolkit().beep();
//			InputStream in = new FileInputStream ("alert_msg.au"); // �� �� һ �� �� �� �� �� �� �� Ϊ �� ��
//			AudioStream as = new AudioStream (in); // �� �� �� �� �� �� һ ��AudioStream �� �� 
//			AudioPlayer.player.start (as); //��player�� ��AudioPlayer �� һ �� ̬ �� Ա �� �� �� �� �� �� 
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
