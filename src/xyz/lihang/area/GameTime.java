package xyz.lihang.area;

import java.util.Date;

import xyz.lihang.window.MainWindow;


/**
 * 游戏时间记录
 * */
public class GameTime implements Runnable {
	public static final Object lock = new Object();
	private long actionTime;
	
	private long overTime;
	//游戏已经进行的世界
	private long time;
	
	//线程状态标记
	private boolean flag = true;
	
	//
	private static GameTime gt;
	//线程
	private Thread tt;

	/**
	 * 开始
	 * */
	public void action() {
		actionTime = new Date().getTime();
		overTime = actionTime;
		tt = new Thread(this);
		tt.start();
	}
	/**
	 * 构造
	 * */
	public GameTime(){
		synchronized (lock) {
			if(GameTime.gt!=null)
				gt.over();
			
			 GameTime.gt = this;
		}
	}
	/**
	 * 结束   返回运行的时间
	 * */
	public String over() {
		this.flag = false;
		return Long.toString(time)+"s";
	}
	
	@Override
	public void run() {
		while (this.flag) {
			try {
				overTime = new Date().getTime();
				time = (overTime - actionTime)/ 1000;
				if(this.flag)
					MainWindow.getMainWondowInstance().getLabel_Time()
							.setText(Long.toString(time)+"s");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "GameTime [time=" + time + ", flag=" + flag + "]";
	}
	/**
	 * 
	 * */
	public long getOverTime() {
		return overTime;
	}

	public void setOverTime(long overTime) {
		this.overTime = overTime;
	}

	public long getActionTime() {
		return actionTime;
	}
	/**
	 * 
	 * */
	public long getTime() {
		return time;
	}
	/**
	 * 获取当前的时间管理对象
	 * */
	public static GameTime GetGameTime() {
		return GameTime.gt;
	}

}
