//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        String[] languages =  {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);
        //System.out.println(n);
        n = n * 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        //System.out.println(n);
        int result = n;


        while(result > 9)
        {  int sum = 0;
            while(result > 0)
            {
                sum += result % 10;
                result/=10;

            }
            result=sum;}

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]+".");


    }
}