/**
* Cliente che calcola mcd fra due numeri passati da riga di comando * utilizzando la libreria MyMath
* @author Fondamenti di Informatica T-2
* @version 1.0 02/2021
*/
public class EsempioMath {
/** Calcola mcd dei due numeri passati da linea di comando */
public static void main(String args[]) { 
       int a = Integer.parseInt(args[0]);
       int b = Integer.parseInt(args[1]); 
       int mcd = MyMath.mcd(a,b);
       int mcm = MyMath.MCM(a, b);
       System.out.println("Mcd: "+ mcd);
       System.out.println("mcm: "+ mcm);
     } }