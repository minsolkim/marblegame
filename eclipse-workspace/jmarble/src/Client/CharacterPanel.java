package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CharacterPanel extends JPanel {
	Font font=new Font("SansSerif",Font.BOLD,15);
	
	private ImageIcon icon =new ImageIcon("cha1.png"); 
	private JLabel img=new JLabel("",icon,SwingConstants.CENTER);
	private JLabel sta1=new JLabel();
	
	private ImageIcon ic =new ImageIcon("cha2.png"); 
	private JLabel img2=new JLabel("",ic,SwingConstants.CENTER);
	private JLabel sta2=new JLabel();
	
	private ImageIcon ico =new ImageIcon("cha3.png"); 
	private JLabel img3=new JLabel("",ico,SwingConstants.CENTER);
	private JLabel sta3=new JLabel();
	
	private ImageIcon icn =new ImageIcon("cha4.png"); 
	private JLabel img4=new JLabel("",icn,SwingConstants.CENTER);
	private JLabel sta4=new JLabel();
	
	//테두리 검정색으로 
	private LineBorder lb = new LineBorder(Color.black, 1, true);
	
	int money[]= {3000,3000,3000,3000};
	String user[]= {"user1","user2","user3","user4"};
	String list;
	
	
	
	
	public CharacterPanel(String s) {
		//list=s;
		//user[0]=list;
		this.setLayout(new GridLayout(4,2));
		this.setBackground(Color.WHITE);
		this.setBorder(lb);
		add(img);
		sta1.setText("<html>"+user[0]+"<br>"+"Money : "+money[0]+"</html>");
		sta1.setFont(font);
		add(sta1);
		
		add(img2);
		sta2.setText("<html>"+user[1]+"<br>"+"Money : "+money[1]+"</html>");
		sta2.setFont(font);
		add(sta2);
		
		add(img3);
		sta3.setText("<html>"+user[2]+"<br>"+"Money : "+money[2]+"</html>");
		sta3.setFont(font);
		add(sta3);
		
		add(img4);
		sta4.setText("<html>"+user[3]+"<br>"+"Money : "+money[3]+"</html>");
		sta4.setFont(font);
		add(sta4);
		
		
	}
	
	public void setName(String s) {
		user[0]=s;
		sta1.setText("<html>"+user[0]+"<br>"+"Money : "+money[0]+"</html>");
	}
	


}
