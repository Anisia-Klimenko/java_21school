#remove target and lib folder
rm -rf target lib

#make target and lib directory
mkdir target lib

#get external libs
wget -O lib/jcommander-1.82.jar "https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar"
wget -O lib/JCDP-4.0.2.jar "https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar"

#give executable rights
chmod 777 lib/jcommander-1.82.jar
chmod 777 lib/JCDP-4.0.2.jar

#unpacking
cp lib/jcommander-1.82.jar target
cp lib/JCDP-4.0.2.jar target
cd target
jar -xf jcommander-1.82.jar
jar -xf JCDP-4.0.2.jar
rm -rf jcommander-1.82.jar JCDP-4.0.2.jar META-INF
cd ..
rm -rf META-INF

#change class files directory
javac -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar `find . -name "*.java"` -d target

#copy sources
cp -R src/resources target

#create jar-file
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target .

#make jar-file executable
chmod 777 target/images-to-chars-printer.jar

#run program
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN