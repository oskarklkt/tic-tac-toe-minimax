# Tic-Tac-Toe

## Stage 1/5: Initial setup
Description
In this project, you'll write a game called Tic-Tac-Toe that you can play against your computer. The computer will have three levels of difficulty — easy, medium, and hard.

To begin with, let's write a program that knows how to work with coordinates and determine the state of the game.

The top-left cell will have the coordinates (1, 1) and the bottom-right cell will have the coordinates (3, 3), as shown in this table:

(1, 1) (1, 2) (1, 3)
(2, 1) (2, 2) (2, 3)
(3, 1) (3, 2) (3, 3)

The program should ask the user to enter the coordinates of the cell where they want to make a move.

Keep in mind that the first coordinate goes from top to bottom, and the second coordinate goes from left to right. Also, notice that coordinates start with 1 and can be 1, 2, or 3.

But what if the user attempts to enter invalid coordinates? This could happen if they try to enter letters or symbols instead of numbers, or the coordinates of an already occupied cell. Your program needs to prevent these things from happening by checking the user's input and catching possible exceptions.

Objectives
The program should work in the following way:

Ask the user to provide the initial state of the 3x3 table with the first input line. This must include nine symbols that can be X, O or _ (the latter represents an empty cell).
Output the specified 3x3 table before the user makes a move.
Request that the user enters the coordinates of the move they wish to make.
The user then inputs two numbers representing the cell in which they wish to place their X or O. The game always starts with X, so the user's move should be made with this symbol if there are an equal number of X's and O's in the table. If the table contains an extra X, the move should be made with O.
Analyze the user input and show messages in the following situations:
• This cell is occupied! Choose another one! — if the cell is not empty;
• You should enter numbers! — if the user tries to enter letters or symbols instead of numbers;
• Coordinates should be from 1 to 3! — if the user attempts to enter coordinates outside of the table's range.
Display the table again with the user's most recent move included.
Output the state of the game.
The possible states are:

com.griddynamics.tictactoe.Game.GameLogic not finished — when no side has three in a row, but the table still has empty cells;
Draw — when no side has three in a row, and the table is complete;
X wins — when there are three X's in a row (up, down, across, or diagonally);
O wins — when there are three O's in a row (up, down, across, or diagonally).
If the user provides invalid coordinates, the program should repeat the request until numbers that represent an empty cell on the table are supplied. You should ensure that the program only outputs the table twice — before the move and after the user makes a legal move.

## Stage 2/5: Easy does it
Description
Now it's time to make a working game, so let's create our first opponent! In this version of the program, the user will be playing as X, and the computer will be playing as O at easy level. This will be our first small step towards creating the AI!

Let's design it so that at this level the computer makes random moves. This should be perfect for people who have never played the game before!

If you want, you could make the game even simpler by including a difficulty level where the computer never wins. Feel free to create this along with the easy level if you like, but it won't be tested.

Objectives
In this stage, you should implement the following:

Display an empty table when the program starts.
The user plays first as X, and the program should ask the user to enter cell coordinates.
Next, the computer makes its move as O, and the players then move in turn until someone wins or the game results in a draw.
Print the final outcome at the very end of the game.

## Stage 3/5: Watch 'em fight
Description
It's time to make things more interesting by adding some game variations. What if you want to play against a friend instead of the AI? How about if you get tired of playing the game and want to see a match between two AIs? You also need to give the user the option of going first or second when playing against the AI.

It should be possible for the user to quit the game after the result is displayed as well.

Objectives
Your tasks for this stage are:

Write a menu loop, which can interpret two commands: start and exit.
Implement the command start. It should take two parameters: who will play X and who will play O. Two options are possible for now: user to play as a human, and easy to play as an AI.
The exit command should simply end the program.
In later steps, you will add the medium and hard levels.

Don't forget to handle incorrect input! The message Bad parameters! should be displayed if what the user enters is invalid.

## Stage 4/5: Signs of intelligence
Description
Let's write the medium difficulty level now. To do this, we need to add awareness to our AI.

This level will be a lot harder to beat than easy, even though the initial moves are still random. When the AI is playing at medium level, it wins when it can because of its first rule, and stops all simple attempts to beat it due to its second.

You can see these rules below.

Objectives
When the AI is playing at medium difficulty level, it makes moves using the following logic:

If it already has two in a row and can win with one further move, it does so.
If its opponent can win with one move, it plays the move necessary to block this.
Otherwise, it makes a random move.
You should add a medium parameter so that you can play against this level. It should also be possible to make AIs using easy and medium levels play against each other!
