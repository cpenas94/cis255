public class Goldfish extends Fish {
    
    public Goldfish(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }
     
    @Override
    public String getDescription() {
        return "Fish (lives in water, does not breathe air)\tGoldfish";
    }
}
