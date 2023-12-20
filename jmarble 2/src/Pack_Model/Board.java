package Pack_Model;

public class Board {
		public String BoardName; //타일 이름
		public int BoardNum; //타일 번호 
		public int BoardType; // 0 - 시티, 1- 여행 2 - 세금 3 - 공연 4 - 황금열쇠5- 무인도6-출발 
		public int BoardX;
		public int BoardY;
		public int flagX;
		public int flagY;
		public int houseX;
		public int houseY;
		public int buildingX;
		public int buildingY;
		public int hotelX;
		public int hotelY;
		public String BoardOwner; //땅 소유자 
		public int bonusPoint; //벌금
		public int LandPrice;
		public int HousePrice; //집 값
		public int BuildPrice; // 빌딩값
		public int HotelPrice; // 호텔 값 
		public int priceAll; //전체 값
		public int tollFeeLand; //땅 통행료
		public int tollFeeHouse; //집 통행료
		public int tollFeeBuilding; //빌딩 통행료
		public int tollFeeHotel; //호텔 통행료 
		public int tollFee; //통행료
		public int isPurchased[];  //구매를 하는지 
		public int ConcertCount; //콘서트 횟수 체크 기본 0 
		public int fundMoney = 0;
		
		public Board() {
			
		}
		
		//CIty (타입, 번호,이름, 소유한 유저, 벌금, 샀는지 체크, 집값,빌딩값,호텔값,전체 구매비용, 좌표)
		public Board (String BoardName, int BoardNum, int BoardType, int BoardX,int BoardY,String BoardOwner, int isPurchased[],int LandPrice, int HousePrice, 
				int BuildPrice, int HotelPrice,int tollFeeLand,int tollFeeHouse, int tollFeeBuilding, int tollFeeHotel, int tollFee, int flagX, int flagY,int houseX, int houseY, int buildingX, int buildingY, int hotelX, int hotelY) {
			
			this.BoardName = BoardName;
			this.BoardNum = BoardNum;
			this.BoardType = BoardType;
			this.BoardX = BoardX;
			this.BoardY = BoardY;
			this.BoardOwner = BoardOwner;
			this.isPurchased = isPurchased;
			this.LandPrice = LandPrice;
			this.HousePrice = HousePrice;
			this.BuildPrice = BuildPrice;
			this.HotelPrice = HotelPrice;
			this.tollFee = tollFee;
			this.flagX = flagX;
			this.flagY = flagY;
			this.houseX = houseX;
			this.houseY = houseY;
			this.buildingX = buildingX;
			this.buildingY = buildingY;
			this.hotelX = hotelX;
			this.hotelY = hotelY;
		}
		//세금 국세청 
		public Board (int BoardType, String BoardName, int fundMoney, int BoardX, int BoardY) {
			this.BoardType = BoardType;
			this.BoardName = BoardName;
			this.fundMoney = fundMoney;
			this.BoardX = BoardX;
			this.BoardY = BoardY;
			
		}
		//황금열쇠, 감옥, 출발 
		public Board(String BoardName, int BoardNum,int BoardType, int BoardX,int BoardY) {
			super();
			this.BoardName = BoardName;
			this.BoardNum = BoardNum;
			this.BoardType = BoardType;
			this.BoardX = BoardX;
			this.BoardY = BoardY;
			
		}
		// 이동수단 
		public Board (int BoardType, String BoardName, String BoardOwner, int isPurchased[], int HousePrice, 
				int tollFee, int landX, int BoardY) {
			this.BoardType = BoardType;
			this.BoardName = BoardName;
			this.BoardOwner = BoardOwner;
			this.isPurchased = isPurchased;
			this.HousePrice = HousePrice;
			this.tollFee = tollFee;
			this.BoardX = BoardX;
			this.BoardY = BoardY;
			
		}
		public String getBoardOwner() {
			return BoardOwner;
		}

		public void setBoardOwner(String BoardOwner) {
			this.BoardOwner = BoardOwner;
		}

		public int getTollFee() {
			return tollFee;
		}

		public void setTollFee(int tollFee) {
			this.tollFee = tollFee;
		}

		public int[] getIsPurchased() {
			return isPurchased;
		}

		public void setIsPurchased(int[] isPurchased) {
			this.isPurchased = isPurchased;
		}

		public int getPriceAll() {
			return priceAll;
		}

		public void setPriceAll(int priceAll) {
			this.priceAll = priceAll;
		}
		
		
}
