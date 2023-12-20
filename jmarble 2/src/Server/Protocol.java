package Server;

public interface Protocol {
	String LOGIN = "100";
	String AllUserLogin = "110";
	String SEQUENCE_SERVER = "120";
	String SET_SEQUENCE = "130";
	String SEQUENCE_DICE = "140";
	String YOUR = "150";
	String SEQUENCE_END = "160";
	String GAMESTART = "170";
	
	String VISIT = "400"; 
	String VISIT_CITY = "410";
	String VISIT_PRISON = "420";
	String VISIT_GOLD_KEY = "430";
	String VISIT_START = "440";
	
	//주사위 턴 관련
	String YOUR_TURN = "200";
	String TURN_END = "250";
	String DICE = "300";
	
	// 출금관련
	String WITHDRAW_TOLLS = "500"; //통행료를 낼경우 
	String WITHDRAW_PURCHASE = "510";
	String WITHDRAW_SOCIAL_FUND = "520";

		// 땅 도착 후 반응 관련
	String SET_MONEY = "600";
	String SET_MONEY_AND_STRUCTURE = "610";
	String TRAVEL="480";
	String VISIT_TRAVEL="490";
	String SET="720"; //여행 좌표 nowlocation 셋팅
	String SETTING="730";
	String DONATE="470";
		// 입금관련
	String DEPOSIT = "700";

		// 기타
	String CONCERT="620";
	String VISIT_CONCERT="630";
	String GAME_END = "800";
	String SERVER = "900";

	
	
}
