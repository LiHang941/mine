package xyz.lihang.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.KeyStroke;

import xyz.lihang.INF.WindowINF;
import xyz.lihang.INF.WindowProperties;
import xyz.lihang.listener.MenuActionAdapter;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * Ö÷´°Ìå
 * */
public class MainWindow extends JFrame {


	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel Label_Time;
	private JLabel Label_Score;
	private static MainWindow mainWindow =  null;
	/**
	 * »ñÈ¡Ö÷´°ÌåÊµÀý
	 * */
	public static MainWindow getMainWondowInstance(){
		if(mainWindow == null) 
			mainWindow = new MainWindow();
		return  mainWindow;
		
	}
	
	
	/**
	 * Create the frame.
	 */
	private  MainWindow() {
		setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		setTitle(WindowProperties.getWindowProperties().getString("MainWindowTitle"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 337);
		setSize(9*(WindowINF.areaWidth+2)+22 , 9*(WindowINF.areaheight+2)+110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUp = new JPanel();
		contentPane.add(panelUp, BorderLayout.NORTH);
		panelUp.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		panelUp.add(menuBar);
		
		JMenu menu = new JMenu(WindowProperties.getWindowProperties().getString("JMenu1"));
		menu.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem(WindowProperties.getWindowProperties().getString("JMenu1-1"));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		menuItem.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		menu.add(menuItem);
		menu.addSeparator();
		menuItem.addActionListener(new MenuActionAdapter());
		{ //ÍË³ö²Ëµ¥°´Å¥
			JMenuItem menuItem_1 = new JMenuItem(WindowProperties.getWindowProperties().getString("JMenu1-2"));
			menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
			menuItem_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			menu.add(menuItem_1);
			menuItem_1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		
		
		{
			//¹ØÓÚ²Ëµ¥°´Å¥
			JMenu menu_1 = new JMenu(WindowProperties.getWindowProperties().getString("JMenu2"));
			menu_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			menuBar.add(menu_1);
			
			JMenuItem menuItem_11 = new JMenuItem(WindowProperties.getWindowProperties().getString("JMenu2-1"));
			menuItem_11.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			menuItem_11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			menuItem_11.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(MainWindow.getMainWondowInstance(),WindowProperties.getWindowProperties().getString("MenuHelp1") );
				}
			});
			
			menu_1.add(menuItem_11);
		}
		
		
		
		AreaPanelCentre panelCentre = AreaPanelCentre.getAreaPanelInstance();
		
		contentPane.add(panelCentre, BorderLayout.CENTER);
		
		
		panelCentre.setLayout(new BorderLayout(0, 0));
		
		//panelCentre.pp();
		
		{
			//Ö÷´°¿Ú×îÏÂÃæµÄ±êÇ©
			JPanel panelDown = new JPanel();
			contentPane.add(panelDown, BorderLayout.SOUTH);
			panelDown.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel lblNewLabel = new JLabel(WindowProperties.getWindowProperties().getString("JLabelText1"));
			lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			panelDown.add(lblNewLabel);
			
			this.Label_Time = new JLabel("0");
			this.Label_Time.setForeground(Color.BLUE);
			panelDown.add(Label_Time);
			
			JLabel lblNewLabel_2 = new JLabel("                  ");
			panelDown.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel(WindowProperties.getWindowProperties().getString("JLabelText2"));
			lblNewLabel_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			panelDown.add(lblNewLabel_3);
			
			this.Label_Score = new JLabel("0");
			this.Label_Score.setForeground(Color.RED);
			panelDown.add(this.Label_Score);
		}
		
		  setLocationRelativeTo(null); 
	}


	public JLabel getLabel_Time() {
		return Label_Time;
	}


	public void setLabel_Time(JLabel labelTime) {
		Label_Time = labelTime;
	}


	public JLabel getLabel_Score() {
		return Label_Score;
	}


	public void setLabel_Score(JLabel labelScore) {
		Label_Score = labelScore;
	}

	

}
