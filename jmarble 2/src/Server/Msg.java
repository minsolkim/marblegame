package Server;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Msg implements Serializable{
	private static final long serialVersionUID = 1L;
	public String code; 
	public String UserName;
	public String data;
	public int moneym=0;

	public ImageIcon iconimg1;
	public int oldBoardNum;
	public int nextx;
	public int nexty;
	public ImageIcon iconimg2;
	public ImageIcon img1;
	public ImageIcon img2;
	 
	public String TRAVEL="480";
	public String VISIT_TRAVEL="490";
	public String SET="720"; //여행 좌표 nowlocation 셋팅
	public String SETTING="730";

	public int userMoney;
	public JLabel usernameField = new JLabel();
	public JLabel usermoneyField = new JLabel();
	
	public String[] strArray = new String[4];
	public int[] intArray = new int[4];
	
	public int player_size = 0;
	public int rollNum = 0;
	public int turnNum = 0;
	public int numofturns;
	public int nowLocation;
	public int nowSequence;
	public int flagCount;
	public int houseCount;
	public int buildingCount;
	public int hotelCount;
	public int minusMoney=3000;
	public int giftMoney;
	// Land 관련 
	public int BoardType;
	public int BoardNum; // 해당 타일의 번호
	public String BoardName; // 땅 이름
	public String BoardOwner; // 소유한 플레이어
	public int isPurchased[];// 땅/집/빌딩/호텔 샀는지 // 0: 
	public int LandPrice; // 땅값
	public int HousePrice; // 집값
	public int BuildPrice; // 빌딩값
	public int HotelPrice; // 호텔값
	public int priceAll; // 전체구매비용 priceLand + priceHouse + priceBuilding + priceHotel
	public int tollFeeLand;
	public int tollFeeHouse;
	public int tollFeeBuilding;
	public int tollFeeHotel;
	public int tollFee;
	public int fundMoney;
	public int BoardX;
	public int BoardY;
	public int flagX = 0;
	public int flagY = 0;
	public int houseX = 0;
	public int houseY = 0;
	public int buildingX = 0;
	public int buildingY = 0;
	public int hotelX = 0;
	public int hotelY = 0;
	public int user_size = 0;
	public  Msg(String UserName, String code, String data) {
		this.UserName = UserName;
		this.code = code;
		this.data = data;
	}
}
