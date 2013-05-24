package cn.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import cn.data.PreAction;
import cn.tool.DateUtil;
import cn.tool.JOptionUtil;
import cn.tool.StringUtil;
import cn.tool.TableTitleTool;
import cn.tool.VoiceUtil;
import cn.tool.WeightUtil;
import cn.vo.OrderCheckVo;

/**
 * ��½����
 * @author penghui.li
 *
 */
public class MainFrame extends JFrame{

	@Resource
	private PreAction preAction;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int adminId = 0;
	
	JFrame jf = new JFrame("��������");
	JPanel jp = new JPanel();
	JTable table;      //�����ά������Ϊ�������      
	JLabel orderSnT = new JLabel("������:"); 
	JLabel shippingSnT = new JLabel("�˵���:");
	JLabel productSnT = new JLabel("��Ʒ������:");
	
	static JTextField orderSnIn = new JTextField(12);
	static JTextField shippingSnIn = new JTextField(12);
	static JTextField productSnIn = new JTextField(12);
	
//	JButton submit = new JButton("�ύɨ��");
	JButton cancel = new JButton("����");
	JButton mark = new JButton("���Ϊ�쳣����");
	
	JScrollPane jsp;
	
	
	
	public MainFrame(int adminId,String userName){
		this.adminId = adminId;
		
		jf.setTitle(userName+"����ӭ��ʹ�ö������ˣ�");
		jp.add(orderSnT);jp.add(orderSnIn);
		jp.add(shippingSnT);jp.add(shippingSnIn);
		jp.add(productSnT);jp.add(productSnIn);
//		jp.add(submit);
		jp.add(cancel);
		jp.add(mark);
		jf.add(jp, BorderLayout.NORTH);
		
		jf.pack();          
		jf.setVisible(true);
		jf.setResizable(true);

		jf.setResizable(false);
//		jf.setExtendedState( Frame.MAXIMIZED_BOTH );
//		jf.setBounds(20, 20, 1000, 100);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jf.setSize(1000, 600);
		jf.setLocationRelativeTo(getOwner()); 
		
		//ע���¼�
		orderInEnter();
		shippingInEnter();
		productSnInEnter();
		cancelClickEvent();
		markClickEvent();
		
		System.out.println("===============���˴��ڳ�ʼ���ɹ���");
	}
	
	
    
	
	//order��enter�¼�
	public void orderInEnter(){
		orderSnIn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
			            String orderSn = orderSnIn.getText();  
			            if(orderSn.equals("")){
			            	VoiceUtil.singSong();
			    			JOptionPane.showMessageDialog(null,"�����Ų���Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			    			return;
			    		}
			            queryTable(orderSn);
			            list = new ArrayList<OrderCheckVo>();
			        }  
			}
		});
	}
	private boolean isSf;
	private Vector<Vector> vector;
	@SuppressWarnings("rawtypes")
	public void queryTable(String orderSn){
		//�Զ�ά�����һά����������һ��JTable����          
		boolean bl = new PreAction().checkOrderRecheck(orderSn);
		if(bl){
			if(table != null){
				jsp.remove(table);
				jf.remove(jsp);
			}
			
			vector = new PreAction().queryOrderInfo(orderSn);
			Vector list2 = TableTitleTool.getCheckOrderTableTitle();
			table = new JTable(vector , list2){
			     private static final long serialVersionUID = -8782973485679606547L;
			     // ��дisCellEditable()��������tblProduct���ɱ༭
			     public boolean isCellEditable(int row, int column) {
			      return false;
			     }
			    };
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);  
			//��JTable�������JScrollPane�У�������JScrollPane���ڴ�������ʾ����         
			jsp = new JScrollPane(table);
			
			jf.add(jsp,BorderLayout.CENTER);
			jf.show();	
			
			Map<String,Object> m = new PreAction().queryShippingByOrder(orderSn);
			if(m.get("shipping_id") != null && "2".equals(m.get("shipping_id").toString())){
				shippingSnIn.setText(m.get("invoice_no").toString());
				shippingSnIn.setEditable(false);
				productSnIn.requestFocus();
			}else{
				shippingSnIn.setEditable(true);
				shippingSnIn.setText("");
				shippingSnIn.requestFocus();
			}
			
		}else{
			orderSnIn.setText("");
		}
	}
	//order��enter�¼�
	public void shippingInEnter(){
		shippingSnIn.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					 if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
				            String shippingSn = shippingSnIn.getText();
				            if(shippingSn.equals("")){
				            	VoiceUtil.singSong();
				    			JOptionPane.showMessageDialog(null,"�˵��Ų���Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            productSnIn.requestFocus();
				        }  
				}
			});
		}
		
		//��Ʒ������enter�¼�
		public void productSnInEnter(){
			productSnIn.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					 if (e.getKeyCode() == KeyEvent.VK_ENTER) {  
						 	String orderSn = orderSnIn.getText();  
				            if(orderSn.equals("")){
				            	VoiceUtil.singSong();
				    			JOptionPane.showMessageDialog(null,"�����Ų���Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            String shippingSn = shippingSnIn.getText();
				            if(shippingSn.equals("")){
				            	VoiceUtil.singSong();
				    			JOptionPane.showMessageDialog(null,"�˵��Ų���Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            String productSn = productSnIn.getText();
				            if(productSn.equals("")){
				            	VoiceUtil.singSong();
				    			JOptionPane.showMessageDialog(null,"��Ʒ�����벻��Ϊ�գ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            productSnIn.setText("");
				            opTable(productSn);
				        }  
				}
			});
		}
	
	public List<OrderCheckVo> list = new ArrayList<OrderCheckVo>();
	
	public  void opTable(String productSn){
		if(table!=null){
			int row=table.getRowCount();
			int j = 0;
			int k = 0;
			for(int i=0;i<row;i++){
				
				if(productSn.equals(table.getValueAt(i, 4).toString())){
					if(Integer.parseInt(table.getValueAt(i, 7).toString())!=Integer.parseInt(table.getValueAt(i, 8).toString())){
						table.setValueAt(Integer.parseInt(table.getValueAt(i, 8).toString())+1, i, 8);
						table.setValueAt(Integer.parseInt(table.getValueAt(i, 9).toString())-1, i, 9);
						OrderCheckVo vo = new OrderCheckVo();
						vo.setOrderSn(orderSnIn.getText());
						vo.setShippingSn(shippingSnIn.getText());
						vo.setSubId(Integer.parseInt(table.getValueAt(i, 11).toString()));
						vo.setQc_num(Integer.parseInt(table.getValueAt(i, 8).toString()));
						vo.setUnqc_num(Integer.parseInt(table.getValueAt(i, 9).toString()));
						vo.setPickNo(table.getValueAt(i, 12).toString());
						list.add(vo);
						k++;
						break;
					}
					j++;
				}
			}
			if(j==0 && k==0){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,"�ö�����û�д���Ʒ", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			int m = 0;
			for(int i=0;i<row;i++){			
					if(Integer.parseInt(table.getValueAt(i, 7).toString())!=Integer.parseInt(table.getValueAt(i, 8).toString())){
						m++;
					}
			}
			if(m==0){
				VoiceUtil.singSong();
				int response=JOptionPane.showOptionDialog(this, "�����е���Ʒȫ��������ɣ��Ƿ��Զ��ύɨ���¼��", "��ʾ",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"ȷ��","ȡ��"}, "ȷ��");
				if(response==0){
					recheck();
				}
				else if(response==1){ 
//					System.out.println("2");
					return;
				}
			
			}
			
			if(k==0 && m!=0){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,"����Ʒ�Ѿ�ɨ����ɣ������ظ�ɨ�裡", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
		}else{
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"��鿴��Ʒ�б�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			orderSnIn.requestFocus();
		}
		System.out.println(list);
	}

	private void recheck(){
		List<OrderCheckVo> l = new ArrayList<OrderCheckVo>();
		if(list.size()==0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ�û����Ҫ���˵���Ʒ!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(list.get(0).getOrderSn())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ������Ų���Ϊ��!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(list.get(0).getShippingSn())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ��˵��Ų���Ϊ��!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if(StringUtil.isEmpty(list.get(0).getPickNo())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ������Ϊ��!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//���˵��������ݣ�������Ϊ�㣬����SubIdΪ�㣩
		for(OrderCheckVo vo : list){
			if(!StringUtil.isEmpty(vo.getQc_num()) && !StringUtil.isEmpty(vo.getSubId())){
				l.add(vo);
			}
		}
		if(l.size()==0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"����ʧ�ܣ�û����Ҫ���˵���Ʒ!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//������ż��
		if(new PreAction().filter(l.get(0).getPickNo())==null){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"������Ų�����!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//�����Ʒ���������Ƿ���ȷ
//		
//				$product_list = $this->order_recheck_model->get_order_product_info($order_sn);
//				if (empty($product_list)) 
//					sys_msg('����ʧ�ܣ�����û����Ҫ���˵���Ʒ',1);
//				foreach ($product_list as $product) {
//					$product_arr[$product->sub_id] = $product->product_number;
//				}
//				if (array_diff_assoc($product_arr,$recheck_arr)) 
//					sys_msg('����ʧ�ܣ��ö�������δ���˵���Ʒ����',1);
//				
		
		
		VoiceUtil.singSong();
		JOptionPane.showMessageDialog(null,"���˳ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
		reset();
		updateWeight(l,adminId);
		
	}	
	
	public void updateWeight(List<OrderCheckVo> l,int adminId){
		VoiceUtil.singSong();
		JOptionPane.showMessageDialog(null,"����Ϊ0����Ѱ����ŵ������ϣ�", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
		Double weight = WeightUtil.getWeight();
		if(weight==0 || weight<0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"��������δ��ȡ�������»�ȡ", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			updateWeight(l,adminId);
		}else{
			int mess = 0;
			VoiceUtil.singSong();
//			int response=JOptionPane.showOptionDialog(this, "����"+list.get(0).getOrderSn()+"  ����Ϊ��"+weight+"  kg!", "��ʾ",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"ȷ��","ȡ������"}, "ȷ��");
			int response = JOptionUtil.open("����"+list.get(0).getOrderSn()+"  \n����Ϊ��"+weight+"  kg!", new String[]{"ȷ��","ȡ������"},"ȷ��");
			if(response==0){
				String message = new PreAction().recheck(l,adminId);
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,message, "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				if(message.equals("���˳ɹ���")){//���˳ɹ��󣬻�ȡ�������������ݿ⣬Ȼ���ӡ�浥��
					mess = new PreAction().updateWeight(weight,list.get(0).getOrderSn());
				}else{
					return;
				}
			}else if(response==1){ 
				return;
			}
//			JOptionPane.showMessageDialog(null,"�ö�������Ϊ��"+weight+"kg�����¶���"+list.get(0).getOrderSn()+"��������", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
			if(mess==0){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,  "����δ֪��������ϵ������Ա��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				Map<String,Object> m = new PreAction().queryShippingByOrder(list.get(0).getOrderSn());
				if(m.get("shipping_id") != null && "2".equals(m.get("shipping_id").toString())){
					//��˳��
					VoiceUtil.singSong();
					JOptionPane.showMessageDialog(null, "����"+list.get(0).getOrderSn()+"���������ɹ�����ݹ�˾��˳�ᣬ��ӡ�浥��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
					print(list.get(0).getOrderSn());
				}else{
					VoiceUtil.singSong();
					JOptionPane.showMessageDialog(null, "����"+list.get(0).getOrderSn()+"���������ɹ�����","��ʾ", JOptionPane.INFORMATION_MESSAGE);
					//��˳��
				}
			}
		}
	}
	
	private void print(String orderSn){
		try {	
			JasperReport jasperReport = 
					(JasperReport) JRLoader.loadObjectFromFile("C:/recheck/sf_shipping.jasper");
			Map<String,Object> r =  new PreAction().getPrintInfo(orderSn);
//			new net.sourceforge.barbecue.BarcodeException(orderSn);
			if(r == null){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null, "�����쳣������ϵ������Ա��","��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
			Map parameters = new HashMap();
			parameters.put("invoice_no",StringUtil.objectToString(r.get("invoice_no")));
			parameters.put("s_name",StringUtil.objectToString(r.get("consignee")));
			parameters.put("s_phone",StringUtil.objectToString(r.get("tel")));
			parameters.put("s_mobil",StringUtil.objectToString(r.get("mobile")));
			parameters.put("s_province",StringUtil.objectToString(r.get("srn")));
			parameters.put("s_city",StringUtil.objectToString(r.get("shrn")));
			parameters.put("s_district",StringUtil.objectToString(r.get("qrn")));
			parameters.put("s_address",StringUtil.objectToString(r.get("address")));
			parameters.put("s_target",StringUtil.objectToString(r.get("dist_code")));
			parameters.put("t_count",StringUtil.objectToString(r.get("product_num")));
			parameters.put("u_date",DateUtil.stringToDate(new Date()));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//�����¼�
	public void cancelClickEvent(){
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				orderSnIn.setText("");
				shippingSnIn.setText("");
				productSnIn.setText("");
		}}); 
	}
	
	//���충���¼�
	public void markClickEvent(){
		mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				mark();
		}}); 
	}
	
	//����
	private void mark(){
		if(vector == null || vector.size()==0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"û����Ҫ���õ��쳣����!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(orderSnIn.getText().toString())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"û����Ҫ���õ��쳣����!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(vector.firstElement().get(12).toString())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"û����Ҫ���õ��쳣����!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
		
		StringBuffer gsn = new StringBuffer("��ţ�");
		Iterator iter = vector.iterator();
		while(iter.hasNext()){
			Vector v = (Vector)iter.next();
			gsn.append(" ["+v.get(1)+"] ");
		}
		gsn.append("û��ͨ�����ˡ�");
		VoiceUtil.singSong();
		int response=JOptionPane.showOptionDialog(this,gsn+"�Ƿ�Ҫ���Ϊ�쳣������", "��ʾ",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"ȷ��","ȡ��"}, "ȷ��");
		if(response==0){
			List<Map<String,Object>> pickList = null;
			List<Map<String,Object>> orderList = null;
			
			//������ż��
			if((pickList = new PreAction().filter(vector.firstElement().get(12).toString()))==null){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,"������Ų�����!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//��鶩���Ƿ����
			if((orderList=new PreAction().filterOrder(orderSnIn.getText().toString()))==null){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,"�ö���������!", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String mess = new PreAction().set_unusual_order(orderSnIn.getText().toString(),vector.firstElement().get(12).toString(),orderList,pickList,adminId,gsn.toString());
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,mess, "��ʾ",JOptionPane.INFORMATION_MESSAGE);
			
		}
		else if(response==1){ 
			System.out.println("2");
			return;
		}
		
		
	}
	
	
	private void reset(){
		orderSnIn.setText("");
		shippingSnIn.setText("");
		productSnIn.setText("");
		jf.remove(jsp);
		jf.setExtendedState(JFrame.ICONIFIED); 
		jf.setExtendedState(JFrame.MAXIMIZED_HORIZ); 
		jf.show();
		
	}

	public PreAction getPreAction() {
		return preAction;
	}




	public void setPreAction(PreAction preAction) {
		this.preAction = preAction;
	}
	
}
