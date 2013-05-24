package cn.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 从称重机获取 包裹重量
 * @author penghui.li
 *
 */
public class WeightUtil {

	public static Double getWeight() {
		try {
			int mark = 0;
			Thread.sleep(1000);
			String weight = RFIDReader.weight;
			Map<String, Integer> map = new HashMap<String, Integer>();
			if (weight != null &&  !"".equals(weight)) {
			while (mark < 10) {
					if (map.get(weight) == null || map.get(weight) == 0) {
						map.put(weight, 1);
					} else {
						map.put(weight, map.get(weight) + 1);
					}
					
					Thread.sleep(50);
					weight = RFIDReader.weight;
					System.out.println(weight);
					mark++;
				}
				
			}

			System.out.println(workByKeySet(map) + "----max");
			return Double.parseDouble(weight.substring(0,weight.indexOf("kg")));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.000;

	}

	public static String workByKeySet(Map<String, Integer> map) {
		int mark = 0;
		String weight = "";
		Set<String> key = map.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			int tmp = map.get(s);
			if (tmp > mark) {
				mark = tmp;
				weight = s;
			}
		}
		return weight;
	}

	public static void main(String[] args) {
		System.out.println(new WeightUtil().getWeight());
	}
}
