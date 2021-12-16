@echo off
start clean.cmd
SET pathmain=
SET /p pathmain=Your main class path:
: Compile
javac -d out -sourcepath src %pathmain%
: Move log into
xcopy log "out/log/" /E/H/C/I
: Move res into
xcopy res "out/res/" /E/H/C/I