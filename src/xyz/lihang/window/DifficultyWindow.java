package xyz.lihang.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;


import javax.swing.JButton;

import xyz.lihang.INF.WindowProperties;
import xyz.lihang.listener.LabelMouseMoveAdapter;

public class DifficultyWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel Label1;
	private JLabel Label2;
	private JLabel Label3;
	private JLabel Label4;
	private JPanel panelDown;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static DifficultyWindow dw = null;
	private WindowProperties wp = WindowProperties.getWindowProperties();
	public static DifficultyWindow getDifficultyWindowInstance() {
		if (dw == null)
			dw = new DifficultyWindow();
		return dw;
	}

	
	private DifficultyWindow() {
		setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		setTitle("ƒ—∂»—°‘Ò");
		setBounds(100, 100, 304, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 296, 173);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		Label1 = new JLabel(wp.getString("DifficultyWindowLabel1"));
		Label1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		Label1.setBackground(SystemColor.textHighlight);
		Label1.setBounds(29, 24, 112, 54);
		Label1.setHorizontalAlignment(JLabel.CENTER);
		Label1.setOpaque(true);
		Label1.addMouseListener(new LabelMouseMoveAdapter(Label1));
		contentPanel.add(Label1);

		Label2 = new JLabel(wp.getString("DifficultyWindowLabel2"));
		Label2.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		Label2.setBackground(SystemColor.textHighlight);
		Label2.setForeground(Color.DARK_GRAY);
		Label2.setBounds(29, 111, 112, 54);
		Label2.setHorizontalAlignment(JLabel.CENTER);
		Label2.setOpaque(true);
		Label2.addMouseListener(new LabelMouseMoveAdapter(Label2));
		contentPanel.add(Label2);

		Label3 = new JLabel(wp.getString("DifficultyWindowLabel3"));
		Label3.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		Label3.setForeground(Color.DARK_GRAY);
		Label3.setBackground(SystemColor.textHighlight);
		Label3.setBounds(151, 24, 112, 54);
		Label3.setHorizontalAlignment(JLabel.CENTER);
		Label3.setOpaque(true);
		Label3.addMouseListener(new LabelMouseMoveAdapter(Label3));
		contentPanel.add(Label3);

		Label4 = new JLabel(wp.getString("DifficultyWindowLabel4"));
		Label4.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		Label4.setForeground(Color.DARK_GRAY);
		Label4.setBackground(SystemColor.textHighlight);
		Label4.setBounds(151, 111, 112, 54);
		Label4.setHorizontalAlignment(JLabel.CENTER);
		Label4.setOpaque(true);
		Label4.addMouseListener(new LabelMouseMoveAdapter(Label4));
		
		contentPanel.add(Label4);

		
		panelDown = new JPanel();
		panelDown.setBounds(0, 173, 296, 75);
		getContentPane().add(panelDown);
		panelDown.setLayout(null);

		JLabel lblNewLabel = new JLabel("––:");
		lblNewLabel.setBounds(37, 13, 18, 15);
		panelDown.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(65, 10, 77, 21);
		panelDown.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("¡–:");
		label.setBounds(152, 13, 18, 15);
		panelDown.add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(174, 10, 85, 21);
		textField_1.setColumns(10);
		panelDown.add(textField_1);

		JLabel label_1 = new JLabel("µÿ¿◊:");
		label_1.setBounds(25, 38, 30, 15);
		panelDown.add(label_1);

		textField_2 = new JTextField();
		textField_2.setBounds(65, 35, 77, 21);
		textField_2.setColumns(10);
		panelDown.add(textField_2);

		JButton btnNewButton = new JButton("»∑∂®");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LabelMouseMoveAdapter(null).customMouseClick(textField.getText(), textField_1.getText(), textField_2.getText());
				
			}
		});
		btnNewButton.setBounds(174, 34, 79, 23);
		panelDown.add(btnNewButton);
		dw= this;
		setPanelVisible(false);
		Point point = MainWindow.getMainWondowInstance().getLocation();
		this.setLocation(point.x + 40, point.y + 20);
	}
	/**
	 * …Ë÷√œ¬∑Ωµƒƒ⁄»› «∑Òœ‘ æ
	 * */
	public void setPanelVisible(boolean flag){
		if(dw == null)
			return ;
		Point point = dw.getLocation();
		if(flag)
			dw.setBounds(point.x, point.y, 304, 277);
		else
			dw.setBounds(point.x, point.y, 304, 250);	
		panelDown.setVisible(flag);
	}
}
