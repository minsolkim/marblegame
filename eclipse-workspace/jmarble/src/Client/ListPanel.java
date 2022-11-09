package Client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ListPanel extends JPanel{
	
	private JTextArea textArea = new JTextArea();
	private JButton end=new JButton("턴 종료");
	private JButton start=new JButton("시작");
	
	Font font=new Font("SansSerif",Font.BOLD,15);
	
	public ListPanel() {
		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		add(scrollPane,BorderLayout.CENTER);
		
		
		JPanel south=new JPanel(new GridLayout(1,2));
		end.setFont(font);
		start.setFont(font);
		south.add(end);
		south.add(start);
	    add(south,BorderLayout.SOUTH);
		
		
		
	}

}

