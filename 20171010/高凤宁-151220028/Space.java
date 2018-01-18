public class Space {
    private int N;
    private Position [][] matrix;

    private void SetN(int N){this.N = N;}
    private void InitMatrix(){
        for(int i = 0;i < N;i++)
            for(int j = 0;j < N;j++)
                matrix[i][j] = new Position();
    }

    Space(){
        SetN(20);
        matrix = new Position[N][N];
        InitMatrix();
    }

    public boolean SetCreature(int x,int y,Creature creature){
        if(matrix[x][y].GetHolder() != null){
            return false;
        }
        matrix[x][y].SetHolder(creature);
        return true;
    }

    public void DelCreature(Creature creature){
        for(int i = 0;i < N;i++)
            for(int j = 0;j < N;j++)
                if(matrix[i][j].GetHolder() != null && matrix[i][j].GetHolder().getClass() == creature.getClass())
                    matrix[i][j].SetHolder(null);
    }

    public boolean SnakeFormation(int x,int y,Queue creatures){
        if(x + creatures.GetSize() > N){
            System.out.println("Sorry,the team is too long!");
            return false;
        }
        for(int i = 0;i < creatures.GetSize();i++){
            if(matrix[x + i][y].GetHolder() != null){
                System.out.println("Sorry,there exists conflicts in the matrix!");
                return false;
            }
            SetCreature(x + i,y,creatures.GetPositions()[i].GetHolder());
        }
        return true;
    }

    public boolean CranewingFormation(int x,int y,Creature []creatures){
        int halfheight = creatures.length / 2 + 1;
        int width;
        if(creatures.length % 2 == 0)
            width = creatures.length / 2 + 1;
        else
            width = (creatures.length + 1) / 2;

        if(x < halfheight || x > N - halfheight || y < width || y > N - 1){
            System.out.println("Sorry,the camp is beyond border!");
            return false;
        }
        int num = 0;
        for(int i = 0;i < halfheight;i++,num++){
            if(matrix[x - i][y - i].GetHolder() != null){
                System.out.println("Sorry,there exists conflicts in the matrix!");
                return false;
            }
            SetCreature(x - i,y - i,creatures[i]);
        }
        for(int i = 1;i < halfheight && num < creatures.length;i++,num++){
            if(matrix[x + i][y - i].GetHolder() != null){
                System.out.println("Sorry,there exists conflicts in the matrix!");
                return false;
            }
            SetCreature(x + i,y - i,creatures[num]);
        }
        return true;
    }

    public boolean ArrowFormation(int x,int y,Creature[] creatures){
        if(x < 1 || x > N - 2 || y < 0 || y > N - 4){
            System.out.println("Sorry,the camp is beyond border!");
            return false;
        }
        for(int i = 0;i < creatures.length - 2;i++){
            if(matrix[x][y + i].GetHolder() != null){
                System.out.println("Sorry,there exists conflicts in the matrix!");
                return false;
            }
            SetCreature(x,y + i,creatures[i]);
        }
        if(matrix[x - 1][y + 1].GetHolder() != null || matrix[x + 1][y + 1].GetHolder() != null){
            System.out.println("Sorry,there exists conflicts in the matrix!");
            return false;
        }
        SetCreature(x - 1,y + 1,creatures[creatures.length - 2]);
        SetCreature(x + 1,y + 1,creatures[creatures.length - 1]);
        return true;
    }

    public void Display(){
        for(int i = 0;i < N;i++){
            for(int j = 0;j < N;j++){
                if (matrix[i][j].GetHolder() == null)
                    System.out.print("\t\t");
                else if (matrix[i][j].GetHolder() instanceof Calabash || matrix[i][j].GetHolder() instanceof Grandpa || matrix[i][j].GetHolder() instanceof Snake)
                    System.out.print(matrix[i][j].GetHolder().GetName() + "\t\t");
                else
                    System.out.print(matrix[i][j].GetHolder().GetName() + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
    }
}