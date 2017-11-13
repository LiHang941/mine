package xyz.lihang.window;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import xyz.lihang.INF.AreaLabelColorProperties;
import xyz.lihang.INF.WindowINF;
import xyz.lihang.INF.WindowProperties;
import xyz.lihang.area.Area;
import xyz.lihang.listener.AreaLabelAdapter;

/**
 * AreaButton ÏÂµÄLabel
 * */
public class AreaLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private Point point;
	private int value;
	private Area area;
	public AreaLabel(Point point,int value,Area area){
		super(" ");
		this.area=area;
		setValue(value);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(AreaLabelColorProperties.getInstance().getColor(value));
		this.setVisible(false);
		this.addMouseListener(new AreaLabelAdapter(this));
		setPoint(point);
		setOpaque(true);
		
		
		
		
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	


	public void setValue(int value){
		this.value = value;
		if(this.value == -1){
			//À×ÏÔÊ¾
			this.setIcon(new ImageIcon(WindowProperties.getWindowProperties().getString("mainFilePath")));
			this.setText(null);
		}else if (this.value == 0){
			this.setText(null);
		}else{
			this.setText(Integer.toString(this.value));
		}
	}

	public Point getPoint() {
		return point;
	}

	
	public void setPoint(Point point){
		this.point = point;
		this.setBounds(point.x, point.y,WindowINF.areaWidth , WindowINF.areaheight);
	}
}
