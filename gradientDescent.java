//some gradient descent linear regression stuff
//lots of room for refactoring

import java.util.Arrays;

public class gradientDescent {
    public double[][] mainList;
    public double alpha;
    public int numOfParams;
    public double[] thetas;
    public int numOfExamples;
    public int yLoc;

    public gradientDescent(double[][] mainList){
        this.mainList = mainList;
        this.alpha = 0.1;
        this.numOfParams = mainList[0].length - 1;
        this.thetas = new double[numOfParams];
        this.numOfExamples = mainList.length;
        this.yLoc = numOfParams;
    }

    //least squares
    public void calculateJ(){
        double currentJ = 0;
        for (int i = 0; i < numOfExamples; i++){ //for each training example
            double currEstimate = 0; //get estimation
            for (int j = 0; j < numOfParams; j++){ //for each parameter
                currEstimate = currEstimate + (mainList[i][j]*thetas[j]);
            }
            double estimateError = Math.pow((currEstimate - mainList[i][yLoc]),2);
            currentJ = currentJ + estimateError;
        }
        System.out.println("Current J: " + currentJ);
    }

    //batch gradient descent
    public void updateTheta(int numOfIterations){
        for (int w = 0; w < numOfIterations; w++){
        for (int i = 0; i < numOfParams; i++){ //loop through parameters
            double currSum = 0;
            for (int j = 0; j < numOfExamples; j++){ //loop through training examples
                double currEstimate = 0;
                for (int k = 0; k < numOfParams; k++){
                    currEstimate = currEstimate + (mainList[j][k]*thetas[k]);
                }
                currEstimate = currEstimate - mainList[j][yLoc];
                currEstimate = currEstimate * mainList[j][i];
                currSum = currSum + currEstimate;
            }
            thetas[i] = (thetas[i]-alpha*currSum);
        }
        System.out.println("Current Thetas: "+Arrays.toString(thetas));
        calculateJ();
    }
    }

    //stochastic gradient descent
    //updates theta at each training example
    public void stochGrad(int numOfIterations){
        for (int w = 0; w<numOfIterations;w++){
        for (int i = 0; i < numOfExamples; i++){ //loop through training examples
            double currEstimate = 0;
            for (int j = 0; j < numOfParams; j++){ //get estimate
                currEstimate = currEstimate + (mainList[i][j]*thetas[j]);
            }
            currEstimate = currEstimate - mainList[i][yLoc];
            for (int j = 0;j < numOfParams; j++){ //update each parameter
                double updatedEstimate = currEstimate*alpha*mainList[i][j];
                thetas[j] = (thetas[j] - updatedEstimate);
            }
            System.out.println("Current Thetas: "+Arrays.toString(thetas));
            calculateJ();
        }
        }
    }

    public void estimateInput(double[] input){
        double estimate = 0;
        for (int i = 0; i < numOfParams; i++){
            estimate = estimate + (thetas[i]*input[i]);
        }
        System.out.println("Input: " + Arrays.toString(input) + " Output: "+ estimate);
    }

    //use locally weighted regression for non-linear curves
    //could add variable tal to adjust weighting function
    // public void localReg(double target){
    //     double[] weights = new double[numOfExamples];
    //     for (int i = 0; i<numOfExamples;i++){
    //         weights[i] = Math.exp(-(Math.pow()))
    //     }
    // }

    public static void main(String[] args){
        //leading one for offset x
        double[][] test = {{1,1,1,3},
                        {1,2,2,3}
                        };
        gradientDescent testObject = new gradientDescent(test);
        testObject.stochGrad(100);
        double[] inputTest = {1.0,3.0,3.0}; 
        testObject.estimateInput(inputTest);
    }
}