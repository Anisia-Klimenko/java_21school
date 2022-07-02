#remove target folder
rm -rf target

#make target directory
mkdir target target/resources

#change class files directory
javac `find . -name "*.java"` -d target

#copy sources
cp -R src/resources target

#create jar-file
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .

#make jar-file executable
chmod 777 target/images-to-chars-printer.jar

#run program
java -classpath target edu.school21.printer.app.Program . 0