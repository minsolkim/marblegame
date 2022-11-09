package Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MarbleFrame extends JFrame implements JFrameSet{
	private JLabel board0, board1, board2, board3, board4,board5,board6,board7,
	board8,board9,board10,board11,board12,board13,board14,board15,board16,board17,
	board18,board19,board20,board21,board22,board23,board24,board25,board26,board27; //각각의 board 값
	//특수한 이벤트가 없는 타일 구분 
	private JLabel board2Normal,board3Normal,board4Normal,board6Normal; //아래 가로줄 
	private JLabel board2Centerla,board3Centerla,board4Centerla,board6Centerla; //아래 가로줄
	
	private RotatedLabel board9Normal,board10Normal,board12Normal,board13Normal; //왼쪽 세로줄
	

	private JLabel board16Normal,board17Normal1,board19Normal,board20Normal; //위 가로줄
	
	private RotatedLabel board23Normal,board24Normal,board25Normal,board27Normal;  //오른쪽 세로줄
	
	private JLabel board16Centerla,board17Centerla,board19Centerla,board20Centerla;
	private ArrayList<JLabel> boarddown, boardup, boardleft, boardright; //밑,위,왼쪽,오른쪽
	private Container c=getContentPane();
	private JLabel boardCenter; //중앙
	public MarbleFrame() {
		init();
		setting();
		batch();
		listener();
		connect();
		setVisible(true);
			
		}
	@Override
	public void init() {
		//타일을 담는 리스트
		boarddown = new ArrayList<>();
		boardup = new ArrayList<>();
		boardleft = new ArrayList<>();
		boardright = new ArrayList<>();
		//서버로부터 바든 타일 리스트를 담는 리스트
		
		//발판 new
		board0 = new JLabel();
		board1 = new JLabel();
		board2 = new JLabel();
		board3 = new JLabel();
		board4 = new JLabel();
		board5 = new JLabel();
		board6 = new JLabel();
		board7 = new JLabel();
		board8 = new JLabel();
		board9 = new JLabel();
		board10 = new JLabel();
		board11 = new JLabel();
		board12 = new JLabel();
		board13 = new JLabel();
		board14 = new JLabel();
		board15 = new JLabel();
		board16 = new JLabel();
		board17 = new JLabel();
		board18 = new JLabel();
		board19 = new JLabel();
		board20 = new JLabel();
		board21 = new JLabel();
		board22 = new JLabel();
		board23 = new JLabel();
		board24 = new JLabel();
		board25 = new JLabel();
		board26 = new JLabel();
		board27 = new JLabel();
		
		//아래 down
		boarddown.add(board2);
		boarddown.add(board3);
		boarddown.add(board4);
		boarddown.add(board6);
		
		//왼쪽 left
		boardleft.add(board9);
		boardleft.add(board10);
		boardleft.add(board12);
		boardleft.add(board13);
		
		//위 up
		boardup.add(board16);
		boardup.add(board17);
		boardup.add(board19);
		boardup.add(board20);
		
		//오른쪽 right
		boardright.add(board23);
		boardright.add(board24);
		boardright.add(board25);
		boardright.add(board27);
		
		//보드별 한국도시 이름
		
		board2Normal = new JLabel("강릉");
		board3Normal = new JLabel("여수");
		board4Normal = new JLabel("가평");
		board6Normal = new JLabel("부산");
		
		board9Normal = new RotatedLabel("광주");
		board10Normal = new RotatedLabel("보령");
		board12Normal = new RotatedLabel("청주");
		board13Normal = new RotatedLabel("울산");
		
		board16Normal = new JLabel("경주");
		board17Normal1 = new JLabel("제주도");
		board19Normal = new JLabel("대전");
		board20Normal = new JLabel("서울");
		
		board23Normal = new RotatedLabel("포항");
		board24Normal = new RotatedLabel("안산");
		board25Normal = new RotatedLabel("인천");
		board27Normal = new RotatedLabel("목포");
		
		boardCenter = new JLabel(); //중간 보드
		
		c =getContentPane();
	}
	@Override
	public void setting() {
		setTitle("Marble");
		setSize(1200,840);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		//테두리색을 지정하기위해 LineBorder 사용 
		board0.setBorder(new LineBorder(new Color(0,0,0))); 
		board1.setBorder(new LineBorder(new Color(0,0,0))); 
		board2.setBorder(new LineBorder(new Color(0,0,0))); 
		board3.setBorder(new LineBorder(new Color(0,0,0))); 
		board4.setBorder(new LineBorder(new Color(0,0,0))); 
		board5.setBorder(new LineBorder(new Color(0,0,0))); 
		board6.setBorder(new LineBorder(new Color(0,0,0))); 
		board7.setBorder(new LineBorder(new Color(0,0,0))); 
		board8.setBorder(new LineBorder(new Color(0,0,0))); 
		board9.setBorder(new LineBorder(new Color(0,0,0))); 
		board10.setBorder(new LineBorder(new Color(0,0,0))); 
		board11.setBorder(new LineBorder(new Color(0,0,0))); 
		board12.setBorder(new LineBorder(new Color(0,0,0))); 
		board13.setBorder(new LineBorder(new Color(0,0,0))); 
		board14.setBorder(new LineBorder(new Color(0,0,0))); 
		board15.setBorder(new LineBorder(new Color(0,0,0))); 
		board16.setBorder(new LineBorder(new Color(0,0,0))); 
		board17.setBorder(new LineBorder(new Color(0,0,0))); 
		board18.setBorder(new LineBorder(new Color(0,0,0))); 
		board19.setBorder(new LineBorder(new Color(0,0,0))); 
		board20.setBorder(new LineBorder(new Color(0,0,0))); 
		board21.setBorder(new LineBorder(new Color(0,0,0)));
		board22.setBorder(new LineBorder(new Color(0,0,0))); 
		board23.setBorder(new LineBorder(new Color(0,0,0))); 
		board24.setBorder(new LineBorder(new Color(0,0,0))); 
		board25.setBorder(new LineBorder(new Color(0,0,0))); 
		board26.setBorder(new LineBorder(new Color(0,0,0)));
		board27.setBorder(new LineBorder(new Color(0,0,0)));
		
		board2Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board3Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board4Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board6Normal.setBorder(new LineBorder(new Color(0,0,0)));
		
		//
		board9Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board10Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board12Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board13Normal.setBorder(new LineBorder(new Color(0,0,0)));
		
		board16Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board17Normal1.setBorder(new LineBorder(new Color(0,0,0)));
		board19Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board20Normal.setBorder(new LineBorder(new Color(0,0,0)));
		
		board23Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board24Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board25Normal.setBorder(new LineBorder(new Color(0,0,0)));
		board27Normal.setBorder(new LineBorder(new Color(0,0,0)));
		
		//발판 레이아웃 null
		for(int i=0; i< boarddown.size();i++) {
			boarddown.get(i).setLayout(null);
		}
		for(int i=0; i< boardleft.size();i++) {
			boarddown.get(i).setLayout(null);
		}
		for(int i=0; i< boardup.size();i++) {
			boarddown.get(i).setLayout(null);
		}
		for(int i=0; i< boardright.size();i++) {
			boarddown.get(i).setLayout(null);
		}
		c.setLayout(null);
		boardCenter.setIcon(new ImageIcon(MarbleFrame.class.getResource("/images/bg_Background.jpg")));
		boardCenter.setBounds(120,120,600,540);
		
		board0.setBackground(new Color(204, 204, 153));
		board1.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\board_fire.jpg")); //불 구덩이
		board5.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\board_key.jpg")); //찬스키
		board7.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\board_land.jpg"));
		board11.setIcon(new ImageIcon(MarbleFrame.class.getResource("/images/bg_key_left.png")));
		board14.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\board_cnt.jpg")); //공연
		board18.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\board_key.jpg")); //찬스키
		board21.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\ktx.jpg")); //기차
		board22.setIcon(new ImageIcon(MarbleFrame.class.getResource("/images/bg_key_right.png")));
		board26.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\board_txservice.jpg")); //국세청
		
		// 시작발판 ~ 감옥
		board0.setBounds(720, 660, 120, 120); // 시작발판
		board1.setBounds(620, 660, 100, 120);
		board2.setBounds(520, 660, 100, 120);
		board3.setBounds(420, 660, 100, 120);
		board4.setBounds(320, 660, 100, 120);
		board5.setBounds(220, 660, 100, 120);
		board6.setBounds(120, 660, 100, 120); 
		board7.setBounds(0, 660, 120, 120); // 감옥
		
		// 감옥~ 공연
		board8.setBounds(0, 570, 120, 90);
		board9.setBounds(0, 480, 120, 90);
		board10.setBounds(0, 390, 120, 90);
		board11.setBounds(0, 300, 120, 90);
		board12.setBounds(0, 210, 120, 90);
		board13.setBounds(0, 120, 120, 90);
		board14.setBounds(0, 0, 120, 120); //공연
		
		//공연~ ktx
		board15.setBounds(120, 0, 100, 120);
		board16.setBounds(220, 0, 100, 120);
		board17.setBounds(320, 0, 100, 120);
		board18.setBounds(420, 0, 100, 120);
		board19.setBounds(520, 0, 100, 120);
		board20.setBounds(620, 0, 100, 120);
		board21.setBounds(720, 0, 120, 120);//ktx
		
		//ktx~시작
		board22.setBounds(720,120,120,90);
		board23.setBounds(720,210,120,90);
		board24.setBounds(720,300,120,90);
		board25.setBounds(720,390,120,90);
		board26.setBounds(720,480,120,90);
		board27.setBounds(720,570,120,90);
		
		
		// 발판별 센터 라벨 설정
		// Line 0
		//board2Centerla.setBounds(0, 40, 100, 70);
		//board2Centerla.setFont(new Font("CookieRun BLACK", Font.BOLD, 45));
		//board2Centerla.setHorizontalAlignment(JLabel.CENTER);

		// 발판별 이름 라벨 설정
		// Line 0
		board2Normal.setBounds(0, 90, 100, 40);
		board2Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board2Normal.setHorizontalAlignment(JLabel.CENTER);

		board3Normal.setBounds(0, 90, 100, 40);
		board3Normal.setFont(new Font("Dialog", Font.BOLD, 20));
		board3Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board4Normal.setBounds(0, 90, 100, 40);
		board4Normal.setFont(new Font("Dialog", Font.BOLD, 20));
		board4Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board6Normal.setBounds(0, 90, 100, 40);
		board6Normal.setFont(new Font("Dialog", Font.BOLD, 20));
		board6Normal.setHorizontalAlignment(JLabel.CENTER);

		//
		board9Normal.setBounds(0, 0, 40, 100);
		board10Normal.setBounds(0, 0, 40, 100);
		board12Normal.setBounds(0, 0, 40, 100);
		board13Normal.setBounds(0, 0, 40, 100);
		
		
		board16Normal.setBounds(0, 90, 100, 40);
		board16Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board16Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board17Normal1.setBounds(0, 90, 100, 40);
		board17Normal1.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board17Normal1.setHorizontalAlignment(JLabel.CENTER);
		
		board19Normal.setBounds(0, 90, 100, 40);
		board19Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board19Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board20Normal.setBounds(0, 90, 100, 40);
		board20Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board20Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board23Normal.setBounds(85, 0, 40, 100);
		board24Normal.setBounds(85, 0, 40, 100);
		board25Normal.setBounds(85, 0, 40, 100);
		board27Normal.setBounds(85, 0, 40, 100);
	}
	@Override
	public void batch() {
		
		getContentPane().add(board0);
		getContentPane().add(board1);
		getContentPane().add(board2);
		getContentPane().add(board3);
		getContentPane().add(board4);
		getContentPane().add(board5);
		getContentPane().add(board6);
		getContentPane().add(board7);
		getContentPane().add(board8);
		getContentPane().add(board9);
		getContentPane().add(board10);
		getContentPane().add(board11);
		getContentPane().add(board12);
		getContentPane().add(board13);
		getContentPane().add(board14);
		getContentPane().add(board15);
		getContentPane().add(board16);
		getContentPane().add(board17);
		getContentPane().add(board18);
		getContentPane().add(board19);
		getContentPane().add(board20);
		getContentPane().add(board21);
		getContentPane().add(board22);
		getContentPane().add(board23);
		getContentPane().add(board24);
		getContentPane().add(board25);
		getContentPane().add(board26);
		getContentPane().add(board27);
		getContentPane().add(boardCenter);
		
		board2.add(board2Normal);
		board3.add(board3Normal);
		board4.add(board4Normal);
		board6.add(board6Normal);
		
		board9.add(board9Normal);
		board10.add(board10Normal);
		board12.add(board10Normal);
		board13.add(board10Normal);
		
		board16.add(board16Normal);
		board17.add(board17Normal1);
		board19.add(board19Normal);
		board20.add(board20Normal);
		
		board23.add(board23Normal);
		board24.add(board24Normal);
		board25.add(board25Normal);
		board27.add(board27Normal);
		
		
		
	}
	@Override
	public void listener() {
		
	}
	@Override
	public void connect() {
		
	}
	private class RotatedLabel extends JLabel {
		char[] tmpTextList;
		public RotatedLabel() {

		}
		public RotatedLabel(String text) {
			tmpTextList = new char[text.length()];
			setLayout(new GridLayout(text.length(), 1));
			for (int i = 0; i < text.length(); i++) {
				tmpTextList[i] = text.charAt(i);
				JLabel tmpla = new JLabel(Character.toString(tmpTextList[i]));
				tmpla.setFont((new Font("CookieRun BLACK", Font.BOLD, 20)));
				tmpla.setHorizontalAlignment(JLabel.CENTER);
				tmpla.setVerticalAlignment(JLabel.CENTER);
				add(tmpla);
			}
		}
		
	}
	
	public static void main(String[] args) {
		MarbleFrame f = new MarbleFrame();
		f.setVisible(true);
		}
}
