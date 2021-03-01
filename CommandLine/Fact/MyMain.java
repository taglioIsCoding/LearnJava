
/**
* Applicazione Java da linea di comando. * Realizza un fattoriale ricorsivo
* @author Fondamenti di Informatica T-2 * @version 1.0 02/2021
*/
public class MyMain {
/**
* Calcola il fattoriale in modo ricorsivo */
   public static int fact(int n) {
       if(n==0)
           return 1;
        else
return n * fact(n-1);
}

public static void main(String[] args) {
int fact1 = fact(3);
System.out.println(fact1 == 6);
int fact2 = fact(6);
System.out.println(fact2 == 720);
int fact3 = fact(8);
System.out.println(fact3 == 40320);

} }
