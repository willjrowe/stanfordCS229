#test runner cuz idk how to test classes whoops

import sys
import GaussDiscrimAnalysis as GDA

# args = sys.argv
# inputList = args[1]

inputList = [[1,3,0],[9,5,1],[2,7,0],[7,6,1],[8,8,1],[1,1,0],[3,2,0],[3,7,1],[6,6,1]]
print("Inputlist: ",end="")
print(inputList)

testObject = GDA.GaussDiscrimAnalysis(inputList)