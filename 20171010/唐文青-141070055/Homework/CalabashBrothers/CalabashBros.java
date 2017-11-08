package Homework.CalabashBrothers;

enum Rank{
    First, Second, Third, Fourth, Fifth, Sixth, Seventh
}
enum Color{
    Red, Orange, Yellow, Green, Cyan, Blue, Purple
}

public class CalabashBros extends Creature implements Show {
    private Rank rank;
    private Color color;

    public CalabashBros(Rank rank, Color color){
        isalive = true;
        isgood = true;
        gender = false;
        species = Species.Calabashbro;
        this.rank = rank;
        this.color = color;
    }

    protected void setRank(Rank r, Color c){
        this.rank = r;
        this.color = c;
    }

    public void showyourself(){
        switch(rank){
            case First:
                System.out.print("①");
                break;
            case Second:
                System.out.print("②");
                break;
            case Third:
                System.out.print("③");
                break;
            case Fourth:
                System.out.print("④");
                break;
            case Fifth:
                System.out.print("⑤");
                break;
            case Sixth:
                System.out.print("⑥");
                break;
            case Seventh:
                System.out.print("⑦");
                break;
        }
    }
}
