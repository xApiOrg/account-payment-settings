@echo off
Set cmdTitle=ipay
Set taskName=ipayService
Set deployFile=@project.build.finalName@.jar
Set fileDir=..
Set projectDir=..\..
Set goto=end

if "%~1"=="/QT" GOTO queryTask
if "%~1"=="/Q" GOTO query
if "%~1"=="/KT" GOTO killTask
if "%~1"=="/K" GOTO kill
if "%~1"=="/BT" GOTO buildTask
if "%~1"=="/B" GOTO build
if "%~1"=="/DT" GOTO deployTask
if "%~1"=="/D" GOTO deploy

:queryTask
echo * Param /QT
echo * Directory "%~dp0"
echo * Ending task %taskName%_Q
SCHTASKS /End /TN %taskName%_Q
echo * Creating task %taskName%_Q
SCHTASKS /Create /TN %taskName%_Q /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /Q" /F
echo * Running task %taskName%_Q
SCHTASKS /Run /TN %taskName%_Q
GOTO %goto%

:query
echo * Param /Q
echo * Directory "%~dp0"
:queryKill
Set goto=queryBuild
GOTO kill
:queryBuild
Set goto=queryDeploy
GOTO build
:queryDeploy
Set goto=end
GOTO deployTask

:kill
echo * Param /K
echo * Directory "%~dp0"
echo * Killing all instances of %cmdTitle%
FOR /F "tokens=2" %%l IN ('tasklist /FO TABLE /NH /V /FI "IMAGENAME eq cmd.exe" ^| findstr /r ".*%cmdTitle%.*"') DO taskkill /T /F /PID %%l
GOTO %goto%

:killTask
echo * Param /KT
echo * Directory "%~dp0"
echo * Ending task %taskName%_K
SCHTASKS /End /TN %taskName%_K
echo * Creating task %taskName%_K
SCHTASKS /Create /TN %taskName%_K /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /K" /F
echo * Running task %taskName%_K
SCHTASKS /Run /TN %taskName%_K
GOTO %goto%

:build
Set ocd=%CD%
CD "%~dp0"
CD "%projectDir%"
cmd /C mvn clean verify test install
CD "%ocd%"
GOTO %goto%

:buildTask
echo * Param /BT
echo * Directory "%~dp0"
echo * Ending task %taskName%_B
SCHTASKS /End /TN %taskName%_B
echo * Creating task %taskName%_B
SCHTASKS /Create /TN %taskName%_B /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /B" /F
echo * Running task %taskName%_B
SCHTASKS /Run /TN %taskName%_B
GOTO %goto%

:deploy
echo * Param /D
echo * Directory "%~dp0"
echo * Changing window title to %cmdTitle%
title %cmdTitle%
echo * Deploying %deployFile%
java -jar "%~dp0%fileDir%/%deployFile%"
GOTO %goto%

:deployTask
echo * Param /DT
echo * Directory "%~dp0"
echo * Ending task %taskName%_D
SCHTASKS /End /TN %taskName%_D
echo * Creating task %taskName%_D
SCHTASKS /Create /TN %taskName%_D /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /D" /F
echo * Running task %taskName%_D
SCHTASKS /Run /TN %taskName%_D
GOTO %goto%

:end