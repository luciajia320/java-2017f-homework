/**
 *  In the gourd mountain, one grandpa raised seven children, and they all have
 *  strong abilities, as well as different colors, which are "red, orange, yellow,
 *  green, blue, indigo, violet".
 **/

public class StorySet {
    static private Grandpa gpa;
    static private Gourd gourd[];
    public static void init() {
        /* Grandpa comes to the stage */
        gpa=new Grandpa();
        /* Seven gourd wa was born in gourd mountain */
        gourd=new Gourd[7];
        /* Blow is seven groud wa with their own ability */
        gourd[0]=new Gourd("one", "red");
        gourd[1]=new Gourd("two", "oringe");
        gourd[2]=new Gourd("three", "yellow");
        gourd[3]=new Gourd("four", "green");
        gourd[4]=new Gourd("five", "blue");
        gourd[5]=new Gourd("six", "indigo");
        gourd[6]=new Gourd("seven", "violet");

    }
    public static void main(String[] args) {
        /* here is the begining of the story, all the characters come into sight */
        init();

        /* everyday in the morning, grandpa will gather gourd wa together and let
        them cound off to train them.*/
        gpa.train(gourd, 7);

    }
}
