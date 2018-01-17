/**
 *  In the Calabash mountain, one grandpa raised seven children, and they all have
 *  strong abilities, as well as different colors, which are "red, orange, yellow,
 *  green, blue, indigo, violet".
 **/

public class StorySet {
    public static God god;
    public static void main(String[] args) {
        god=new God();
        /* here is the begining of the story, all the characters come into sight */
        god.init();

        /* everyday in the morning, grandpa will gather Calabash wa together and let
        them cound off to train them.*/
        god.story_1();

        /* grandpa let Calabash wa stand in the stage */
        god.story_2();
        //god.show();
    }

}
