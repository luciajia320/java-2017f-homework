package test;

public class output {
	static void output_num(int x) {
		switch(x){
        default:
            System.out.println("error");
            break;
        case 1:
            System.out.println("老大");
            break;
        case 2:
            System.out.println("老二");
            break;
        case 3:
            System.out.println("老三");
            break;
        case 4:
            System.out.println("老四");
            break;
        case 5:
            System.out.println("老五");
            break;
        case 6:
            System.out.println("老六");
            break;
        case 7:
            System.out.println("老七");
            break;
        
		}
	}
	static void output_color(char x) {
		switch(x){
        default:
            System.out.println("error");
            break;
        case 'r':
            System.out.println("红色");
            break;
        case 'o':
            System.out.println("橙色");
            break;
        case 'y':
            System.out.println("黄色");
            break;
        case 'g':
            System.out.println("绿色");
            break;
        case 'b':
            System.out.println("青色");
            break;
        case 'd':
            System.out.println("蓝色");
            break;
        case 'p':
            System.out.println("紫色");
            break;
        
		}
	}
	static void output_change(int x,int i,int j) {
		switch(x){
        default:
            System.out.println("error");
            break;
        case 1:
            System.out.println("老大:" +i +"->" +j);
            break;
        case 2:
            System.out.println("老二:" +i +"->" +j);
            break;
        case 3:
            System.out.println("老三:" +i +"->" +j);
            break;
        case 4:
            System.out.println("老四:" +i +"->" +j);
            break;
        case 5:
            System.out.println("老五:" +i +"->" +j);
            break;
        case 6:
            System.out.println("老六:" +i +"->" +j);
            break;
        case 7:
            System.out.println("老七:" +i +"->" +j);
            break;
        
		}
	}
}
