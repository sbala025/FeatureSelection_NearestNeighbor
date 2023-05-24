# FeatureSelection_NearestNeighbor

## How To Run Program:
1. Download file
2. Compile: ```javac Main.java```,```javac Part1/ForwardSelection.java```, and ```javac Part1/BackwardElimination.java```
3. Run ```java Main```

## Implementing Intro
Using Scanner I get the number of features and decide which algorithm to use based of user. 

Files: 
* Main.java

Functions:
* intro()

## Implementing Part 1 - Greedy Search Algorithms
<ins>Forward Slection</ins>: Perform a forward selection search algorithm for feature selection, gradually building up a set of selected features based on their individual evaluation scores using randomization.

Files:
* Part1/ForwardSelection.java

Functions:
* searchForwardSelection(int)
* evaluateFeatureNum (List)

<ins>Backward Elimination</ins>: Perform a backward elimination search algorithm for feature selection, gradually reducing a set of selected features based on their individual evaluation scores using randomization.

Files:
* Part1/BackwardElimination.java

Functions:
* searchbackwardElimination(int)
* evaluateFeatureNum (List)

## Implementing Part 2 -

