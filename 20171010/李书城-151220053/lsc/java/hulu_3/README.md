


###类：

    creature:所有生物
        派生：
            爷爷；
            葫芦娃；
            蝎子精；
            蛇精；
            小喽啰；
            
    Heyi & Yanxing:阵法
        用来对生物排成的方阵进行对应变换
    
    Square:生物所在二维空间
    
    Position：对二维空间的单元：位置，进行具体描述
    
    各类各司其职，遵循SRP单一职责原则.
    
###实现机制：
    
    封装:对各个类的封装，使类的功能点明确，内部高聚合，外部低耦合
    
    多态:在继承时定义了抽象类，利于子类的拓展与实现.
        如类"Snake"对"Creature"的继承：
       
 public class Snake extends Creature{
    public Snake()
    {
        this.spieces = 1;
    }
    
    @override
    public void appear(){
        System.out.print("蛇");
    }
    
    @override
    public int return_index()
    {
        return 1;
    }

}

    而子类对某些方法进行了重写，用基类变量引用某子类对象时
    便可根据对象的不同选择执行某具体方法.
    
    各类可以继承，但是子类无法影响父类，遵循OCP开放封闭原则.
    
    

