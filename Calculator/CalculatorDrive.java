import java.util.Scanner;
import java.util.Stack;

abstract class Calculator{

    public static double calculate(String input){
        String postfixed = changeToPostfix(input);
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < postfixed.length(); ++i){
            if (postfixed.charAt(i) == ' ')
                continue;
            
            if (isDigit(postfixed.charAt(i))){
                String ch = Character.toString(postfixed.charAt(i));
                double temp = Double.parseDouble(ch);
                stack.push(temp);
            }
            else{
                double numberTwo = stack.peek();
                stack.pop();
                double numberOne = stack.peek();
                stack.pop();

                double result;

                if (postfixed.charAt(i) == '+')
                    result = numberOne + numberTwo;
                else if (postfixed.charAt(i) == '-')
                    result = numberOne - numberTwo;
                else if (postfixed.charAt(i) == '*')
                    result = numberOne * numberTwo;
                else
                    result = numberOne / numberTwo;
                stack.push(result);
            }
        }
        return stack.peek();
    }

    public static String changeToPostfix(String input){
        if (!input.matches("[^a-zA-Z]*"))
            System.exit(0);
        
            Stack<Character> stack = new Stack<>();
            String result = "";

        for (int i = 0; i < input.length(); ++i){
            if (input.charAt(i) == ' ')
                continue;
            
            if (isDigit(input.charAt(i))){
                result += input.charAt(i) + " ";
            }
            else{
                if (stack.isEmpty())
                    stack.push(input.charAt(i));
                else {
                    while ((!stack.isEmpty()) && isWeaker(input.charAt(i), stack.peek())){
                        result += stack.peek() + " ";
                        stack.pop();
                    }
                    stack.push(input.charAt(i));
                }
            }
        }
        while (!stack.isEmpty()){
            result += stack.peek();
            stack.pop();
        }
        return result;
    }

    private static boolean isWeaker(char input, char stack){
        if ((input == '+' || input == '-') && (stack == '*' || stack == '/'))
            return true;
        if ((input == '+' || input == '-') && (stack == '+' || stack == '-'))
            return true;
        if ((input == '*' || input == '/') && (stack == '*' || stack == '/'))
            return true;
        return false;
    }

    private static boolean isDigit(char c){
        if (c == '+' || c == '-' || c == '/' || c == '*')
            return false;
        return true;
    }
}




public class CalculatorDrive{
    public static void main(String[] args){
        String input = getInput();

        // System.out.println(Calculator.changeToPostfix(input));
        System.out.println(Calculator.calculate(input));
    }
    
    public static String getInput(){
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        scanner.close();
        return result;
    }
}
