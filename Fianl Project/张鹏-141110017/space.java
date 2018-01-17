

import java.awt.*;  
  
import javax.swing.*;  



public class space{
        private int size;
        private Position[][] positions;
        private String ImagePath;

        public String GetPath(){
          return ImagePath;
        }
        public void SetPath(String ip){
          this.ImagePath=ip;
        }


        public Position GetPosition(int i,int j){
          return positions[i][j];
        }

        public void Setsize(int size){
          this.size=size;
        }
        public int Getsize(){
          return size;
        }        
   

//空间打印
        public void printout(){
          for(int i=0;i<size;i++)
            for(int j=0;j<size;j++){
                 if(j<size-1)
                     System.out.print(positions[i][j].holder.GetName()+" ");
                 else
                     System.out.println(positions[i][j].holder.GetName());
            }
             System.out.println("----next---");
        }
//植入子空间
       public void Addsubspace(space sub,int i,int j) throws MyException{
           if(i+sub.Getsize()>this.size||j+sub.Getsize()>this.size)
              throw new MyException("Out of bounds");


           for(int m=0;m<sub.Getsize();m++)
              for(int n=0;n<sub.Getsize();n++){
                   /*if(sub.positions[m][n].holder!=ground){
                       if(this.positions[i+m][j+n].holder!=ground)
                          throw new MyException("Conflicted");
                       else*/
                           this.positions[i+m][j+n].holder=sub.positions[m][n].holder;
                   
              }
       }
        public void SetST(organism st,int l, int c)/*throws MyException*/{
            /*if(l>this.Getsize()||c>this.Getsize())
              throw new MyException("Out of bounds");*/
       
       
               positions[l][c].holder=st;
        }



        space(int size){
           Setsize(size);SetPath("src/b.jpg");
           organism  ground=new organism();
           ground.SetName("****");
           positions=new Position[size][size];
           for(int i=0;i<size;i++)
              for(int j=0;j<size;j++){
                  positions[i][j]=new Position();
                  positions[i][j].line=i;
                  positions[i][j].column=j;
                  positions[i][j].holder=ground;
              }
              
        }
        space(){
           Setsize(7);
           organism  ground=new organism();
           ground.SetName("****");
           positions=new Position[size][size];
           for(int i=0;i<size;i++)
              for(int j=0;j<size;j++){
                  positions[i][j]=new Position();
                  positions[i][j].line=i;
                  positions[i][j].column=j;
                  positions[i][j].holder=ground;
           }
         }




          
 



}
