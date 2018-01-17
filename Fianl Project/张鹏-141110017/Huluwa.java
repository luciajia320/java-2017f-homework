
 public class Huluwa extends organism implements Runnable{
    
    private COLOR co;
    private int rank;

    Huluwa(String na,COLOR co,int ra,space sp,String ip){
          SetName(na);SetColor(co);SetRank(ra);SetSpace(sp);SetLife(100);SetPath(ip);
    }
        


    public  String GetColor(){
         return this.co.GetCoName();
    }
    public void SetColor(COLOR co){
          this.co=co;
    }



    public int GetRank(){
          return this.rank;
    }
    public void SetRank(int ra){
          this.rank=ra;
    }


    public void report(Huluwa hu,int i,int j){
         System.out.println("ÀÏ"+hu.GetRank()+":"+i+"->"+j);
    }
    public void reportRank(Huluwa hu){
         System.out.println(hu.GetRank());
    }
    public void reportCo(Huluwa hu){
         System.out.println(hu.GetColor());
    }

   public Position search(){
      for(int i=0;i<this.GetSpace().Getsize();i++)
         for(int j=0;j<this.GetSpace().Getsize();j++){
            if(this.GetSpace().GetPosition(i,j).holder instanceof yaoguai){
              return this.GetSpace().GetPosition(i,j);
          }
       }return null;
   }

   public void remove(int i,int j){
      if(this.GetSpace().GetPosition(i,j).holder.GetName()!="****"){
           if(this instanceof Huluwa&&this.GetSpace().GetPosition(i,j).holder instanceof yaoguai)
                 this.attack(i,j);
      }

      else{ 
          organism ground=new organism();
          ground.SetName("****");
          this.GetSpace().GetPosition(i,j).holder=this;
          this.GetSpace().GetPosition(this.GetX(),this.GetY()).holder=ground;
          this.SetXY(i,j);
      }
    }


    public void attack(int i,int j){
      if(this instanceof Huluwa){
            double  a=Math.random();
           if(a>=0.8*this.GetLife()/this.GetSpace().GetPosition(i,j).holder.GetLife()){
              organism ground=new organism();
              ground.SetName("****");
              this.GetSpace().GetPosition(this.GetX(),this.GetY()).holder=ground;
              this.GetSpace().GetPosition(i,j).holder.SetLife(a*this.GetSpace().GetPosition(i,j).holder.GetLife());
      
           }
           else{
              organism ground=new organism();
              ground.SetName("****");
              this.GetSpace().GetPosition(i,j).holder=ground;               
              this.remove(i,j);
              this.SetLife((1-a)*this.GetLife());
           }
      }
    }
    

    public void run(){
       synchronized(this){
         /*for(int i=1;i<8;i++){
            if(this.GetSpace().GetPosition(i,0).holder.GetName()=="****"){
               try{
                 this.GetSpace().SetST(this,i,0);
                 this.SetXY(i,0);
                 break;
                 
                 
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         }*/
         
        /* if(this.GetSpace().GetPosition(8,0).holder.GetName()=="****")
           try{
               this.wait();
           }catch(Exception e){
                  e.printStackTrace();
           }*/
         if(this.GetName()=="ºìÍÞ")
         {this.GetSpace().SetST(this,1,0);this.SetXY(1,0);}
         if(this.GetName()=="³ÈÍÞ")         
         {this.GetSpace().SetST(this,2,1);this.SetXY(2,1);}
         if(this.GetName()=="»ÆÍÞ")
         {this.GetSpace().SetST(this,3,2);this.SetXY(3,2);}
         if(this.GetName()=="ÂÌÍÞ")         
         {this.GetSpace().SetST(this,4,3);this.SetXY(4,3);}
         if(this.GetName()=="ÇàÍÞ")
         {this.GetSpace().SetST(this,5,4);this.SetXY(5,4);}
         if(this.GetName()=="À¶ÍÞ")         
         {this.GetSpace().SetST(this,6,5);this.SetXY(6,5);}
         if(this.GetName()=="×ÏÍÞ")
         {this.GetSpace().SetST(this,7,6);this.SetXY(7,6);}


        
         for(int a=0;a<7;a++){
            if(this.GetLife()>0)


                if(this.search()!=null){
                    int m=this.search().holder.GetY();
                    int n=this.search().holder.GetX();
                    int y=this.GetY();
                    int x=this.GetX();
                    for(int i=y;i<m;i++)
                         {this.remove(this.GetX(),i+1);this.GetSpace().printout();}
                    for(int j=x;j<n;j++)
                         {this.remove(j+1,this.GetY());this.GetSpace().printout();}
                }
         }
       }
    }

       
         








}