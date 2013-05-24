package cn.data;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.todo.Launcher;
import cn.tool.DateUtil;
import cn.tool.StringUtil;
import cn.vo.OrderCheckVo;

public class PreAction {
	
	/**
	 * 
	 * @param orderSn
	 * @return
	 */
	public boolean checkOrderRecheck(String orderSn){
		String sql = "SELECT is_pick,pick_admin,qc_admin,is_qc,odd as odd1 from baby.ty_order_info where order_sn='"+orderSn+"'";
		String sqlCount = "SELECT count(*) from baby.ty_order_info where order_sn='"+orderSn+"'";
		try{
			System.out.println(sql);
			int count  = Launcher.baby_db.queryForInt(sqlCount);
			if(count>0){
				Map<String,Object> resultMap = Launcher.baby_db.queryForMap(sql);
				System.out.println(resultMap.get("odd1").toString());
				if("true".equals(resultMap.get("odd1").toString())){
					JOptionPane.showMessageDialog(null,"该订单是异常订单","提示", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				if("1".equals(resultMap.get("is_qc").toString()) && 0<Integer.parseInt(resultMap.get("qc_admin")==null?"0":resultMap.get("qc_admin").toString())){
					JOptionPane.showMessageDialog(null,"该订单已复核","提示", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
				if("0".equals(resultMap.get("is_pick").toString()) && 0>=Integer.parseInt(resultMap.get("pick_admin")==null?"0":resultMap.get("pick_admin").toString())){
					JOptionPane.showMessageDialog(null,"订单还未完成拣货","提示", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}else{
				JOptionPane.showMessageDialog(null,"该订单不存在","提示", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}

	/**
	 * 获取订单下的商品列表
	 * @param orderSn
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector<Vector> queryOrderInfo(String orderSn){
		Vector<Vector> vector = new Vector<Vector>() ;
		String sql = "SELECT pks.sub_id,oi.pick_sn,pks.product_id,pks.color_id,pks.size_id,ppi.product_sn,ppi.provider_productcode,brand.brand_name,pc.color_sn,pc.color_name,ps.size_name,ps.size_sn,ppi.product_name,pks.pick_num,pks.qc_num,pks.product_number,(pks.product_number-pks.qc_num) AS unqc_num,pu.provider_barcode" 
				+" FROM baby.ty_order_info AS oi"
				+" LEFT JOIN baby.ty_pick_sub AS pks ON oi.order_sn = pks.rel_no" 
				+" LEFT JOIN baby.ty_product_info AS ppi ON pks.product_id = ppi.product_id" 
				+" LEFT JOIN baby.ty_product_size AS ps ON pks.size_id = ps.size_id "
				+" LEFT JOIN baby.ty_product_color AS pc ON pks.color_id = pc.color_id" 
				+" LEFT JOIN baby.ty_product_brand AS brand ON brand.brand_id = ppi.brand_id" 
                +" LEFT JOIN baby.ty_product_sub AS pu ON pks.product_id = pu.product_id AND pks.color_id = pu.color_id AND pks.size_id = pu.size_id"
				+" WHERE oi.order_sn = '"+orderSn+"' "
                +" GROUP BY pks.sub_id ";
		String sqlCount = "SELECT count(*) from ("
				+"SELECT pks.sub_id,oi.pick_sn,pks.product_id,pks.color_id,pks.size_id,ppi.product_sn,ppi.provider_productcode,brand.brand_name,pc.color_sn,pc.color_name,ps.size_name,ps.size_sn,ppi.product_name,pks.pick_num,pks.qc_num,pks.product_number,(pks.product_number-pks.qc_num) AS unqc_num,pu.provider_barcode" 
				+" FROM baby.ty_order_info AS oi"
				+" LEFT JOIN baby.ty_pick_sub AS pks ON oi.order_sn = pks.rel_no" 
				+" LEFT JOIN baby.ty_product_info AS ppi ON pks.product_id = ppi.product_id" 
				+" LEFT JOIN baby.ty_product_size AS ps ON pks.size_id = ps.size_id "
				+" LEFT JOIN baby.ty_product_color AS pc ON pks.color_id = pc.color_id" 
				+" LEFT JOIN baby.ty_product_brand AS brand ON brand.brand_id = ppi.brand_id" 
                +" LEFT JOIN baby.ty_product_sub AS pu ON pks.product_id = pu.product_id AND pks.color_id = pu.color_id AND pks.size_id = pu.size_id"
				+" WHERE oi.order_sn = '"+orderSn+"' "
                +" GROUP BY pks.sub_id ) t";
		try{
			System.out.println(sql);
			int count  = Launcher.baby_db.queryForInt(sqlCount);
			if(count>0){
				List<Vector> l = Launcher.baby_db.query(sql.toString(), new RowMapper() {
					public Object mapRow(ResultSet rs, int arg1) throws SQLException {
						Vector vo = new Vector();
						vo.addElement(rs.getString("product_name"));
						vo.addElement(rs.getString("product_sn"));
						vo.addElement(rs.getString("provider_productcode"));
						vo.addElement(rs.getString("brand_name"));
						vo.addElement(rs.getString("provider_barcode"));
						vo.addElement(rs.getString("color_name")+"["+rs.getString("color_sn")+"]");
						vo.addElement(rs.getString("size_name")+"["+rs.getString("size_sn")+"]");
						vo.addElement(rs.getInt("product_number"));
						vo.addElement(rs.getInt("qc_num"));
						vo.addElement(rs.getInt("unqc_num"));
						vo.addElement("已拣货");
						vo.addElement(rs.getInt("sub_id"));
						vo.addElement(rs.getString("pick_sn"));
						
						vo.addElement(rs.getInt("product_id"));
						vo.addElement(rs.getInt("color_id"));
						vo.addElement(rs.getInt("size_id"));
						vo.addElement(rs.getInt("pick_num"));
						
						return vo;
					}
				});
				for(Vector v : l){
					vector.addElement(v);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return vector;
	}
	
	
	/**
	 * 查询拣货号
	 * @param pickNo
	 * @return
	 */
	public List<Map<String,Object>> filter(String pickNo){
		String sqlCount = "select count(*) from baby.ty_pick_info pi "
            +" INNER JOIN baby.ty_shipping_info s ON pi.shipping_id = s.shipping_id WHERE pi.pick_sn = '"+pickNo+"'";
        String sql = "SELECT pi.*, s.shipping_name FROM baby.ty_pick_info pi "
            +" INNER JOIN baby.ty_shipping_info s ON pi.shipping_id = s.shipping_id WHERE pi.pick_sn = '"+pickNo+"'";
        List<Map<String,Object>> resultMap = null;
        try{
			System.out.println(sql);
			int count  = Launcher.baby_db.queryForInt(sqlCount);
			if(count>0){
				resultMap = Launcher.baby_db.queryForList(sql);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
        return resultMap;
        
	}
	
	

	/**
	 * 查询拣货号
	 * @param pickNo
	 * @return
	 */
	public List<Map<String,Object>> filterOrder(String orderSn){
		String sqlCount = "select count(*) from baby.ty_order_info where order_sn='"+orderSn+"'";
        String sql = "select * from baby.ty_order_info where order_sn='"+orderSn+"'";
        List<Map<String,Object>> resultMap = null;
        try{
			System.out.println(sql);
			int count  = Launcher.baby_db.queryForInt(sqlCount);
			if(count>0){
				resultMap = Launcher.baby_db.queryForList(sql);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
        return resultMap;
        
	}
	
	public Map<String,Object> getPrintInfo(String orderSn){
		String sql ="select oi.consignee,oi.tel,oi.mobile,oi.province,"
				+" ri1.region_name as srn,oi.city,ri2.region_name shrn,oi.district,ri3.region_name qrn,oi.address,cc.dist_code,oi.product_num,oi.invoice_no"
				+" from ty_order_info oi"
				+" left join ty_shipping_package_interface cc on oi.order_id=cc.order_id"
				+" left join ty_region_info ri1 on ri1.region_id=oi.province"
				+" left join ty_region_info ri2 on ri2.region_id=oi.city"
				+" left join ty_region_info ri3 on ri3.region_id=oi.district"
				+" where oi.order_sn='"+orderSn+"'";
		String sqlCount ="select count(*) from (select oi.consignee from ty_order_info oi"
				+" where oi.order_sn='"+orderSn+"') t";
		Map<String,Object> resultMap = null;
		try{
			System.out.println(sql);
			int count  = Launcher.baby_db.queryForInt(sqlCount);
			if(count>0){
				resultMap = Launcher.baby_db.queryForMap(sql);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultMap;
	}
	
	public String recheck(List<OrderCheckVo> list,int adminId){
		String message = "";
		int mark = 0;
		  DefaultTransactionDefinition def = new DefaultTransactionDefinition();  
		  def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);  
		  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		  
		  TransactionStatus status = Launcher.txManager.getTransaction(def);  
		  try {
			  for(OrderCheckVo vo : list){
				  int qc = Integer.parseInt(get_pick_sub(vo.getSubId()).get("product_number").toString());
				  if(vo.getQc_num()!=qc){
					  message="复核失败，复核的商品数量错误！";
					  mark++;
					  break;
				  }
				  //更新拣货子表
				  update_pick_sub(vo.getQc_num(),adminId,DateUtil.stringToDate(new Date()),vo.getSubId());
			  }
			  if(mark!=0){
				  Launcher.txManager.rollback(status); 
				  return message;
			  }
			  
			  //更新拣货主表
			  update_pick(list.get(0).getPickNo(),adminId);
			   
			  //更新订单详情表
			  update_order_info(list.get(0).getOrderSn(),list.get(0).getShippingSn().toUpperCase(),1,adminId,DateUtil.stringToDate(new Date()));
			  
			  //事务提交
			  Launcher.txManager.commit(status); 
			  message="复核成功！";
		  } catch (Exception ex) {
			  //事务回滚
			  message="出现未知错误！请联系技术人员！";
			  ex.printStackTrace();
			  Launcher.txManager.rollback(status);  
		  }  
		  return message;
	}
	
	/**
	 * 检查要更新的数据
	 * @param subId
	 * @return
	 */
	public Map<String,Object> get_pick_sub(Integer subId){
		String sql = "select product_number from baby.ty_pick_sub where sub_id="+subId +" limit 1 ";
		System.out.println(sql);
		Map<String,Object> resultMap = Launcher.baby_db.queryForMap(sql);
		return resultMap;
	}
	
	/**
	 * 更新拣货子表
	 * @param qcNum
	 * @param adminId
	 * @param date
	 * @param subId
	 */
	private void update_pick_sub(int qcNum,int adminId,String date,int subId){
		String sql ="update baby.ty_pick_sub set qc_num="+qcNum+",qc_admin="+adminId+",qc_date='"+date+"' where sub_id="+subId;
		System.out.println(sql);
		Launcher.baby_db.update(sql);
	}
	
	/**
	 * 更新拣货主表
	 * @param pickSn
	 * @param adminId
	 */
	private void update_pick(String pickSn,int adminId){
		String sql = "UPDATE baby.ty_pick_info SET over_num = over_num + 1 ,qc_admin = "+adminId+" ,qc_date = '"+DateUtil.stringToDate(new Date())+"' WHERE pick_sn = '"+pickSn+"'";
		System.out.println(sql);
		Launcher.baby_db.update(sql);
	}
	
	private void update_order_info(String orderSn,String shippingNo,int isQc,int adminId,String date){
		String sql = "UPDATE baby.ty_order_info SET invoice_no='"+shippingNo+"',is_qc="+isQc+" ,qc_admin = "+adminId+" ,qc_date = '"+date+"' WHERE order_sn = '"+orderSn+"'";
		System.out.println(sql);
		Launcher.baby_db.update(sql);
	}
	
	
	/**
	 * 登陆
	 * @param adminName
	 * @param password
	 * @return
	 * >0  登陆成功 用户id
	 * -21 没有权限
	 * -31 密码错误
	 * -41 用户名不存在
	 * -51 未知错误
	 */
	public int login(String adminName,String password){
		String sql = "select admin_password,admin_id,action_list from ty_admin_info where admin_name='"+adminName+"' ";
		String sqlCount = "select count(*) from ty_admin_info where admin_name='"+adminName+"' ";
		int result;
		try{
			int count = Launcher.baby_db.queryForInt(sqlCount);
			if(count>0){
				Map<String,Object> map = Launcher.baby_db.queryForMap(sql);
				if(!password.equals(map.get("admin_password").toString())){
					if("-1".equals(map.get("action_list").toString()) || map.get("action_list").toString().contains("menu_order_recheck")){
						result = Integer.parseInt(map.get("admin_id").toString());
					}else{
						result = -21;
					}
				}else{
					result = -31;
				}
			}else{
				result = -41;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			result = -51;
		}
		return result;
	}
	
	public String  set_unusual_order(String orderSn,String pickNo,List<Map<String,Object>> orderList,List<Map<String,Object>> pickList,int userId,String gsn){
		String message = "";
		  DefaultTransactionDefinition def = new DefaultTransactionDefinition();  
		  def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);  
		  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		  
		  TransactionStatus status = Launcher.txManager.getTransaction(def);  
		  try {
			  //更新订单信息表
			  update_order_info(orderSn);
			  
			  //添加订单建议表
			  insert_order_advice(orderList,userId,gsn);
			  //添加订单操作记录
			  insert_order_action(orderList,pickNo,userId,gsn);
			  
			  //更新拣货单主表
			  update_pick_sn(pickList,pickNo);
			  
			  //删除拣货单子表信息
			  delete_pick_sub(pickNo,orderSn);
			  
			  //事务提交
			  Launcher.txManager.commit(status); 
			  message="复核成功！";
		  } catch (Exception ex) {
			  //事务回滚
			  message="出现未知错误！请联系技术人员！";
			  ex.printStackTrace();
			  Launcher.txManager.rollback(status);  
		  }  
		  return message;
	
	}
	
	//更新订单信息表
	private void update_order_info(String orderSn){
		String sql = "update ty_order_info set odd=1,pick_sn='',invoice_no='',is_pick='0',pick_admin='',pick_date='',is_qc='0',qc_admin='',qc_date='' where order_sn='"+orderSn+"'";
		System.out.println(sql);
		Launcher.baby_db.update(sql);
	}
	
	 //添加订单建议表
	private void insert_order_advice(List<Map<String,Object>> orderList,int userId,String gsn){
		if(StringUtil.isEmpty(gsn)) gsn="订单复核异常";
		int order_id = Integer.parseInt(orderList.get(0).get("order_id").toString());
		String sql = "insert into baby.ty_order_advice(order_id,type_id,is_return,advice_content,advice_admin,advice_date)"
				+"values("+order_id+",2,1,'"+gsn+"',"+userId+",'"+DateUtil.stringToDate(new Date())+"')";
		System.out.println(sql);
		Launcher.baby_db.execute(sql);
	}
	
	//添加订单操作记录
	private void insert_order_action(List<Map<String,Object>> orderList,String pickNo,int userId,String gsn){
		int order_id = Integer.parseInt(orderList.get(0).get("order_id").toString());
		String orderStatus = orderList.get(0).get("order_status").toString();
		String shippingStatus = orderList.get(0).get("shipping_status").toString();
		String payStatus = orderList.get(0).get("pay_status").toString();
		String actionNote = "订单复核异常,标记为问题单并从拣货单["+pickNo+"]中剔除。";
		String date = DateUtil.stringToDate(new Date());
		
		String sql = "insert into baby.ty_order_action(order_id,is_return,order_status,shipping_status,pay_status,action_note,create_admin,create_date)" +
				" values("+order_id+",0,'"+orderStatus+"','"+shippingStatus+"','"+payStatus+"','"+actionNote+"',"+userId+",'"+date+"') ";
		System.out.println(sql);
		Launcher.baby_db.execute(sql);
	}
	

	  //更新拣货单主表
	  private void update_pick_sn(List<Map<String,Object>> pickList,String pickNo){
		  int update_total_num = Integer.parseInt(pickList.get(0).get("total_num").toString())-1;
		  if(StringUtil.isEmpty(update_total_num)){
			  String sql = "delete from baby.ty_pick_info where pick_sn='"+pickNo+"'";
			  System.out.println(sql);
			  Launcher.baby_db.execute(sql);
		  }else{
			  String sql = "update baby.ty_pick_info set total_num="+update_total_num+" where pick_sn='"+pickNo+"'";
			  System.out.println(sql);
			  Launcher.baby_db.update(sql);
		  }
	  }
	  
	  //删除拣货单子表信息
	  private void delete_pick_sub(String pickNo,String orderSn){
		  String sql ="delete from baby.ty_pick_sub where pick_sn='"+pickNo+"' and rel_no='"+orderSn+"'";
		  System.out.println(sql);
		  Launcher.baby_db.execute(sql);
	  }
	  
	  public Map<String,Object> queryShippingByOrder(String orderSn){
		  String sql = "select shipping_id,invoice_no from ty_order_info where order_sn='"+orderSn+"'";
		  return Launcher.baby_db.queryForMap(sql);
	  }
	  
	  public int  updateWeight(Double weight,String orderSn){
		  Double weight_unreal=0.0;
		  	if(weight>=1.5){
		  		weight_unreal = weight-0.5;
		  	}else{
		  		weight_unreal = weight;
		  	}
			int message = 0;
			  DefaultTransactionDefinition def = new DefaultTransactionDefinition();  
			  def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);  
			  def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			  
			  TransactionStatus status = Launcher.txManager.getTransaction(def);  
			  try {
				  String sql = "update ty_order_info set order_weight="+weight+",order_weight_unreal="+weight_unreal+" where order_sn='"+orderSn+"'";
				  System.out.println(sql);
				  Launcher.baby_db.update(sql);
				  //事务提交
				  Launcher.txManager.commit(status); 
				  message=1;
			  } catch (Exception ex) {
				  //事务回滚
				  ex.printStackTrace();
				  Launcher.txManager.rollback(status);  
			  }  
			  return message;
		}
	  
}