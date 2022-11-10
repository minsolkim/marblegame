package Server;

import java.util.Vector;


import Pack_Model.EventBoard;
import Pack_Model.NormalBoard;
import Pack_Model.Board;
public class marbleserver {
	Vector<Board> areaArr; //Tile 객체를 담는 tilsList 
	
	public marbleserver() {
			initTile();
			initSetting();
			
	}
	private void initTile() {
		
	}
	public void initSetting() {
		
		areaArr = new Vector<>();
		Board T0 = new EventBoard("시작", 1, 720, 660);
		Board T1 = new EventBoard("산불",1,620,660);
		Board T2 = new NormalBoard("강릉", 0, 0, 0, null, 0, null, 0, 0, 0, 0, 0);
		
		
		areaArr.add(T0);
		areaArr.add(T1);
		areaArr.add(T2);
		
	}
}
