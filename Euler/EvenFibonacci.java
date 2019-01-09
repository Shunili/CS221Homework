import java.util.ArrayList;
import java.util.List;

public class EvenFibonacci {

    public static long sumEven(){
        List<Long> fibonacci = new ArrayList<>();
        long a = 1;
        long b = 2;
        fibonacci.add(a);
        fibonacci.add(b);

        for (int i = 2; i <= 4000000; i++) {
            if (fibonacci.get(i - 1) + fibonacci.get(i - 2) <= 4000000) {
                fibonacci.add(i, fibonacci.get(i - 1) + fibonacci.get(i - 2));
            } else{
                break;
            }
        }

        long sum = 0;
        for (int i = 0; i < fibonacci.size(); i++) {
            if (fibonacci.get(i) % 2 ==0){
            sum += fibonacci.get(i);
            }
        }
        return sum;

    }

    public static void main(String[] args) {
        long result = sumEven();

        System.out.println(result);

    }
}