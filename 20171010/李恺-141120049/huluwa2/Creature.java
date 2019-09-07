package huluwa2;

enum Species {
	HULUWA, GRANDPA, SOLDIERS, SNAKE, SCORPION,
}

public abstract class Creature {
	public Position position;
	public Species species;

	public abstract void report();

	Creature() {
		position = null;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition() {
		if (position.Empty()) {
			leavePosition();
			position.setHolder(this);
			this.position = position;
		} else
			;
	}

	public void leavePosition() {
		if (this.position != null) {
			position.out();
			this.position = null;

		}
	}
}

class Grandpa extends Creature {//үү�̳�������
	Grandpa() {
		this.species = Species.GRANDPA;
	}
	public void report() {
		System.out.print("үү");
	}
	class Snake extends Creature {//�߾��̳�������
		Snake() {
			this.species = Species.SNAKE;
		}
		public void report() {
			System.out.print("�߾�");
		}
	}
}
