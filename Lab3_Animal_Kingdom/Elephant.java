public class Elephant extends Mammal implements Endangered {
    
	public Elephant(int id, String name) {
	    super(id, name, BirthType.LIVE_BIRTH);
	}

 
	@Override
    public boolean isWarmBlooded() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Mammal\tElephant\t(lives on land, endangered)";
    }
    
    @Override
    public boolean isEndangered() {
        return true;
    }
    @Override
    public void displayConservationInformation() {
        System.out.println(" says: \"Help save the beautiful elephants!");
    }
    

}
