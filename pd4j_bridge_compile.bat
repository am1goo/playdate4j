REM build
SET CWD=%cd%
cd %~dp0
SET JAVA_SDK=thirdparty\java-se-8u43-ri
if not exist buildcache mkdir buildcache
if not exist buildcache\classes mkdir buildcache\classes
if not exist buildcache\compiled mkdir buildcache\compiled
if not exist pd4j_engine\lib mkdir pd4j_engine\lib
%JAVA_SDK%\bin\javac -h pd4j_bridge pd4j_engine\src\main\java\com\am1goo\playdate4j\sdk\*.java -d buildcache\classes
g++ -c -I "%JAVA_SDK%\include" -I "%JAVA_SDK%\include\win32" pd4j_bridge\com_am1goo_playdate4j_sdk_DisplayBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_DisplayBridge.o
g++ -c -I "%JAVA_SDK%\include" -I "%JAVA_SDK%\include\win32" pd4j_bridge\com_am1goo_playdate4j_sdk_SysBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SysBridge.o
g++ -shared -o pd4j_engine\lib\pd4j_bridge.dll buildcache\compiled\com_am1goo_playdate4j_sdk_DisplayBridge.o buildcache\compiled\com_am1goo_playdate4j_sdk_SysBridge.o -Wl,--add-stdcall-alias
cd %CWD%