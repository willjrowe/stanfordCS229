import static java.lang.Math.pow;

public class otherClass {
    public static void main(String[] args){
        // System.out.println(remainder(19,254,4,1));
        // System.out.println(recurExp(6, 2));
        // System.out.println(recurMod(13, 3));
        int[] A = {999999,1,0,2,3,4,5,6,5,5};
        int n = 9;
        System.out.println(LCIS(A, n));
        //need to recheck LCIS
        // System.out.println(ModAlgo(3, 512, 17));
    }

    public static int ModAlgo(int x, int n, int p){
        int result = 1;
        x = x % p;
        while (n>0){
            if(n%2!=0){
                result = (result*x)%p;
            }
            n=n/2;
            x=(x*x)%p;
        }
        return result;
    }

    public static int LCIS(int[] A, int n){
        if (n<=0){
            return n;
        }
        else{
            if (A[n]<A[n-1]){
                return LCIS(A,n-1);
            }
            else{
                return Math.max(LCIS(A,n-2), LCIS(A,n-1)+1);
            }
        }
    }

    public static int recurMod(int dividend, int divisor){
        if (dividend >= divisor){
            return recurMod(dividend-divisor, divisor);
        }
        else{
            return dividend;
        }
    }

    public static int recurExp(int base, int power){
        if(power == 0){
            return 1;
        }
        else{
            return base * recurExp(base, power-1);
        }
    }

    public static int recurDiv(int dividend, int divisor){
        if (dividend >= divisor){
            return 1 + recurDiv(dividend-divisor, divisor);
        }
        else{
            return 0;
        }
    }

    public static int remainder(int x, int p, int n, int k){
        System.out.println("x: "+x);
        System.out.println("p: "+p);
        System.out.println("n: "+n);
        System.out.println("k: "+k);
        if (k==n){ //base case
            double y = x;
            y = Math.pow(x, 2);
            y = y%p;
            int returnVal = (int)y;
            return returnVal;
        }
        else {
            double y = x;
            if (k>1){
            y = Math.pow(x, 2);
            }
            y = y%p;
            int returnVal = (int)y;
            return remainder(returnVal, p, n, k*2);
        }
    }
}
