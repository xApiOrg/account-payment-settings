@echo off
Set cmdTitle=ipay
Set taskName=ipayService
Set deployFile=@project.build.finalName@.jar
Set fileDir=..

if "%~1"=="/D" (
	echo * Param /D
    echo * CD "%~dp0"
    ::pause
    cd "%~dp0"
    echo Changing window title to %cmdTitle%
    ::pause
    title %cmdTitle%
    echo Deploying %deployFile% at CD %fileDir%
    ::pause
    java -jar %fileDir%/%deployFile%
)
if "%~1"=="/DT" (
	echo * Param /DT
    echo * CD "%~dp0"
    ::pause
    cd "%~dp0"
    echo * Ending task %taskName%_D
    ::pause
    SCHTASKS /End /TN %taskName%_D
    echo * Creating task %taskName%_D
    ::pause
    SCHTASKS /Create /TN %taskName%_D /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /D" /F
    echo * Running task %taskName%_D
    ::pause
    SCHTASKS /Run /TN %taskName%_D
)
if "%~1"=="/K" (
	echo * Param /K
    echo * CD "%~dp0"
    ::pause
    cd "%~dp0"
    echo Killing all instances of %cmdTitle%
    ::pause
    FOR /F "tokens=2" %%l IN ('tasklist /FO TABLE /NH /V /FI "IMAGENAME eq cmd.exe" ^| findstr /r ".*%cmdTitle%.*"') DO taskkill /T /F /PID %%l
)
if "%~1"=="/KT" (
	echo * Param /KT
    echo * CD "%~dp0"
    ::pause
    cd "%~dp0"
    echo * Ending task %taskName%_K
    ::pause
    SCHTASKS /End /TN %taskName%_K
    echo * Creating task %taskName%_K
    ::pause
    SCHTASKS /Create /TN %taskName%_K /SC ONCE /ST 00:00 /TR "'%~dp0run_windows.bat' /K" /F
    echo * Running task %taskName%_K
    ::pause
    SCHTASKS /Run /TN %taskName%_K
)