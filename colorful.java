import java.util.Timer;
import java.util.TimerTask;

public class colorful {
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

    // we want to print a matrix with objects like {string:"#", color: red}

    public static void main(String[] args){
        //syntax: p(red+"az");
        int maxX = 160;
        int maxY = 12;
        String field[][] = new String[maxX][maxY];
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int n = 0;
            @Override
            public void run() {  //this function will be repeated as frames
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
                for (double y = 0; y < maxY; y++) {
                    for (double x = 0; x < maxX; x++) {
                        String element = "";
                        element += red; //color
//                        if (x + y > n) {
//                            element += blue;
//                        }
                        if(Math.sin(x/4)*3+5>y){
                            element += blue;
                        }
                        element += "@"; //string (one element recommended)
                        element += reset;
                        p(element);
                    }
                    l("");
                }
                n++;
            }


        };
        timer.scheduleAtFixedRate(task, 0, 1000);
}}
