package xyz.lihang.INF;

import java.util.Properties;


/**
 * ����  
 * 
 * ����һЩ�ı�����ʾ
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
		pp.setProperty("DifficultyWindowLabel1", "��  9X9 10��");
		pp.setProperty("DifficultyWindowLabel2", "һ��  16X16 40��");
		pp.setProperty("DifficultyWindowLabel3","���� 16X30 99��");
		pp.setProperty("DifficultyWindowLabel4","�Զ���");
		
		
		
		//��Ϸ��ʾ
		pp.setProperty("gameOver", "��Ϸ����!\n��ը��~");
		pp.setProperty("gameSuccess", "��ϲ,�Ѿ��ҳ���������!\n  ����ʱ:");
		
		//���������
		pp.setProperty("MainWindowTitle", "ɨ��");
		
		//�˵���һ��
		pp.setProperty("JMenu1", "��Ϸ");
		pp.setProperty("JMenu1-1", "��ʼ");
		pp.setProperty("JMenu1-2", "�˳�");
		
		//�˵��ڶ���
		pp.setProperty("JMenu2", "����");
		pp.setProperty("JMenu2-1", "����");
		
		
		//�������·�JLabel
		pp.setProperty("JLabelText1", "����ʱ��:");
		pp.setProperty("JLabelText2", "ʣ������:");
		
		//�Զ����״�����ʾ
		pp.setProperty("parameterError1", "��������ȷ������");
		pp.setProperty("parameterError2", "����Ĳ�������");
		pp.setProperty("parameterError3", "�׵��������ò���ȷ!");
		
		//JMenu2-1��ʾ����
		pp.setProperty("MenuHelp1","By:С������");
		
		//ͼƬ����
		pp.setProperty("mainFilePath", "src/images/mine.png");
		pp.setProperty("flagFilePath", "src/images/flag.png");
	}
	
	public String getString(String key){
		return pp.getProperty(key);
	}
}
