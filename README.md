##Instructions

You will need to have the java runtime installed.<br>
At your command prompt type: `./run.sh`


There are three commands that are executed by run.sh:<br>
1.build command:
`javac -d . -cp junit-4.8.2.jar src/sudokuplus/*`
2.run program:
`java sudokuplus.SudokuPlus "sampleInput 4x4.txt" "sampleInput 9x9.txt"`
3.Run tests:
`java -cp .:junit-4.8.2.jar org.junit.runner.JUnitCore sudokuplus.PuzzleTest`

