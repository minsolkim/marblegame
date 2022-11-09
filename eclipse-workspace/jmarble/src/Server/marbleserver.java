package Server;

import java.util.Vector;

import Object.Tile;
import Object.CityTile;
import Object.SpecialTile;
import Object.IsLandTile;

public class marbleserver {
	private Vector<Tile> tileList;
	
	
	public marbleserver() {
		initSetting();
	}
	public void initSetting() {
		
		tileList = new Vector<>();
		Tile T0 = new SpecialTile("시작", 1, 1, 650, 650);
		Tile T1 = new CityTile("이천",1,1,550,650);
		tileList.add(T0);
		tileList.add(T1);
		
	}
}
