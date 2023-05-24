import java.util.Timer;
import java.util.TimerTask;

class pixel{
    public double x;
    public double y;
    public String color;
    public pixel(double x, double y, String color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
}

public class az {
    public static final String reset = "\u001B[0m";    //return to default color
    public static final String black = "\u001B[30m";   //choose color
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";

    public static void p(String s){      //easy print
        System.out.print(s);
    }

    public static void l(String s){      //easy println
        System.out.println(s);
    }

    public static pixel[][] arr = new pixel[160][12];

    public static void fill(int startX, int startY, int endX, int endY, String color){
        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                arr[x][y] = new pixel(x,y,color);
            }
        }
    }

    // we want to print a matrix with objects like {string:"#", color: red}

    public static void main(String[] args){
        //syntax: p(red+"az");
        int maxX = arr.length;
        int maxY = arr[0].length;
        String field[][] = new String[maxX][maxY];
        fill(0,0,160,12,green);
        fill(10,2,20,5,red);
        fill(30,4,60,10,blue);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            double n = 0;
            @Override
            public void run() {  //this function will be repeated as frames
                System.out.print("\033[H\033[2J");
                System.out.flush();
                for (double y = 0; y < maxY; y++) {
                    for (double x = 0; x < maxX; x++) {
                        int intX = (int) x;
                        int intY = (int) y;
                        String element = "";
//                        element += red; //color
////                        if (x + y > n) {
////                            element += blue;
////                        }
//                        if(Math.sin(x/4+n)*3+5>y){
//                            element += blue;
//                        }
                        element+= arr[intX][intY].color;
                        element += "@"; //string (one element recommended)
                        element += reset;
                        p(element);
                    }
                    l("");
                }
                n+=0.2;
            }


        };
        timer.scheduleAtFixedRate(task, 0, 500);
    }}
