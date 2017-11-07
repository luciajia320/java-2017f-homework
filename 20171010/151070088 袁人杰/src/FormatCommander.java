public class FormatCommander implements Formation{
    @Override
    public void arrowFormat(CreatureQueue queue, Coordinate start, Planar planar) {
        int planarSize=planar.getPlanarSize();
        int x,y;
        int queueLength=0;
        queueLength=queue.getLength();
        x=start.getX();
        y=start.getY();

        for (int i=0;i<queueLength;i++){
            if (i==0){
                planar.getGridOF(x,y).setHolder(queue.getGrid(i).getHolder());
            }
            else if (i<10){
                if (i%3==1){planar.getGridOF(x+1+(i-1)/3,y-1-i/3).setHolder(queue.getGrid(i).getHolder()); }
                if (i%3==2){planar.getGridOF(x+1+(i-1)/3,y).setHolder(queue.getGrid(i).getHolder()); }
                if (i%3==0){planar.getGridOF(x+1+(i-1)/3,y+i/3).setHolder(queue.getGrid(i).getHolder()); }
            }
            else
                planar.getGridOF(x+i-6,y).setHolder(queue.getGrid(i).getHolder());
        }
    }

    @Override
    public void chongeFormat(CreatureQueue queue, Coordinate start, Planar planar) {
        int planarSize=planar.getPlanarSize();
        int x,y;
        int queueLength=0;
        queueLength=queue.getLength();
        x=start.getX();
        y=start.getY();

        for(int i=0;i<queueLength;i++){
            if (x-1>=0 && x<planarSize && y+i>=0 && y+i<planarSize){
                if(planar.getGridOF((int)(x+Math.pow(-1,i)),y+i).isOccupied()){
                    System.out.println("Overlapping!");
                    return;
                }
            }
            else{
                System.out.println("Out of bound!");
                return;
            }
        }

        for(int i=0;i<queueLength;i++){
            int curX=(int) (x+Math.pow(-1,i));
            int curY=y+i;
            planar.getGridOF(curX,curY).setHolder(queue.getGrid(i).getHolder());
        }
    }


    @Override
    public void lineFormat(CreatureQueue queue, Coordinate start, Planar planar) {
        int planarSize=planar.getPlanarSize();
        int x,y;
        int queueLength=0;
        queueLength=queue.getLength();
        x=start.getX();
        y=start.getY();

        for(int i=0;i<queueLength;i++){
            if (x>=0 && x<planarSize && y+i>=0 && y+i<planarSize){
                if(planar.getGridOF(x,y+i).isOccupied()){
                    System.out.println("Overlapping!");
                    return;
                }
            }
            else{
                System.out.println("Out of bound!");
                return;
            }
        }

        for(int i=0;i<queueLength;i++){
            planar.getGridOF(x,y+i).setHolder(queue.getGrid(i).getHolder());
        }
    }

    @Override
    public void yanxingFormat(CreatureQueue queue, Coordinate start, Planar planar) {
        int planarSize=planar.getPlanarSize();
        int x,y;
        int queueLength=0;
        queueLength=queue.getLength();
        x=start.getX();
        y=start.getY();

        for(int i=0;i<queueLength;i++){
            if (x>=0 && x<planarSize && y+i>=0 && y+i<planarSize){
                if(planar.getGridOF(x-i,y+i).isOccupied()){
                    System.out.println("Overlapping!");
                    return;
                }
            }
            else{
                System.out.println("Out of bound!");
                return;
            }
        }

        for(int i=0;i<queueLength;i++){
            planar.getGridOF(x-i,y+i).setHolder(queue.getGrid(i).getHolder());
        }

    }

    @Override
    public void heyiFormat(CreatureQueue queue, Coordinate start, Planar planar) {
        int planarSize=planar.getPlanarSize();
        int x,y;
        int queueLength=0;
        queueLength=queue.getLength();
        x=start.getX();
        y=start.getY();

        for (int i=0;i<queueLength;i++){
            int curX=x+(int)((i+1)/2*(-1));
            int curY=y+(int)((i+1)/2*Math.pow(-1,i));
            planar.getGridOF(curX,curY).setHolder(queue.getGrid(i).getHolder());
        }
    }

}
