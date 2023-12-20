package Server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Pack_Model.Board;
import Server.Protocol;

public class server extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextArea textArea;
	private JTextField txtPortNumber;
	private int playerNum = 4; //플레이어 숫자 4로 세팅 
	private ServerSocket socket; // 서버소켓
	private Socket client_socket; // accept() 에서 생성된 client 소켓
	private Vector UserVec = new Vector(); // 연결된 사용자를 저장할 벡터
	private Vector<Board> BoardVec;//보드 값을 저장할 벡터 
	private int[] purchased = {-1,-1,-1,-1}; // 0->땅, 1->집 2->빌딩 3->호텔
	private int n;
	private int nowSequence = 0; // 현재 순서의 플레이어 순서 번호
	private int count = 0;
	private int[] arrayinit = {0,0,0};
	private int rollNum1 = 0, rollNum2 = 0;
	private int rollNum = 0;
	private int duplicateCheckNum = 0; // 순서 결정 시 중복 체크
	private int nextnum;
	private ImageIcon newIcon1;
	private ImageIcon newIcon;
	private ImageIcon newDice1;
	private ImageIcon newDice2;
	private int[] player_seq = new int[4]; // 순서 결정 시에 굴렸던 주사위 수를 담아오는 배열
	private String[] player_sequence = new String[4]; // 순서를 담아오는 배열
	private int numOfTurns = 30; // 전체 턴 수
	private int[] wealth = new int[4];
	private String[] player_name = new String[4]; // 우승자 결정 시 순서대로 이름을 담을 배열
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server frame = new server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public server() {
		boardVector(); //vector값 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 300, 244);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("Port Number");
		lblNewLabel.setBounds(12, 264, 87, 26);
		contentPane.add(lblNewLabel);

		txtPortNumber = new JTextField();
		txtPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtPortNumber.setText("30000");
		txtPortNumber.setBounds(111, 264, 199, 26);
		contentPane.add(txtPortNumber);
		txtPortNumber.setColumns(10);

		JButton btnServerStart = new JButton("Server Start");
		btnServerStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new ServerSocket(Integer.parseInt(txtPortNumber.getText()));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AppendText("Marble Server Running..");
				btnServerStart.setText("Marble Running..");
				btnServerStart.setEnabled(false); // 서버를 더이상 실행시키지 못 하게 막는다
				txtPortNumber.setEnabled(false); // 더이상 포트번호 수정못 하게 막는다
				AcceptServer accept_server = new AcceptServer();
				accept_server.start();
			}
		});
		btnServerStart.setBounds(12, 300, 300, 35);
		contentPane.add(btnServerStart);
	}
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
		Board b26 = new Board("국세청", 26, 2,730, 500);
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
		
	}// 새로운 참가자 accept() 하고 user thread를 새로 생성한다.
		class AcceptServer extends Thread {
			@SuppressWarnings("unchecked")
			public void run() {
				while (true) { // 사용자 접속을 계속해서 받기 위해 while문
					try {
						AppendText("Waiting new clients ...");
						client_socket = socket.accept(); // accept가 일어나기 전까지는 무한 대기중
						AppendText("새로운 참가자 from " + client_socket);
						// User 당 하나씩 Thread 생성
						UserService new_user = new UserService(client_socket);
						UserVec.add(new_user); // 새로운 참가자 배열에 추가
						new_user.start(); // 만든 객f체의 스레드 실행
						AppendText("현재 참가자 수 " + UserVec.size());
						
						 //클라이언트 5명 되면 서버에서도 처리 x
						//client_socket = socket.accept(); // accept가 일어나기 전까지는 무한 대기중
					    //UserService new_user = new UserService(client_socket);
						//UserVec.add(new_user); // 새로운 참가자 배열에 추가
						//new_user.start(); // 만든 객체의 스레드 실행
						//UserVec.removeElement(new_user); //5명이상되면 바로 서버에서 삭제 (서비스 안함)
					} catch (IOException e) {
						AppendText("accept() error");
						//System.exit(0);
					}
				}
			}
		}
		public void AppendText(String str) {
			// textArea.append("사용자로부터 들어온 메세지 : " + str+"\n");
			textArea.append(str + "\n");
			textArea.setCaretPosition(textArea.getText().length());
		}
		public void AppendObject(Msg msg) {
			textArea.append("code =" + msg.code +"\n");
			textArea.append("id = " + msg.UserName +"\n");
			textArea.append("data =" + msg.data + "\n");
			textArea.setCaretPosition(textArea.getText().length());
		}
		// User 당 생성되는 Thread
		// Read One 에서 대기 -> Write All
		class UserService extends Thread {
			UserService userservice = this;
			private ObjectInputStream ois;
			private ObjectOutputStream oos;
			public int UserId;
			private Socket client_socket;
			public int rollNumber = 0;
			private Vector user_vc; //user vector
			private Vector board_vc;
			public String UserName = ""; //유저들의 이름 
			public int UserMoney = 10000;
			public String UserStatus;
			public int Wealth = 0; // 재산(우승자를 결정할 때 UserMoney + 유저들이 산 건물 값)
			public int UserSequence = 0; // 유저들의 순서
			public int turn = 0;
			public int nowLocation=0; //현재 위
			public String BoardName = ""; // 땅 이름, 도착했을 때 표시하기 위함
			public boolean nowPlaying = true;
			public int islandCount = 0; // 감옥에 걸렸을 때
			

			public int flagCount = 0;
			public int houseCount = 0;
			public int buildingCount = 0;
			public int hotelCount = 0;
			public UserService(Socket client_socket) {
				// TODO Auto-generated constructor stub
				// 매개변수로 넘어온 자료 저장
	
				this.client_socket = client_socket;
				this.user_vc = UserVec;
				try {
					oos = new ObjectOutputStream(client_socket.getOutputStream());
					oos.flush();
					ois = new ObjectInputStream(client_socket.getInputStream());
					
				} catch (Exception e) {
					AppendText("userService error");
				}
			}
			public void Login() {
					
				if (user_vc.size()== playerNum) {
					AppendText("모든 참가자 입장 완료");

					UserService firstUser = (UserService) user_vc.elementAt(0);

					String msg1 = "모든 참가자가 입장을 완료하였습니다.순서 결정을 위해 " + firstUser.UserName
							+ "님부터 주사위를 굴려주세요.";
					AppendText(msg1);
					Msg obcm1 = new Msg("SERVER", Protocol.AllUserLogin, msg1);
					Msg obcm2 = new Msg("SERVER", Protocol.SEQUENCE_SERVER, msg1);

					for (int i = 0; i < user_vc.size(); i++) {
						UserService user = (UserService) user_vc.elementAt(i);
						obcm1.strArray[i] = user.UserName;
						obcm1.intArray[i] = user.UserMoney;
						obcm1.turnNum = turn;
						obcm1.numofturns = numOfTurns;
						obcm2.strArray[i] = user.UserName;
						obcm2.intArray[i] = user.UserMoney;
						obcm2.turnNum = turn;
						obcm2.numofturns = numOfTurns;

					}
					obcm1.player_size = user_vc.size();
					obcm2.player_size = user_vc.size();

					for (int i = 0; i < user_vc.size(); i++) {
						UserService user = (UserService) user_vc.elementAt(i);

						if (i == 0) {
							user.WriteOneObject(obcm1);
						} else {
							user.WriteAllObject(obcm2);
						}
					}

				} else {
					String msg = UserName + "님이 입장하였습니다.";
					AppendText(msg);

					Msg obcm = new Msg(UserName, Protocol.LOGIN, msg);
					WriteAllObject(obcm);
				}

			}
			//주사위 순서 결정
			public void setSequenceForDice() {
				Random r1 = new Random();
				Random r2 = new Random();
				rollNum1 = r1.nextInt(6) + 1;
				rollNum2 = r2.nextInt(6) + 1;
				rollNum = rollNum1 + rollNum2;
				rollNumber = rollNum;
				
				newDice1 = new ImageIcon("dice" + rollNum1 + "-1.png");
				newDice2 = new ImageIcon("dice" + rollNum2 + "-1.png");
				//System.out.println(rollNum1+" "+rollNum2);
				for (int i = 0; i < user_vc.size(); i++) {
					if (rollNum == player_seq[i]) {
						AppendText("중복");
						duplicateCheckNum++;
						count--;
						Msg obcm1 = new Msg(UserName, Protocol.YOUR, "");
						obcm1.rollNum = rollNum;
						obcm1.img1 = newDice1;
						obcm1.img2 = newDice2;
						WriteOneObject(obcm1);

					}
				}
				
				for (int i = 0; i <user_vc.size(); i++) {
					UserService user = (UserService) user_vc.elementAt(i);
					if (i == count) {
						player_seq[i] = user.rollNumber;
						duplicateCheckNum = 0;
					}

				}
				if (duplicateCheckNum == 0 && count < 3) {

					UserService user1 = (UserService) user_vc.elementAt(count + 1);
					String msg = "" + UserName + "님의 주사위 : " + rollNum + "" + user1.UserName + "님 차례입니다.";
					Msg obcm = new Msg("SERVER", Protocol.SEQUENCE_DICE, msg);
					obcm.img1 = newDice1;
					obcm.img2 = newDice2;

					Msg obcm1 = new Msg(UserName, Protocol.YOUR, msg);
					obcm1.img1 = newDice1;
					obcm1.img2 = newDice2;

					for (int i = 0; i < user_vc.size(); i++) {
						UserService user = (UserService)user_vc.elementAt(i);
						if (user != user1) {
							user.WriteOneObject(obcm);
						} else
							user.WriteOneObject(obcm1);
					}
				}
				if (duplicateCheckNum == 0 && count == 3) {
					int temp;

					for (int i = 0; i < player_seq.length; i++) {
						for (int j = i + 1; j < player_seq.length; j++) {
							if (player_seq[j] > player_seq[i]) {
								temp = player_seq[j];
								player_seq[j] = player_seq[i];
								player_seq[i] = temp;
							}
						}
					}

					for (int i = 0; i < user_vc.size(); i++) {
						UserService user = (UserService) user_vc.elementAt(i);
						for (int j = 0; j < user_vc.size(); j++) {
							if (player_seq[j] == user.rollNumber) {
								player_sequence[j] = user.UserName;
							} else
								continue;
						}

					}

					// 결정된 순서를 user들의 UserSequence 변수에 넣어줌
					for (int i = 0; i < user_vc.size(); i++) {
						UserService user = (UserService) user_vc.elementAt(i);
						if (user.UserName.matches(player_sequence[0]))
							user.UserSequence = 1;
						else if (user.UserName.matches(player_sequence[1]))
							user.UserSequence = 2;
						else if (user.UserName.matches(player_sequence[2]))
							user.UserSequence = 3;
						else if (user.UserName.matches(player_sequence[3]))
							user.UserSequence = 4;
					}

					String msg = "" + UserName + "님의 주사위 : " + rollNum + "게임순서가 정해졌습니다."
							+ player_sequence[0] + "님의 차례입니다.";

					Msg obcm = new Msg(UserName, Protocol.GAMESTART, msg);
					Msg obcm1 = new Msg(UserName, Protocol.SEQUENCE_END, msg);

					obcm.img1 = newDice1;
					obcm.img2 = newDice2;
					obcm.strArray = player_sequence;
					obcm.nowSequence = nowSequence;
					obcm1.img1 = newDice1;
					obcm1.img2 = newDice2;
					obcm1.strArray = player_sequence;
					obcm.nowSequence = nowSequence;

					for (int i = 0; i < user_vc.size(); i++) {
						UserService user = (UserService) user_vc.elementAt(i);
						if (user.UserSequence == 1)
							user.WriteOneObject(obcm);
						else if (user.UserSequence != 1)
							user.WriteOneObject(obcm1);
					}
				}
				count++;


			}
			
			// 현재 순서를 알려주고, 순서를 넘기는 함수
			public void turnFlow() {
				UserService user;

				nowSequence++;
				turn++;
				// 현재 인원 수보다 많아지면 다시 1로 세팅 = 첫번째 순서의 유저 차례
				if (nowSequence > user_vc.size() - 1)
					nowSequence = 0;

				for (int i = 0; i < user_vc.size(); i++) {
					user = (UserService) user_vc.elementAt(i);
					if (user.UserName.matches(player_sequence[nowSequence])) {
						if (user.islandCount != 0) {
							user.islandCount--;
							user.turn++;
							turn--;
							System.out.println(nowSequence);
							turnFlow();
							return;
						} else
							break;
					}
				}

				for (int i = 0; i < 4; i++) {
					UserService user1 = (UserService) user_vc.elementAt(i);
					System.out.println(user1.UserName + "의 턴 수: " + user1.turn);
					System.out.println(user1.UserName + "의 무인도 카운트 수: " + user1.islandCount);
				}

				// 모든 플레이어가 주어진 턴을 다 돌았으면 게임 종료
				if ((((UserService) user_vc.elementAt(0)).turn == numOfTurns)
						&& (((UserService) user_vc.elementAt(1)).turn == numOfTurns)
						&& (((UserService) user_vc.elementAt(2)).turn == numOfTurns)
						&& (((UserService) user_vc.elementAt(3)).turn == numOfTurns))
					gameEnd(user_vc);

				else {
					String msg = "" + UserName + "님의 턴이 종료되었습니다." + player_sequence[nowSequence]
							+ "님의 턴 입니다.";

					Msg obcm = new Msg(UserName, Protocol.YOUR_TURN, msg);
					Msg obcm1 = new Msg(UserName, Protocol.SERVER, msg);

					obcm.nowSequence = nowSequence;
					obcm1.nowSequence = nowSequence;
					obcm.turnNum = turn;
					obcm1.turnNum = turn;

					for (int i = 0; i < user_vc.size(); i++) {
						user = (UserService) user_vc.elementAt(i);
						if (user.UserName.matches(player_sequence[nowSequence])) {
							user.WriteOneObject(obcm);
						} else
							user.WriteOneObject(obcm1);
					}
				}
			}
			// 주사위(본 게임)
			public void diceRoll() {
					
					Random r1 = new Random();
					Random r2 = new Random();
					rollNum1 = r1.nextInt(6) + 1;
					rollNum2 = r2.nextInt(6) + 1;
					rollNum = rollNum1 + rollNum2;
					newIcon1 = new ImageIcon("icon" + rollNum + ".png");
					newDice1 = new ImageIcon("dice" + rollNum1 + "-1.png");
					newDice2 = new ImageIcon("dice" + rollNum2 + "-1.png");
					n=BoardVec.get(nowLocation).BoardNum;
					//nextnum = (n + 1) %27;
					nowLocation = (nowLocation + rollNum) % 27;
					BoardName = BoardVec.get(nowLocation).BoardName;
					String msg = "" + UserName + "님의 주사위: " + rollNum + "" + UserName + "님이 " + BoardName
									+ "에 도착하였습니다.";
					Msg obcm = new Msg(UserName, Protocol.DICE, msg);
					obcm.iconimg1 = newIcon1;
					obcm.img1 = newDice1;
					obcm.img2 = newDice2;
					obcm.BoardName = BoardName;
					obcm.BoardX = BoardVec.get(nowLocation).BoardX;
					obcm.BoardY = BoardVec.get(nowLocation).BoardY;
					  //채은
		               //보드 넘버도 함께 넘김 목적지 보드넘버 (도착지 보드 번호)
		               obcm.BoardNum=BoardVec.get(nowLocation).BoardNum;
		               //채은 출발지 보드 번호
		               obcm.oldBoardNum=n;
		               

					 //보드 넘버도 함께 넘김 목적지 보드넘버 (도착지 보드 번호)
		           // obcm.BoardNum=BoardVec.get(nextnum).BoardNum;
		           // obcm.oldBoardNum=BoardVec.get(nowLocation).BoardNum;
		            //obcm.nextx = BoardVec.get(nextnum).BoardX;
		           // obcm.nexty = BoardVec.get(nextnum).BoardY;
		               //채은 출발지 보드 번호
		            
					WriteAllObject(obcm);

						}
			// 플레이어의 게임이 끝났을 때
			public void gameEnd(Vector v) {

				System.out.println("게임 완전 종료");
				UserService user;
				for (int i = 0; i < v.size(); i++) {
					user = (UserService) v.elementAt(i);
					user.Wealth += user.UserMoney;
					wealth[i] = user.Wealth;
					System.out.println(user.UserName + "의 재산: " + wealth[i]);
				}

				// 재산이 큰 순서대로 정렬 (재산(건물값+UserMoney)이 가장 많은 사람)
				for (int i = 0; i < wealth.length; i++) {
					for (int j = 0; j < wealth.length; j++) {
						if (wealth[i] > wealth[j]) {
							int tmp = wealth[j];
							wealth[j] = wealth[i];
							wealth[i] = tmp;
						}
					}
				}
				// 재산이 큰 순서대로 이름 정렬
				for (int i = 0; i < wealth.length; i++) {
					user = (UserService) v.elementAt(i);
					for (int j = 0; j < wealth.length; j++) {
						if (user.Wealth == wealth[j])
							player_name[j] = user.UserName;
					}
				}
				System.out.print("우승자는 " + player_name[0]);
				String msg = "<html><center>게임 종료<br><br> <h2>우승자: " + player_name[0] + "</h2>" + "<br>2등: "
						+ player_name[1] + "<br>3등: " + player_name[2] + "<br>4등: " + player_name[3];

				Msg obcm = new Msg("SERVER", Protocol.GAME_END, msg);
				WriteAllObject(obcm);

			}


			
			
			//땅에 도착했을 때
			
		public void Logout() {
			String msg ="["+UserName+"]님이 퇴장 하였습니다.\n";
			UserVec.removeElement(this); // Logout한 현재 객체를 벡터에서 지운다
			WriteAllObject(msg); // 나를 제외한 다른 User들에게 전송
			AppendText("사용자 " +"[" + UserName + "] 퇴장. 현재 참가자 수 " + UserVec.size());
		}
	
		public void WriteMsg(Msg obj) {
			try {
				oos.writeObject(obj.code);
				oos.writeObject(obj.UserName);
				oos.writeObject(obj.data);
			} catch (IOException e) {
				AppendText("oos.writeObject(ob) error");
				try {
					ois.close();
					oos.close();
					client_socket.close();
					client_socket = null;
					ois = null;
					oos = null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				Logout();

			}
		}
		

		// 모든 User들에게 방송. 각각의 UserService Thread의 WriteONe() 을 호출한다.
		public void WriteAllObject(Object ob) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
					user.WriteOneObject(ob);
				
			}
		}
		
		// 나를 제외한 User들에게 방송. 각각의 UserService Thread의 WriteONe() 을 호출한다.
		public void WriteOthersObject(Object ob) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserService user = (UserService) user_vc.elementAt(i);
				if (user!=this)
					user.WriteOneObject(ob);
			}
		}
		public void WriteOneObject(Object ob) {
			try {
				oos.writeObject(ob);
				// oos.reset();
			} catch (IOException e) {
				AppendText("oos.writeObject(ob) error in WriteOneObject");
				try {
					ois.close();
					oos.close();
					client_socket.close();
					client_socket = null;
					ois = null;
					oos = null;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Logout();
			}
		}
		public void run() {
			while (true) { // 사용자 접속을 계속해서 받기 위해 while문
				try {
					Object obcm = null;
					String msg = null;
					Msg cm = null;
					if (socket == null)
						break;
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
					if (obcm == null)
						break;
					if (obcm instanceof Msg) {
						cm = (Msg) obcm;
						AppendObject(cm);
					} else
						continue;	
					if(cm.code.matches(Protocol.LOGIN)) {
						UserName = cm.UserName;
						
						for (int i = 0; i < user_vc.size(); i++) {
							UserService user = (UserService) user_vc.elementAt(i);
							user.UserId = i;
							
							user.nowLocation = 0; // player들의 초기 위치를 '시작'판으로 설정
						}
						Login();
						
					} else if (cm.code.matches(Protocol.SET_SEQUENCE)) {
						setSequenceForDice();

					} else if (cm.code.matches(Protocol.DICE)) {
						diceRoll();
					}
					else if(cm.code.matches(Protocol.VISIT)) {
						visitboard();
					}
					else if(cm.code.matches(Protocol.VISIT_PRISON)) { //두번의 턴동안 게임 할 수 없음
						islandCount = 2; 
					}
					//콘서트 통행료 지불하면 머니 줄어듬
		            else if(cm.code.matches(Protocol.CONCERT)) {
		                //  if(cm.BoardType == 3)  //공연 인 경우
		                     UserMoney =UserMoney- 3000;
		                  System.out.println(UserMoney);
		                  Msg obcm1 = new Msg("SERVER", Protocol.SET_MONEY, "change money");
		                  for (int i = 0; i < user_vc.size(); i++) {
		                     UserService user = (UserService) user_vc.elementAt(i);
		                     obcm1.intArray[i] = user.UserMoney;
		                  }
		                  WriteAllObject(obcm1);
		                  
		                  
		            }
					//기부
		            else if(cm.code.matches(Protocol.DONATE)) {
		                  
		                     UserMoney =UserMoney- cm.moneym;
		                  System.out.println(UserMoney);
		                  Msg obcm1 = new Msg("SERVER", Protocol.SET_MONEY, "change money");
		                  for (int i = 0; i < user_vc.size(); i++) {
		                     UserService user = (UserService) user_vc.elementAt(i);
		                     obcm1.intArray[i] = user.UserMoney;
		                  }
		                  WriteAllObject(obcm1);
		                  
		                  
		               }
		               //여행
		             else if(cm.code.matches(Protocol.TRAVEL)) {
		                  
		                  Msg obcm1=new Msg("SERVER",Protocol.TRAVEL,"travel");
		                  obcm1.UserName=UserName;
		                  
		                  WriteAllObject(obcm1);
		                  
		                  
		               }
		               //여행 후 위치 다시 세팅
		             else if(cm.code.matches(Protocol.SET)) {
		                  // 
		                  if(cm.UserName==UserName)
		                     nowLocation=cm.BoardNum;
		                  //Msg obcm1=new Msg("SERVER",Protocol.SETTING,"travel");
		                  //obcm1.UserName=UserName;
		                  
		                  //WriteAllObject(obcm1);
		                  
		                  
		                  
		               }
					// 통행료를 지불했을 경우
					else if (cm.code.matches(Protocol.WITHDRAW_TOLLS)) {
						UserMoney -= BoardVec.get(nowLocation).tollFee;
						Msg obcm1 = new Msg("SERVER", Protocol.SET_MONEY, "change money");
						for (int i = 0; i < user_vc.size(); i++) {
							UserService user = (UserService) user_vc.elementAt(i);
							if (user.UserName.matches(BoardVec.get(nowLocation).BoardOwner))
								user.UserMoney += BoardVec.get(nowLocation).tollFee;
							obcm1.intArray[i] = user.UserMoney;
						}
						WriteAllObject(obcm1);
					}
					// 땅 구매를 했을 경우
					else if (cm.code.matches(Protocol.WITHDRAW_PURCHASE)) {
						BoardVec.get(nowLocation).BoardOwner = cm.UserName;
						BoardVec.get(nowLocation).isPurchased = cm.isPurchased;

						Msg obcm1 = new Msg("SERVER", Protocol.SET_MONEY_AND_STRUCTURE,
								"change money & structure");
						obcm1.UserName = cm.UserName;
						obcm1.isPurchased = cm.isPurchased;

						//if (cm.BoardType == 0 || cm.BoardType == -1) { // 도시 땅인 경우
							UserMoney -= cm.priceAll;
							Wealth += cm.priceAll;
							if (BoardVec.get(nowLocation).isPurchased[0] == 1) {
								//if (cm.BoardType == 0) {
									BoardVec.get(nowLocation).tollFee += BoardVec.get(nowLocation).tollFeeLand;
									obcm1.BoardType = BoardVec.get(nowLocation).BoardType;
								//}
								//if (cm.BoardType == -1)
									obcm1.BoardType = cm.BoardType;
								obcm1.flagCount = BoardVec.get(nowLocation).BoardNum;
								obcm1.flagX = BoardVec.get(nowLocation).flagX;
								obcm1.flagY = BoardVec.get(nowLocation).flagY;
							}
							if (BoardVec.get(nowLocation).isPurchased[1] == 1) {
								BoardVec.get(nowLocation).tollFee += BoardVec.get(nowLocation).tollFeeHouse;
								houseCount++;
								obcm1.houseCount = houseCount;
								obcm1.houseX = BoardVec.get(nowLocation).houseX;
								obcm1.houseY = BoardVec.get(nowLocation).houseY;
							}
							if (BoardVec.get(nowLocation).isPurchased[2] == 1) {
								BoardVec.get(nowLocation).tollFee += BoardVec.get(nowLocation).tollFeeBuilding;
								buildingCount++;
								obcm1.buildingCount = buildingCount;
								obcm1.buildingX = BoardVec.get(nowLocation).buildingX;
								obcm1.buildingY = BoardVec.get(nowLocation).buildingY;
							}
							if (BoardVec.get(nowLocation).isPurchased[3] == 1) {
								BoardVec.get(nowLocation).tollFee += BoardVec.get(nowLocation).tollFeeHotel;
								hotelCount++;
								obcm1.hotelCount = hotelCount;
								obcm1.hotelX = BoardVec.get(nowLocation).hotelX;
								obcm1.hotelY = BoardVec.get(nowLocation).hotelY;
							}
						//}

						if (cm.BoardType == 1 || cm.BoardType == 5) { // 한국 도시 땅, 이동수단 땅인 경우
							//UserMoney -= BoardVec.get(nowLocation).priceLand;
							//Wealth += BoardVec.get(nowLocation).priceLand;
							//if (BoardVec.get(nowLocation).isPurchased[0] == 1) {
							//	BoardVec.get(nowLocation).tollFee += BoardVec.get(nowLocation).tollFeeLand;
							//	obcm1.flagCount = BoardVec.get(nowLocation).landIndex;
							//	obcm1.BoardType = BoardVec.get(nowLocation).landType;
							//	obcm1.landName = BoardVec.get(nowLocation).landName;
							//	obcm1.flagX = BoardVec.get(nowLocation).flagX;
							//	obcm1.flagY = BoardVec.get(nowLocation).flagY;
						//	}
						}

						for (int i = 0; i < user_vc.size(); i++) {
							UserService user = (UserService) user_vc.elementAt(i);
							obcm1.intArray[i] = user.UserMoney;
						}

						WriteAllObject(obcm1);

					} 
					//입금
					else if(cm.code.matches(Protocol.DEPOSIT)) {
						if(cm.BoardType == 4)  //찬스카드 인 경우
							UserMoney += cm.giftMoney;
						if(cm.BoardType ==6)
							UserMoney += 30000;
						Msg obcm1 = new Msg("SERVER", Protocol.SET_MONEY,"change money");
						for (int i = 0; i < user_vc.size(); i++) {
							UserService user = (UserService) user_vc.elementAt(i);
							obcm1.intArray[i] = user.UserMoney;
						}
						WriteAllObject(obcm1);
					} else if (cm.code.matches(Protocol.TURN_END)) {
						turnFlow();
					}
					
				}catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
						ois.close();
						oos.close();
						client_socket.close();
						Logout(); // 에러가난 현재 객체를 벡터에서 지운다
						break;
					} catch (Exception ee) {
						break;
					} // catch문 끝
				} // 바깥 catch문 끝
			}
		
	}// run
		
		public void visitboard() { //도시에 방문했을 때 
			if(BoardVec.get(nowLocation).BoardType == 0) {
				Msg obcm = new Msg(UserName, Protocol.VISIT_CITY,"");
				obcm.UserName = UserName;
				obcm.userMoney = UserMoney;
				obcm.BoardName = BoardVec.get(nowLocation).BoardName;
				obcm.BoardType = BoardVec.get(nowLocation).BoardType;
				obcm.BoardOwner = BoardVec.get(nowLocation).BoardOwner;
				obcm.isPurchased = BoardVec.get(nowLocation).isPurchased;
				obcm.LandPrice = BoardVec.get(nowLocation).LandPrice;
				obcm.HousePrice = BoardVec.get(nowLocation).HousePrice;
				obcm.BuildPrice = BoardVec.get(nowLocation).BuildPrice;
				obcm.HotelPrice = BoardVec.get(nowLocation).HotelPrice;
				obcm.tollFee = BoardVec.get(nowLocation).tollFee; //벌금
				
				
				System.out.print(BoardVec.get(nowLocation).BoardName + " :  ");
				
				for (int i = 0; i < 4; i++) { //집, 빌딩,호텔을 샀는지 체크 
					System.out.print(BoardVec.get(nowLocation).isPurchased[i]);
				}
				WriteOneObject(obcm);
				//구매한 사람이 없으면 구매를 가능하게 해주는 다이얼로그를 띄어준다.
				
			}
			//찬스카드 땅일 경우
			if (BoardVec.get(nowLocation).BoardType == 4) {
				Msg obcm = new Msg(UserName, Protocol.VISIT_GOLD_KEY, "");
				obcm.UserName = UserName;
				obcm.userMoney = UserMoney;
				obcm.BoardName = BoardVec.get(nowLocation).BoardName;
				obcm.BoardType = BoardVec.get(nowLocation).BoardType;

				WriteOneObject(obcm);
			}
			//콘서트 땅일 경우
	         if (BoardVec.get(nowLocation).BoardType == 3) {
	            Msg obcm = new Msg(UserName, Protocol.VISIT_CONCERT, "");
	            obcm.UserName = UserName;
	            obcm.userMoney = UserMoney;
	            obcm.BoardName = BoardVec.get(nowLocation).BoardName;
	            obcm.BoardType = BoardVec.get(nowLocation).BoardType;

	            WriteOneObject(obcm);
	         }
			//감옥 갈 경우 
			if(BoardVec.get(nowLocation).BoardType == 5) {
				Msg obcm = new Msg(UserName, Protocol.VISIT_PRISON,"");
				obcm.UserName = UserName;
				obcm.BoardType = BoardVec.get(nowLocation).BoardType;
				obcm.BoardName = BoardVec.get(nowLocation).BoardName;
				WriteOneObject(obcm);
			} 
			if(BoardVec.get(nowLocation).BoardType ==3) { //여행
				//Msg obcm = new Msg(UserName, Protocol.,"");
			}
			

			// 출발 땅인 경우
			if (BoardVec.get(nowLocation).BoardType == 6) {
				Msg obcm = new Msg(UserName, Protocol.VISIT_START, "");
				obcm.UserName = UserName;
				obcm.BoardType = BoardVec.get(nowLocation).BoardType;
				obcm.BoardName = BoardVec.get(nowLocation).BoardName;
				WriteOneObject(obcm);
			}
			
		}
		}

}

