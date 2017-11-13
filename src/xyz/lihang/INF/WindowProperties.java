package xyz.lihang.INF;

import java.util.Properties;


/**
 * 配置  
 * 
 * 设置一些文本的显示
 * 
 * */
public class WindowProperties {
	private Properties pp = new Properties();
	
	private static WindowProperties wp = null;
	
	
	
	public static WindowProperties getWindowProperties(){
		if(wp==null)
			wp =new WindowProperties();
		return wp;
	}
	private WindowProperties(){
		load();
	}
	public void load(){
		//DifficultyWindow
		//
		pp.setProperty("DifficultyWindowLabel1", "简单  9X9 10雷");
		pp.setProperty("DifficultyWindowLabel2", "一般  16X16 40雷");
		pp.setProperty("DifficultyWindowLabel3","困难 16X30 99雷");
		pp.setProperty("DifficultyWindowLabel4","自定义");
		
		
		
		//游戏提示
		pp.setProperty("gameOver", "游戏结束!\n雷炸了~");
		pp.setProperty("gameSuccess", "恭喜,已经找出了所有雷!\n  共耗时:");
		
		//主窗体标题
		pp.setProperty("MainWindowTitle", "扫雷");
		
		//菜单第一项
		pp.setProperty("JMenu1", "游戏");
		pp.setProperty("JMenu1-1", "开始");
		pp.setProperty("JMenu1-2", "退出");
		
		//菜单第二项
		pp.setProperty("JMenu2", "帮助");
		pp.setProperty("JMenu2-1", "关于");
		
		
		//主窗体下方JLabel
		pp.setProperty("JLabelText1", "已用时间:");
		pp.setProperty("JLabelText2", "剩余雷数:");
		
		//自定义雷错误提示
		pp.setProperty("parameterError1", "请输入正确的整数");
		pp.setProperty("parameterError2", "输入的参数有误");
		pp.setProperty("parameterError3", "雷的数量设置不正确!");
		
		//JMenu2-1提示内容
		pp.setProperty("MenuHelp1","By:小航博客");
		
		//图片索引
		pp.setProperty("mainFilePath", "src/images/mine.png");
		pp.setProperty("flagFilePath", "src/images/flag.png");
	}
	
	public String getString(String key){
		return pp.getProperty(key);
	}
}
