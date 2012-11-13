You will need to have the java runtime installed.
The following command should be run from the root of project. 

build command:
`javac -d . -cp junit-4.8.2.jar src/sudokuplus/*`

run program:
`java sudokuplus.SudokuPlus "sampleInput 4x4.txt" "sampleInput 9x9.txt`

run tests:
`java -cp .:junit-4.8.2.jar org.junit.runner.JUnitCore sudokuplus.PuzzleTest`

