# 作业:面向葫芦娃编程

## 类的设计

1. Creature类

   Creature类作为基类, 相较于前一个版本所做的改变是去除了position field. 这是考虑到Creature类出现在Space中是在formation类的包装下,而formation类包含Position信息, 故考虑除去冗余部分

2. Huluwa, Grandpa, Snake, Minions类

   这些都是继承Creature的子类.

   值得注意的是, 引入了接口Active

   ```java
   public interface Active {
       public void act();
   }
   ```

   尽管理论上此时爷爷和蛇精都只会喊"666", 但拥有玉如意的蛇精 和 主要配角之一的爷爷, 不可能只喊"666"

   为了拓展和与其他类的统一, 引入接口Active

   例如爷爷类:

   ```java
   public class Grandpa extends Creature implements Active {
       Grandpa(){
           super("\uD83C\uDF85");
       }
       @Override
       public void act(){
           System.out.println("葫芦娃,666!");
       }
   }
   ```

   另外, 由于蝎子精在剧中只是一个高级步兵, 故暂时将其归入了杂兵类

   ​

3. 阵法类Formation

   Formation类相比example中所给出的Formation类做了一点改变

   ``` java
   //protected String[][] content;
   protected Creature[][] content;
   ```

   这是因为若仅仅存储String, Formation类则仅仅只有显示功能, 而丧失了拓展的可能性, 例如可能存在的"属性加成", "群体技能"等等

   而Formation有一个子类SingleFormation

   ``` java
   public class SingleFormation extends Formation {
       SingleFormation(Creature creature){
           super(1,1);
           Creature[][] creatures = super.getContent();
           creatures[0][0] = creature;
       }
   }
   ```

   这是由单个生物形成的formation, 是为了让爷爷,蛇精 和葫芦娃及蝎子精带队的小喽啰有相同的形式.

4. 类的关系

   Space类只记录formation类, 而 formation类记录包含的Creature以及自己的Position信息

5. 关于sort

   由于各种Formation中,只有长蛇阵才有sort的可能, 故在长蛇阵类中实现sort函数, 通过之前实现的Bubble Sorter, 对稍做一些改变的content数组进行排序