public class ShipFactory {
	public PirateShipInterface getPirateShip(String Type) {
		OceanMap oceanMap = OceanMap.getOceanMap();
		
		if (Type == null) {
			return null;
		}
		if (Type.equals("PirateShip")) {
			return new PirateShip(oceanMap);
		}
		if (Type.equals("FastShip")){
			return new FasterSpeed(oceanMap);
		}
		if (Type.equals("Ship")) {
			return new Ship(oceanMap);
		}
		return null;
	}
}
