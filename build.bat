REM build
SET CWD=%cd%
cd %~dp0
if not exist buildcache mkdir buildcache
if not exist buildcache\classes mkdir buildcache\classes
if not exist buildcache\compiled mkdir buildcache\compiled
if not exist pd4j_engine\lib mkdir pd4j_engine\lib

"%JAVA_HOME%\bin\javac" pd4j_engine\src\main\java\com\am1goo\playdate4j\sdk\*.java -h pd4j_bridge -d buildcache\classes
if %ERRORLEVEL% NEQ 0 EXIT /B 1

SET GCC_INCLUDE=-I "%JAVA_HOME%\include" -I "%JAVA_HOME%\include\win32" -I "%PLAYDATE_SDK_PATH%\C_API"

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\pd4j_api.cpp  -o buildcache\compiled\pd4j_api.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_DebugBridge.cpp    -o buildcache\compiled\com_am1goo_playdate4j_sdk_DebugBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_DisplayBridge.cpp  -o buildcache\compiled\com_am1goo_playdate4j_sdk_DisplayBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_InputBridge.cpp    -o buildcache\compiled\com_am1goo_playdate4j_sdk_InputBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_GraphicsBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_GraphicsBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SysBridge.cpp      -o buildcache\compiled\com_am1goo_playdate4j_sdk_SysBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -shared -o pd4j_engine\lib\pd4j_bridge.dll buildcache\compiled\pd4j_api.o buildcache\compiled\com_am1goo_playdate4j_sdk_DebugBridge.o buildcache\compiled\com_am1goo_playdate4j_sdk_DisplayBridge.o buildcache\compiled\com_am1goo_playdate4j_sdk_InputBridge.o buildcache\compiled\com_am1goo_playdate4j_sdk_GraphicsBridge.o buildcache\compiled\com_am1goo_playdate4j_sdk_SysBridge.o -Wl,--add-stdcall-alias
if %ERRORLEVEL% NEQ 0 EXIT /B 1

cd %CWD%