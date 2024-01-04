public class Mammal extends Animal implements Endangered {

    public Mammal(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }
 
    @Override
    public boolean isWarmBlooded() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Mammal";
    }

    @Override
    public void displayConservationInformation() {
        System.out.println("Conservation information for " + getName());
    }
    
    @Override
    public boolean isEndangered() {
        return false;
    }
}
