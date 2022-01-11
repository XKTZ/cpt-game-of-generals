@echo off
: Clean
start clean.cmd
: Compile
javac -d bin -sourcepath ./src ./src/generals/main/ClientMain.java
javac -d bin -sourcepath ./src ./src/generals/main/ServerMain.java
javac -d bin -sourcepath ./src ./src/generals/main/TestMain.java
: Move log into
xcopy log "bin/log/" /E/H/C/I
: Move res into
xcopy res "bin/res/" /E/H/C/I

pause