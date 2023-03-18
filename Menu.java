
import java.util.ArrayList;
import java.util.Scanner;

public class Menu <E extends Object>{
    public int int_getChoice(ArrayList<E> options){
        int response, i;
        Scanner sc = new Scanner(System.in);
        int n = options.size();
        System.out.println("----------MENU----------");
        for (i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Please choose an option 1.."+n+":");
        response = sc.nextInt();
        System.out.println("------------------------");
        return response;
    }

    
    public E ref_getChoice(ArrayList<E> options){
        int response;
        int n = options.size();
        
        do {
        response = int_getChoice(options);
        } while ( response < 0 || response > n);
        
        return options.get(response - 1);
    }

}
