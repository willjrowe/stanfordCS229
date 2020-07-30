#lecture 5
# y in set {0,1}
# x with two continous features, no intercept needed

#p(x|y) is gaussian oh boy multivar gausssians
#input list element has format [[x1,x2],y]

#p(x|y=0) is gaussian (see formula)  same for y=1
#need mean0, mean1, and sigma and phi whoops
#p(y) is bernoulli, p(y=1)=phi
#how to find sigma

#prediction
#max p(y|x) = arg max p(x|y)p(y)

#import numpy as np

class GaussDiscrimAnalysis: 
    def __init__(self, inputList):
        self.mainList = inputList
        self.numOfFeatures = inputList[0][0].length
        self.thetas = [0.0] * self.numOfFeatures
        self.numOfExamples = inputList.length[0]
        self.yLoc = 1
        self.phi = self.calcParam(1)
        self.mean0 = self.calcParam(0)
        self.mean1 = self.calcParam(1)
        print("Phi: "+self.phi)
        print("Mean0: "+self.mean0)
        print("Mean1: "+self.mean1)
        
    def calcPhi(self,pos) -> float:
        initialSum = 0.0
        for i in self.mainList:
            initialSum+=i[pos]
        initialSum/=self.numOfExamples
        return initialSum

    def calcMean(self):
        

    def calcSigma(self):
        initialSum = 0.0
        for i in self.mainList:
            if i[yLoc] == 0:

        return initialSum

    


