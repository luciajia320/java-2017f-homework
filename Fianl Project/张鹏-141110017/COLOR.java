
public enum COLOR{
        
      RED("��ɫ"),ORANGE("��ɫ"),YELLOW("��ɫ"),GREEN("��ɫ"),CYAN("��ɫ"),BLUE("��ɫ"),PURPLE("��ɫ");
      private String name;
     
      private COLOR(String name){
            this.name=name;
      }
      public String GetCoName(){
         return name;
      }
      public void SetName(String name){
          this.name=name;
      }
}


