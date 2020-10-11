
echo
echo Enter inputfolder name:
read inputFolder
echo Enter outputFolder name:
read outputFolder
echo
echo
echo "### TOKENIZING HAS BEGUN, STARTING MODULES ### "
echo
echo

STARTTIME=$(date +%s)
java JLex.Main Textprocessing.flex
javac Textprocessing.flex.java
ENDTIME=$(date +%s)
echo
echo
echo "--> IT TOOK $[$ENDTIME - $STARTTIME] SECONDS TO COMPLETE THIS TASK"
echo
echo
echo
echo "### TOKENIZING COMPLETED, INITIATING INDEXING ###"
echo
echo

STARTTIME=$(date +%s)


java Textprocessing $inputFolder 
javac Pass1.java
java Pass1 tempout $outputFolder

echo
echo "### INDEXING COMPLETED, HASH(KEY) GAVE CORRECT INDEX ### "
echo

rm -rf *.class

ENDTIME=$(date +%s)
echo
echo
echo "--> IT TOOK $[$ENDTIME - $STARTTIME] SECONDS TO COMPLETE THIS TASK"
echo
echo
echo


