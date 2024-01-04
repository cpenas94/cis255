public class DuckbilledPlatypus extends Mammal {
    
	public DuckbilledPlatypus(int id, String name) {
	    super(id, name, BirthType.LAYS_EGGS);
	}
 

	@Override
    public boolean isWarmBlooded() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Mammal\tDuck-Billed Platypus";
    }
}
