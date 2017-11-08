package Homework.CalabashBrothers;

public class Main {
    public static void main(String[] args) {
        try {
            Field lawn = new Field(15, 15, Type.henge);
            System.out.println("【第一次对峙：】");
            lawn.setfield(15,15,Type.henge);
            lawn.showyourself();
            System.out.println("【第二次对峙：】");
            lawn.setfield(15, 15, Type.heyi);
            lawn.showyourself();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}