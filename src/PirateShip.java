import java.util.Observable;
import java.util.Observer;
import java.awt.Point;

public class PirateShip implements Observer, PirateShipInterface {
	Point[] currentLocation;
	OceanMap oceanMap;

	public PirateShip(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getPirateShips();
	}

	public Point[] getShipLocation() {
		return currentLocation;
	}

	public void goRight(Point p) {
		if (p.x < oceanMap.getDimensions() && !oceanMap.isFilled(p.x + 1, p.y))
			p.x++;
	}

	public void goLeft(Point p) {
		if (p.x > 0 && !oceanMap.isFilled(p.x - 1, p.y))
			p.x--;
	}

	public void goUp(Point p) {
		if (p.y > 0 && !oceanMap.isFilled(p.x, p.y - 1))
			p.y--;
	}

	public void goDown(Point p) {
		if (p.y < oceanMap.getDimensions() && !oceanMap.isFilled(p.x, p.y + 1))
			p.y++;
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			Point shipLocation = ((Ship) o).getShipLocation();

			System.out.println(); 
			for (Point p : currentLocation) {
				boolean hasMoved = false;
				if (p.x < shipLocation.x && !hasMoved) {
					if (p.x < oceanMap.getDimensions() && !oceanMap.isFilled(p.x + 1, p.y)) {
						goRight(p);
						hasMoved = true;
					}
				}
				if (p.x > shipLocation.x && !hasMoved) {
					if (p.x > 0 && !oceanMap.isFilled(p.x - 1, p.y)) {
						goLeft(p);
						hasMoved = true;
					}
				}
				if (p.y > shipLocation.y && !hasMoved) {
					if (p.y > 0 && !oceanMap.isFilled(p.x, p.y - 1)) {
						goUp(p);
						hasMoved = true;
					}
				}
				if (p.y < shipLocation.y && !hasMoved) {
					if (p.y < oceanMap.getDimensions() && !oceanMap.isIsland(p.x, p.y + 1)) {
						goDown(p);
						hasMoved = true;
					}
				}

			}
		
		}
	}

	@Override
	public void New(OceanMap oceanMap) {
		this.oceanMap = oceanMap; 
		currentLocation = oceanMap.getPirateShips(); 
		
	}
}