
import java.awt.Point;

public class MapTest {

		OceanMap oceanMap;
		
		
		public void test1(){
			OceanMap x = OceanMap.getOceanMap();
			OceanMap.createOceanMap(20, 20);
			assert(x.getShipLocation() instanceof Point);
		}
		
	}