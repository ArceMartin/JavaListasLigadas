cls
echo Eliminando archivos anteriores... && del *.class 2> nul
echo Compilando.. && javac *.java
echo Ejecutando.. && java Main %1