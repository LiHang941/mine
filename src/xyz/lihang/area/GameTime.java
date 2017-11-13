package xyz.lihang.area;

import java.util.Date;

import xyz.lihang.window.MainWindow;


/**
 * ��Ϸʱ���¼
 * */
public class GameTime implements Runnable {
	public static final Object lock = new Object();
	private long actionTime;
	
	private long overTime;
	//��Ϸ�Ѿ����е�����
	private long time;
	
	//�߳�״̬���
	private boolean flag = true;
	
	//
	private static GameTime gt;
	//�߳�
	private Thread tt;

	/**
	 * ��ʼ
	 * */
	public void action() {
		actionTime = new Date().getTime();
		overTime = actionTime;
		tt = new Thread(this);
		tt.start();
	}
	/**
	 * ����
	 * */
	public GameTime(){
		synchronized (lock) {
			if(GameTime.gt!=null)
				gt.over();
			
			 GameTime.gt = this;
		}
	}
	/**
	 * ����   �������е�ʱ��
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
	 * ��ȡ��ǰ��ʱ��������
	 * */
	public static GameTime GetGameTime() {
		return GameTime.gt;
	}

}
