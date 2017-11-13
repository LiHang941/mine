package xyz.lihang.INF;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置对象
 * 
 * 设置显示AreaLabel  的 内容颜色
 * */

public class AreaLabelColorProperties {
	private Map <Integer,Color > map =new HashMap<Integer,Color>();
	
	private static AreaLabelColorProperties alcp = null;
	/**
	 * 获取该对象实例
	 * */
	public static AreaLabelColorProperties getInstance(){
		if(alcp == null)
			alcp =new AreaLabelColorProperties();
		return alcp;
	}
	
	
	
	private AreaLabelColorProperties(){
		map.put(-1, Color.BLACK);   //黑色
		map.put(0, Color.BLACK);   //黑色
		map.put(1, Color.BLACK);   //黑色
		map.put(2, Color.GRAY);	   //灰色
		map.put(3, Color.DARK_GRAY );  //浅灰色
		map.put(4, Color.GREEN);      //绿色
		map.put(5, Color.ORANGE );  //桔黄色
		map.put(6, Color.YELLOW ); //黄色
		map.put(7, Color.MAGENTA); //洋红色
		map.put(8, Color.RED);
		map.put(1111, Color.RED);  //Label  雷炸了  显示的颜色
		
	}
	/**
	 * 获取颜色
	 * */
	public Color getColor (int key){
		return map.get(key);
	}
	
		
}
