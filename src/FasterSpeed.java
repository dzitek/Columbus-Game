import java.awt.Point;
import java.util.Observable;
import java.util.Observer;


public class FasterSpeed implements Observer, PirateShipInterface {
	Point currentLocation;
	OceanMap oceanMap;
	Point shipLocation;

	public FasterSpeed(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getSpeed();
	}


	public Point getFasterSpeed() {
		return currentLocation;
	}

	public void goUp(Point p) {
		if (p.y > 0 && oceanMap.isFilled(p.x, p.y - 1)) {
			p.y -= 2;
		}
	}

	public void goRight(Point p) {
		if (p.x < 9 && oceanMap.isFilled(p.x + 1, p.y)) {
			p.x += 2;
		}
	}

	public void goLeft(Point p) {
		if (p.x > 0 && oceanMap.isFilled(p.x - 1, p.y)) {
			p.x -= 2;
		}
	}

	public void goDown(Point p) {
		if (p.y < 9 && oceanMap.isFilled(p.x, p.y + 1)) {
			p.y += 2;
		}
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			shipLocation = ((Ship) o).getShipLocation();
			if (shipLocation.x < currentLocation.x) {
				if (currentLocation.x > 0 && oceanMap.isFilled(currentLocation.x - 2, currentLocation.y)) {
					goLeft(currentLocation);
				}
			}
			if (shipLocation.y > currentLocation.y) {
				if (currentLocation.y < oceanMap.getDimensions()
						&& oceanMap.isFilled(currentLocation.x, currentLocation.y + 2)) {
					goDown(currentLocation);
				}

			}
			if (shipLocation.y < currentLocation.y) {
				if (currentLocation.y > 0 && oceanMap.isFilled(currentLocation.x, currentLocation.y - 2)) {
					goUp(currentLocation);
				}
			}
			if (shipLocation.x > currentLocation.x) {
				if (currentLocation.x < oceanMap.getDimensions()
						&& oceanMap.isFilled(currentLocation.x + 2, currentLocation.y)) {
					goRight(currentLocation);
				}
			}
		}
	}
	
	@Override
	public void New(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getSpeed();
	}
}