package cn;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

class Hello implements Printable {
	public int print(Graphics g ,PageFormat pf ,int pageIndex){
		if(pageIndex != 0 ) return NO_SUCH_PAGE;//ֹͣ��ӡ��
		g.drawString("Hello World!",100,100);//��ֽ�ϻ��ַ�����
		return 1;//������ӡ��
	}
}

