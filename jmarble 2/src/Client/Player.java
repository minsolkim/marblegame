package Client;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Pack_Model.Board;
public class Player extends JLabel{
	private Player player = this;
	private ImageIcon icPlayer; // 플레이어 이미지 구현
	private int playerX;
	private int playerY; // 플레이어의 좌표
	public String username; 
	public int usermoney;
	private String imageSource;
	private int nowPlayerTile = 0;
	public int id;
	public ImageIcon userFlagImg;
	public ImageIcon userPieceImg;
	public JLabel userPiece;
	public ImageIcon userHouseImg;
	public ImageIcon userBuildingImg;
	public ImageIcon userHotelImg;
	public JLabel userProfile;
	public JLabel usernameField;
	public JLabel usermoneyField;
	public JLabel userturnField;
	public JLabel[] userFlag = new JLabel[26];
	public JLabel[] userHouse = new JLabel[26];
	public JLabel[] userBuilding = new JLabel[26];
	public JLabel[] userHotel = new JLabel[26];
	int money = 10000; // 보유 현금
	int asset; // 보유 총자산 // 건물 + 현금
	Vector<Board> playerCity; // 보유한 건물
	private int nowLocation;
	public Player() {
		
	}
	public Player(int id, String username, int usermoney, JLabel usernameField,JLabel usermoneyField, JLabel userturnField) {
		this.id = id;
		this.username = username;
		this.usermoney = usermoney;
		this.usernameField = new JLabel();
		this.usermoneyField = new JLabel();
		this.userturnField = new JLabel();
		setUsernameFieldWithUserName(this.username);
		setUserMoneyFieldWithUserMoney(this.usermoney);
		
	}
	public void setUsernameFieldWithUserName(String username) {
		usernameField.setText(username);
	}
	public void setUserMoneyFieldWithUserMoney (int usermoney) {
		usermoneyField.setText(String.valueOf(usermoney));
	}
	public Player(int x, int y, String imageSource) {
		this.playerX = x;
		this.playerY = y;
		this.imageSource = imageSource;

		icPlayer = new ImageIcon(imageSource);
		setIcon(icPlayer); // 기본이미지(오른쪽)
		setSize(40, 40); // 크기설정
		setLocation(playerX, playerY); // 시작좌표 설정
	}

	public void moveAnimation(int newX, int newY, int newPlayerTile) { // 이동
		new Thread(new Runnable() {
			@Override
			public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					playerX = playerX +(newX-playerX)/4; //한 칸 이동시 한번만 1/4씩 이동
					playerY = playerY+ (newY-playerY)/4;
					setLocation(playerX, playerY); // 내부에 repaint() 존재
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					playerX = playerX +(newX-playerX)/4;
					playerY = playerY+ (newY-playerY)/4;
					setLocation(playerX, playerY); // 내부에 repaint() 존재
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					playerX = playerX +(newX-playerX)/4;
					playerY = playerY+ (newY-playerY)/4;
					setLocation(playerX, playerY); // 내부에 repaint() 존재
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					playerX = playerX +(newX-playerX)/4;
					playerY = playerY+ (newY-playerY)/4;
					setLocation(playerX, playerY); // 내부에 repaint() 존재
				nowPlayerTile = newPlayerTile; //한칸 이동 완료시 타일위치 변경.
			}
		}).start();
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserPieceImg(ImageIcon userPieceImg) {
		this.userPieceImg = userPieceImg;
		
	}
	public void setUserPiece(JLabel userPiece) {
		this.userPiece = userPiece;
		this.userPiece = new JLabel(userPieceImg);
	}
	public JLabel getUserPiece() {
		return userPiece;
	}
	public ImageIcon getUserPieceImg() {
		return userPieceImg;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUsermoney() {
		return usermoney;
	}
	public void setUsermoney(int usermoney) {
		this.usermoney = usermoney;
	}

	public int getNowLocation() {
		return nowLocation;
	}
	public void setNowLocation(int nowLocation) {
		this.nowLocation = nowLocation;
	}
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}
	public void setUserFlag(JLabel[] userFlag) {
		this.userFlag = userFlag;
		for (int i = 0; i<17; i++)
			this.userFlag[i] = new JLabel(userFlagImg);
	}
	public void setUserHouse(JLabel[] userHouse) {
		this.userHouse = userHouse;
		for(int i=0;i<17;i++) 
			this.userHouse[i] = new JLabel(userHouseImg);
	}
	public void setUserBuilding(JLabel[] userBuilding) {
		this.userBuilding = userBuilding;
		for (int i = 0; i<17; i++)
			this.userBuilding[i] = new JLabel(userBuildingImg);
	}
	public void setUserHotel(JLabel[] userHotel) {
		this.userHotel = userHotel;
		for (int i = 0; i<17; i++)
			this.userHotel[i] = new JLabel(userHotelImg);
	}
	public void setUserFlagImg(ImageIcon userFlagImg) {
		this.userFlagImg = userFlagImg;
	}
	public void setUserHouseImg(ImageIcon userHouseImg) {
		this.userHouseImg = userHouseImg;
	}
	
	public void setUserBuildingImg(ImageIcon userBuildingImg) {
		this.userBuildingImg = userBuildingImg;
	}


	public void setUserHotelImg(ImageIcon userHotelImg) {
		this.userHotelImg = userHotelImg;
	}
	public JLabel getUserFlag(int i) {
		return userFlag[i];
	}
	public JLabel getUserHouse(int i) {
		return userHouse[i];
	}
	public JLabel getUserBuilding(int i) {
		return userBuilding[i];
	}
	public JLabel getUserHotel(int i) {
		return userHotel[i];
	}
	
}

