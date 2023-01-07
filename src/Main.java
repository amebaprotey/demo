import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String string1 = scanner.nextLine();
            try {
                System.out.println(calc(string1));
            } catch (IOException e) {
                System.out.println("throws Exception");
                break;
            }
        }
    }

    public static String calc(String input) throws IOException {
        String result = "";
        int a = 0;
        int b = 0;
        int c = 0;

        String [] strings = input.split("[*+/-]");
        if (strings.length != 2) {
            throw new IOException();
        }

        try {
            a = Integer.parseInt(strings[0].trim());
            b = Integer.parseInt(strings[1].trim());
            noMoreTen(a);
            noMoreTen(b);
            c = calculation(input, a, b);
            result = "" + c;

        }catch (NumberFormatException e){
            char [] character = strings[0].trim().toCharArray();
            char [] character1 = strings[1].trim().toCharArray();
            a = romanToArabic(character);
            b = romanToArabic(character1);
            noMoreTen(a);
            noMoreTen(b);
            c = calculation(input, a, b);
            if (c < 0)
                throw new IOException();
            result = arabicToRoman(c);
        }

        return result;
    }
    public static int romanToArabic(char [] charArray) throws IOException {

        int current = 0;
        int previous = 0;
        int result = 0;

        for(int i = charArray.length - 1; i >= 0; i--){
            if (charArray[i] == 'I') {
                current = 1;
                result += addOrSubtract(current,previous);
            }
            else if(charArray[i] == 'V') {
                current = 5;
                result += addOrSubtract(current,previous);
            }
            else if(charArray[i] == 'X') {
                current = 10;
                result += addOrSubtract(current,previous);
            }else
                throw new IOException();

            previous = current;
        }
        return result;
    }
    public static int addOrSubtract(int current, int previous){
        if (current < previous)
            return  - current;
        else
            return  + current;
    }
    public static int calculation(String input, int a, int b){
        int c = 0;
        if (input.contains("+"))
            c = a + b;
        else if (input.contains("-"))
            c = a - b;
        else if (input.contains("*"))
            c = a * b;
        else if (input.contains("/"))
            c = a / b;

        return c;
    }
    public static String arabicToRoman(int c){
        String romanNumber = "";
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"I");
        map.put(2,"II");
        map.put(3,"III");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(6,"VI");
        map.put(7,"VII");
        map.put(8,"VIII");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(20,"XX");
        map.put(30,"XXX");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(60,"LX");
        map.put(70,"LXX");
        map.put(80,"LXXX");
        map.put(90,"XC");
        map.put(100,"C");

        int c1 = 0;
        if (map.containsKey(c)){
            romanNumber = map.get(c);
        }else{
            c1 = c/10*10;
            romanNumber += map.get(c1);
            c1 = c % 10;
            romanNumber += map.get(c1);
        }

        return romanNumber;
    }
    public static void noMoreTen(int x) throws IOException {
        if (x > 10)
            throw new IOException();
    }

}
