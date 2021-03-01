public class MyMathTest {
public static void main(String args[]) {
 testOK();
 //testKO();
}
private static void testOK() {
assert(MyMath.mcd(10, 5) == 5);
assert(MyMath.mcd(7, 3) == 1);
assert(MyMath.mcd(21,49) == 7);

assert(MyMath.MCM(10, 5) == 10);
assert(MyMath.MCM(7, 3) == 21);
assert(MyMath.MCM(21,49) == 147);
}

private static void testKO() {
System.out.println(MyMath.mcd(10, 5) == 4);
assert(MyMath.mcd(10, 5) == 4);
assert(MyMath.MCM(7, 3) == 1); 
}

 }