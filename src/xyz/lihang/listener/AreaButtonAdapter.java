package xyz.lihang.listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import xyz.lihang.INF.AreaLabelColorProperties;
import xyz.lihang.area.Area;
import xyz.lihang.window.AreaButton;


/**
 * AreaButton的事件
 * */
public class AreaButtonAdapter extends MouseAdapter {
	private AreaButton ab;

	public AreaButtonAdapter(AreaButton ab) {
		this.ab = ab;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {

		int modifiers = e.getModifiers();
		// 鼠标左键

		Area area = ab.getArea();
		if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			area.MouseButton1Chick();
			// 踩到雷了
			if (area.getValue() == -1) {
				area.getAreaLabel().setBackground(AreaLabelColorProperties.getInstance().getColor(1111));
				area.getAreaFactory().over(false);
			}

		}
		// 鼠标右键
		if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			area.MouseButton3Chick();
		}
		
		area.getAreaFactory().game();
		
	}

}
