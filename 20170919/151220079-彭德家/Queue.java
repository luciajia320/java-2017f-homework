package myJavahomework;
import java.util.Random;
class seat{
	Huluwa hulu;
	int position;
	seat(Huluwa i,int j){
		hulu = i;
		position = j;
	}
	void exchange(seat i,seat j) {
		i.hulu.hop(i.position, j.position);
		Huluwa temp = i.hulu;
		i.hulu = j.hulu;
		j.hulu = temp;
	}
}
public class Queue {
	 private Huluwa[] was;
	 private seat[] seats;
	Queue(){
		was = new Huluwa[7];
		seats = new seat[7];
		for(int i = 0;i < 7;i++) {
			was[i] = new Huluwa(i);
			seats[i] = new seat(was[i],i);
		}
	}
	void random() {
		Random rand = new Random();
		for(int i = 0;i < 7;i++) {
			int j = rand.nextInt(7);
			Huluwa temp = seats[i].hulu;
			seats[i].hulu = seats[j].hulu;
			seats[j].hulu = temp;
		}
	}
	void buffer_sort() {
		for(int i = 0;i < 6;i++) {
			int max = i;
			for(int j = i + 1;j < 7;j++) {
				if(seats[j].hulu.get_no() < seats[max].hulu.get_no())
					max = j;
			}
			seats[i].exchange(seats[i], seats[max]);
		}
	}
	void binaryInsertSort() {
		for(int i = 1;i < seats.length;i++) {
			Huluwa temp = seats[i].hulu;
			int low = 0;
			int high = i- 1;
			while(low <= high) {
				int mid = (low+high)/2;
				if(seats[mid].hulu.get_color() < temp.get_color())
				{
					low = mid + 1;
				}
				else
					high = mid - 1;
			}
			for(int j = i - 1;j >= low;j--)
				seats[j].exchange(seats[j], seats[j+1]);
			seats[low].hulu = temp;
		}
	}

	void print_no() {
		for(int i = 0;i < 7;i++) {
			seats[i].hulu.print_no();
		}
		System.out.println();
	}
	void print_color() {
		for(int i = 0;i < 7;i++) {
			seats[i].hulu.print_color();
		}
		System.out.println();
	}
	public static void main(String[]args) {
		Queue myqueue = new Queue();
		myqueue.random();
		System.out.println("排序前:");
		myqueue.print_color();
		myqueue.print_no();
		myqueue.binaryInsertSort();
		System.out.println("排序后:");
		myqueue.print_color();
		myqueue.print_no();
		myqueue.random();
		System.out.println("排序前:");
		myqueue.buffer_sort();
		System.out.println("排序后:");
		myqueue.print_color();
		myqueue.print_no();
	}
}
