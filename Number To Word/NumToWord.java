public class NumToWord{
    private String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private String[] tens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                    "seventeen", "eighteen", "nineteen"};
    private String[] twos = {"", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private String hundred = "hundred";
    private String[] big = {"", "thousand", "million", "billion", "trillion", "quadrillion","quintillion"};

    public void changeToWord(String input){
        if (!input.matches("[0-9]+")){
            System.out.println("Only numbers 0-9");
            return ;
        }
        if (input.length() > 21){
            System.out.println("Too big");
            return;
        }
        String newString = "";
        int length = input.length();
        for (int i = 0; i < input.length(); ++i){
            if (length % 3 == 0)
                newString +="_";
            length--;
            newString += input.charAt(i);
            
        }
        int i = 0;
        while (newString.charAt(i) == ' ' || newString.charAt(i) == '_')
            i++;
        newString = newString.substring(i, newString.length());
        System.out.println(newString);
        String[] numbers = newString.split("_");

        
        int range = numbers.length - 1;
        for (i = 0; i < numbers.length; ++i){
            boolean haveAtLeastOneNonZero = false;
            String temp = numbers[i];

            if (temp.length() == 1)
                System.out.print(ones[temp.charAt(0) - '0'] + " ");
            else if (temp.length() == 2){
                if (temp.charAt(0) == '1'){
                    if (tens[temp.charAt(1) - '0'] != ""){
                        System.out.print(tens[temp.charAt(1) - '0'] + " ");
                        haveAtLeastOneNonZero = true;
                    }
                }else{
                    if (twos[temp.charAt(0) - '0'] != ""){
                        System.out.print(twos[temp.charAt(0) - '0'] + " ");
                        haveAtLeastOneNonZero = true;
                    }
                    if(ones[temp.charAt(1) - '0'] != ""){
                        System.out.print(ones[temp.charAt(1) - '0'] + " ");
                        haveAtLeastOneNonZero = true;
                    }
                }
            }else{
                if (ones[temp.charAt(0) - '0'] != ""){
                    System.out.print(ones[temp.charAt(0) - '0'] + " ");
                    haveAtLeastOneNonZero = true;
                }
                if (ones[temp.charAt(0) - '0'] != "")
                    System.out.print(hundred + " ");
                if (temp.charAt(1) == '1'){
                    if (tens[temp.charAt(2) - '0'] != ""){
                        System.out.print(tens[temp.charAt(2) - '0'] + " ");
                        haveAtLeastOneNonZero = true;
                    }
                }else{
                    if (twos[temp.charAt(1) - '0'] != ""){
                        System.out.print(twos[temp.charAt(1) - '0'] + " ");
                        haveAtLeastOneNonZero = true;
                    }
                    if (ones[temp.charAt(2) - '0'] != ""){
                        System.out.print(ones[temp.charAt(2) - '0'] + " ");
                        haveAtLeastOneNonZero = true;
                    }
                }
            }
            if (haveAtLeastOneNonZero)
                System.out.print(big[range] + " ");
            range--;
        }
    }

    public static void main(String[] args) {
        new NumToWord().changeToWord("000001110001");
    }
}
