package xyz.lihang.window;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;

import xyz.lihang.INF.WindowINF;
import xyz.lihang.area.Area;
import xyz.lihang.listener.AreaButtonAdapter;

/**
 *  AreaLabel上的按钮  AreaButton
 * */
public class AreaButton extends JButton{

	private static final long serialVersionUID = 1L;
	private Point point ;
	private Area area ;
	public AreaButton(Point point,Area area) {
		//button没有内容
		super("");
		this.area=area;
		//添加监听器
		this.addMouseListener(new AreaButtonAdapter(this));
		setPoint(point);
		this.setVisible(true);
		this.setBackground(Color.LIGHT_GRAY);
	}
	
	
	
	public Area getArea() {
		return this.area;
	}



	public void setArea(Area area) {
		this.area = area;
	}



	public Point getPoint() {
		return this.point;
	}

	public void setPoint(Point point) {
		this.point = point;
		this.setBounds(point.x, point.y, WindowINF.areaWidth,WindowINF.areaheight);
	}

	

	
}
