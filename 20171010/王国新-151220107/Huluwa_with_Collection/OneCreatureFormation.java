
public class OneCreatureFormation extends Formation{
	public OneCreatureFormation(Creature creature) {
		super(1,1);
		this.content[0][0]=creature.getMatter();
	}
}
