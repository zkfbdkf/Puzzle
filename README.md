# Puzzle
get answer for solving number puzzle

the given states of puzzles are 

  static int[][] Given1 = { { 1, 3, 2 }, { 0, 7, 8 }, { 5, 6, 4 } };  
  static int[][] Given2 = { { 3, 7, 0 }, { 1, 6, 2 }, { 5, 4, 8 } };  
  static int[][] Given3 = { { 7, 5, 6 }, { 3, 4, 2 }, { 1, 0, 8 } };  

this program used BFS algorithm to find out shortest movement to be the correct state of puzzle.

correct state

  static int[][] Correct = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
  
 the running time for the states are;
 state1: 115ms
 state2: 324ms
 state3: 8117ms
