echo -en '\033c\033[3J'
find * -name "*.java" > sources.txt
rm -rf simulation.txt
javac @sources.txt
java ru.school21.avaj.Main scenario.txt
cat simulation.txt
