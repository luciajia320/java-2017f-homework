public class monsters implements creature{
    int number;

    public monsters(int num){
        number=num;
    }

    @Override
    public String getName() {
        return "🐙";
    }
}
