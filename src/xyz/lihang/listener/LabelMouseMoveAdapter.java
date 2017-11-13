package xyz.lihang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import xyz.lihang.INF.WindowINF;
import xyz.lihang.INF.WindowProperties;
import xyz.lihang.area.AreaFactory;
import xyz.lihang.area.GameTime;
import xyz.lihang.window.AreaPanelCentre;
import xyz.lihang.window.DifficultyWindow;
import xyz.lihang.window.MainWindow;


/**
 *   游戏模式选择事件  
 * */
public class LabelMouseMoveAdapter extends MouseAdapter {
	private JLabel jLabel;
	private WindowProperties wp = WindowProperties.getWindowProperties();

	public LabelMouseMoveAdapter(JLabel jLabel) {
		this.jLabel = jLabel;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		jLabel.setBackground(WindowINF.DifficultyWindowLabelEntered);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		jLabel.setBackground(WindowINF.DifficultyWindowLabelExited);
		if (!jLabel.getText().equals(wp.getString("DifficultyWindowLabel4"))) 
			DifficultyWindow.getDifficultyWindowInstance().setPanelVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		/**
		 * 设置难度配置
		 * */
		
		String str = jLabel.getText();
		if (str.equals(wp.getString("DifficultyWindowLabel1"))) {
			//new AreaFactory(3,3,4).Action().getAreaList().forEach((arr)->System.out.println(arr));
			//9*9  10个雷
			customMouseClick("9","9","10");
		}
		if (str.equals(wp.getString("DifficultyWindowLabel2"))) {
			//16* 16   40
			customMouseClick("16","16","40");
		}
		if (str.equals(wp.getString("DifficultyWindowLabel3"))) {
			customMouseClick("16","30","99");
		}
		
		if (str.equals(wp.getString("DifficultyWindowLabel4"))) {
			DifficultyWindow.getDifficultyWindowInstance().setPanelVisible(true);
		}else{
			new GameTime().action();
		}
	}
	
	public void customMouseClick(String x,String y,String number){
		try{
			Integer.parseInt(x);
			Integer.parseInt(y);
			Integer.parseInt(number);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(), WindowProperties.getWindowProperties().getString("parameterError1"));
			return;
		}
		int xx =Integer.parseInt(x);
		int yy =Integer.parseInt(y);
		int num =Integer.parseInt(number);
		if(xx <= 0 && yy<=0 && num <=0){
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(), WindowProperties.getWindowProperties().getString("parameterError2"));
			return;
		}
		if(num > (xx*yy)){
			JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(), WindowProperties.getWindowProperties().getString("parameterError3"));
			return;
		}
		new AreaFactory(xx,yy,num).Action();
		DifficultyWindow.getDifficultyWindowInstance().setVisible(false);
		MainWindow.getMainWondowInstance().setSize(yy*(WindowINF.areaWidth+2)+22 , xx*(WindowINF.areaheight+2)+110);
		AreaPanelCentre.getAreaPanelInstance().setXx(xx).setYy(yy).update(AreaPanelCentre.getAreaPanelInstance().getGraphics());	
		MainWindow.getMainWondowInstance().setLocationRelativeTo(null); 
	}

}
