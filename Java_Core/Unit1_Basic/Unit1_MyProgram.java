/*
Coder:NNKhai
Date:05/09/2022
JSE.Unit01.Basic.1
*/
import java.util.Arrays;

class MyProgram {

    public static void main(String[] args) {
        System.out.println("The number of arguments is " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Value at " + i + " is " + args[i]);
        }
    }
}

class StreamProgram {

    public static void main(String[] args) {
        System.out.println(args.length);
        Arrays.stream(args).forEach((String value) -> {
            System.out.println("value is " + value);
        });
    }
}

class NumberExample {

    public static void main(String[] args) {
        int number1 = Integer.parseInt(args[0]);
        int number2 = Integer.parseInt(args[1]);
        System.out.println("Subtraction example: "
                + "number1 - number2 = " + (number1 - number2));
        System.out.println("Relational example: "
                + "number1 > number2 = " + (number1 > number2));
    }
}

class IfElseExample {

    public static void main(String[] args) {
        int value = Integer.parseInt(args[0]);
        if (value < 5) {
            System.out.println("bad");
        } else {
            System.out.println("ok");
        }
    }
}

class SwitchExample {

    public static void main(String[] args) {
        int value = Integer.parseInt(args[0]);
        switch (value) {
            case 0:
                System.out.println("bad");
                break;
            case 1:
                System.out.println("ok");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }
}

class ArrayExample {

    public static void main(String[] args) {
        float total = 0;
        float[] values = {1.2f, 2.5f, 5, 8.9f, 10};
        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }
        System.out.println("The total value of array is " + total);
        float max = values[0];
        for (int i = 0; i < values.length; i++) {
            if (max < values[i]) {
                max = values[i];
            }
        }
        System.out.println("Gia tri lon nhat = " + max);
    }
}

class MethodExample {

    private static int add(int number1, int number2) {
        return number1 + number2;
    }

    private static int add(int... values) {
        int total = 0;
        for (int element : values) {
            total += element;
        }
        return total;
    }

    public static void main(String[] args) {
        // System.out.println("4 + 7 = " + add(4, 7));
        // int value1 = Integer.parseInt(args[0]);
        // int value2 = Integer.parseInt(args[1]);
        // System.out.println(value1 + " + " + value2 + "= " + add(value1, value2));
        System.out.println(add(4, 7, 5, 12, 6, 9));
    }
}

class StringExample1 {

    public static void main(String[] args) {
        String text = "say hello to everyone";
        System.out.println("Lenght of text is " + text.length());
        System.out.println("vi tri cua tu hello la " + text.indexOf("hello"));
        System.out.println("cat tu = " + text.substring(4, 8));
    }
}

class StringExample2 {

    public static void main(String[] args) {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        String text = new String(chars);
        System.out.println(text);
        text = text.concat(" ");
        text = text.concat(args[0]);
        System.out.println("new value is: " + text);
        System.out.println("hello java".equals(text));
    }
}

class StringExample3 {

    public static void main(String[] args) {
        String text = "Absolute Value";
        byte[] bytes = text.getBytes();
        System.out.print("Byte values are:");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print((int) bytes[i]);
            if ((int) bytes[i] != (int) bytes[bytes.length - 1]) {
                System.out.print(" ,");
            }
        }
    }
}

class StringExample4 {

    public static void main(String[] args) {
        String text = "say hello to everyone";
        System.out.println("lenght of text is " + text.length());
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            System.out.println("Character at " + i + " is " + c);
            i++;
        }
    }
}

class StringExample5 {

    public static void main(String[] args) {
        byte[] bytes = {75, 104, 97, -31, 105};
        try {
            System.out.println(new String(bytes, "utf8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MultidimensionalArrayExample {

    public static void main(String[] args) {
        String[][] values = {
            {"Doan", "Van", "A"},
            {"Tran", "Thi", "B"}
        };
        for (int i = 0; i < 2; i++) {
            System.out.print("|");
            for (int j = 0; j < values[i].length; j++) {
                System.out.print(i + "," + j + "|");
            }
            System.out.println("");
            System.out.print("|");
            for (int j = 0; j < values[i].length; j++) {
                System.out.print(values[i][j] + "|");
            }
            System.out.println();
        }
    }
}

class MathExample {

    public static void main(String[] args) {
        int i = 4;
        int j = -8;
        double x = 47.1;
        double y = 1.79;
        System.out.println("|" + j + "| is " + Math.abs(j));
        System.out.println("|" + x + "| is " + Math.abs(x));
        System.out.println(x + "is approximately " + Math.round(x));
        System.out.println("The ceiling of " + i + " is " + Math.ceil(i));
        System.out.println("The ceiling of " + y + " is " + Math.ceil(y));
        System.out.println("The floor of " + x + " is " + Math.floor(x));
        System.out.println("min(" + x + "," + y + ") is " + Math.min(x, y));
    }

}

class MathExampleCont {

    public static void main(String[] args) {
        System.out.println("Pi is: " + Math.PI);
        double angle = 45.0 * 2.0 * Math.PI / 360.0;
        System.out.println("cos(" + angle + ")is " + Math.cos(angle));
        double value = 0.707;
        System.out.println("acos(" + value + ")is" + Math.acos(value));
        System.out.println("exp(0.0) is " + Math.exp(0.0));
        System.out.println("log(10.0) is " + Math.log(10.0));
        System.out.println("pow(2.0,2.0) is " + Math.pow(2.0, 2.0));
        System.out.println("Here's one random number: " + Math.random());
    }
}
