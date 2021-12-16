@echo off
SET pathmain=
SET /p pathmain=Your main class:
CD out
java %pathmain%
CD ../