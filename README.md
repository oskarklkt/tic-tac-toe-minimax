# Tic-Tac-Toe



## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://gitlab.griddynamics.net/pl-java-internship-2024-q1/tic-tac-toe.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](https://gitlab.griddynamics.net/pl-java-internship-2024-q1/tic-tac-toe/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thanks to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README

Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.

##Stage 1/5: Initial setup
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

Game not finished — when no side has three in a row, but the table still has empty cells;
Draw — when no side has three in a row, and the table is complete;
X wins — when there are three X's in a row (up, down, across, or diagonally);
O wins — when there are three O's in a row (up, down, across, or diagonally).
If the user provides invalid coordinates, the program should repeat the request until numbers that represent an empty cell on the table are supplied. You should ensure that the program only outputs the table twice — before the move and after the user makes a legal move.

Examples
The examples below show how your program should work.
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

Example 1:

Enter the cells: > _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
Enter the coordinates: > 3 1
This cell is occupied! Choose another one!
Enter the coordinates: > one
You should enter numbers!
Enter the coordinates: > one three
You should enter numbers!
Enter the coordinates: > 4 1
Coordinates should be from 1 to 3!
Enter the coordinates: > 1 1
---------
| X X X |
| O O   |
| O X   |
---------
X wins
Example 2:

Enter the cells: > XX_XOXOO_
---------
| X X   |
| X O X |
| O O   |
---------
Enter the coordinates: > 3 3
---------
| X X   |
| X O X |
| O O O |
---------
O wins
Example 3:

Enter the cells: > XX_XOXOO_
---------
| X X   |
| X O X |
| O O   |
---------
Enter the coordinates: > 1 3
---------
| X X O |
| X O X |
| O O   |
---------
O wins
Example 4:

Enter the cells: > OX_XOOOXX
---------
| O X   |
| X O O |
| O X X |
---------
Enter the coordinates: > 1 3
---------
| O X X |
| X O O |
| O X X |
---------
Draw
Example 5:

Enter the cells: >  _XO_OX___
---------
|   X O |
|   O X |
|       |
---------
Enter the coordinates: > 3 1
---------
|   X O |
|   O X |
| X     |
---------
Game not finished
