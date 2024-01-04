public class Bird extends Animal implements Winged {
    public Bird(int id, String name, BirthType birthType) {
        super(id, name, birthType);
    }
 
    @Override
    public boolean isWarmBlooded() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Bird";
    }

    @Override
    public boolean canFly() {
        return true;
    }
}
