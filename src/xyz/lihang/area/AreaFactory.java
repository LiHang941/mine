package xyz.lihang.area;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import xyz.lihang.INF.WindowProperties;
import xyz.lihang.window.AreaPanelCentre;
import xyz.lihang.window.MainWindow;

/**
 * Area����
 * 
 * */
public class AreaFactory {
	/**
	 * �����Area����
	 * */
	private int transverse = 9;

	/**
	 * �����Area����
	 * */
	private int vertical = 9;

	/**
	 * �׵ĸ���
	 * */
	private int mineNumber;

	/**
	 * ά��һ����ά����
	 * */
	private Area[][] AreaSet;

	/**
	 * transverse ������� vertical ������� mineNumber �׵ĸ���
	 * */
	public AreaFactory(int transverse, int vertical, int mineNumber) {
		AreaPanelCentre.getAreaPanelInstance().removeAll();
		
		this.transverse = transverse;
		this.vertical = vertical;
		this.mineNumber = mineNumber;
		AreaSet = new Area[transverse][vertical];
		// ��Areaװ�뼯����

		for (int i = 0; i < AreaSet.length; i++) {
			for (int j = 0; j < AreaSet[i].length; j++) {
				Area area = new Area(new Point(j, i), this);
				AreaSet[i][j] = area;
			}
		}

	}

	/**
	 * ÿ�ε������� �Ϳ�ʼɨ��
	 * */
	public AreaFactory game() {
		ArrayList<Area> arr = getAreaList();
		// ��ʾʣ����
		// �����ӵ�����
		int count = 0;
		for (Area aa : arr) {
			if (aa.getState() == State.FLAG) {
				count++;
			}
		}
		MainWindow
				.getMainWondowInstance()
				.getLabel_Score()
				.setText(
						Integer
								.toString((mineNumber - count) > 0 ? (mineNumber - count)
										: 0));

		// ɨ�������Ƿ��ȫ  
		// ���Ӳ��겢�Ҷ������
		if(count == mineNumber){
			boolean ff =true;
			for (Area aa : arr) {
				if(aa.getState() == State.FLAG && aa.getValue() != -1 ){
						ff=false;
						break;
				}
			}
			if(ff){
				over(true);
			}
		}

		return this;
	}

	/**
	 * ��ʼɨ�׹�����Դ����
	 * */
	public AreaFactory Action() {
		return productionMine().productionArea().addPanel();
	}

	/**
	 * �ȵ�����
	 * 
	 * true Ӯ��
	 * 
	 * false  ����
	 * */
	public AreaFactory over(boolean ff) {
		ArrayList<Area> arr = getAreaList();
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).getAreaButton().setVisible(false);
			arr.get(i).getAreaLabel().setVisible(true);
		}
		String str = (GameTime.GetGameTime()!=null ? GameTime.GetGameTime().over():"0s");
		if(!ff){
			//����
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(),
			WindowProperties.getWindowProperties().getString("gameOver"));
		}else{
			//Ӯ��
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(),
			WindowProperties.getWindowProperties().getString("gameSuccess")+ str );
			
		}
		
		return this;
	}

	/**
	 * ��������
	 * 
	 * */
	private AreaFactory productionMine() {
		ArrayList<Area> arr = getAreaList();
		for (int i = 0; i < this.mineNumber; i++) {
			int ran =new Random().nextInt(arr.size()) ;
			arr.get(ran).setValue(-1);
			arr.remove(ran);
		}
		
		return this;
	}
	
	
	
	/**
	 * ���ؼ���ӵ������
	 * 
	 * */
	private AreaFactory addPanel() {
		ArrayList<Area> arr = getAreaList();
		for (int i = 0; i < arr.size(); i++) {
			AreaPanelCentre.getAreaPanelInstance().add(
					arr.get(i).getAreaButton());
			AreaPanelCentre.getAreaPanelInstance().add(
					arr.get(i).getAreaLabel());
		}
		JLabel jl = new JLabel();
		jl.setVisible(false);
		AreaPanelCentre.getAreaPanelInstance().add(jl);
		return this;
	}

	/**
	 * ����Area
	 * */
	private AreaFactory productionArea() {
		ArrayList<Area> arr = getAreaList();
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).construction(this.AreaSet);
		}
		return this;
	}

	/**
	 * ��ȡ����ĸ���
	 * */
	public int getTransverse() {
		return transverse;
	}

	/**
	 * ��ȡ����ĸ���
	 * */
	public int getVertical() {
		return vertical;
	}

	/**
	 * ��ȡ�׵ĸ���
	 * */
	public int getMineNumber() {
		return mineNumber;
	}

	/**
	 * ����ά����ת���ɼ���  
	 * */
	public ArrayList<Area> getAreaList() {
		ArrayList<Area> arr = new ArrayList<Area>();
		for (int i = 0; i < AreaSet.length; i++) {
			for (int j = 0; j < AreaSet[i].length; j++) {
				arr.add(AreaSet[i][j]);
			}
		}
		return arr;
	}

	public Area[][] getAreaSet() {
		return AreaSet;
	}
	
	
	/**
	 * �������
	 * */
	public AreaFactory sys() {
		for (int i = 0; i < AreaSet.length; i++) {
			for (int j = 0; j < AreaSet[i].length; j++) {
				System.out.print(AreaSet[i][j].getValue() + ",");
			}
			System.out.println();
		}
		return this;
	}
	/**
	 * �������   ״̬
	 * */
	public void sys(List <Area> lsit){
		for (Area area : lsit) {
			System.out.print(area.getValue()+",");
		}
		System.out.println();
	}
}
