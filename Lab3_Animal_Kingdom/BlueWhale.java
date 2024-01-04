public class BlueWhale extends Whale implements Endangered, WaterDweller {
	public BlueWhale(int id, String name) {
	    super(id, name, BirthType.LIVE_BIRTH);
	}

 
    @Override
    public String getDescription() {
        return "Whale\tBlue Whale\t(lives in water, breathes air, endangered)";
    }

    @Override
    public boolean breathesAir() {
        return true;
    }
    
    @Override
    public boolean isEndangered() {
        return true;
    }
    
    @Override
    public void displayConservationInformation() {
        System.out.println(" says: \"Help save the mighty blue whale!");
    }
}
