#remove target folder
rm -rf target

#make target directory
mkdir target

#change class files directory
javac src/java/edu/school21/printer/*/*.java -d target

#run program
java -classpath target edu.school21.printer.app.Program . 0 test.bmp