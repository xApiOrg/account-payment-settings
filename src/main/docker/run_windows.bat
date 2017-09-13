@echo off
Set cmdTitle=ipay
Set taskName=ipayService
if "%~1"=="/R" (
    echo * Param /R
    ::pause
    echo * Terminating all instances of the Server (Windows with title containing %cmdTitle%^)*
    ::pause
    FOR /F "tokens=2" %%l IN ('tasklist /FO TABLE /NH /V /FI "IMAGENAME eq cmd.exe" ^| findstr /r ".*ipay.*"') DO taskkill -f -t -pid %%l
    echo * Ending previous Task
    ::pause
    SCHTASKS /END /TN %taskName%
    echo * Creating new Task with command "cmd /K '%~dp0run_windows.bat' /D"
    ::pause
    SCHTASKS /CREATE /TN %taskName% /SC ONCE /ST 00:00 /TR "cmd /K '%~dp0run_windows.bat' /D" /F
    echo * Running Task
    ::pause
    SCHTASKS /RUN /TN %taskName%
)
if "%~1"=="/D" (
    echo Param /D
    ::pause
    echo Changing Window Title to %cmdTitle%
    ::pause
    title %cmdTitle%
    echo Moving to folder "%~dp0"
    ::pause
    cd "%~dp0"
    echo Deploying Server
    ::pause
    java -jar ../xapi-account-payment-settings-0.0.1-SNAPSHOT.jar
)