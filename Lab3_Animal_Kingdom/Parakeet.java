public class Parakeet extends Bird {
    
    public Parakeet(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }
      
    @Override
    public String getDescription() {
        return "Bird (Has Wings)\tParakeet (flies)";
    }
    
}
