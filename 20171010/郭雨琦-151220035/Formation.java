enum FORMATIONS{
    HEYI,YANXING,CHONGE,CHANGSHE,YULIN,FANGYUAN,YANYUE,FENGSHI
}

public class Formation {
  public static void buildYANXING(Creature[] queue,Position[][] positions,int leaderX, int leaderY, boolean pn){
      for(int i = 0; i < queue.length; ++i){
          if(queue[i].getPosition()!=null)
              queue[i].getPosition().setHolder(null);
      }
      queue[0].setPosition(positions[leaderX][leaderY]);
      if(pn){
          for(int i = 1; i <queue.length; ++i){
              queue[i].setPosition(positions[leaderX+i][leaderY-i]);
          }
      }
      else{
          for(int i = 1; i <queue.length; ++i){
              queue[i].setPosition(positions[leaderX-i][leaderY+i]);
          }
      }
  }
  public static void buildCHANGSHE(Creature[] queue,Position[][] positions,int leaderX, int leaderY, boolean pn){
      for(int i = 0; i < queue.length; ++i){
          if(queue[i].getPosition()!=null)
              queue[i].getPosition().setHolder(null);
      }
      queue[0].setPosition(positions[leaderX][leaderY]);
      if(pn){
          for(int i = 1; i <queue.length; ++i){
              queue[i].setPosition(positions[leaderX+i][leaderY]);
          }
      }
      else{
          for(int i = 1; i <queue.length; ++i){
              queue[i].setPosition(positions[leaderX-i][leaderY]);
          }
      }
  }
  public static void bulidHEYI(Creature[] queue,Position[][] positions,int leaderX, int leaderY, boolean pn){
      for(int i = 0; i < queue.length; ++i){
          if(queue[i].getPosition()!=null)
            queue[i].getPosition().setHolder(null);
      }
      queue[0].setPosition(positions[leaderX][leaderY]);
      if(pn){
          for(int i = 1; i <queue.length; ++i){
              if(i%2==1){
                  queue[i].setPosition(positions[leaderX+(i+1)/2][leaderY-(i+1)/2]);
              }
              else{
                  queue[i].setPosition(positions[leaderX+i/2][leaderY+i/2]);
              }
          }
      }
      else{
          for(int i = 1; i <queue.length; ++i){
              if(i%2==1){
                  queue[i].setPosition(positions[leaderX-(i+1)/2][leaderY-(i+1)/2]);
              }
              else{
                  queue[i].setPosition(positions[leaderX-i/2][leaderY+i/2]);
              }
          }
      }
  }
}

