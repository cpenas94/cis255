public class BelugaWhale extends Whale {
	public BelugaWhale(int id, String name) {
	    super(id, name, BirthType.LIVE_BIRTH);
	}
 
    @Override
    public String getDescription() {
        return "Mammal\tWhale (Lives in water, breathes air)\tBeluga Whale";
    }
}
