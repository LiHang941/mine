package xyz.lihang.INF;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * ���ö���
 * 
 * ������ʾAreaLabel  �� ������ɫ
 * */

public class AreaLabelColorProperties {
	private Map <Integer,Color > map =new HashMap<Integer,Color>();
	
	private static AreaLabelColorProperties alcp = null;
	/**
	 * ��ȡ�ö���ʵ��
	 * */
	public static AreaLabelColorProperties getInstance(){
		if(alcp == null)
			alcp =new AreaLabelColorProperties();
		return alcp;
	}
	
	
	
	private AreaLabelColorProperties(){
		map.put(-1, Color.BLACK);   //��ɫ
		map.put(0, Color.BLACK);   //��ɫ
		map.put(1, Color.BLACK);   //��ɫ
		map.put(2, Color.GRAY);	   //��ɫ
		map.put(3, Color.DARK_GRAY );  //ǳ��ɫ
		map.put(4, Color.GREEN);      //��ɫ
		map.put(5, Color.ORANGE );  //�ۻ�ɫ
		map.put(6, Color.YELLOW ); //��ɫ
		map.put(7, Color.MAGENTA); //���ɫ
		map.put(8, Color.RED);
		map.put(1111, Color.RED);  //Label  ��ը��  ��ʾ����ɫ
		
	}
	/**
	 * ��ȡ��ɫ
	 * */
	public Color getColor (int key){
		return map.get(key);
	}
	
		
}
