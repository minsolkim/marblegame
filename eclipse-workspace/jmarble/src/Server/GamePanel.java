package Server;
import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {
	
	private ImageIcon icon1 =new ImageIcon("dice1.png"); 
	private JLabel img1=new JLabel(icon1);
	private ImageIcon icon2 =new ImageIcon("dice2.png"); 
	private JLabel img2=new JLabel(icon2);
	private ImageIcon icon3 =new ImageIcon("dice3.png"); 
	private JLabel img3=new JLabel(icon3);
	private ImageIcon icon4 =new ImageIcon("dice4.png"); 
	private JLabel img4=new JLabel(icon4);
	private ImageIcon icon5 =new ImageIcon("dice5.png"); 
	private JLabel img5=new JLabel(icon5);
	private ImageIcon icon6 =new ImageIcon("dice6.png"); 
	private JLabel img6=new JLabel(icon6);
	
	private ImageIcon ic1 =new ImageIcon("dice1.png"); 
	private JLabel im1=new JLabel(ic1);
	private ImageIcon ic2 =new ImageIcon("dice2.png"); 
	private JLabel im2=new JLabel(ic2);
	private ImageIcon ic3 =new ImageIcon("dice3.png"); 
	private JLabel im3=new JLabel(ic3);
	private ImageIcon ic4 =new ImageIcon("dice4.png"); 
	private JLabel im4=new JLabel(ic4);
	private ImageIcon ic5 =new ImageIcon("dice5.png"); 
	private JLabel im5=new JLabel(ic5);
	private ImageIcon ic6 =new ImageIcon("dice6.png"); 
	private JLabel im6=new JLabel(ic6);
	
	
	public GamePanel() {
		this.setSize(getMaximumSize());
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		
		Random random = new Random(); // ·£´ý °´Ã¼ »ý¼º
		
		int num=(random.nextInt(6))+1;
			
			if(num==1) {
				add(img1);
				img1.setBounds(400,300,90,90);
			}
			else if(num==2) {
				add(img2);
				img2.setBounds(400,300,90,90);
			}
			else if(num==3) {
				add(img3);
				img3.setBounds(400,300,90,90);
			}
			else if(num==4) {
				add(img4);
				img4.setBounds(400,300,90,90);
			}
			else if(num==5) {
				add(img5);
				img5.setBounds(400,300,90,90);
			}
			else if(num==6) {
				add(img6);
				img6.setBounds(400,300,90,90);
			}
			
			
			num=(random.nextInt(6))+1;
			
			if(num==1) {
				add(im1);
				im1.setBounds(530,300,90,90);
			}
			else if(num==2) {
				add(im2);
				im2.setBounds(530,300,90,90);
			}
			else if(num==3) {
				add(im3);
				im3.setBounds(530,300,90,90);
			}
			else if(num==4) {
				add(im4);
				im4.setBounds(530,300,90,90);
			}
			else if(num==5) {
				add(im5);
				im5.setBounds(530,300,90,90);
			}
			else if(num==6) {
				add(im6);
				im6.setBounds(530,300,90,90);
			}
		
		
		
	}
	


}
