@echo off
cd /d "%~dp0"
javac Contact.java ContactManager.java ContactsApp.java
if errorlevel 1 (
    pause
    exit /b 1
)
java ContactsApp
pause
