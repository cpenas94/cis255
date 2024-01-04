public class Ostrich extends Bird {
    
    public Ostrich(int id, String name) {
        super(id, name, BirthType.LAYS_EGGS);
    }
 
    @Override
    public String getDescription() {
        return "Bird (Has Wings)\tOstrich (does not fly)";
    }
    
    @Override
    public boolean canFly() {
        return false;
    }
}
