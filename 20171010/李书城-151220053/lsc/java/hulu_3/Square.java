package lsc.java.hulu_3;

public class Square {

    private position[][] square;
    int size;

    public Square(int s)
    {
        size = s;
        square = new position[size][size];

        for(int i =0; i < size ;++i)
            for(int j = 0; j < size; ++j)
            {
                square[i][j] = new position();
                square[i][j].set_x(i);
                square[i][j].set_y(j);
            }
    }


    //set one position to a creature
    public void set(int i, int j, Creature creature)
    {
      //  System.out.print(square[0][0].occupied);
        if(i < square.length && j <size && square[i][j].occupied == false)
        {
            square[i][j].holder = creature;
            square[i][j].occupied = true;
        }
    }

    //display
    public void display()
    {
        for(int i =0 ; i < size; ++i)
        {
            for(int j = 0; j < size; ++j)
            {
                if(square[i][j].occupied == true)
                   square[i][j].holder.appear();
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
    //get values from another square
    public void move_from_square(Square square_new)
    {
        for(int i =0 ; i < size; ++i)
        {
            for(int j = 0; j < size; ++j)
            {
                if(!square_new.return_empty(i,j) && square[i][j].occupied == false) {
                    square[i][j].holder = square_new.creature_re(i, j);
                    square[i][j].occupied = true;
                   // square_new.clear_holder(i, j);
                }
            }
        }
    }

    //return length
    public int length_re(){
        return square.length;
    }

    public boolean return_empty(int i ,int j)
    {
        return !square[i][j].occupied;
    }
    //return holder
    public Creature creature_re(int i, int j)
    {
        return square[i][j].holder;
    }

    public void clear_holder(int i, int j){
        square[i][j] = new position();
        square[i][j].set_y(j);
        square[i][j].set_x(i);
    }

    public void clear_all(){
        for(int i =0; i < size ;++i)
            for(int j = 0; j < size; ++j)
            {
                square[i][j] = new position();
                square[i][j].set_x(i);
                square[i][j].set_y(j);
            }
    }
    public void exchange(int i1, int j1, int i2, int j2){
        Creature temp = square[i1][j1].holder;
        square[i1][j1].holder = square[i2][j2].holder;
        square[i2][j2].holder = temp;
    }
}
