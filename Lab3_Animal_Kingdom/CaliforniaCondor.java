public class CaliforniaCondor extends Bird implements Endangered, Winged {

    public CaliforniaCondor(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }
  
    @Override
    public boolean isWarmBlooded() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Bird\tCalifornia Condor\t(can fly, endangered)";
    }

    @Override
    public boolean canFly() {
        return true;
    }

    
    @Override
    public boolean isEndangered() {
        return true;
    }
    
    @Override
    public void displayConservationInformation() {
        System.out.println(" says: \"Help save the breathtaking California Condor!\"");
    }
}
