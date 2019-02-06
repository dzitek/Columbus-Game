import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.ImageView;
import java.awt.Point;

public class SeaMonster implements Observer {
	Point currentLocation;
	OceanMap oceanMap;
	ImageView s;

	public SeaMonster(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getSeaMonsterLocation();
	}

	public Point getSeaMonsterLocation() {
		return currentLocation;
	}

	public void goRight(Point p) {
		if (p.x < oceanMap.getDimensions() && !oceanMap.isFilled(p.x + 1, p.y))
			p.x++;
		oceanMap.getMap()[p.x-1][p.y] = 0;
		oceanMap.getMap()[p.x][p.y] = 3;
	}

	public void goLeft(Point p) {
		if (p.x > 0 && !oceanMap.isFilled(p.x - 1, p.y))
			p.x--;
		oceanMap.getMap()[p.x+1][p.y] = 0;
		oceanMap.getMap()[p.x][p.y] = 3;
	}

	public void goUp(Point p) {
		if (p.y > 0 && !oceanMap.isFilled(p.x, p.y - 1))
			p.y--;
		oceanMap.getMap()[p.x][p.y+1] = 0;
		oceanMap.getMap()[p.x][p.y] = 3;
	}

	public void goDown(Point p) {
		if (p.y < oceanMap.getDimensions() && !oceanMap.isFilled(p.x, p.y + 1))
			p.y++;
		oceanMap.getMap()[p.x][p.y-1] = 0;
		oceanMap.getMap()[p.x][p.y] = 3;
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			Point shipLocation = ((Ship) o).getShipLocation();
			boolean hasMoved = false;
				if (currentLocation.x < shipLocation.x) {
					if (currentLocation.x < oceanMap.getDimensions()
							&& !oceanMap.isFilled(currentLocation.x + 1, currentLocation.y)) {
						if (!hasMoved) {
							goRight(currentLocation);
							hasMoved = true;
						}
					}
				}
				if (currentLocation.x > shipLocation.x) {
					if (currentLocation.x > 0 && !oceanMap.isFilled(currentLocation.x - 1, currentLocation.y)) {
						if (!hasMoved) {
							goLeft(currentLocation);
							hasMoved = true;
						}
					}
				}
				if (currentLocation.y > shipLocation.y) {
					if (currentLocation.y > 0 && !oceanMap.isFilled(currentLocation.x, currentLocation.y - 1)) {
						if (!hasMoved) {
							goUp(currentLocation);
							hasMoved = true;
						}
					}
				}
				if (currentLocation.y < shipLocation.y) {
					if (currentLocation.y < oceanMap.getDimensions()
							&& !oceanMap.isFilled(currentLocation.x, currentLocation.y + 1)) {
						if (!hasMoved) {
							goDown(currentLocation);
							hasMoved = true;
						}
					}
				}

		}
	}
}
