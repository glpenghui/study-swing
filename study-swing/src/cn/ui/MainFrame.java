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
 * 登陆窗体
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
	
	JFrame jf = new JFrame("订单复核");
	JPanel jp = new JPanel();
	JTable table;      //定义二维数组作为表格数据      
	JLabel orderSnT = new JLabel("订单号:"); 
	JLabel shippingSnT = new JLabel("运单号:");
	JLabel productSnT = new JLabel("商品形条码:");
	
	static JTextField orderSnIn = new JTextField(12);
	static JTextField shippingSnIn = new JTextField(12);
	static JTextField productSnIn = new JTextField(12);
	
//	JButton submit = new JButton("提交扫描");
	JButton cancel = new JButton("重置");
	JButton mark = new JButton("标记为异常订单");
	
	JScrollPane jsp;
	
	
	
	public MainFrame(int adminId,String userName){
		this.adminId = adminId;
		
		jf.setTitle(userName+"，欢迎您使用订单复核！");
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
		
		//注册事件
		orderInEnter();
		shippingInEnter();
		productSnInEnter();
		cancelClickEvent();
		markClickEvent();
		
		System.out.println("===============复核窗口初始化成功！");
	}
	
	
    
	
	//order按enter事件
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
			    			JOptionPane.showMessageDialog(null,"订单号不能为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
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
		//以二维数组和一维数组来创建一个JTable对象          
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
			     // 重写isCellEditable()方法，让tblProduct不可编辑
			     public boolean isCellEditable(int row, int column) {
			      return false;
			     }
			    };
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);  
			//将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来         
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
	//order按enter事件
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
				    			JOptionPane.showMessageDialog(null,"运单号不能为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            productSnIn.requestFocus();
				        }  
				}
			});
		}
		
		//商品条形吗按enter事件
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
				    			JOptionPane.showMessageDialog(null,"订单号不能为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            String shippingSn = shippingSnIn.getText();
				            if(shippingSn.equals("")){
				            	VoiceUtil.singSong();
				    			JOptionPane.showMessageDialog(null,"运单号不能为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
				    			return;
				    		}
				            String productSn = productSnIn.getText();
				            if(productSn.equals("")){
				            	VoiceUtil.singSong();
				    			JOptionPane.showMessageDialog(null,"商品条形码不能为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null,"该订单内没有此商品", "提示",JOptionPane.INFORMATION_MESSAGE);
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
				int response=JOptionPane.showOptionDialog(this, "订单中的商品全部复核完成，是否自动提交扫描记录？", "提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"确定","取消"}, "确定");
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
				JOptionPane.showMessageDialog(null,"该商品已经扫描完成，无需重复扫描！", "提示",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
		}else{
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"请查看商品列表", "提示",JOptionPane.INFORMATION_MESSAGE);
			orderSnIn.requestFocus();
		}
		System.out.println(list);
	}

	private void recheck(){
		List<OrderCheckVo> l = new ArrayList<OrderCheckVo>();
		if(list.size()==0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"复核失败，没有需要复核的商品!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(list.get(0).getOrderSn())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"复核失败，订单号不能为空!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(list.get(0).getShippingSn())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"复核失败，运单号不能为空!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if(StringUtil.isEmpty(list.get(0).getPickNo())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"复核失败，拣货号为空!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//过滤掉垃圾数据（复核数为零，或者SubId为零）
		for(OrderCheckVo vo : list){
			if(!StringUtil.isEmpty(vo.getQc_num()) && !StringUtil.isEmpty(vo.getSubId())){
				l.add(vo);
			}
		}
		if(l.size()==0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"复核失败，没有需要复核的商品!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//拣货单号检查
		if(new PreAction().filter(l.get(0).getPickNo())==null){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"拣货单号不存在!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//检查商品复核数量是否正确
//		
//				$product_list = $this->order_recheck_model->get_order_product_info($order_sn);
//				if (empty($product_list)) 
//					sys_msg('复核失败，订单没有需要复核的商品',1);
//				foreach ($product_list as $product) {
//					$product_arr[$product->sub_id] = $product->product_number;
//				}
//				if (array_diff_assoc($product_arr,$recheck_arr)) 
//					sys_msg('复核失败，该订单还有未复核的商品数量',1);
//				
		
		
		VoiceUtil.singSong();
		JOptionPane.showMessageDialog(null,"复核成功！", "提示",JOptionPane.INFORMATION_MESSAGE);
		reset();
		updateWeight(l,adminId);
		
	}	
	
	public void updateWeight(List<OrderCheckVo> l,int adminId){
		VoiceUtil.singSong();
		JOptionPane.showMessageDialog(null,"重量为0。请把包裹放到秤盘上！", "提示",JOptionPane.INFORMATION_MESSAGE);
		Double weight = WeightUtil.getWeight();
		if(weight==0 || weight<0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"订单重量未获取，请重新获取", "提示",JOptionPane.INFORMATION_MESSAGE);
			updateWeight(l,adminId);
		}else{
			int mess = 0;
			VoiceUtil.singSong();
//			int response=JOptionPane.showOptionDialog(this, "订单"+list.get(0).getOrderSn()+"  重量为："+weight+"  kg!", "提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"确定","取消复核"}, "确定");
			int response = JOptionUtil.open("订单"+list.get(0).getOrderSn()+"  \n重量为："+weight+"  kg!", new String[]{"确定","取消复核"},"确定");
			if(response==0){
				String message = new PreAction().recheck(l,adminId);
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,message, "提示",JOptionPane.INFORMATION_MESSAGE);
				if(message.equals("复核成功！")){//复核成功后，获取重量，插入数据库，然后打印面单。
					mess = new PreAction().updateWeight(weight,list.get(0).getOrderSn());
				}else{
					return;
				}
			}else if(response==1){ 
				return;
			}
//			JOptionPane.showMessageDialog(null,"该订单重量为："+weight+"kg，更新订单"+list.get(0).getOrderSn()+"的重量！", "提示",JOptionPane.INFORMATION_MESSAGE);
			
			if(mess==0){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,  "出现未知错误，请联系技术人员！","提示",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				Map<String,Object> m = new PreAction().queryShippingByOrder(list.get(0).getOrderSn());
				if(m.get("shipping_id") != null && "2".equals(m.get("shipping_id").toString())){
					//是顺风
					VoiceUtil.singSong();
					JOptionPane.showMessageDialog(null, "订单"+list.get(0).getOrderSn()+"更新重量成功。快递公司是顺丰，打印面单！","提示", JOptionPane.INFORMATION_MESSAGE);
					print(list.get(0).getOrderSn());
				}else{
					VoiceUtil.singSong();
					JOptionPane.showMessageDialog(null, "订单"+list.get(0).getOrderSn()+"更新重量成功。！","提示", JOptionPane.INFORMATION_MESSAGE);
					//非顺丰
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
				JOptionPane.showMessageDialog(null, "出现异常，请联系技术人员！","提示", JOptionPane.INFORMATION_MESSAGE);
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

	//重置事件
	public void cancelClickEvent(){
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				orderSnIn.setText("");
				shippingSnIn.setText("");
				productSnIn.setText("");
		}}); 
	}
	
	//标异订单事件
	public void markClickEvent(){
		mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				mark();
		}}); 
	}
	
	//标异
	private void mark(){
		if(vector == null || vector.size()==0){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"没有需要设置的异常订单!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(orderSnIn.getText().toString())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"没有需要设置的异常订单!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(StringUtil.isEmpty(vector.firstElement().get(12).toString())){
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,"没有需要设置的异常订单!", "提示",JOptionPane.INFORMATION_MESSAGE);
			
			return;
		}
		
		StringBuffer gsn = new StringBuffer("款号：");
		Iterator iter = vector.iterator();
		while(iter.hasNext()){
			Vector v = (Vector)iter.next();
			gsn.append(" ["+v.get(1)+"] ");
		}
		gsn.append("没有通过复核。");
		VoiceUtil.singSong();
		int response=JOptionPane.showOptionDialog(this,gsn+"是否要标记为异常订单？", "提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"确定","取消"}, "确定");
		if(response==0){
			List<Map<String,Object>> pickList = null;
			List<Map<String,Object>> orderList = null;
			
			//拣货单号检查
			if((pickList = new PreAction().filter(vector.firstElement().get(12).toString()))==null){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,"拣货单号不存在!", "提示",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//检查订单是否存在
			if((orderList=new PreAction().filterOrder(orderSnIn.getText().toString()))==null){
				VoiceUtil.singSong();
				JOptionPane.showMessageDialog(null,"该订单不存在!", "提示",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String mess = new PreAction().set_unusual_order(orderSnIn.getText().toString(),vector.firstElement().get(12).toString(),orderList,pickList,adminId,gsn.toString());
			VoiceUtil.singSong();
			JOptionPane.showMessageDialog(null,mess, "提示",JOptionPane.INFORMATION_MESSAGE);
			
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
