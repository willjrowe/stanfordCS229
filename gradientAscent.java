//classification problem this time (binary)
//logistic regression
//i think i broke it

import java.util.Arrays;

public class gradientAscent {
    public double[][] mainList;
    public double alpha;
    public int numOfParams;
    public double[] thetas;
    public int numOfExamples;
    public int yLoc;

    public gradientAscent(double[][] mainList){
        this.mainList = mainList;
        this.alpha = 0.1;
        this.numOfParams = mainList[0].length - 1;
        this.thetas = new double[numOfParams];
        this.numOfExamples = mainList.length;
        this.yLoc = numOfParams;
    }

    public void calculateL(){
        double currentL = 0;
        for (int i = 0; i < numOfExamples; i++){ //for each training example
            double currentEstimate = mainList[i][yLoc];
            double thetaTX = 0;
            for (int j = 0; j < numOfParams; j++){ //loop through params
                thetaTX = thetaTX + (thetas[j]*mainList[i][j]);
            }
            thetaTX = 1 + Math.exp(-thetaTX);
            thetaTX = 1/thetaTX;
            currentEstimate = currentEstimate * Math.log(thetaTX);
            currentEstimate = currentEstimate + (1-mainList[i][yLoc])*Math.log(1-thetaTX);
            currentL = currentL + currentEstimate;
        }
        System.out.println("Current L: " + currentL);
    }

    //batch gradient ascent
    public void updateTheta(int numOfIterations){
        for (int w = 0; w < numOfIterations; w++){
        for (int i = 0; i < numOfParams; i++){ //loop through parameters
            double currSum = 0;
            for (int j = 0; j < numOfExamples; j++){ //loop through training examples
                double currEstimate = 0;
                for (int k = 0; k < numOfParams; k++){
                    currEstimate = currEstimate + (mainList[j][k]*thetas[k]);
                }
                double dummyEstimate = 1+Math.exp(-currEstimate);
                dummyEstimate = 1/dummyEstimate;
                currEstimate = mainList[j][yLoc] - dummyEstimate;
                currEstimate = currEstimate * mainList[j][i];
                currSum = currSum + currEstimate;
            }
            thetas[i] = (thetas[i]+alpha*currSum);
        }
        System.out.println("Current Thetas: "+Arrays.toString(thetas));
        calculateL();
    }
}

    public void estimateInput(double[] input){
        double estimate = 0;
        for (int i = 0; i < numOfParams; i++){
            estimate = estimate + (thetas[i]*input[i]);
        }
        double finalEstimate = 1 + Math.exp(-estimate);
        finalEstimate = 1/finalEstimate;
        System.out.println("Input: " + Arrays.toString(input) + " Probability of positive: "+ finalEstimate);
    }

    public static void main(String[] args){
        double[][] test = {{1,2,1},
                        {1,3,1}, {3,4,0}, {1,0,1}, {5,5,0}
                        };
        gradientAscent testObject = new gradientAscent(test);
        testObject.updateTheta(100);
        double[] inputTest = {1,1};
        testObject.estimateInput(inputTest);
    }
}