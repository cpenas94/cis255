public class Whale extends Mammal implements WaterDweller {

    public Whale(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }

    @Override
    public boolean isWarmBlooded() {
        return true; 
    }

    @Override
    public String getDescription() {
        return "Whale (lives in water, breathes air)";
    }
 
    @Override
    public boolean breathesAir() {
        return true; 
    }

    @Override
    public boolean isEndangered() {
        return false;
    }
    
    @Override
    public void displayConservationInformation() {
        System.out.println("Conservation information for " + getName());
    }
}
