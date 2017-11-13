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
 * Area工厂
 * 
 * */
public class AreaFactory {
	/**
	 * 横向的Area个数
	 * */
	private int transverse = 9;

	/**
	 * 纵向的Area个数
	 * */
	private int vertical = 9;

	/**
	 * 雷的个数
	 * */
	private int mineNumber;

	/**
	 * 维护一个二维数组
	 * */
	private Area[][] AreaSet;

	/**
	 * transverse 横向个数 vertical 纵向个数 mineNumber 雷的个数
	 * */
	public AreaFactory(int transverse, int vertical, int mineNumber) {
		AreaPanelCentre.getAreaPanelInstance().removeAll();
		
		this.transverse = transverse;
		this.vertical = vertical;
		this.mineNumber = mineNumber;
		AreaSet = new Area[transverse][vertical];
		// 将Area装入集合中

		for (int i = 0; i < AreaSet.length; i++) {
			for (int j = 0; j < AreaSet[i].length; j++) {
				Area area = new Area(new Point(j, i), this);
				AreaSet[i][j] = area;
			}
		}

	}

	/**
	 * 每次单击或者 就开始扫描
	 * */
	public AreaFactory game() {
		ArrayList<Area> arr = getAreaList();
		// 显示剩余雷
		// 插旗子的数量
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

		// 扫描旗子是否插全  
		// 旗子插完并且都查对了
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
	 * 开始扫雷构造资源内容
	 * */
	public AreaFactory Action() {
		return productionMine().productionArea().addPanel();
	}

	/**
	 * 踩到雷了
	 * 
	 * true 赢了
	 * 
	 * false  输了
	 * */
	public AreaFactory over(boolean ff) {
		ArrayList<Area> arr = getAreaList();
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).getAreaButton().setVisible(false);
			arr.get(i).getAreaLabel().setVisible(true);
		}
		String str = (GameTime.GetGameTime()!=null ? GameTime.GetGameTime().over():"0s");
		if(!ff){
			//输了
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(),
			WindowProperties.getWindowProperties().getString("gameOver"));
		}else{
			//赢了
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(),
			WindowProperties.getWindowProperties().getString("gameSuccess")+ str );
			
		}
		
		return this;
	}

	/**
	 * 地雷生成
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
	 * 将控件添加到面板中
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
	 * 构造Area
	 * */
	private AreaFactory productionArea() {
		ArrayList<Area> arr = getAreaList();
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).construction(this.AreaSet);
		}
		return this;
	}

	/**
	 * 获取横向的个数
	 * */
	public int getTransverse() {
		return transverse;
	}

	/**
	 * 获取纵向的个数
	 * */
	public int getVertical() {
		return vertical;
	}

	/**
	 * 获取雷的个数
	 * */
	public int getMineNumber() {
		return mineNumber;
	}

	/**
	 * 将二维数组转换成集合  
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
	 * 调试输出
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
	 * 调试输出   状态
	 * */
	public void sys(List <Area> lsit){
		for (Area area : lsit) {
			System.out.print(area.getValue()+",");
		}
		System.out.println();
	}
}
