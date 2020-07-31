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

#add support for variable feature vector length later?

#i think i did it right? might be mistake in covariance matrix or probabilty stuff

import numpy as np
import math

class GaussDiscrimAnalysis: 
    def __init__(self, inputList):
        self.mainList = inputList
        self.numOfFeatures = len(inputList[0]) - 1
        self.numOfExamples = len(inputList)
        self.yLoc = 1
        self.convToNPArray()
        self.calcSigma()
        print("NumofFeatures: ", end="") 
        print(self.numOfFeatures)
        print("Numofexamples: ", end="")
        print(self.numOfExamples)
        print("Phi: ", end="") 
        print(self.phi)
        print("Mean0: ", end="") 
        print(self.mean0)
        print("Mean1: ", end="") 
        print(self.mean1)
        print("Avg of means: ",end="")
        print((self.mean1-self.mean0)/2)
        print("Sigma:")
        print(self.sigmaArray)
        print("Determinant: ",end="")
        print(self.sigmaDet)
        self.estimNewVal([10,10])

    #numpy makes life ez and calculate means/phi
    def convToNPArray(self):
        zeroFeature = np.zeros(self.numOfExamples)
        oneFeature = np.zeros(self.numOfExamples)
        yValues = np.zeros(self.numOfExamples)
        for i in range(len(self.mainList)):
            zeroFeature[i] = self.mainList[i][0]
            oneFeature[i] = self.mainList[i][1]
            yValues[i] = self.mainList[i][2]
        self.zeroFeature = zeroFeature
        self.oneFeature = oneFeature
        self.yValues = yValues
        self.phi = np.mean(yValues)
        yZeroCount = 0
        yOneCount = 0
        yZeroArray = [0] * self.numOfFeatures # if y=0
        yOneArray = [0] * self.numOfFeatures # if y=1
        for i in range(len(yValues)):
            if (yValues[i]==0):
                yZeroCount+=1
                yZeroArray[0]+=zeroFeature[i]
                yZeroArray[1]+=oneFeature[i]
            else:
                yOneCount+=1
                yOneArray[0]+=zeroFeature[i]
                yOneArray[1]+=oneFeature[i]
        self.mean0 = np.array([yZeroArray[0]/yZeroCount,yZeroArray[1]/yZeroCount])
        self.mean1 = np.array([yOneArray[0]/yOneCount,yOneArray[1]/yOneCount]) 

    #covariance matrix and determinant
    def calcSigma(self):
        sigmaArray = np.zeros((2, 2))
        for i in range(self.numOfExamples):
            # stable
            # currFeature = np.array([self.zeroFeature[i],self.oneFeature[i]])
            # if (self.yValues[i]==0):
            #     currFeature = currFeature - self.mean0
            # else:
            #     currFeature = currFeature - self.mean1
            # currFeature = np.atleast_2d(currFeature)
            # returnMatrix = np.matmul(np.transpose(currFeature),currFeature)
            # sigmaArray = sigmaArray + returnMatrix
            currFeature = np.array([self.zeroFeature[i],self.oneFeature[i]])
            currFeature = currFeature - self.mean0
            currFeature = np.atleast_2d(currFeature)
            returnMatrix = np.matmul(np.transpose(currFeature),currFeature)
            sigmaArray = sigmaArray + returnMatrix
            currFeature = np.array([self.zeroFeature[i],self.oneFeature[i]])
            currFeature = currFeature - self.mean1
            currFeature = np.atleast_2d(currFeature)
            returnMatrix = np.matmul(np.transpose(currFeature),currFeature)
            sigmaArray = sigmaArray + returnMatrix
        sigmaArray = sigmaArray / self.numOfExamples
        self.sigmaDet = np.linalg.det(sigmaArray)
        self.sigmaArray = sigmaArray
        
    #input is feature list, return expected classification
    #should not have control c control v whoops
    def estimNewVal(self,newVal):
        print("Test input: ",end="")
        print(newVal)
        print("p(y=0): ",end="")
        py0 = 1-self.phi
        print(py0)
        print("p(y=1): ",end="")
        py1 = self.phi
        print(py1)
        newVal = np.array(newVal) 
        newVal = np.atleast_2d(newVal)
        newVal = newVal - self.mean0
        newValTransp = np.transpose(newVal)
        sigmaInverse = np.linalg.inv(self.sigmaArray)
        newValTransp = np.matmul(sigmaInverse,newValTransp)
        newVal = np.matmul(newVal,newValTransp)
        newVal = newVal[0][0]
        newVal = newVal * (-1/2)
        newVal = math.exp(newVal)
        coeff = 2*math.pow(math.pi,1)*math.pow(self.sigmaDet,0.5)
        coeff = 1/coeff
        pXy0 = newVal * coeff
        print("Not normalized: p(x|y=0): ",end="")
        print(pXy0)
        newVal = np.array(newVal) 
        newVal = np.atleast_2d(newVal)
        newVal = newVal - self.mean1
        newValTransp = np.transpose(newVal)
        sigmaInverse = np.linalg.inv(self.sigmaArray)
        newValTransp = np.matmul(sigmaInverse,newValTransp)
        newVal = np.matmul(newVal,newValTransp)
        newVal = newVal[0][0]
        newVal = newVal * (-1/2)
        newVal = math.exp(newVal)
        coeff = 2*math.pow(math.pi,1)*math.pow(self.sigmaDet,0.5)
        coeff = 1/coeff
        pXy1 = newVal * coeff
        print("Not normalized: p(x|y=1): ",end="")
        print(pXy1)
        pX = pXy1*self.phi + pXy0*(1-self.phi)
        print("p(x): ",end="")
        print(pX,end="")
        print(" *technically a useless constant but so are most things")
        pY0x = pXy0 * py0 / pX
        print("Normalized p(x|y=0): ",end="")
        print(pY0x)
        pY1x = pXy1 * py1 / pX
        print("Normalized p(x|y=1): ",end="")
        print(pY1x)
        if (pY0x > pY1x):
            print("Classifed as zero")
        elif (pY1x > pY0x):
            print("Classifed as one")
        else:
            print("Woah you're on the decision boundary nice")
        print("------------------")
        




