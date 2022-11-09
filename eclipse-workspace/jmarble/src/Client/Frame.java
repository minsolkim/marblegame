package Client;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class Frame extends JFrame {
	private String UserName;
	//private JPanel ;
	private GamePanel gamePanel =new GamePanel();
	private CharacterPanel characterPanel=new CharacterPanel(UserName);
	private ListPanel listPanel=new ListPanel();
	
	public Frame(String s) {
		UserName=s;
		characterPanel.setName(s);
		setTitle("부루마블");
		Container c=getContentPane();
		//c.setLayout(null);
		
		JSplitPane hPane = new JSplitPane();
		//c.add(hPane,BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); 
		hPane.setDividerLocation(840);   
		hPane.setEnabled(false); //스플릿팬 못움직이게 고정
		hPane.setLeftComponent(gamePanel); 
		
		JSplitPane pPane = new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(350); 
		pPane.setTopComponent(characterPanel);
		pPane.setBottomComponent(listPanel); 
		hPane.setRightComponent(pPane);  //pPane을 hPane의 오른쪽에 붙인다.
		
		c.add(hPane,BorderLayout.CENTER);
		setSize(1149,820);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame("HI");
		f.setVisible(true);

	}

}
