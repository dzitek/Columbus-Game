import java.awt.Point;
import java.util.Random;

public class OceanMap {
	final static int pirates = 2;
	private static OceanMap oceanMap = null;
	
	int[][] grid;
	int dimensions;
	int islandCount;
	Random rand = new Random();
	Point shipLocation;
	Point seaMonsterLocation;
	Point SpeedLocation;
	Point treasureLocation;
	Point[] islands = new Point[20];
	Point[] pirate = new Point[pirates];

	private OceanMap(int dimensions, int islandCount) {
		this.dimensions = dimensions;
		this.islandCount = islandCount;
		createGrid();
		placeIslands();
		placeShip();
		placePirate();
		placeSeaMonster();
		placeFasterSpeed();
		placeTreasure();
	}
	
	//Singleton Pattern used for Map
	public static OceanMap createOceanMap(int dimensions, int islandCount) {
		oceanMap = new OceanMap(dimensions, islandCount);
		return oceanMap;
	}
	
	
	public static OceanMap getOceanMap() {
		return oceanMap;
	}

	
	private void createGrid() {
		grid = new int[dimensions][dimensions];
		
	}
	public void retry() {
		placeIslands();
		placePirate();
		placeSeaMonster();
		placeFasterSpeed();
		placeTreasure();
		placeShip();
	}

	private void placeIslands() {
		int islandsToPlace = islandCount;
		int count = 0;
		while (islandsToPlace > 0) {
			int x = rand.nextInt(dimensions);
			int y = rand.nextInt(dimensions);
			if (grid[x][y] < 1) {
				grid[x][y] = 1;
				islandsToPlace--;
				islands[count] = new Point(x,y);
				count++;
			}
		}
	}
	
	private void placeShip() {
		boolean placedShip = false;
		int x = 0, y = 0;
		while (!placedShip) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (grid[x][y] < 1) {
				placedShip = true;
				shipLocation = new Point(x,y);
				grid[x][y] = 2;
			}
		}
	}

	private void placeSeaMonster() {
		boolean placedSeaMonster = false;
		int x = 0;
		int y = 0;
		while (!placedSeaMonster) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (grid[x][y] < 1) {
				grid[x][y] = 3;
				seaMonsterLocation = new Point(x, y);
				placedSeaMonster = true;
			}
		}
	}
	
	private void placeTreasure() {
		boolean placedTreasure = false;
		int x = 0;
		int y = 0;
		while (!placedTreasure) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (grid[x][y] < 1) {
				grid[x][y] = 4;
				treasureLocation = new Point(x, y);
				placedTreasure = true;
			}
		}
	}
	
	private void placeFasterSpeed() {
		boolean placedFasterSpeed = false;
		int x = 0;
		int y = 0;
		while (!placedFasterSpeed) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (grid[x][y] < 1) {
				grid[x][y] = 5;
				seaMonsterLocation = new Point(x, y);
				placedFasterSpeed = true;
			}
		}
	}
	
	
	private void placePirate() {
		int x = 0, y = 0;
		int count = 0;
		while (count < pirates) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (grid[x][y] < 1) {
				grid[x][y] = 6;
				pirate[count] = new Point(x,y);
				count++;
			}
		}
	}

	public Point getShipLocation() {
		return shipLocation;
	}

	public Point[] getPirateShips() {
		return pirate;
	}

	public Point getSeaMonsterLocation() {
		return seaMonsterLocation;
	}

	public Point getSpeed() {
		return seaMonsterLocation;
	}

	public Point[] getIslands() {
		return islands;
	}

	public Point getTreasureLocation() {
		return treasureLocation;
	}

	public int[][] getMap() {
		return grid;
	}

	public int getDimensions() {
		return dimensions;
	}

	
	
	public boolean isIsland(int x, int y) {
		if (x >= 0 && x < dimensions && y >= 0 && y < dimensions)
			if (grid[x][y] == 1)
				return true;
		return false;
	}
	
	public boolean isFilled(int x, int y) {
		if (x >= 0 && x < dimensions && y >= 0 && y < dimensions)
			if (grid[x][y] > 2 || grid[x][y] == 1)
				return true;
		return false;
	}

	public boolean checkLose() {
		if (getSeaMonsterLocation().equals(getShipLocation())) {
			return false;
		}
		if (getSpeed().equals(getShipLocation())) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (getPirateShips()[i].equals(getShipLocation())) {
				return false;
			}
		}
		return true;
	}

	public boolean checkWin() {
		if (getShipLocation().equals(getTreasureLocation()))
			return true;
		else
			return false;
	}
}
