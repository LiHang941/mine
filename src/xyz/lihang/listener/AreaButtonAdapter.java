package xyz.lihang.listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import xyz.lihang.INF.AreaLabelColorProperties;
import xyz.lihang.area.Area;
import xyz.lihang.window.AreaButton;


/**
 * AreaButton���¼�
 * */
public class AreaButtonAdapter extends MouseAdapter {
	private AreaButton ab;

	public AreaButtonAdapter(AreaButton ab) {
		this.ab = ab;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {

		int modifiers = e.getModifiers();
		// ������

		Area area = ab.getArea();
		if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			area.MouseButton1Chick();
			// �ȵ�����
			if (area.getValue() == -1) {
				area.getAreaLabel().setBackground(AreaLabelColorProperties.getInstance().getColor(1111));
				area.getAreaFactory().over(false);
			}

		}
		// ����Ҽ�
		if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
			area.MouseButton3Chick();
		}
		
		area.getAreaFactory().game();
		
	}

}
