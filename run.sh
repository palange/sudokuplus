echo Building
javac -d . -cp junit-4.8.2.jar src/sudokuplus/*

echo 
echo Running Tests
java -cp .:junit-4.8.2.jar org.junit.runner.JUnitCore sudokuplus.PuzzleTest

echo 
echo Running Puzzle validator
java sudokuplus.SudokuPlus "sampleInput 4x4.txt" "sampleInput 9x9.txt"

