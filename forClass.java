import java.util.*;
import java.lang.Math;

public class forClass {
    public static void main(String[] args){
        // System.out.println(evalFunction(8));
        // System.out.println(checkFunction(8));
        // System.out.println(findGCD(4, 12));
        // System.out.println(getCombo(5, 1, 4, 1, 1, 1));
        // System.out.println(calcFrag(4));
        // System.out.println(uglyFrag(1));
        // System.out.println(uglyFrag(2));
        // System.out.println(uglyFrag(3));
        // System.out.println(uglyFrag(4));
        // System.out.println(uglyFrag(5));
        // System.out.println(uglyFrag(6));
        // System.out.println(uglyFrag(7));
        // System.out.println(uglyFrag(8));
        // System.out.println(uglyFrag(9));
        int arr[] = { 12,-16,-1,0, 2,0 , -3, 4, 5, 6, -7, 8, 9 }; 
        int n = arr.length; 
  
        rearrange(arr, n); 
        printArray(arr, n); 
    }

    public static int uglyFrag(int n){
        int count=0;
        for (int i=1; i<=n; i++){
            int j=1;
            while(j<=i){
                count++;
                j*=2;
            }
        }
        return count;
    }

    public static int calcFrag(int n){
        int count = 0;
        for (int i = 1; i <= n; i++){
            for (int j = i; j<=n; j++){
                for (int k=1; k<=(n*2);k++){
                    count++;
                }
            }
        }
        return count;
    }

    public static int getCombo(int n, int k, int nk, int top, int bottom, int bottom2){
        if (n<1){
            n=1;
        }
        if (k<1){
            k=1;
        }
        if (nk<1){
            nk=1;
        }
        top*=n;
        bottom*=k;
        bottom2*=nk;
        if (n==1&k==1&nk==1){
            return (top)/(bottom*bottom2);
        }
        else {
            n-=1;
        k-=1;
        nk-=1;
            return getCombo(n,k,nk,top,bottom,bottom2);
        }
    }

    public static void rearrange(int arr[], int n) 
    { 
        int j = 0, temp; 
        int k=n-1;
        for (int i = 0; i < n; i++) { 
            printArray(arr, n); 
            if (arr[i] < 0) { 
                if (i != j) { 
                    temp = arr[i]; 
                    arr[i] = arr[j]; 
                    arr[j] = temp; 
                } 
                j++; 
            } 
            if (arr[i]==0){
                if (i < k){
                    temp = arr[i]; 
                    arr[i] = arr[k]; 
                    arr[k] = temp;
                }
                k--;
            }
        } 
    }

    static void printArray(int arr[], int n) 
    { 
        for (int i = 0; i < n; i++) 
            System.out.print(arr[i] + " "); 
        System.out.print("\n");
    } 
  

    // public static int[] uglySort(int[] A){
    //     int[] B = A;
    //     int positivePointer = 0;
    //     int zeroPointer = B.length;
    //     for (int i=0; i<A.length; i++){
    //         if (A[i]==0){
    //             zeroPointer-=1;
    //         }
    //         else if(A[i]>0){
    //             int holder = A[i]
    //             for (int j=positivePointer;j<i;j++){

    //             }
    //             //shift everything from A[positive pointer] to A[i]
    //         }
    //         else{

    //         }
    //     }
    // }

    public static int evalFunction(int at){
        if(at==0){
            return 5;
        }
        return 3*evalFunction(at-1)+4;
    }

    public static int findGCD(int a, int b){
        if (b==0){
            return a;
        }
        else{
            return findGCD(b,(a%b));
        }
    }

    public static double checkFunction(int at){
        double plz = 3.0;
        double newAt = at + 0.0;
        return 7*Math.pow(plz, newAt)-2;
    }
}