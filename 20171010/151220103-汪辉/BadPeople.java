import java.util.Vector;

public class BadPeople extends creature{
	private Snake Snake_essence;
	private Vector<creature> LittleEssence=new Vector<creature>();
	BadPeople(){
		Snake_essence=new Snake();
		creature tmp=new creature("Scorp ");
		tmp.setProperties(false);
		LittleEssence.addElement(tmp);
	}
	public void addEssence(String name) {
		creature tmp=new creature(name);
		tmp.setProperties(false);
		LittleEssence.addElement(tmp);
	}
	public void deleteAnBadPeople(int i) {
		if(i>LittleEssence.size()) {
			System.out.println("There is no Essence index of "+i);
			return;
		}
		else
			LittleEssence.remove(i);
	}
	public void AddAnBadPeople(creature a) {
		LittleEssence.addElement(a);
	}
	public int SizeOfBadPeople() {
		return LittleEssence.size();
	}
	public Snake GetSnake() {
		return Snake_essence;
	}
	public Vector<creature> GetLittleEssence(){
		return LittleEssence;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
