# 葫芦娃final project说明

## 游戏说明

### 人物

爷爷，七个葫芦娃，蛇精，蝎子精，小喽啰，所有生物都可以上、下、左、右活动。七个兄弟和爷爷以长蛇阵型在空间的左侧战队；妖精（蛇精、蝎子精、小喽啰）以鹤翼阵型在空间右侧战队；当某个生物体与敌方相遇（二者间x和y方向距离均小于15时）时，以1/2概率决定双方生死，死者在屏幕上显示墓碑，生者寻找下一个距离最近的敌人继续攻击。

### 操作说明

1. 按空格键时所有生物体向敌方前进；
2. 战斗未开始或结束状态下，按下L键,将按“report.txt"（项目目录下）文件中记录的内容进行战斗回放（如果是结束状态下，"report.txt"中存储的是刚结束的战斗记录；游戏开始状态下，存储的是上一次运行游戏产生的记录）；在按下L键后到开始重放可能有2-3秒的延迟。
3. 按R键，游戏重置。在重放状态、游戏进行中、游戏结束时想重新开始，均需按下R键重置，再按空格键开始。

## 用到的面向对象的概念、机制、设计模式

### 继承

Player是所生物的父类，实现了生物的共同特征，Huluwa、GrandPa等都从Player继承。

### 组合(Composition)

Grand类有一个数据成员Field类型的对象，代表所有生物活动的场地，这是一种两类之间的组合关系。组合通常是希望新类内部具有已存在类的功能时使用，而不是希望已存在的类作为他的接口。组合更符合该场景。

### 访问控制、封装

对数据成员使用private的访问控制，对外提供setIdname()和getIdname()接口函数，实现了数据的封装，更安全。

### RTTI

```

Thing2D item = (Thing2D) world.get(i);

if (item instanceof Player) {
        
        Player p=(Player)item;
                
                if(p.getIsLive())
                
                        g.drawImage(item.getImage(), item.x() , item.y() , this);
}

```

item instanceof Player用到了运行时类型的判断。

### 异常处理

```

try {
           
        file.createNewFile(); // 创建文件
    
} catch (IOException e) {
            
            e.printStackTrace();
        
}

```

对文件的操作用到了异常处理。

### 集合类型、泛型

```

private ArrayList<Player> player1=new ArrayList<Player>();
    
```

对生物体的存储用到了集合类型ArrayList，同时它的参数类型为Player。

### 输入输出

FileOperation类为对文件的读写操作类，用以实现游戏记录和重现。

### 多线程协同

```

for (Player p : enemys) {
        
        synchronized (Player.class){
        
        int dis = distance(p);
        
        if (p.isLive == true && dis <= min) {
        
                goal = p;
                
                min = dis;
    
        }
        
}
                
```

由于每个生物体都是单独的线程，在一个生物体判断另一个生物体的位置时，其它生物体线程如果同时工作将产生线程不安全，因此采用synchronized机制保证线程安全。

### 装饰器模式

```

private static  FileInputStream in=null;

private static InputStreamReader isr=null;
   
private static  BufferedReader bufr=null;

……   
  
try {

        in = new FileInputStream(file);
        
        isr = new InputStreamReader(in);
        
        bufr = new BufferedReader(isr);
} catch (Exception e) {
            
        e.printStackTrace();

}

```

这段FileOperation类中的文件操作的代码用到了装饰器模式。装饰器通过包装一个装饰对象来扩展其功能，而又不改变其接口。

### 单元测试

GroundTest类为测试类，实现了对Player类中重要方法的测试。

### 自动构建

使用maven进行构建管理。（注：maven是用Ideal自带的maven，没有问题。在控制台用自己安装的maven进行构建出现一点问题，暂时没有发现原因，可能是我自己的maven没有设置正确。但用Ideal的maven是可以正确clean、test、packge并生成.jar文件。）

## 致谢

感想曹老师、余老师的辛勤教诲和付出，感谢在实验过程中解答我问题的同学。