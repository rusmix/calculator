import java.util.Scanner;
import java.util.TreeMap;
class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: " );
        var expression= in.nextLine();
        in.close();
        rebuilder pp = new rebuilder(expression);
        pp.rebuild();

       //System.out.print();

    }
}
 class Error {
     void display(int errorCode){
         switch (errorCode) {
             case 1 -> {
                 System.out.print("Error: impossible to calculate different types of numbers.");
                 System.exit(0);
             }
             case 2 -> {
                 System.out.print("Error: it is allowed to use numbers from 1 to 10.");
                 System.exit(0);
             }
         }
    }
 }




class roman {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

}
 class rebuilder {
    String expression;
    String[] arabic = {"I","II","III","IV","V","VI","VII","IIX","IX","X"};
     public rebuilder(String buff) {
         expression = buff;
     }
     Error error;
     {
         error = new Error();
     }
     roman funcRom = new roman();

     void  rebuild () {
         int a = 0, b = 0,typeOfAnswer = 0;
        String[] things = expression.split(" ");
         if ((toArabic(things[0]) != -1 ^ toArabic(things[2]) !=-1)){
             error.display(1);
         }
         else if ((toArabic(things[0]) == -1 && toArabic(things[2]) ==-1) && (things[0].length()==1 && things[2].length()==1))  {
              a = Integer.parseInt(things[0]);
              b = Integer.parseInt(things[2]);
         }
         else if (toArabic(things[0]) != -1 && toArabic(things[2]) !=-1){
              a = toArabic(things[0]);
              b = toArabic(things[2]);
             typeOfAnswer = 1;
         }
         else {
             error.display(2);
         }
         calculator object = new calculator(a,b,things[1],typeOfAnswer);

        //System.out.print(a +" "+ b);
     }

     public int toArabic(String n) {
         int count = 1;
         for (String elem:
              arabic) {
             if (n.equalsIgnoreCase(elem)) {
                 return count;
             }
             count += 1;
         }
         return -1;
     }
 }

 class calculator {
    int a,b;
     roman funcRom = new roman();

     public calculator(int first, int second, String thing,int typeOfAnswer) {
         a = first;
         b = second;
         int c = 0;
         switch (thing) {
             case "+" -> {
                 c = a + b;
                 deploy(c,typeOfAnswer);
             }
             case "-" -> {
                 c = a - b;
                 deploy(c,typeOfAnswer);
             }
             case "/" -> {
                 c = a / b;
                 deploy(c,typeOfAnswer);
             }
             case "*" -> {
                 c = a * b;
                 deploy(c,typeOfAnswer);
             }
         }

     }
     private void deploy(int answer,int typeOfAnswer){
         if (typeOfAnswer == 0) {
             System.out.println(answer);
         }
         else {
             System.out.print(funcRom.toRoman(answer));
         }
     }

 }