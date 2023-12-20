package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Pack_Model.Board;
import Server.Msg;
import Server.Protocol;

public class ClientView extends JFrame{
	private static final long serialVersionUID = 1L;
	private Socket socket; // 연결소켓
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JPanel contentPane;
	private JTextField txtInput;
	private Vector<Board> BoardVec;
	private String UserName;
	private int UserMoney = 0;
	private int numOfTurns;
	private ImageIcon minusButtonImg=new ImageIcon("minus_money.png");
	private JButton minusButton=new JButton(minusButtonImg);
	private JLabel moneym=new JLabel("0");
	private JLabel minusLabel=new JLabel("money");
	private ImageIcon travelImg=new ImageIcon("travel_a.png");
	private JPanel travel;
	   
	private ImageIcon donateImg=new ImageIcon("donate.png");
	private JPanel donate;
	// 통행료 
	int minusMoney=3000;
	private int BoardType;
	private boolean stStatus = false;
	// 플레이어 배열
	private int[] player_money = new int[4];
	private ImageIcon cityPurchaseInfoImg = new ImageIcon("purchasecity.png");
	private ImageIcon isprisonInfoImg = new ImageIcon("prisonpanel.png");
	private ImageIcon ownerExistsInfoImg = new ImageIcon("tollfee.png");
	private ImageIcon goldkeyInfoImg = new ImageIcon("chancePanel.png"); //황금열쇠 키
	private ImageIcon tollfeeImg=new ImageIcon("btstoll.png");
	private JPanel tollfee;
	private ImageIcon confirmButtonImg = new ImageIcon("confirmButtonImg.png");
	// 입금될 돈
	private int giftMoney = 0;
	private ImageIcon ok2ButtonImg=new ImageIcon("ok2.png");
	private JButton ok2Button=new JButton(ok2ButtonImg);
	private ImageIcon goButtonImg=new ImageIcon("gobtn.png");
	private JButton goButton=new JButton(goButtonImg);
	private ImageIcon okButtonImg=new ImageIcon("ok.png");
	private JButton okButton=new JButton(okButtonImg);
	// 체크박스 선택 시 보여지는 총 구매비용
	private int allSum = 0;

	// 체크박스 선택한 것
	private int[] purchasedArray = new int[4];

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 520;

	private JFrame frame;
	public ClientView view;
	// 주사위 이미지
	
	
	private ImageIcon origDice1Img = new ImageIcon("dice1-1.png");
	private ImageIcon origDice2Img = new ImageIcon("dice1-2.png");
	private ImageIcon yesButtonImg = new ImageIcon("yesButton.png");
	private ImageIcon noButtonImg = new ImageIcon("noButton.png");
	private ImageIcon turnEndButtonImg = new ImageIcon("turnEnd.png");
	private JPanel goldKeyInfo;
	private JPanel CityPurchaseInfo; //도시 보드패널
	private JPanel ownerExistsInfo;
	private JLabel icon1;
	ImageIcon originicon1 = new ImageIcon("icon1.png");
	Image origin = originicon1.getImage();
	Image originimg = origin.getScaledInstance(30,30,Image.SCALE_SMOOTH);
	ImageIcon originlast = new ImageIcon(originimg);
	private JLabel dice1, dice2; // 주사위
	private ArrayList<JLabel> playerMoney;
	//집,빌딩,호텔 이미지
	ImageIcon houseimg = new ImageIcon("img_house.png");
	Image house = houseimg.getImage();
	Image houselast = house.getScaledInstance(40,40,Image.SCALE_SMOOTH);
	
	ImageIcon buildingimg = new ImageIcon("img_building.png");
	Image build = buildingimg.getImage();
	Image buildlast = build.getScaledInstance(40,40,Image.SCALE_SMOOTH);
	
	ImageIcon hotleimg = new ImageIcon("img_hotel.png");
	Image hotel = hotleimg.getImage();
	Image hotellast = hotel.getScaledInstance(40,40,Image.SCALE_SMOOTH);
	private ImageIcon user1FlagImg = new ImageIcon("flag.png");
	private ImageIcon user1HouseImg = new ImageIcon("yellow-house.png");
	private ImageIcon user1BuildingImg = new ImageIcon("yellow-building.png");
	private ImageIcon user1HotelImg = new ImageIcon("yellow-hotel.png");
	
	private ImageIcon user2FlagImg = new ImageIcon("flag.png");
	private ImageIcon user2HouseImg = new ImageIcon("violet-house.png");
	private ImageIcon user2BuildingImg = new ImageIcon("violet-building.png");
	private ImageIcon user2HotelImg = new ImageIcon("violet-hotel.png");
	
	private ImageIcon user3FlagImg = new ImageIcon("flag.png");
	private ImageIcon user3HouseImg = new ImageIcon("red-house.png");
	private ImageIcon user3BuildingImg = new ImageIcon("red-building.png");
	private ImageIcon user3HotelImg = new ImageIcon("red-hotel.png");
	
	private ImageIcon user4FlagImg = new ImageIcon("flag.png");
	private ImageIcon user4HouseImg = new ImageIcon("green-house.png");
	private ImageIcon user4BuildingImg = new ImageIcon("green-building.png");
	private ImageIcon user4HotelImg = new ImageIcon("green-hotel.png");
	
	
	private JButton yesButton = new JButton(yesButtonImg);
	private JButton noButton = new JButton(noButtonImg);
	
	private JCheckBox landCheck = new JCheckBox();
	private JCheckBox houseCheck = new JCheckBox();
	private JCheckBox buildingCheck = new JCheckBox();
	private JCheckBox hotelCheck = new JCheckBox();
	private JLabel landPrice = new JLabel();
	private JLabel housePrice = new JLabel();
	private JLabel buildingPrice = new JLabel();
	private JLabel hotelPrice = new JLabel();
	private JLabel landTitle = new JLabel();
	private JLabel landOwnerLabel = new JLabel();
	private JLabel tollFeeLabel = new JLabel();
	// 오른쪽 플레이어창, 채팅창
		private JPanel isprisonInfo;
		private JPanel player1Info, playerChatPanel;
		private JLabel player1Img, player2Img, player3Img, player4Img;
		private JLabel cardInfoLabel = new JLabel();
		private JLabel giftMoneyLabel = new JLabel();
		private JButton withdrawButton = new JButton(confirmButtonImg);
		private JButton depositButton = new JButton(confirmButtonImg);
		private JButton confirmButton = new JButton(confirmButtonImg);
		//프로필 필드!!
		private JLabel usernameField = new JLabel("");
		private JLabel usermoneyField = new JLabel("");
		private JLabel userturnField = new JLabel("");
		JScrollPane scrollPane=new JScrollPane();
		// 오른쪽 채팅창
		private ScrollPane scChatList;
		private JTextArea playerChatList;
		private JLabel infoLabel = new JLabel();
		private JLabel[] userSequenceLabel = new JLabel[4];
		private JLabel sequenceLabel = new JLabel();
		private JTextField playerChatField;
		//gamepanel
		//이미지 집, 빌딩, 호텔 
		private JLabel board0 = new JLabel();
		private JLabel board1 = new JLabel();
		private JLabel board2 = new JLabel();
		private JLabel board3 = new JLabel();
		private JLabel board4 = new JLabel();
		private JLabel board5 = new JLabel();
		private JLabel board6 = new JLabel();
		private JLabel board7 = new JLabel();
		private JLabel board8 = new JLabel();
		private JLabel board9 = new JLabel();
		private JLabel board10 = new JLabel();
		private JLabel board11 = new JLabel();
		private JLabel board12 = new JLabel();
		private JLabel board13 = new JLabel();
		private JLabel board14 = new JLabel();
		private JLabel board15 = new JLabel();
		private JLabel board16 = new JLabel();
		private JLabel board17 = new JLabel();
		private JLabel board18 = new JLabel();
		private JLabel board19 = new JLabel();
		private JLabel board20 = new JLabel();
		private JLabel board21 = new JLabel();
		private JLabel board22 = new JLabel();
		private JLabel board23 = new JLabel();
		private JLabel board24 = new JLabel();
		private JLabel board25 = new JLabel();
		private JLabel board26 = new JLabel();
		private JLabel board27 = new JLabel();
		private Player char1,char2,char3,char4;
		private Container c;
		//특수한 이벤트가 없는 타일 구분 
		private JLabel board2Normal,board3Normal,board4Normal,board6Normal; //아래 가로줄 
		private JLabel board2inland,board3inland,board4inland,board6inland; //라벨 위 
		
		private RotatedLabel board8Normal,board9Normal,board10Normal,board12Normal,board13Normal; //왼쪽 세로줄
		private JLabel board8inland, board9inland, board10inland, board12inland,board13inland;

		private JLabel board16Normal,board17Normal1,board19Normal,board20Normal; //위 가로줄
		private JLabel board16inland,board17inland,board19inland,board20inland;
		
		private RotatedLabel board23Normal,board24Normal,board25Normal,board27Normal;  //오른쪽 세로줄
		private JLabel board23inland, board24inland, board25inland, board27inland;
	//	private JButton[] boardBtn = new JButton[27];
		//땅,집, 빌딩, 호텔 
		public JLabel user1Flag[] = new JLabel[26];
		public JLabel user1House[] = new JLabel[26];
		public JLabel user1Building[] = new JLabel[26];
		public JLabel user1Hotel[] = new JLabel[26];
		public JLabel user2Flag[] = new JLabel[26];
		public JLabel user2House[] = new JLabel[26];
		public JLabel user2Building[] = new JLabel[26];
		public JLabel user2Hotel[] = new JLabel[26];
		public JLabel user3Flag[] = new JLabel[26];
		public JLabel user3House[] = new JLabel[26];
		public JLabel user3Building[] = new JLabel[26];
		public JLabel user3Hotel[] = new JLabel[26];
		public JLabel user4Flag[] = new JLabel[26];
		public JLabel user4House[] = new JLabel[26];
		public JLabel user4Building[] = new JLabel[26];
		public JLabel user4Hotel[] = new JLabel[26];

		private ArrayList<JLabel> boarddown, boardup, boardleft, boardright; //밑,위,왼쪽,오른쪽
		private int[] arrayinit = {0,0,0,0};
		private JLabel boardCenter; //중앙
		 ImageIcon bg = new ImageIcon("boro.png");
	     Image bgicon = bg.getImage();
	     Image changeImg = bgicon.getScaledInstance(620,600,Image.SCALE_SMOOTH);
	     ImageIcon imglast = new ImageIcon(changeImg);

		
		ImageIcon card = new ImageIcon("card.jpg");
		Image cardicon = card.getImage();
		Image cardImg = cardicon.getScaledInstance(100,140,Image.SCALE_SMOOTH);
		ImageIcon cardlast = new ImageIcon(cardImg);
		
		ImageIcon fire = new ImageIcon("fireboard.png");
		Image fireicon = fire.getImage();
		Image fireImg = fireicon.getScaledInstance(100,140,Image.SCALE_SMOOTH);
		ImageIcon firelast = new ImageIcon(fireImg);
		
		ImageIcon card2 = new ImageIcon("cardr.jpg");
		Image card2icon = card2.getImage();
		Image card2Img = card2icon.getScaledInstance(118,100,Image.SCALE_SMOOTH);
		ImageIcon card2last = new ImageIcon(card2Img);
		
		ImageIcon prison = new ImageIcon("prison.jpg");
		Image prisonicon = prison.getImage();
		Image prisonImg = prisonicon.getScaledInstance(125,138,Image.SCALE_SMOOTH);
		ImageIcon prisonlast = new ImageIcon(prisonImg);
		
		ImageIcon train = new ImageIcon("train.jpg");
		Image trainicon = train.getImage();
		Image trainImg = trainicon.getScaledInstance(125,129,Image.SCALE_SMOOTH);
		ImageIcon trainlast = new ImageIcon(trainImg);
		
		ImageIcon concert = new ImageIcon("concert.jpg");
		Image concerticon = concert.getImage();
		Image concertImg = concerticon.getScaledInstance(120,129,Image.SCALE_SMOOTH);
		ImageIcon concertlast = new ImageIcon(concertImg);
		
		
		
		ImageIcon ch1 = new ImageIcon("char1.png");
		Image ch1icon = ch1.getImage();
		Image ch1Img = ch1icon.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		Image ch1Img2 = ch1icon.getScaledInstance(70,70,Image.SCALE_SMOOTH);
		//ImageIcon ch1last = new ImageIcon(ch1Img);
		ImageIcon ch1last2 = new ImageIcon(ch1Img2);
		
		ImageIcon ch2 = new ImageIcon("char2.png");
		Image ch2icon = ch2.getImage();
		Image ch2Img = ch2icon.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		Image ch2Img2 = ch2icon.getScaledInstance(70,70,Image.SCALE_SMOOTH);
		ImageIcon ch2last2 = new ImageIcon(ch2Img2);
		
		ImageIcon ch3 = new ImageIcon("char3.png");
		Image ch3icon = ch3.getImage();
		Image ch3Img = ch3icon.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		Image ch3Img2 = ch3icon.getScaledInstance(70,70,Image.SCALE_SMOOTH);
		ImageIcon ch3last2 = new ImageIcon(ch3Img2);
		ImageIcon start = new ImageIcon("start.jpg");
		ImageIcon tx = new ImageIcon("bg_Tx.jpg");
		ImageIcon ch4 = new ImageIcon("char4.png");
		Image ch4icon = ch4.getImage();
		Image ch4Img = ch4icon.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		Image ch4Img2 = ch4icon.getScaledInstance(70,70,Image.SCALE_SMOOTH);
		ImageIcon ch4last2 = new ImageIcon(ch4Img2);

		private ImageIcon user1PieceImg = new ImageIcon(ch1Img);
		private ImageIcon user2PieceImg = new ImageIcon(ch2Img);
		private ImageIcon user3PieceImg = new ImageIcon(ch3Img);
		private ImageIcon user4PieceImg = new ImageIcon(ch4Img);
		
		
		ImageIcon roll = new ImageIcon("roll.png");
		Image rollicon = roll.getImage();
		Image rollImg = rollicon.getScaledInstance(150,131,Image.SCALE_SMOOTH);
		//ImageIcon rolllast = new ImageIcon(rollImg);
		// 주사위 click 버튼 이미지 (순서결정)
		private ImageIcon sequenceClickButtonImg = new ImageIcon(rollImg);
		// 주사위 click 버튼 이미지 (본 게임)
		private ImageIcon clickButtonImg = new ImageIcon(rollImg);	
	
	private JButton bu=new JButton("dice roll");
	private JButton btnSend;
	private JTextPane textArea;
	
	// 주사위 클릭 버튼 (순서 결정)
	private JButton sequenceClickButton = new JButton(sequenceClickButtonImg);
		// 주사위 클릭 버튼 (본 게임)
	private JButton clickButton = new JButton(clickButtonImg);
		// 턴 종료 버튼
	private JButton turnEndButton = new JButton(turnEndButtonImg);
	public String id;
	 private int[] purchased = {-1,-1,-1}; // 0->집, 1->빌딩 2->호텔
	   
	   
	   private void boardVector() {
	         BoardVec = new Vector<Board>(27);
	         Board b0 = new Board("시작",0,6,720, 660);
	         Board b1 = new Board("산불",1,4, 620,660);
	         Board b2 = new Board("강릉", 2, 0, 520, 660, null, purchased,1000,2000,5000,20000,1000,2000,3000,5000,5000,547, 685,504, 690, 538, 692, 558, 692);
	         Board b3 = new Board("여수", 3, 0, 420, 660, null,purchased,1000, 2000,5000, 20000,1000, 2000,3000,5000,5000,450, 685,404, 690, 438, 692, 460, 692);
	         Board b4 = new Board("가평", 4, 0, 320, 660, null, purchased,1000, 2000, 5000, 20000,1000, 2000, 3000,5000,5000,350, 685,304, 690, 338, 692, 358, 692);
	         Board b5 = new Board("찬스키", 5,4, 220, 660);
	         Board b6 = new Board("부산", 6, 0, 120, 660, null, purchased,1000, 2000, 5000, 20000,1000, 2000, 3000,5000,5000,150, 685,103, 690, 134, 692, 154, 692);
	         Board b7 = new Board("감옥", 7, 5,0, 660);
	         Board b8 = new Board("울산", 8, 0, 60, 570, null,purchased,1000, 2000, 5000, 20000,1000, 2000, 3000,5000,5000,60, 585,60, 597, 65, 577, 65, 548);
	         Board b9 = new Board("광주", 9, 0, 60, 480, null,purchased,1000,2000, 5000, 20000,1000, 2000, 3000,5000,5000,60,490,60, 512, 65, 487, 65, 457);
	         Board b10 = new Board("보령", 10, 0, 60, 390, null, purchased,1000,2000, 5000, 20000,1000, 2000, 3000,5000,5000,60,400,60, 420, 65, 400, 65, 370);
	         Board b11 = new Board("찬스키",11,4, 60, 340);
	         Board b12 = new Board("청주", 12, 0, 60, 210,null,purchased,1000,2000, 5000, 20000,1000, 2000, 3000,5000,5000,60,220,60, 240, 65, 218, 65, 192);
	         Board b13 = new Board("여수", 13, 0, 60, 120,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,5000,60,130,60, 150, 65, 128, 65, 102);
	         Board b14 =  new Board("공연", 14,3, 0, 0);
	         Board b15 =  new Board("찬스", 15, 4,120, 0);
	         Board b16 = new Board("경주", 16, 0, 220, 0,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,5000,257,30,200, 33, 237, 31, 263, 31);
	         Board b17 = new Board("제주도", 17, 0, 320, 0,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,5000,355,30,300, 33, 332, 31, 332, 392);
	         Board b18 = new Board("찬스키", 18,4, 420, 0);
	         Board b19 = new Board("대전", 19, 0, 520, 0, null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,10000,555,30,500, 33, 534, 31, 332, 392);
	         Board b20 = new Board("서울", 20, 0, 620, 0,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,10000,655,30,600, 33, 632, 31, 332, 392);
	         Board b21 = new Board("ktx", 21,1, 720, 0);
	         Board b22 = new Board("찬스키", 22,4, 720, 130);
	         Board b23 = new Board("포항", 23, 0, 730, 230,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,10000,730,230,695, 240, 700, 220, 332, 392);
	         Board b24 = new Board("안산", 24, 0, 730, 320,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,10000,730,320,695, 332, 700, 310, 332, 392);
	         Board b25 = new Board("인천", 25, 0, 730, 410,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,10000,730,410,695, 420, 700, 400, 332, 392);
	         Board b26 = new Board("국세청", 26, 2,730,500);
	         Board b27 = new Board("목포", 27, 0, 730, 590,null,purchased,1000,2000, 5000, 30000,1000, 2000, 3000,5000,10000,730,590,695, 600, 700, 580, 332, 392);
	      BoardVec.add(b0);
	      BoardVec.add(b1); //값을 넣어줌 
	      BoardVec.add(b2);
	      BoardVec.add(b3);
	      BoardVec.add(b4);
	      BoardVec.add(b5);
	      BoardVec.add(b6);
	      BoardVec.add(b7);
	      BoardVec.add(b8);
	      BoardVec.add(b9);
	      BoardVec.add(b10);
	      BoardVec.add(b11);
	      BoardVec.add(b12);
	      BoardVec.add(b13);
	      BoardVec.add(b14);
	      BoardVec.add(b15);
	      BoardVec.add(b16);
	      BoardVec.add(b17);
	      BoardVec.add(b18);
	      BoardVec.add(b19);
	      BoardVec.add(b20);
	      BoardVec.add(b21);
	      BoardVec.add(b22);
	      BoardVec.add(b23);
	      BoardVec.add(b24);
	      BoardVec.add(b25);
	      BoardVec.add(b26);
	      BoardVec.add(b27);
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
	//player 말
	private JLabel user1Piece = new JLabel(user1PieceImg);
	private JLabel user2Piece = new JLabel(user2PieceImg);
	private JLabel user3Piece = new JLabel(user3PieceImg);
	private JLabel user4Piece = new JLabel(user4PieceImg);
	private Player[] player = new Player[4];
	public ClientView(String username, String ip_addr, String port_no) {
		boardVector();
		UserName = username;
		setTitle("부루마블");
		setVisible(true);
		setResizable(false);
		setSize(1149,820);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		
		
		c=getContentPane(); //메인
		c.setLayout(null);
		//chane
		//도시 패널 
		CityPurchaseInfo = new JPanel() {
			Image purchasei = cityPurchaseInfoImg.getImage();
			Image purchaseimg = purchasei.getScaledInstance(280,470,Image.SCALE_SMOOTH);
			ImageIcon purlast = new ImageIcon(purchaseimg);
			Image background = purlast.getImage();
			public void paint(Graphics g) {
				g.drawImage(background, 0, 0, null);
			}
		}; 
		ownerExistsInfo = new JPanel() {
				Image owner = ownerExistsInfoImg.getImage();
				Image ownerimg = owner.getScaledInstance(280,470,Image.SCALE_SMOOTH);
				ImageIcon ownerlast = new ImageIcon(ownerimg);
				Image background = ownerlast.getImage();
				public void paint(Graphics g) {
					g.drawImage(background, 0,0,null);
				}
				
		};
		//찬스키 패널 
		goldKeyInfo = new JPanel() {
			Image goldkey = goldkeyInfoImg.getImage();
			Image gold1img = goldkey.getScaledInstance(280,480,Image.SCALE_SMOOTH);
			ImageIcon goldlast = new ImageIcon(gold1img);
			Image background = goldlast.getImage();
			public void paint(Graphics g) {
				g.drawImage(background,0,0,null);
			}
		};
		//여행 패널 
	      travel = new JPanel() {
	         Image trip = travelImg.getImage();
	         Image trip1 = trip.getScaledInstance(280,480,Image.SCALE_SMOOTH);
	         ImageIcon triplast = new ImageIcon(trip1);
	         Image background = triplast.getImage();
	         public void paint(Graphics g) {
	            g.drawImage(background,0,0,null);
	         }
	      };
	      //기부처 패널 
	      donate = new JPanel() {
	               Image don = donateImg.getImage();
	               Image don1 = don.getScaledInstance(280,480,Image.SCALE_SMOOTH);
	               ImageIcon donlast = new ImageIcon(don1);
	               Image background = donlast.getImage();
	               public void paint(Graphics g) {
	                  g.drawImage(background,0,0,null);
	               }
	      };
		//콘서트 통행료
		tollfee=new JPanel() {
	         Image toll=tollfeeImg.getImage();
	         Image toll1=toll.getScaledInstance(280, 480, Image.SCALE_SMOOTH);
	         ImageIcon tollast=new ImageIcon(toll1);
	         Image background=tollast.getImage();
	         public void paint(Graphics g) {
	            g.drawImage(background,0,0,null);
	         }
	      };
		isprisonInfo = new JPanel() {
			Image prisom = isprisonInfoImg.getImage();
			Image prisonimg = prisom.getScaledInstance(280,480,Image.SCALE_SMOOTH);
			ImageIcon prisonlast = new ImageIcon(prisonimg);
			Image background = prisonlast.getImage();

			public void paint(Graphics g) {
				g.drawImage(background, 0, 0, null);
			}
		};
		ok2Button.setBounds(380, 560, 120, 40);
	    add(ok2Button);
	    ok2Button.setVisible(false);
	      
	    goButton.setBounds(380, 560, 120, 40);
	    add(goButton);
	    goButton.setVisible(false);
	    okButton.setBounds(380, 560, 120, 40);
	    add(okButton);
	    okButton.setVisible(false);
		minusButton.setBounds(380,560,120,40);
	    add(minusButton);
	    minusButton.setVisible(false);
	    moneym.setBounds(300, 500, 70, 30);
	    add(moneym);
	    moneym.setVisible(false);   
	    minusLabel.setBounds(350, 500, 70, 30);
	    add(minusLabel);
	    minusLabel.setVisible(false);
		yesButton.setBounds(330, 520, 80, 40);
		noButton.setBounds(418, 520, 80, 40);
		yesButton.setBorderPainted(false);
		yesButton.setContentAreaFilled(false);
		noButton.setBorderPainted(false);
		noButton.setContentAreaFilled(false);
		yesButton.setVisible(false);
		noButton.setVisible(false);
		add(yesButton);
		add(noButton);
		landCheck.setBounds(300,400,30,30);
		landCheck.setContentAreaFilled(false);
		houseCheck.setBounds(366, 400, 30, 30);
		houseCheck.setContentAreaFilled(false);
		buildingCheck.setBounds(431, 400, 30, 30);
		buildingCheck.setContentAreaFilled(false);
		hotelCheck.setBounds(504, 400, 30, 30);
		hotelCheck.setContentAreaFilled(false);
		landCheck.setVisible(false);
		houseCheck.setVisible(false);
		buildingCheck.setVisible(false);
		hotelCheck.setVisible(false);
		add(landCheck);
		add(houseCheck);
		add(buildingCheck);
		add(hotelCheck);
		//체크 버튼
		landOwnerLabel.setBounds(269,340,300,300);
		tollFeeLabel.setBounds(269,379,300,300);
		confirmButton.setBounds(405,560,30,30);
		confirmButton.setBorderPainted(false);
		confirmButton.setVisible(false);
		add(confirmButton);
		depositButton.setBounds(405,560,30,30);
		depositButton.setBorderPainted(false);
		depositButton.setVisible(false);
		add(depositButton);
		withdrawButton.setBounds(405,560,30,30);
		withdrawButton.setBorderPainted(false);
		withdrawButton.setVisible(false);
		add(withdrawButton);
		landTitle.setFont(new Font("BM JUA_OTF", Font.BOLD, 30));
		landTitle.setHorizontalAlignment(SwingConstants.CENTER);
		landTitle.setVisible(false);
		add(landTitle);
		giftMoneyLabel.setBounds(267,385,300,300);
		giftMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		giftMoneyLabel.setFont(new Font("BM JUA_OTF", Font.PLAIN, 20));
		giftMoneyLabel.setForeground(Color.blue);
		giftMoneyLabel.setVisible(false);
		add(giftMoneyLabel);
		housePrice.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 15));
		buildingPrice.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 15));
		hotelPrice.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 15));
		housePrice.setHorizontalAlignment(SwingConstants.CENTER);
		tollFeeLabel.setFont(new Font("BM JUA_OTF", Font.PLAIN, 20));
		tollFeeLabel.setForeground(Color.blue);
		tollFeeLabel.setVisible(false);
		add(tollFeeLabel);
		
		landOwnerLabel.setFont(new Font("BM JUA_OTF", Font.PLAIN, 20));
		landOwnerLabel.setForeground(Color.blue);
		landOwnerLabel.setVisible(false);
		add(landOwnerLabel);
		//가격 setBounds
		landPrice.setHorizontalAlignment(SwingConstants.CENTER);
		landOwnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buildingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		hotelPrice.setHorizontalAlignment(SwingConstants.CENTER);
		cardInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cardInfoLabel.setVisible(false);
		landPrice.setVisible(false);
		tollFeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		housePrice.setVisible(false);
		buildingPrice.setVisible(false);
		hotelPrice.setVisible(false);
		add(housePrice);
		add(buildingPrice);
		add(hotelPrice);
		add(cardInfoLabel);
		add(landPrice);
		icon1 = new JLabel(originlast);
		dice1 = new JLabel(origDice1Img);
		dice2 = new JLabel(origDice2Img);
		//다이스
		icon1.setBounds(300,170,50,40);
		icon1.setVisible(false);
		dice1.setBounds(255, 230, 43, 47);
		dice2.setBounds(324, 230, 43, 47);
		dice1.setVisible(false);
		dice2.setVisible(false);

		boardCenter = new JLabel(); //중간 보드
		boardCenter.setBounds(120, 120, 600, 540);
		//설명창
		/*infoLabel.setVisible(true);
		infoLabel.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 14));
		infoLabel.setBounds(153, 30, 300, 300);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		boardCenter.add(infoLabel);*/
		
		clickButton.setBounds(85, 280, 450, 330);
		clickButton.setVisible(false);
		boardCenter.add(clickButton);
		clickButton.setBorderPainted(false);
		clickButton.setContentAreaFilled(false);
		
		sequenceClickButton.setBounds(85,280,450,330);
		sequenceClickButton.setVisible(true);
		sequenceClickButton.setContentAreaFilled(false);
		sequenceClickButton.setBorderPainted(false);
		
		turnEndButton.setBounds(410, 450, 40, 40);
		turnEndButton.setVisible(false);
		turnEndButton.setBorderPainted(false);
		turnEndButton.setContentAreaFilled(false); //버튼 내용 제거해줌
		add(turnEndButton);
		
		sequenceLabel.setBounds(166, 36, 250, 20);
		sequenceLabel.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 15));
		sequenceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sequenceLabel.setText(" ➙            ➙            ➙");
		sequenceLabel.setVisible(false);
		boardCenter.add(sequenceLabel);
		
		//유저 초기화
		for (int i = 0; i < 4; i++) {
			player[i] = new Player(i, UserName, UserMoney, usernameField, usermoneyField, userturnField);
			purchasedArray[i] = -1;
			userSequenceLabel[i] = new JLabel();
			userSequenceLabel[i].setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 13));
			userSequenceLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			userSequenceLabel[i].setVisible(false);
			boardCenter.add(userSequenceLabel[i]);

		}
		//##
		userSequenceLabel[0].setBounds(120,40, 100, 20);
		userSequenceLabel[1].setBounds(202, 40, 100, 20);
		userSequenceLabel[2].setBounds(282, 40, 100, 20);
		userSequenceLabel[3].setBounds(372, 40, 100, 20);
		
		//집, 빌딩, 호텔
		
		
		player1Info = new JPanel(); //플레이어 창 
		player1Img = new JLabel();
		player2Img = new JLabel();
		player3Img = new JLabel();
		player4Img = new JLabel();
		
		//오른쪽 창
		playerChatPanel = new JPanel();
	    
		scChatList = new ScrollPane();
		playerChatList = new JTextArea();
		playerChatField = new JTextField(20);
		boarddown = new ArrayList<>();
		boardleft = new ArrayList<>();
		boardup = new ArrayList<>();
		boardright = new ArrayList<>();
		playerMoney = new ArrayList<>();
		
		player1Info.setBounds(839,0, 295, 400);
		player1Info.setBackground(Color.WHITE);
		player1Info.setLayout(null);
		
		CityPurchaseInfo.setVisible(true);
		isprisonInfo.setBounds(280,133,275,475);
		isprisonInfo.setVisible(false);
		
		tollfee.setBounds(280,133,275,475);
	    tollfee.setVisible(false);
	    travel.setBounds(280, 133, 275, 475);
	    travel.setVisible(false);
	    donate.setBounds(280, 133, 275, 475);
	    donate.setVisible(false);
		ownerExistsInfo.setBounds(280,133,275,475);
		ownerExistsInfo.setVisible(false);
		add(ownerExistsInfo);
		goldKeyInfo.setBounds(280,133,275,475);
		goldKeyInfo.setVisible(false);
		isprisonInfo.setVisible(false);
		add(tollfee);
		add(CityPurchaseInfo);
		add(goldKeyInfo);
		add(isprisonInfo);
		add(travel);
	    add(donate);
		playerChatPanel.setBounds(840, 400, 295, 380);
		scChatList.setSize(180, 370);
		playerChatField.setSize(200, 30);
		textArea=new JTextPane();
	      textArea.setEditable(true);
	      textArea.setFont(new Font("굴림체",Font.PLAIN,14));
	      scrollPane.setViewportView(textArea);
	     
		//땅 별로 버튼 설정 
		
		//발판 new
				board0.setBounds(720, 660, 120, 120);
				board1.setBounds(620, 660, 100, 120);
				board2.setBounds(520, 660, 100, 120);
				board3.setBounds(420, 660, 100, 120);
				board4.setBounds(320, 660, 100, 120);
				board5.setBounds(220, 660, 100, 120);
				board6.setBounds(120, 660, 100, 120);
				board7.setBounds(0, 660, 120, 120);
				board8.setBounds(0, 570, 120, 90);
				board9.setBounds(0, 480, 120, 90);
				board10.setBounds(0, 390, 120, 90);
				board11.setBounds(0, 300, 120, 90);
				board12.setBounds(0, 210, 120, 90);
				board13.setBounds(0, 120, 120, 90);
				
				board14.setBounds(0, 0, 120, 120);
				board15.setBounds(120, 0, 100, 120);
				board16.setBounds(220, 0, 100, 120);
				board17.setBounds(320, 0, 100, 120);
				board18.setBounds(420, 0, 100, 120);
				board19.setBounds(520, 0, 100, 120);
				board20.setBounds(620, 0, 100, 120);
				board21.setBounds(720, 0, 120, 120);
				board22.setBounds(720, 120, 120, 90);
				board23.setBounds(720, 210, 120, 90);
				board24.setBounds(720, 300, 120, 90);
				board25.setBounds(720, 390, 120, 90);
				board26.setBounds(720, 480, 120, 90);
				board27.setBounds(720, 570, 120, 90);
				
				
				//아래 down
				boarddown.add(board0);
				boarddown.add(board1);
				boarddown.add(board2);
				boarddown.add(board3);
				boarddown.add(board4);
				boarddown.add(board5);
				boarddown.add(board6);
				boarddown.add(board7); //감옥
				
				//왼쪽 left
				boardleft.add(board8);
				boardleft.add(board9);
				boardleft.add(board10);
				boardleft.add(board11);
				boardleft.add(board12);
				boardleft.add(board13);
				boardleft.add(board14);
				
				//위 up
				boardup.add(board15);
				boardup.add(board16);
				boardup.add(board17);
				boardup.add(board18);
				boardup.add(board19);
				boardup.add(board20);
				
				//오른쪽 right
				boardright.add(board21);
				boardright.add(board22);
				boardright.add(board23);
				boardright.add(board24);
				boardright.add(board25);
				boardright.add(board26);
				boardright.add(board27);
				//배열에 플레이어들 돈 담기
				;
				
				board2inland = new JLabel();
				board3inland = new JLabel();
				board4inland = new JLabel();
				board6inland = new JLabel();
				
				board8inland = new JLabel();
				board9inland = new JLabel();
				board10inland = new JLabel();
				board12inland = new JLabel();
				board13inland = new JLabel();
				
				board16inland = new JLabel();
				board17inland = new JLabel();
				board19inland = new JLabel();
				board20inland = new JLabel();
				
				board23inland = new JLabel();
				board24inland = new JLabel();
				board25inland = new JLabel();
				board27inland = new JLabel();
				
				//보드별 한국도시 이름
				
				board2Normal = new JLabel("강릉");
				board3Normal = new JLabel("여수");
				board4Normal = new JLabel("가평");
				board6Normal = new JLabel("부산");
				
				board8Normal = new RotatedLabel("울산");
				board9Normal = new RotatedLabel("광주");
				board10Normal = new RotatedLabel("보령");
				board12Normal = new RotatedLabel("청주");
				board13Normal = new RotatedLabel("여수");
				
				board16Normal = new JLabel("경주");
				board17Normal1 = new JLabel("제주도");
				board19Normal = new JLabel("대전");
				board20Normal = new JLabel("서울");
				
				board23Normal = new RotatedLabel("포항");
				board24Normal = new RotatedLabel("안산");
				board25Normal = new RotatedLabel("인천");
				board27Normal = new RotatedLabel("목포");
				
				
				//diceroll.setBounds(230, 280, 450, 330);
				//요기
				char1 = new Player(780,685,"char1.png");
				char2 = new Player(720,685,"char2.png");
				char3 = new Player(720,735,"char3.png");
				char4 = new Player(780,735,"char4.png");
				
		//테두리색을 지정하기위해 LineBorder 사용 
		//플레이어 창
		player1Info.setBorder(new LineBorder(new Color(0, 0, 0)));
	
		// 오른쪽 채팅창
		playerChatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		playerChatPanel.setLayout(new BorderLayout());
		playerChatList.setEditable(false);
		
		
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
		board8Normal.setBorder(new LineBorder(new Color(0,0,0)));
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
		
		
		
		boardCenter.setIcon(imglast);
		//diceroll.setIcon(sequenceClickButtonImg);
		//유저 창 캐릭터 이미지 
		player1Img.setIcon(ch1last2);
		player2Img.setIcon(ch2last2);
		player3Img.setIcon(ch3last2);
		player4Img.setIcon(ch4last2);
		
		player1Img.setBounds(10, 10, 80, 80);
		player1Img.setBorder(new LineBorder(new Color(255, 235, 74),5,true));
		player1Img.setVisible(true);
		player[0].setUserHouseImg(user1HouseImg);
		player[0].setUserFlagImg(user1FlagImg);
		player[0].setUserBuildingImg(user1BuildingImg);
		player[0].setUserHotelImg(user1HotelImg);
		player[0].setUserPieceImg(user1PieceImg);
		player[0].setUserPiece(user1Piece);
		player[0].setUserFlag(user1Flag);
		player[0].setUserHouse(user1House);
		player[0].setUserBuilding(user1Building);
		player[0].setUserHotel(user1Hotel);
		player[0].userPiece.setBounds(700,655, 100, 100);
		
		player[1].setUserPieceImg(user2PieceImg);
		player[1].setUserPiece(user2Piece);
		player[1].userPiece.setBounds(750, 655, 100, 100);
		player[1].setUserFlagImg(user2FlagImg);
		player[1].setUserHouseImg(user2HouseImg);
		player[1].setUserBuildingImg(user2BuildingImg);
		player[1].setUserHotelImg(user2HotelImg);
		player[1].setUserHouse(user2House);
		player[1].setUserBuilding(user2Building);
		player[1].setUserHotel(user2Hotel);
		player[1].setUserFlag(user2Flag);
		
		player[2].setUserPieceImg(user3PieceImg);
		player[2].setUserPiece(user3Piece);
		player[2].userPiece.setBounds(700, 695, 100, 100);
		player[2].setUserFlagImg(user3FlagImg);
		player[2].setUserHouseImg(user3HouseImg);
		player[2].setUserBuildingImg(user3BuildingImg);
		player[2].setUserHotelImg(user3HotelImg);
		player[2].setUserHouse(user3House);
		player[2].setUserBuilding(user3Building);
		player[2].setUserHotel(user3Hotel);
		player[2].setUserFlag(user3Flag);
		
		player[3].setUserPieceImg(user4PieceImg);
		player[3].setUserPiece(user4Piece);
		player[3].setUserFlagImg(user4FlagImg);
		player[2].setUserHouseImg(user4HouseImg);
		player[3].setUserBuildingImg(user4BuildingImg);
		player[3].setUserHotelImg(user4HotelImg);
		player[3].setUserFlag(user4Flag);
		player[3].setUserHouse(user4House);
		player[3].setUserBuilding(user4Building);
		player[3].setUserHotel(user4Hotel);
		player[3].userPiece.setBounds(750, 695, 100, 100);
		player2Img.setBounds(10, 100, 80, 80);
		player2Img.setBorder(new LineBorder(new Color(146, 129, 205),5,true));
		player2Img.setVisible(true);
		
		player3Img.setBounds(10, 200, 80, 80);
		player3Img.setBorder(new LineBorder(new Color(234, 87, 123),5,true));
		player3Img.setVisible(true);
		
		player4Img.setBounds(10, 300, 80, 80);
		player4Img.setBorder(new LineBorder(new Color(122, 173, 76),5,true));
		player4Img.setVisible(true);
		//플레이어 이름필드
		player[0].usernameField.setBounds(100, 10, 80, 20);
		player[1].usernameField.setBounds(100, 100, 80, 20);
		player[2].usernameField.setBounds(100, 210, 80, 20);
		player[3].usernameField.setBounds(100, 310, 80, 20);
		//플레이어 돈필드
		player[0].usermoneyField.setBounds(100, 75, 80, 20);
		player[1].usermoneyField.setBounds(100, 175, 80, 20);
		player[2].usermoneyField.setBounds(100, 275, 80, 20);
		player[3].usermoneyField.setBounds(100, 375, 80, 20);
		//플레이어 턴필드
		player[0].userturnField.setBounds(100, 45, 80, 20);
		player[1].userturnField.setBounds(100, 145, 80, 20);
		player[2].userturnField.setBounds(100, 245, 80, 20);
		player[3].userturnField.setBounds(100, 345, 80, 20);
		//턴 setText
		player[0].userturnField.setText("0/4");
		player[1].userturnField.setText("0/4");
		player[2].userturnField.setText("0/4");
		player[3].userturnField.setText("0/4");
		
		for(int i=0; i<4;i++) {
			player[i].usernameField.setHorizontalAlignment(SwingConstants.CENTER);
			player[i].usermoneyField.setHorizontalAlignment(SwingConstants.CENTER);
			player[i].userturnField.setHorizontalAlignment(SwingConstants.CENTER);
			player[i].usernameField.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 16));
			player[i].usermoneyField.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 13));
			player[i].userturnField.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 15));
			player[i].usernameField.setVisible(false);
			player[i].usermoneyField.setVisible(false);
			player[i].userturnField.setVisible(false);
			player[i].userPiece.setVisible(true);
			System.out.println(player[i].userPiece.getX());
			add(player[i].userPiece);
			player1Info.add(player[i].usernameField);
			player1Info.add(player[i].usermoneyField);
			player1Info.add(player[i].userturnField);
			System.out.println(player[i].usernameField.toString());
			//여기
		}
		
		board0.setIcon(start); //시작 
		board1.setIcon(firelast); //불 구덩이
		board5.setIcon(cardlast); //찬스키
		board7.setIcon(prisonlast); //감옥
		board11.setIcon(card2last); //왼쪽 찬스키
		board14.setIcon(concertlast); //공연
		board15.setIcon(cardlast); //찬스키
		board18.setIcon(cardlast); //찬스키
		board21.setIcon(trainlast);//기차
		board22.setIcon(card2last);
		board26.setIcon(tx); //국세청
		
		JLabel houseimg = new JLabel();
		/*houseimg.setIcon(new ImageIcon("C:\\Users\\samsung\\Downloads\\tilehouse.jpg"));
		houseimg.setBounds(3,3,30,30);*/
		JLabel buildimg = new JLabel();
		//buildimg.setIcon(new ImageIcon(GamePanel.class.getResource("/images/bg_build.jpg")));
		//buildimg.setBounds(32,3,30,30);
		//JLabel hotelimg = new JLabel();
		//hotelimg.setIcon(new ImageIcon(GamePanel.class.getResource("/images/bg_hotel.jpg")));
		//hotelimg.setBounds(63,3,30,30);

		
		
		// 발판별 이름 라벨 설정
		// Line 0
		board2Normal.setBounds(0, 90, 100, 30);
		board2Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board2Normal.setHorizontalAlignment(JLabel.CENTER);

		board3Normal.setBounds(0, 90, 100, 30);
		board3Normal.setFont(new Font("Dialog", Font.BOLD, 20));
		board3Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board4Normal.setBounds(0, 90, 100, 30);
		board4Normal.setFont(new Font("Dialog", Font.BOLD, 20));
		board4Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board6Normal.setBounds(0, 90, 100, 30);
		board6Normal.setFont(new Font("Dialog", Font.BOLD, 20));
		board6Normal.setHorizontalAlignment(JLabel.CENTER);

		//
		board8Normal.setBounds(0, 0, 40, 100);
		board9Normal.setBounds(0, 0, 40, 100);
		board10Normal.setBounds(0, 0, 40, 100);
		board12Normal.setBounds(0, 0, 40, 100);
		board13Normal.setBounds(0, 0, 40, 100);
		
		
		board16Normal.setBounds(0, 90, 100, 30);
		board16Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board16Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board17Normal1.setBounds(0, 90, 100, 30);
		board17Normal1.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board17Normal1.setHorizontalAlignment(JLabel.CENTER);
		
		board19Normal.setBounds(0, 90, 100, 30);
		board19Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board19Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board20Normal.setBounds(0, 90, 100, 30);
		board20Normal.setFont(new Font("CookieRun BLACK", Font.BOLD, 20));
		board20Normal.setHorizontalAlignment(JLabel.CENTER);
		
		board23Normal.setBounds(85, 0, 40, 100);
		board24Normal.setBounds(85, 0, 40, 100);
		board25Normal.setBounds(85, 0, 40, 100);
		board27Normal.setBounds(85, 0, 40, 100);
		
		board2inland.setBounds(0, 12, 100, 78);

		board3inland.setBounds(0, 12, 100, 78);

		board4inland.setBounds(0, 12, 100, 78);

		board6inland.setBounds(0, 12, 100, 78);
		
		board8inland.setBounds(90, 0, 40, 100);
		board9inland.setBounds(100, 0, 40, 100);
		board10inland.setBounds(100, 0, 40, 100);
		board12inland.setBounds(100, 0, 40, 100);
		board13inland.setBounds(100, 0, 40, 100);
		
		board16inland.setBounds(0, 12, 100,78);
		board17inland.setBounds(0, 12, 100, 78);
		board19inland.setBounds(0, 12, 100, 78);
		board20inland.setBounds(0, 12, 100, 78);
		
		board23inland.setBounds(100, 0, 40, 100);
		board24inland.setBounds(100, 0, 40, 100);
		board25inland.setBounds(100, 0, 40, 100);
		board27inland.setBounds(100, 0, 40, 100);
	
		
		add(board0);
		//하하
		//player[0].userHouse[0].setBounds(504, 690, 80, 80); //강릉
		//player[0].userBuilding[0].setBounds(538,692,70,70);//강릉
		
		//player[0].userBuilding[0].setBounds(438,692,70,70);//여수
		//player[0].userBuilding[0].setBounds(338,692,70,70);//가평
		//player[0].userBuilding[0].setBounds(134,692,70,70);//부산
		//player[0].userBuilding[0].setBounds(65,577,70,70);//울산
		//player[0].userBuilding[0].setBounds(65, 487, 70, 70); //광주
		//player[0].userBuilding[0].setBounds(65, 400, 70, 70); //보령
		//player[0].userBuilding[0].setBounds(65, 218, 70, 70); //청주
		//player[0].userBuilding[0].setBounds(65, 128, 70, 70); //여수
		//player[0].userBuilding[0].setBounds(237,31,70,70); //경주
		//player[0].userBuilding[0].setBounds(332,31,70,70); //제주도
		//player[0].userBuilding[0].setBounds(534,31,70,70); //대전
		//player[0].userBuilding[0].setBounds(632,31,70,70); //서울
		//player[0].userBuilding[0].setBounds(700,220,70,70); //포항
		//player[0].userBuilding[0].setBounds(700,310,70,70); //안산
		//player[0].userBuilding[0].setBounds(700,400,70,70); //인천
		//player[0].userBuilding[0].setBounds(700,580,70,70); //목포
		
		//player[0].userHotel[0].setBounds(558,692,70,70);//강릉
		//player[0].userHotel[0].setBounds(460,692,70,70);//여수
		//player[0].userHotel[0].setBounds(358,692,70,70);//가평
		//player[0].userHotel[0].setBounds(154,692,70,70);//부산
		//player[0].userHotel[0].setBounds(65,548,70,70);//울산
		//player[0].userHotel[0].setBounds(65,457,70,70);//광주
		//player[0].userHotel[0].setBounds(65,370,70,70);//보령
		//player[0].userHotel[0].setBounds(65, 192, 70, 70); //청주
		//player[0].userHotel[0].setBounds(65, 102, 70, 70); //여수
		//player[0].userHotel[0].setBounds(263,31,70,70);//경주
		//여기부터
		//player[0].userHotel[0].setBounds(263,31,70,70); //제주도
		//player[0].userHotel[0].setBounds(263,31,70,70); //대전
		//player[0].userHotel[0].setBounds(263,31,70,70); //서울
		//player[0].userHotel[0].setBounds(263,31,70,70); //포항
		//player[0].userHotel[0].setBounds(263,31,70,70); //안산
		//player[0].userHotel[0].setBounds(263,31,70,70); //인천
		//player[0].userHotel[0].setBounds(263,31,70,70); //목포
		//add(player[0].userHotel[0]);
		
		add(board1);
		add(board2);
		add(board3);
		add(board4);
		add(board5);
		add(board6);
		add(board7);
		add(board8);
		add(board9);
		add(board10);
		add(board11);
		add(board12);
		add(board13);
		add(board14);
		add(board15);
		add(board16);
		add(board17);
		add(board18);
		add(board19);
		add(board20);
		add(board21);
		add(board22);
		add(board23);
		add(board24);
		add(board25);
		add(board26);
		add(board27);
		add(boardCenter);
		//@@
		boardCenter.add(icon1);
		boardCenter.add(dice1);
		boardCenter.add(dice2);
		boardCenter.add(sequenceClickButton);
		board2.add(board2Normal);
		board3.add(board3Normal);
		board4.add(board4Normal);
		board6.add(board6Normal);
		
		board8.add(board8Normal);
		board9.add(board9Normal);
		board10.add(board10Normal);
		board12.add(board12Normal);
		board13.add(board13Normal);
		
		board16.add(board16Normal);
		board17.add(board17Normal1);
		board19.add(board19Normal);
		board20.add(board20Normal);
		
		board23.add(board23Normal);
		board24.add(board24Normal);
		board25.add(board25Normal);
		board27.add(board27Normal);
		
		board2.add(board2inland);
		board3.add(board3inland);
		board4.add(board4inland);
		board6.add(board6inland);
		
		board8.add(board8inland);
		board9.add(board9inland);
		board10.add(board10inland);
		board12.add(board12inland);
		board13.add(board13inland);
		
		board16.add(board16inland);
		board17.add(board17inland);
		board19.add(board19inland);
		board20.add(board20inland);
		
		board23.add(board23inland);
		board24.add(board24inland);
		board25.add(board25inland);
		board27.add(board27inland);
		
		add(player1Info);
		player1Info.add(player1Img);
		player1Info.add(player2Img);
		player1Info.add(player3Img);
		player1Info.add(player4Img);
		add(playerChatPanel);
	    playerChatPanel.add(scrollPane, BorderLayout.CENTER);
		//scChatList.add(playerChatList);
		//playerChatPanel.add(playerChatField, BorderLayout.SOUTH);
		
		
		
		
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
			
			Msg obcm = new Msg(UserName, Protocol.LOGIN, "Login");
			SendObject(obcm);
			ListenNetwork net = new ListenNetwork();
			net.start();
			
			
			// 리스너 등록
			sequenceClickButton.addActionListener(sequenceClickButtonListener); //게임 순서 정하는 주사위 
			minusButton.addActionListener(tollfeeListener); //콘서트 이벤트
			clickButton.addActionListener(listener); //주사위 이벤트
			depositButton.addActionListener(depositListener); //입금 이벤트
			confirmButton.addActionListener(confirmListener); //확인버튼
			yesButton.addActionListener(yesListener); //yes버튼 이벤트
			noButton.addActionListener(noListener); //no버튼 이벤트
			ok2Button.addActionListener(travelListener);
			turnEndButton.addActionListener(noListener); //턴종료 이벤트 
			landCheck.addItemListener(itemListener); //땅 체크박스 아이템 리스너
			houseCheck.addItemListener(itemListener); //집 체크박스 아이템 리스너 
			withdrawButton.addActionListener(withdrawListener); //출금 버튼 이벤트 
			buildingCheck.addItemListener(itemListener); //빌딩 체크박스 아이템 리스너 
			hotelCheck.addItemListener(itemListener); //호텔 체크박스 아이템 리스너 
			//배열에 발판 담기 
			for(int i=0; i< boarddown.size();i++) {
				boarddown.get(i).setLayout(null);
				boarddown.get(i).addMouseListener(clickLandListener);
			}
			for(int i=0; i< boardleft.size();i++) {
				boardleft.get(i).setLayout(null);
				boardleft.get(i).addMouseListener(clickLandListener);
			}
			for(int i=0; i< boardup.size();i++) {
				boardup.get(i).setLayout(null);
				boardup.get(i).addMouseListener(clickLandListener);
			}
			for(int i=0; i< boardright.size();i++) {
				boardright.get(i).setLayout(null);
				boardright.get(i).addMouseListener(clickLandListener);
			}
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//AppendText("connect error");
		}

	}

	
	// 서버에서 결정된 주사위 수를 주사위 이미지로 보여주는 함수
		public void sequenceSetDice(ImageIcon servDice1, ImageIcon servDice2) {
		
			boardCenter.remove(dice1);
			boardCenter.remove(dice2);

		  
			dice1 = new JLabel(servDice1);
			dice2 = new JLabel(servDice2);

			dice1.setBounds(255, 230, 43, 47);
			dice2.setBounds(324, 230, 43, 47);
		
			System.out.println(servDice1+" "+servDice2);
			
			boardCenter.add(dice1);
			boardCenter.add(dice2);
			boardCenter.repaint();

		}

		// 서버에서 결정된 주사위 수를 주사위 이미지로 보여주는 함수
		public void showDice(String username, ImageIcon servDice1, ImageIcon servDice2,ImageIcon servIcon1, int X, int Y) {
			//public void showDice(String username, int nextX,int nextY,int boardNum,int oldBoardNum,ImageIcon servDice1,ImageIcon servDice2,
			//ImageIcon servIcon1, int X, int Y) {
			boardCenter.remove(dice1);
			boardCenter.remove(dice2);
			boardCenter.remove(icon1);
			icon1 = new JLabel(servIcon1);
			dice1 = new JLabel(servDice1);
			dice2 = new JLabel(servDice2);
			icon1.setBounds(270,95,90,90);
			dice1.setBounds(255, 230, 43, 47);
			dice2.setBounds(324, 230, 43, 47);
			boardCenter.add(icon1);
			boardCenter.add(dice1);
			boardCenter.add(dice2);
			//moveAnimation(username,nextX, nextY, boardNum,oldBoardNum,X,Y);
			//movePiece(username, X, Y);
			
			boardCenter.repaint();

		}
		
	
	// Server Message를 수신해서 화면에 표시
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {
					Object obcm = null;
					Vector v = null;
					String msg = null;
					Msg cm;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof Msg) {
						cm = (Msg) obcm;
					} else
						continue;
					switch(cm.code) {
					case Protocol.LOGIN:
						msg = cm.data;
						AppendText(msg);
						break;
					case Protocol.AllUserLogin: //모든 참가자가 로그인 하면 
						msg = cm.data;
						AppendText(msg);
						PlaySound("background.wav",true);
						
						dice1.setVisible(true);
						dice2.setVisible(true);
						sequenceClickButton.setVisible(true);
						setProfile(cm.strArray, cm.intArray, cm.player_size, cm.turnNum, cm.numofturns);
						break;
					case Protocol.SEQUENCE_SERVER:
						msg = cm.data;
						AppendText(msg);
						setProfile(cm.strArray, cm.intArray, cm.player_size, cm.turnNum, cm.numofturns);
						
						dice1.setVisible(true);
						dice2.setVisible(true);
						break;
					case Protocol.SEQUENCE_DICE: //순서를 정하기 위해 주사위를 돌림
						msg = cm.data;
						AppendText(msg);
						for(int i=0;i<7;i++) {
		                     Random r1 = new Random();
		                     Random r2 = new Random();
		                     int rollNum1 = r1.nextInt(6) + 1;
		                     int rollNum2 = r2.nextInt(6) + 1;
		                     ImageIcon newDice1 = new ImageIcon("step" + rollNum1 + ".png");
		                     ImageIcon newDice2 = new ImageIcon("step" + rollNum2 + ".png");
		                     try {
		                        try {
		                           Thread.sleep(40);
		                           sequenceSetDice(newDice1,newDice2);
		                        } catch (InterruptedException e) {
		                           // TODO Auto-generated catch block
		                           e.printStackTrace();
		                        }
		                        //sequenceSetDice(newDice1,newDice2);
		                        
		                     }
		                     finally {
		                        
		                     }
		                     
		                  }

						sequenceSetDice(cm.img1, cm.img2);
						break;
					case Protocol.YOUR:
						msg = cm.data;
						AppendText(msg);
						sequenceSetDice(cm.img1, cm.img2);
						  //중복시에도 주사위 애니메이션 효과 주기위해
		                  for(int i=0;i<7;i++) {
		                     Random r1 = new Random();
		                     Random r2 = new Random();
		                     int rollNum1 = r1.nextInt(6) + 1;
		                     int rollNum2 = r2.nextInt(6) + 1;
		                     ImageIcon newDice1 = new ImageIcon("step" + rollNum1 + ".png");
		                     ImageIcon newDice2 = new ImageIcon("step" + rollNum2 + ".png");
		                     try {
		                        try {
		                           Thread.sleep(40);
		                           sequenceSetDice(newDice1,newDice2);
		                        } catch (InterruptedException e) {
		                           // TODO Auto-generated catch block
		                           e.printStackTrace();
		                        }
		                        //sequenceSetDice(newDice1,newDice2);
		                        
		                     }
		                     finally {
		                        
		                     }
		                     
		                  }

						sequenceClickButton.setVisible(true);
						break;
					case Protocol.GAMESTART:
						msg = cm.data;
						AppendText(msg);
						sequenceLabel.setVisible(true);
						for (int i = 0; i < 4; i++) {
							userSequenceLabel[i].setText(cm.strArray[i]);
							userSequenceLabel[i].setVisible(true);
							userSequenceLabel[i].setFont(new Font("Serif",Font.BOLD,28));
							if (i == cm.nowSequence)
								userSequenceLabel[i].setForeground(Color.red);
							else
								userSequenceLabel[i].setForeground(Color.black);
						}
						//주사위 중복시 중복아닐때까지 주사위 굴리다가 중복아닌 숫자 나오는 경우의 주사위 굴릴때도 애니메이션 효과주기위해
		                  for(int i=0;i<7;i++) {
		                     Random r1 = new Random();
		                     Random r2 = new Random();
		                     int rollNum1 = r1.nextInt(6) + 1;
		                     int rollNum2 = r2.nextInt(6) + 1;
		                     ImageIcon newDice1 = new ImageIcon("step" + rollNum1 + ".png");
		                     ImageIcon newDice2 = new ImageIcon("step" + rollNum2 + ".png");
		                     try {
		                        try {
		                           Thread.sleep(40);
		                           sequenceSetDice(newDice1,newDice2);
		                        } catch (InterruptedException e) {
		                           // TODO Auto-generated catch block
		                           e.printStackTrace();
		                        }
		                        //sequenceSetDice(newDice1,newDice2);
		                        
		                     }
		                     finally {
		                        
		                     }
		                     
		                  }

						clickButton.setVisible(true);
						sequenceSetDice(cm.img1, cm.img2);
						break;
					case Protocol.SEQUENCE_END:
						msg = cm.data;
						AppendText(msg);
						sequenceLabel.setVisible(true);
						for(int i=0;i<7;i++) {
		                     Random r1 = new Random();
		                     Random r2 = new Random();
		                     int rollNum1 = r1.nextInt(6) + 1;
		                     int rollNum2 = r2.nextInt(6) + 1;
		                     ImageIcon newDice1 = new ImageIcon("step" + rollNum1 + ".png");
		                     ImageIcon newDice2 = new ImageIcon("step" + rollNum2 + ".png");
		                     try {
		                        try {
		                           Thread.sleep(40);
		                           sequenceSetDice(newDice1,newDice2);
		                        } catch (InterruptedException e) {
		                           // TODO Auto-generated catch block
		                           e.printStackTrace();
		                        }
		                        //sequenceSetDice(newDice1,newDice2);
		                        
		                     }
		                     finally {
		                        
		                     }
		                     
		                  }
		                  for (int i = 0; i < 4; i++) {
		                     userSequenceLabel[i].setText(cm.strArray[i]);
		                     userSequenceLabel[i].setVisible(true);
		                     if (i == cm.nowSequence)
		                        userSequenceLabel[i].setForeground(Color.red);
		                     else
		                        userSequenceLabel[i].setForeground(Color.black);
		                  }
		                  sequenceSetDice(cm.img1, cm.img2); // 모든 참가자들이 굴러가는 주사위를 보기 위함
		                  break;

					case Protocol.YOUR_TURN:
						msg = cm.data;
						AppendText(msg);
						updateUserTurnNum(cm.UserName, cm.turnNum);
						for (int i = 0; i < 4; i++) {
							if (i == cm.nowSequence)
								userSequenceLabel[i].setForeground(Color.red);
							else
								userSequenceLabel[i].setForeground(Color.black);
						}
						clickButton.setVisible(true);// 현재 순서인 참가자에게만 주사위 클릭 버튼이 보이게 함
						break;
					
						
					case Protocol.DICE: // 현재 순서인 유저가 받는 프로토콜
						msg = cm.data;
						AppendText(msg);
						 for(int i=0;i<7;i++) {
		                     Random r1 = new Random();
		                     Random r2 = new Random();
		                     int rollNum1 = r1.nextInt(6) + 1;
		                     int rollNum2 = r2.nextInt(6) + 1;
		                     ImageIcon newDice1 = new ImageIcon("step" + rollNum1 + ".png");
		                     ImageIcon newDice2 = new ImageIcon("step" + rollNum2 + ".png");
		                     try {
		                        try {
		                           Thread.sleep(40);
		                           sequenceSetDice(newDice1,newDice2);
		                        } catch (InterruptedException e) {
		                           // TODO Auto-generated catch block
		                           e.printStackTrace();
		                        }
		                        //sequenceSetDice(newDice1,newDice2);
		                        
		                     }
		                     finally {
		                        
		                     }
		                     
		                  }

						//showDice(cm.UserName,cm.nextx,cm.nexty,cm.BoardNum,cm.oldBoardNum, cm.img1, cm.img2,cm.iconimg1,cm.BoardX, cm.BoardY);
						 showDice(cm.UserName,cm.img1, cm.img2,cm.iconimg1,cm.BoardX, cm.BoardY);
					     //채은
		                moveAnimation(cm.UserName,cm.BoardX,cm.BoardY,cm.BoardNum,cm.oldBoardNum);

						break;
					case Protocol.SET_MONEY:
						updateUserMoney(cm.intArray);
						break;
					case Protocol.SET_MONEY_AND_STRUCTURE:
						for (int i = 0; i < 4; i++) {
							if (cm.isPurchased[i] != 1)
								continue;
							for (int j = 0; j < 4; j++) {
								if (player[j].username.matches(cm.UserName) && cm.isPurchased[0] == 1
										&& cm.isPurchased[1] == -1 && cm.isPurchased[2] == -1
										&& cm.isPurchased[3] == -1) {  //땅만 구매한경우 
										
										player[j].userFlag[cm.flagCount].setBounds(cm.flagX,cm.flagY, 50, 57);
										add(player[j].userFlag[cm.flagCount]);
									}

								
								// 본인 소유 땅에 재방문해서 건물을 지은 경우 원래 세워진 flag를 없애고 건물을 지음
								if (player[j].username.matches(cm.UserName) && cm.isPurchased[0] == 1
										&& cm.BoardType == -1) {
									player[j].userFlag[cm.flagCount].setVisible(false);
								}
								// 별장 구매
								if (i == 1 && player[j].username.matches(cm.UserName)) {
									player[j].userHouse[cm.houseCount].setBounds(cm.houseX, cm.houseY, 80, 80);
									add(player[j].userHouse[cm.houseCount]);
								}
								// 빌딩 구매
								if (i == 2 && player[j].username.matches(cm.UserName)) {
									player[j].userBuilding[cm.buildingCount].setBounds(cm.buildingX, cm.buildingY, 200,
											200);
									add(player[j].userBuilding[cm.buildingCount]);
								}
								// 호텔 구매
								if (i == 3 && player[j].username.matches(cm.UserName)) {
									player[j].userHotel[cm.hotelCount].setBounds(cm.hotelX, cm.hotelY, 200, 200);
									add(player[j].userHotel[cm.hotelCount]);
								}
							}
						}
						for (int i = 0; i < 4; i++) {
							player_money[i] = cm.intArray[i];
							player[i].setUsermoney(player_money[i]);
							player[i].usermoneyField.setText(String.valueOf(player[i].getUsermoney()) + "원");
						}
						repaint();
						break;
					case Protocol.SERVER:
						msg = cm.data;
						AppendText(msg);
						updateUserTurnNum(cm.UserName, cm.turnNum);
						for (int i = 0; i < 4; i++) {
							if (i == cm.nowSequence)
								userSequenceLabel[i].setForeground(Color.red);
							else
								userSequenceLabel[i].setForeground(Color.black);
						}
						
						break;
					case Protocol.VISIT_GOLD_KEY:
						goldKey(cm.UserName, cm.userMoney,cm.BoardName, cm.BoardType);
						break;
					case Protocol.VISIT_CITY:
						AppendText(cm.data);
						City(cm.UserName, cm.userMoney, cm.BoardName, cm.BoardType, cm.BoardOwner, cm.isPurchased,
								 cm.LandPrice,cm.HousePrice, cm.BuildPrice, cm.HotelPrice, cm.tollFee);
						break;
					case Protocol.VISIT_PRISON:
						island(cm.UserName, cm.BoardName, cm.BoardType);
						break;
					case Protocol.VISIT_CONCERT:
		                 tollFee(cm.UserName,cm.userMoney,cm.BoardName,cm.BoardType);
		                  System.out.println("concertttttttttt");
		                  break;
					
					}
					
					
				}
					catch (IOException e) {
					AppendText("dis.read() error");
					try {
						ois.close();
						oos.close();
						socket.close();
						break;
					} catch (Exception ee) {
						break;
					} // catch문 끝
				} // 바깥 catch문끝
				
			}
		}
	}
	//확인
	ActionListener confirmListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			isprisonInfo.setVisible(false);
			//ownerExistsInfo.setVisible(false);
			landTitle.setVisible(false);
			cardInfoLabel.setVisible(false);
			confirmButton.setVisible(false);
			turnEndButton.setVisible(true);

			if (BoardType == 5) { // 무인도인 경우
				Msg cm = new Msg(UserName, Protocol.VISIT_PRISON, "Island");
				SendObject(cm);
			}

		}

	};

	   ActionListener travelListener =new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	         travel.setVisible(false);
	         ok2Button.setVisible(false);
	         
	         Msg cm=new Msg(UserName,Protocol.TRAVEL,"TRAVEL");
	         
	         cm.BoardType=BoardType;
	         SendObject(cm);
	         //turnEndButton.setVisible(true);
	         
	      }
	      
	   };
	//yes버튼 리스너
	ActionListener yesListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CityPurchaseInfo.setVisible(false);
			landTitle.setVisible(false);
			cardInfoLabel.setVisible(false);
			yesButton.setVisible(false);
			noButton.setVisible(false);

			if (BoardType == 3) { // ktx
				stStatus = true;
				infoLabel.setVisible(true);
				infoLabel.setText("이동하실 땅을 클릭해주세요");
				for (int i = 0; i < 27; i++) { // 땅 버튼 활성화
				
				}

			} else {
				landCheck.setVisible(false);
				houseCheck.setVisible(false);
				buildingCheck.setVisible(false);
				hotelCheck.setVisible(false);
				
				landPrice.setVisible(false);
				housePrice.setVisible(false);
				buildingPrice.setVisible(false);
				hotelPrice.setVisible(false);
				turnEndButton.setVisible(true);
				infoLabel.setVisible(true);
				repaint();

				if (BoardType == 0)
					purchasedArray[0] = 1; 
				Msg cm = new Msg(UserName, Protocol.WITHDRAW_PURCHASE, "Purchase");
				cm.BoardOwner = UserName;
				cm.isPurchased = purchasedArray;
				cm.priceAll = allSum;
				cm.BoardType = BoardType;
				SendObject(cm);
			}

		}

	};
	ActionListener noListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CityPurchaseInfo.setVisible(false);
			landTitle.setVisible(false);
			landCheck.setVisible(false);
			houseCheck.setVisible(false);
			buildingCheck.setVisible(false);
			hotelCheck.setVisible(false);

			landPrice.setVisible(false);
			housePrice.setVisible(false);
			buildingPrice.setVisible(false);
			hotelPrice.setVisible(false);
			cardInfoLabel.setVisible(false);

			yesButton.setVisible(false);
			noButton.setVisible(false);

			turnEndButton.setVisible(false);
			
			repaint();

			Msg cm = new Msg(UserName, Protocol.TURN_END, "Turn End");
			SendObject(cm);

		}
	};	
	

	// keyboard enter key 치면 서버로 전송
	ItemListener itemListener = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			int select = 1;
			if(e.getStateChange() == ItemEvent.SELECTED)
				select = 1;
			else
				select = -1;
			if (e.getItem() == landCheck) {
				allSum += select * Integer.parseInt(landPrice.getText());
				purchasedArray[0] = select; //땅을 구매하면 1로 바뀜 
			} 
			else if (e.getItem() == houseCheck) {
				allSum += select * Integer.parseInt(housePrice.getText());
				purchasedArray[1] = select;
			} else if (e.getItem() == buildingCheck) {
				allSum += select * Integer.parseInt(buildingPrice.getText());
				purchasedArray[2] = select;
			} else if (e.getItem() == hotelCheck) {
				allSum += select * Integer.parseInt(hotelPrice.getText());
				purchasedArray[3] = select;
			}
			if ((UserMoney - allSum) < 0) {
				houseCheck.setSelected(false);
				buildingCheck.setSelected(false);
				hotelCheck.setSelected(false);
			}

			if (BoardType != -1) { // 첫 방문일 경우
				if (((UserMoney - allSum) >= 0) && (landCheck.isSelected() == true))
					yesButton.setVisible(true);
				else
					yesButton.setVisible(false);
			}

			else { // 재방문일 경우
				if (((UserMoney - allSum) >= 0))
					yesButton.setVisible(true);
				else
					yesButton.setVisible(false);
			}
			cardInfoLabel.setText("<html><center>구매시 잔액: " + (UserMoney - allSum) + "원<br>구매하시겠습니까?</center></html>");
		}
	};
	//출금
	ActionListener withdrawListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			landTitle.setVisible(false);
			ownerExistsInfo.setVisible(false);
			cardInfoLabel.setVisible(false);
			landOwnerLabel.setVisible(false);
			tollFeeLabel.setVisible(false);
			withdrawButton.setVisible(false);
			turnEndButton.setVisible(true);
			infoLabel.setVisible(true);
			Msg cm;
			if(BoardType == 2)
				cm = new Msg(UserName, Protocol.WITHDRAW_SOCIAL_FUND,"Social Fund");
			else
				cm = new Msg(UserName, Protocol.WITHDRAW_TOLLS,"Toll fee");
			SendObject(cm);
		}
	};
	
	// 순서 버튼을 누르면 주사위를 굴리는 리스너
	ActionListener sequenceClickButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//if(e.getActionCommand()=="sequenceClickButton") {
					
					Play("rolldice.wav");
					
			//}
			// TODO Auto-generated method stub
			Msg cm = new Msg(UserName, Protocol.SET_SEQUENCE, "set sequence");
			SendObject(cm);
			sequenceClickButton.setVisible(false);
		}
	};

	// 주사위 클릭 버튼을 누르면 실행되는 리스너
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//if(e.getActionCommand()=="clickButton") {
				Play("rolldice.wav");
			//}
			// TODO Auto-generated method stub
			clickButton.setVisible(false);
			for (int i = 0; i < 27; i++) {
				//landBtn[i].setEnabled(true);
			}
			Msg cm = new Msg(UserName, Protocol.DICE, "Dice");
			SendObject(cm);
		}
	};
	//땅을 클리하면 실행되는 리스너 
	MouseAdapter clickLandListener = new MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(BoardType !=1) { //여행이 아닐 경우
				landCheck.setSelected(false);
				houseCheck.setSelected(false);
				buildingCheck.setSelected(false);
				hotelCheck.setSelected(false);
				allSum = 0;
				for(int i=0;i<4;i++) {
					purchasedArray[i] = -1; //초기값 -1 
				}
				Msg cm = new Msg(UserName, Protocol.VISIT,"Visit");
				SendObject(cm);
			}
	
			
			
		}
	};
	//입금
	ActionListener depositListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			goldKeyInfo.setVisible(false);
			//socialFundInfo.setVisible(false);
			//startInfo.setVisible(false);
			//landTitle.setVisible(false);
			cardInfoLabel.setVisible(false);
			depositButton.setVisible(false);
			giftMoneyLabel.setVisible(false);
			turnEndButton.setVisible(true);
			infoLabel.setVisible(true);

			Msg cm = new Msg(UserName, Protocol.DEPOSIT, "Deposit");
			cm.giftMoney = giftMoney;
			cm.BoardType = BoardType;
			SendObject(cm);
		}

	};
	 ActionListener tollfeeListener = new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	         tollfee.setVisible(false);
	         minusButton.setVisible(false);
	         minusLabel.setVisible(false);
	         Msg cm=new Msg(UserName, Protocol.CONCERT,"MINUS MONEY");
	         cm.minusMoney=minusMoney;
	         cm.BoardType=BoardType;
	         SendObject(cm);
	         turnEndButton.setVisible(true);
	      }
	   };
		// 유저들의 말을 옮김
		/*public void movePiece(String name, int X, int Y) {
			for (int i = 0; i < 4; i++) {
				if (player[i].getUsername().matches(name)) {
					player[i].userPiece.setBounds(X, Y, 40, 40);
				}
			}
			
		}*/
		  
		  
	      /*public void moveAnimation(String name,int nextX, int nextY, int boardNum,int oldBoardNum,int X,int Y) { // 이동
	    	  if (name.equals(player[0].getUsername())) { //첫번째 유저
	              // 주사위를 굴려 나온 만큼 이동하지 않았다면
	              for (int i=0;i<cnt;i++) {
	              
	              }
	    	  }
	      }*/
		  public void moveAnimation(String name,int newX, int newY, int boardNum,int oldBoardNum) { // 이동
		         //int playerX=0,playerY=0;
		         //boardNum이 최종 목적지인 보드 넘버!
		         
		         int nextx, nexty, nextnum; // 다음칸의 x, y, tilenum
		         
		         int old=oldBoardNum;
		         int now=boardNum;
		         int cnt=0;
		         if(old>now) { //한바퀴 돌려고할때 오른쪽 보드부터 적용
		            cnt=(27-old)+now;
		         }
		         else if(old<now) { //한바퀴 이전
		            cnt=now-old;
		         }
		         if (name.equals(player[0].getUsername())) { //첫번째 유저
		            // 주사위를 굴려 나온 만큼 이동하지 않았다면
		            for (int i=0;i<cnt;i++) {
		               nextx = BoardVec.get((old + 1)%27 ).BoardX;
		               nexty = BoardVec.get((old + 1)%27 ).BoardY;
		               nextnum = (old+1)%27;
		               moveAnimation2(name,nextx , nexty , nextnum);
		               try {
		                  Thread.sleep(100);
		               } catch (InterruptedException e) {
		                  // TODO Auto-generated catch block
		                  e.printStackTrace();
		               }  // moveAnimation 스레드가 끝날때 까지 대기.
		               old++;
		            }
		         }
		          
		         else if (name.equals(player[1].getUsername())) { //두번째유저
		            for (int i=0;i<cnt;i++) {
		               nextx = BoardVec.get((old + 1)%27 ).BoardX;
		               nexty = BoardVec.get((old + 1)%27 ).BoardY;
		               nextnum = (old+1)%27;
		               moveAnimation2(name,nextx , nexty , nextnum);
		               try {
		                  Thread.sleep(100);
		               } catch (InterruptedException e) {
		                  // TODO Auto-generated catch block
		                  e.printStackTrace();
		               }
		               old++;
		            }
		         } else if (name.equals(player[2].getUsername())) { //세번째 유저
		            for (int i=0;i<cnt;i++) {
		               nextx = BoardVec.get((old + 1)%27 ).BoardX;
		               nexty = BoardVec.get((old + 1) %27).BoardY;
		               nextnum = (old+1)%27;
		               moveAnimation2(name,nextx , nexty , nextnum);
		               try {
		                  Thread.sleep(100);
		               } catch (InterruptedException e) {
		                  // TODO Auto-generated catch block
		                  e.printStackTrace();
		               }
		               old++;
		            }
		            
		         } else if (name.equals(player[3].getUsername())) { //네번째 유저
		            for (int i=0;i<cnt;i++) {
		               nextx = BoardVec.get((old + 1) % 27).BoardX;
		               nexty = BoardVec.get((old + 1) % 27).BoardY;
		               nextnum = (old+1)%27;
		               moveAnimation2(name,nextx , nexty , nextnum);
		               try {
		                  Thread.sleep(100);
		               } catch (InterruptedException e) {
		                  // TODO Auto-generated catch block
		                  e.printStackTrace();
		               }
		               old++;
		            }
		         }
		         
		         
		         

		         
		      }
	    //채은
	      int playerX=700,playerY=655;
	      int playerX2=750,playerY2=655;
	      int playerX3=700,playerY3=695;
	      int playerX4=750,playerY4=695;
	      int n=0;
	      public void moveAnimation2(String name,int newX, int newY, int newPlayerTile) { // 이동
	         
	         int s=newPlayerTile;
	         
	         if(name.equals(player[0].getUsername())) {
	            n=0;
	            new Thread(new Runnable() {
	               public void run() {
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	                  
	                  playerX=newX;
	                  playerY=newY;
	                  player[n].userPiece.setBounds(playerX, playerY, 40, 40);
	                  System.out.println(playerX+" , "+playerY);
	                  //setLocation(playerX, playerY); // 내부에 repaint() 존재
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	               }
	            }).start();
	            
	         }
	         else if(name.equals(player[1].getUsername())) {
	            n=1;
	            new Thread(new Runnable() {
	               public void run() {
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	                  //playerX = playerX -(playerX-newX)/4; //한 칸 이동시 한번만 1/4씩 이동
	                  //playerY = playerY;
	                  playerX2=newX;
	                  playerY2=newY;
	                  player[n].userPiece.setBounds(playerX2, playerY2, 40, 40);
	                  //setLocation(playerX, playerY); // 내부에 repaint() 존재
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	               }
	            }).start();
	            
	         }
	         else if(name.equals(player[2].getUsername())) {
	            n=2;
	            new Thread(new Runnable() {
	               public void run() {
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	                  //playerX = playerX -(playerX-newX)/4; //한 칸 이동시 한번만 1/4씩 이동
	                  //playerY = playerY;
	                  playerX3=newX;
	                  playerY3=newY;
	                  player[n].userPiece.setBounds(playerX3, playerY3, 40, 40);
	                  //setLocation(playerX, playerY); // 내부에 repaint() 존재
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	               }
	            }).start();
	   
	         }
	         else if(name.equals(player[3].getUsername())) {
	            n=3;
	            new Thread(new Runnable() {
	               public void run() {
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	                  //playerX = playerX -(playerX-newX)/4; //한 칸 이동시 한번만 1/4씩 이동
	                  //playerY = playerY;
	                  playerX4=newX;
	                  playerY4=newY;
	                  player[n].userPiece.setBounds(playerX4, playerY4, 40, 40);
	                  //setLocation(playerX, playerY); // 내부에 repaint() 존재
	                  
	                  try {
	                     Thread.sleep(100);
	                  } catch (InterruptedException e) {
	                     e.printStackTrace();
	                  }
	               }
	            }).start();
	   
	         }
	      }


	public void PlaySound(String fileName, boolean isLoop) {
			try {
				File audiofile = new File(fileName);
				AudioInputStream ais = AudioSystem.getAudioInputStream(audiofile);
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				if(isLoop)
					clip.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(Exception ex)
			{
				
			}
		}
	//음악 재생 메서드 
	public void Play(String fileName) {
		try {
			File audiofile = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(audiofile);
			Clip clip = AudioSystem.getClip();
			clip.start();
			clip.open(ais);
			clip.start();
		}catch(Exception ex)
		{
			
		}
	}
	//ImageIcon icon1 = new ImageIcon("src/icon2.jpg");
	public void AppendIcon(ImageIcon icon) {
		//int len = textArea.getDocument().getLength();
		//textArea.setCaretPosition(len); // place caret at the end (with no selection)
		//textArea.insertIcon(icon);	
	}
	public void updateUserTurnNum(String name, int userturn) {
		for (int i = 0; i < 4; i++) {
			if (player[i].getUsername().matches(name)) {
				player[i].userturnField.setText(String.valueOf(userturn) + "/" + String.valueOf(numOfTurns));
			}
		}
	}
	// 모든 유저들의 프로필을 세팅(처음)
			public void setProfile(String arr1[], int arr2[], int size, int myturn, int allturn) {
				numOfTurns = allturn;
				for (int i = 0; i < size; i++) {
					player[i].setUsername(arr1[i]);
					player[i].setUsermoney(arr2[i]);
					player[i].usernameField.setText(player[i].getUsername());
					player[i].usermoneyField.setText(String.valueOf(player[i].getUsermoney()) + "원");
					player[i].userturnField.setText(String.valueOf(myturn) + "/" + String.valueOf(numOfTurns));
					player[i].usernameField.setVisible(true);
					player[i].usermoneyField.setVisible(true);
					player[i].userPiece.setVisible(true);
					//player[i].userProfile.setVisible(true);
					player[i].userturnField.setVisible(true);
				
			}
			}
	//황금열쇠
	public void goldKey(String name, int money,String boardname, int boardtype) {
		BoardType = boardtype;
		
		Random r = new Random();
		int randomNumber = 0;
		giftMoney = 0;
		randomNumber = r.nextInt(10) + 1;
		giftMoney = randomNumber * 10000;
		cardInfoLabel.setForeground(Color.black);
		//cardInfoLabel.setBounds(251, 120, 300, 300);
		cardInfoLabel.setFont(new Font("BM JUA_OTF", Font.BOLD, 14));
		cardInfoLabel.setVisible(true);
		giftMoneyLabel.setVisible(true);
		depositButton.setVisible(true);
		giftMoneyLabel.setText(String.valueOf(giftMoney) + "원");
		cardInfoLabel.setBounds(266, 357, 300, 300);
		cardInfoLabel.setText("축하합니다! 당첨금을 가져가세요.");
		goldKeyInfo.setVisible(true);
		
		repaint();
	}
	public void tollFee(String name, int money, String boardname,int boardtype) {
	      tollfee.setVisible(true);
	      minusButton.setVisible(true);
	      minusLabel.setVisible(true);
	      BoardType=boardtype;
	      minusLabel.setText("3000");
	       repaint();
	       
	       
	       
	   }
	//감옥
	public void island(String name, String boardname, int Boardtype) {

		BoardType = Boardtype;

		isprisonInfo.setVisible(true);
		cardInfoLabel.setVisible(true);
		confirmButton.setVisible(true);
		cardInfoLabel.setForeground(Color.black);
		cardInfoLabel.setText("<html><center>지금부터 2번의 턴 동안<br> 감옥에 갇힙니다</center></html>");
		cardInfoLabel.setFont(new Font("BM JUA_OTF", Font.BOLD, 15));
		cardInfoLabel.setBounds(270, 380, 300, 300);

		
		repaint();
	}

	// 유저들의 돈을 업데이트
	public void updateUserMoney(int[] arr) {
		for (int i = 0; i < 4; i++) {
			player_money[i] = arr[i];
			player[i].setUsermoney(player_money[i]);
			player[i].usermoneyField.setText(String.valueOf(player[i].getUsermoney() + "원"));
		}
		repaint();
	}
	//도시 다이얼로그 띄우기

	public void City(String name, int money, String boardName, int boardType, String ownername,
			int[] ispuarr, int priceland,int priceHouse, int pricebuilding, int pricehotel, int tollFee) {
		UserMoney = money;
		
		allSum = 0;
		if(ownername == null) { //소유주가 없는 경우
			
			BoardType = boardType;
			CityPurchaseInfo.setVisible(true);
			landTitle.setBounds(350, 162, 150, 30);
			landTitle.setForeground(Color.black);
			landTitle.setVisible(true);
			CityPurchaseInfo.setBounds(280,133,280,475);
			cardInfoLabel.setForeground(Color.black);
			landCheck.setVisible(true);
			houseCheck.setVisible(true); //집 체크박스만 보이게 한다. 
			buildingCheck.setVisible(true);
			hotelCheck.setVisible(true);
			landPrice.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 14));
			landPrice.setBounds(286, 375, 50, 20);
			housePrice.setBounds(367, 375, 50, 20);
			buildingPrice.setBounds(423, 375, 50, 20);
			hotelPrice.setBounds(492, 375, 50, 20);
			housePrice.setVisible(true);
			landPrice.setVisible(true);
			buildingPrice.setVisible(true);
			hotelPrice.setVisible(true);
			cardInfoLabel.setBounds(258, 329, 300, 300);
			cardInfoLabel.setFont(new Font("BM JUA_OTF", Font.BOLD, 16));
			cardInfoLabel.setText("<html><center>구매시 잔액: " + UserMoney + "원<br>구매하시겠습니까?</center></html>");
			cardInfoLabel.setVisible(true);
			
			landTitle.setText(String.valueOf(boardName));
			landPrice.setText(String.valueOf(priceland));
			housePrice.setText(String.valueOf(priceHouse));
			buildingPrice.setText(String.valueOf(pricebuilding));
			hotelPrice.setText(String.valueOf(pricehotel));
			yesButton.setVisible(true);
			noButton.setVisible(true);
			infoLabel.setVisible(false);
		}else {
			if ((ownername.matches(name)) && ((ispuarr[1] != 1) || (ispuarr[2] != 1) || (ispuarr[3] != 1))) { // 소유주가
				// 본인이면
			purchasedArray = ispuarr;
			BoardType = -1;
			CityPurchaseInfo.setVisible(true);
			landTitle.setBounds(327, 136, 150, 30);
			landTitle.setForeground(Color.black);
			cardInfoLabel.setForeground(Color.black);
			landTitle.setVisible(true);
			cardInfoLabel.setBounds(299, 327, 200, 60);
			cardInfoLabel.setFont(new Font("BM JUA_OTF", Font.PLAIN, 18));
			cardInfoLabel.setText("<html><center>구매시 잔액: " + UserMoney + "원<br>구매하시겠습니까?</center></html>");
			cardInfoLabel.setVisible(true);
			landPrice.setFont(new Font("NeoDunggeunmo Pro", Font.PLAIN, 14));
			landPrice.setBounds(276, 280, 50, 20);
			housePrice.setBounds(342, 280, 50, 20);
			buildingPrice.setBounds(409, 280, 50, 20);
			hotelPrice.setBounds(473, 280, 50, 20);
			landPrice.setVisible(true);
			housePrice.setVisible(true);
			buildingPrice.setVisible(true);
			hotelPrice.setVisible(true);
			
			landTitle.setText(String.valueOf(boardName));
			landPrice.setText(String.valueOf(priceland));
			housePrice.setText(String.valueOf(priceHouse));
			buildingPrice.setText(String.valueOf(pricebuilding));
			hotelPrice.setText(String.valueOf(pricehotel));
			
			noButton.setVisible(true);
			infoLabel.setVisible(false);
			if (purchasedArray[1] != 1)
			houseCheck.setVisible(true);
			if (purchasedArray[2] != 1)
			buildingCheck.setVisible(true);
			if (purchasedArray[3] != 1)
			hotelCheck.setVisible(true);
			} else { //소유주가 본인이 아니면 
			yesButton.setVisible(false);
			visitedOwnerExisteLand(name, money, boardName, boardType, ownername, tollFee);
			}
}
		repaint();
		
	}
	//소유주가 있는 땅에 도착할때 
	public void visitedOwnerExisteLand(String name, int money, String boardName, int boardType, String ownername, int tollFee )
	{
		BoardType = boardType;
		landTitle.setForeground(Color.black);
		cardInfoLabel.setForeground(Color.black);
		landTitle.setVisible(true);
		landTitle.setBounds(350, 161, 150, 30);
		landTitle.setText(String.valueOf(boardName));
		cardInfoLabel.setBounds(260, 315, 300, 300);
		cardInfoLabel.setFont(new Font("BM JUA_OTF", Font.BOLD, 13));
		ownerExistsInfo.setVisible(true);
		cardInfoLabel.setVisible(true);
		
		infoLabel.setVisible(false);
		if (ownername.matches(name)) {
			cardInfoLabel.setText("<html><center>소유주가 본인인 땅에<br> 도착하였습니다.</center></html>");
			confirmButton.setVisible(true);
		}else {
			landOwnerLabel.setText(ownername);
			tollFeeLabel.setText(tollFee + "원");
			cardInfoLabel.setText("<html><center>소유주가 있는 땅에 도착하였습니다.<br> "
					+ "통행료를 지불해주세요.<br><br> 소유주 <br><br>통행료<br></center></html>");
			landOwnerLabel.setVisible(true);
			tollFeeLabel.setVisible(true);
			withdrawButton.setVisible(true);
		}
		repaint();
	}
	// 화면에 출력
	public void AppendText(String msg) {
		infoLabel.setText(msg);
		int len=textArea.getDocument().getLength();
	     textArea.setCaretPosition(len);
	     textArea.replaceSelection(msg+"\n");

	}

	
		
	// Server에게 network으로 전송
		public void SendMessage(String protocol, String msg) {
			try {
				Msg obcm = new Msg(UserName, protocol, msg);
				oos.writeObject(obcm);
				oos.reset();
			} catch (IOException e) {
				AppendText("oos.writeObject() error");
				try {
					ois.close();
					oos.close();
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(0);
				}
			}
		}
	// Server에게 network으로 전송
	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		try {
			oos.writeObject(ob);
			oos.reset();
		} catch (IOException e) {
			infoLabel.setText("Client -> Server 메세지 송신 에러!!\n");
			AppendText("SendObject Error");
		}
	}

	}


