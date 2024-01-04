public class GreatWhiteShark extends Fish implements Endangered {
    
    public GreatWhiteShark(int id, String name) {
        super(id, name, BirthType.LIVE_BIRTH);
    }
     
    @Override
    public boolean isEndangered() {
        return true;
    }
    
    @Override
    public String getDescription() {
        return "Fish (lives in water, does not breathe air)\tGreat White Shark (endangered)";
    }
    
    
    @Override
    public void displayConservationInformation() {
        System.out.println(" says: \"Help save the great white shark!\"");
    }
}
