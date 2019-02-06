

public class OceanTest {
		
		public void TestForOceanMap() {
			OceanMap x = OceanMap.getOceanMap();
			OceanMap y = OceanMap.getOceanMap();
			assert(x == y);
		}
		
		
		public void TestOceanMap1() {
			OceanMap x = OceanMap.getOceanMap();
			OceanMap y = OceanMap.getOceanMap();
		
			assert(x == y);
			
			OceanMap.createOceanMap(10, 10);
			assert(x == y);
		}
		
	
		
		

}
