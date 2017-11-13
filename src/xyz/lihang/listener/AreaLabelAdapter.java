package xyz.lihang.listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import xyz.lihang.window.AreaLabel;


/**
 * AreaLabel的鼠标中健事件
 * */
public class AreaLabelAdapter extends MouseAdapter {
	private AreaLabel  al  ;
	public AreaLabelAdapter(AreaLabel al) {
		this.al = al;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int modifiers = e.getModifiers();
		// 鼠标滚轮
		if ((modifiers & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
			al.getArea().MouseButton2Chick();
		}
		al.getArea().getAreaFactory().game();
	}
	
	
	
}
