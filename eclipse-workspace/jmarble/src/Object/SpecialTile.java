package Object;

public class SpecialTile extends Tile implements SpecialInterface{
	public SpecialTile(String tileName, int tileNum, int tileType, int tileX, int tileY) {
		super(tileName, tileNum, tileType, tileX, tileY);
	}

	@Override
	public void event() {
	} 

}
