@echo off
IF "%~1"=="/KT" GOTO deployTask
IF "%~1"=="/DT" GOTO deployTask
IF "%~1"=="/E" GOTO echoDP0
exit 0

:echoDP0
echo "%~dp0"
pause
exit 0

:deployTask
SCHTASKS /End /TN ipayService
SCHTASKS /Create /TN ipayService /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /E" /F
SCHTASKS /Run /TN ipayService
exit 0