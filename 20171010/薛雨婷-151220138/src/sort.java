public class sort {
    public static  void main(String[] args) {
        brothers[] brother = new brothers[7];
        brother[0] = new brothers(COLORS.values()[3], SENIORITY.values()[3]);
        brother[1] = new brothers(COLORS.values()[2], SENIORITY.values()[2]);
        brother[2] = new brothers(COLORS.values()[5], SENIORITY.values()[5]);
        brother[3] = new brothers(COLORS.values()[4], SENIORITY.values()[4]);
        brother[4] = new brothers(COLORS.values()[6], SENIORITY.values()[6]);
        brother[5] = new brothers(COLORS.values()[1], SENIORITY.values()[1]);
        brother[6] = new brothers(COLORS.values()[0], SENIORITY.values()[0]);

        xiezijing xiezi=new xiezijing();

        monsters[] monster=new monsters[6];
        for(int i=0;i<6;i++)
            monster[i]=new monsters(i);

        grandpa grand=new grandpa();

        shejing snake=new shejing();

        Map map=new Map(brother,xiezi,monster);
       // map.shuffle();
        System.out.println("初始站位：");
        map.output();

        System.out.println("葫芦娃成长蛇队形：");
        new changshe().sort(map);
        map.output();

        System.out.println("蝎子精带领它的小喽啰布好阵：");
        new heyi().sort(map);
        map.output();

        System.out.println("老爷爷和蛇精前来助阵：");
        new GrandpaAndShejing(grand,snake).sort(map);
        map.output();

        System.out.println("蝎子精带领它的小喽啰转换阵型：");
        new jianshi().sort(map);
        map.output();
    }

        // BubbleSort(brother1);
        /*for(int i=0;i<7;i++)
            System.out.print(brother1[i].get_name()+"  ");
        System.out.println();*/

        //QuickSort(brother2,0,6);


    /*static void BubbleSort(brothers brother[]){
        for(int i=0;i<6;i++){
            for(int j=0;j<6-i;j++){
                if(brother[j].get_num()>brother[j+1].get_num()){
                    brothers temp=brother[j];
                    brother[j]=brother[j+1];
                    brother[j+1]=temp;
                    System.out.println(brother[j].get_name()+": "+j+" -> "+(j+1));
                    System.out.println(brother[j+1].get_name()+": "+(j+1)+" -> "+j);
                }
            }
        }
    }*/

    /*static void QuickSort(brothers brother[],int low,int high){
        if(low<high){
            int par=partition(brother,low,high);
            QuickSort(brother,low,par-1);
            QuickSort(brother,par+1,high);
        }
        return;
    }
    static int partition(brothers brother[],int low,int high){
        brothers pivot=brother[low];
        int sta=low;
        while(low<high){
            while(brother[high].get_num()>=pivot.get_num()&&high>low)
                high--;
            if(low!=high) {
                brother[low] = brother[high];
                System.out.println(brother[high].get_color() + ": " + high + " -> " + low);
            }
            while(brother[low].get_num()<=pivot.get_num()&&high>low)
                low++;
            if(low!=high) {
                brother[high] = brother[low];
                System.out.println(brother[low].get_color() + ": " + low + " -> " + high);
            }
        }
        if(high!=sta) {
            brother[high] = pivot;
            System.out.println(pivot.get_color() + ": " + sta + " -> " + high);
        }
        return high;
    }*/
}
