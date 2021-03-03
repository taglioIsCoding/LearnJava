public class Frazione{
    private int num, den;

    public Frazione(int n, int d){
        if( n > 0 && d > 0 || n < 0 && d > 0){
            num = n; 
            den = d;
        }else if ( n < 0 && d < 0 ){
            num = Math.abs(n);
            den = Math.abs(d);
        }else if ( n > 0 && d < 0){
            num = -1 * n; 
            den = Math.abs(d);
        }
    }
    // segue il resto della classe...

    public Frazione(int n){
       num = n; den = 1;
    }

    public int getNum(){
        return this.num;
    }

    public int getDen(){
        return this.den;
    }

    public boolean equals(Frazione f2){
        if(f2.getNum() * this.den == f2.getDen() * this.num){
            return true;
        }else{
            return false;
        }
    }

    public Frazione minTerm(){
        int mcd = MyMath.mcd(Math.abs(this.num),this.den);
        Frazione fMin = new Frazione(this.num/mcd, this.den/mcd);
        return fMin;
    }

    public String toString(){
        return Integer.toString(this.num) + "/" +Integer.toString(this.den);
    }

}
