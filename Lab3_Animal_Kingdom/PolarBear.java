public class PolarBear extends Mammal implements Endangered {

	// Extra Credit
    public PolarBear(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }
 
    @Override
    public boolean isWarmBlooded() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Mammal\tPolar bear (endangereed)";
    }

    @Override
    public void displayConservationInformation() {
        System.out.println(" says: \"Help save the astonishing Polar Bear!");
    }

}
