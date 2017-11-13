package xyz.lihang.listener;
import java.awt.event.*;

import javax.swing.JDialog;

import xyz.lihang.area.GameTime;
import xyz.lihang.window.DifficultyWindow;

/**
 * 菜单栏游戏开始按钮单几
 * */
public class MenuActionAdapter implements ActionListener {
	@Override
	 public void actionPerformed(ActionEvent e){
		try {
			GameTime gt =GameTime.GetGameTime();
			if(gt != null)
				gt.over();
			
			
			DifficultyWindow dialog =DifficultyWindow.getDifficultyWindowInstance();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModal(true);
			dialog.setVisible(true);
			
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	 }
}
