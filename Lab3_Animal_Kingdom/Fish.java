public class Fish extends Animal implements WaterDweller, Endangered {

    public Fish(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }
 
    @Override
    public boolean isWarmBlooded() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Fish";
    }

    @Override
    public boolean breathesAir() {
        return false;
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
