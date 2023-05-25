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

    //FIELD SIZE:
    public static pixel[][] arr = new pixel[160][12];

    public static void fill(int startX, int startY, int endX, int endY, String color){
        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                if(x<arr.length && y<arr[1].length&&x>=0&&y>=0){
                    arr[x][y] = new pixel(x,y,color);
                }
            }
        }
    }

    public static void fillB(double startX, double startY, double endX, double endY, String color){
        double oldX1 = startX*2.5;
        int x1 = (int) oldX1;
        double oldX2 = endX*2.5;
        int x2 = (int) oldX2;
        int y1 = (int) startY;
        int y2 = (int) endY;
        x2 += x1;
        y2 += y1;
        if(x1 < x2){
            if(y1 < y2){
                fill(x1,y1,x2,y2,color);
            }else{
                fill(x1,y2,x2,y1,color);
            }
        }else{
            if(y1 < y2){
                fill(x2,y1,x1,y2,color);
            }else{
                fill(x2,y2,x1,y1,color);
            }
        }
    }

    // we want to print a matrix with objects like {string:"#", color: red}

    public static void main(String[] args){
        //syntax: p(red+"az");
        int maxX = arr.length;
        int maxY = arr[0].length;
        String field[][] = new String[maxX][maxY];
        fill(0,0,arr.length,arr[1].length,white);
        double maxXB = (double) arr.length/2;
        double maxYB = (double) arr[0].length;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            //INIT-------------------------------------------------------------------------------------------------------->
            double n = 0;
            //------------------------------------------------------------------------------------------------------------>
            @Override
            public void run() {  //this function will be repeated as frames
                System.out.print("\033[H\033[2J");
                System.out.flush();
                l("");
                l("");
                for (double y = 0; y < maxY; y++) {
                    for (double x = 0; x < maxX; x++) {
                        int intX = (int) x;
                        int intY = (int) y;
                        String element = "";
                        element+= arr[intX][intY].color;
                        element += "@"; //string (one element recommended)
                        element += reset;
                        p(element);
                    }
                    l("");
                }
                //background color
                fill(0,0,maxX,maxY,black);
                //UPDATE & DRAW------------------------------------------------------------------------------------------->

                for(double a = 0; a < Math.PI*50; a+=Math.PI/400){
                    fillB(Math.cos(a+n)*0.5*a+30, Math.sin(a+n)*0.5*a+7,1, 1, blue);
                }

                n+=0.3;


                //-------------------------------------------------------------------------------------------------------->
            }


        };
        timer.scheduleAtFixedRate(task, 0, 500);
    }}
