package xyz.lihang.window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;

import xyz.lihang.INF.WindowINF;

/**
 * 游戏显示区域
 * */
public class AreaPanelCentre extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AreaPanelCentre areaPanel = null;
	
	private int xx = 9;
	private int yy = 9;
	
	public static AreaPanelCentre getAreaPanelInstance(){
		if(areaPanel == null)
			areaPanel= new AreaPanelCentre();
		return areaPanel;
	}
	
	private AreaPanelCentre() {
		super();
		this.setLayout(null);
		
	}
	public void pp() {
		for(int y=1;y<=xx*(WindowINF.areaheight+2)+1;y+=(WindowINF.areaheight+2)){
			Graphics2D g2d = (Graphics2D) this.getGraphics();
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.setStroke(new BasicStroke(2f));

			g2d.drawLine(1, y, yy*(WindowINF.areaWidth+2), y);
		}

		//绘制竖线
		for(int x=1;x<=yy*(WindowINF.areaWidth+2)+1;x+=(WindowINF.areaWidth+2)){
			Graphics2D g2d = (Graphics2D) this.getGraphics();
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.setStroke(new BasicStroke(3f));
			g2d.drawLine(x, 1, x,xx*(WindowINF.areaheight+2));
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(),  this.getHeight());
		pp();
		super.paint(g);
	}
	
	
	public int getXx() {
		return xx;
	}

	public AreaPanelCentre setXx(int xx) {
		this.xx = xx;
		return this;
	}

	public int getYy() {
		return yy;
	}

	public AreaPanelCentre setYy(int yy) {
		this.yy = yy;
		return this;
	}

}
