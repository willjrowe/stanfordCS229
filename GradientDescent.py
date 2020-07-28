#gonna try and rewrite in python yay

class GradientDescent:
    alpha = 0.1

    def __init__(self, inputList):
        self.mainList = inputList
        self.numOfParams = inputList.length - 1
        self.thetas = [0.0] * numOfParams
        self.numOfExamples = inputList.length
        self.yLoc = self.numOfParams

    def getCurrentEstimate(self):
        map(,mainList)

        # for (int j = 0; j < numOfParams; j++){ //for each parameter
        #         currEstimate = currEstimate + (mainList[i][j]*thetas[j]);
        # }

    def calculateJ(self):
        currentJ=0
        for i in range(numOfExamples):
            currentEstimate=0
            for j in range(numOfParams):
                
