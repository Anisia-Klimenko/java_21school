#remove target folder
rm -rf target

#make target directory
mkdir target target/resources

#change class files directory
javac java/edu/school21/printer/*/*.java -d target

#copy sources
cp resources ../target/resources

#run program
java -classpath target edu.school21.printer.app.Program . 0 test.bmp