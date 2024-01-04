public class Horse extends Mammal {
    
    public Horse(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }
     
    @Override
    public String getDescription() {
        return "Mammal\tHorse";
    }
}
