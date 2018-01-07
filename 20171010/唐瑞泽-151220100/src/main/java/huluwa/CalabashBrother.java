/**
 * Created by tangruize on 10/16/17.
 */
package huluwa;
public class CalabashBrother extends Creature {
    private int rank;
    // constructor
    CalabashBrother(int i) {
        super(CalabashNames.values()[i].toString(), CalabashColors.values()[i].toString());
        rank = i;
    }

    @Override
    public String getName() {
        return CalabashNames.values()[rank].toString() + (CalabashColors.values()[rank].toString());
    }
}

enum CalabashNames {
    大娃, 二娃, 三娃, 四娃, 五娃, 六娃, 七娃
}

enum CalabashColors {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

