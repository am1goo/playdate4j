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

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\pd4j_math.cpp  -o buildcache\compiled\pd4j_math.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_ApiBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_ApiBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_DisplayBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_DisplayBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_FilesystemBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_FilesystemBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_InputBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_InputBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_GraphicsBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_GraphicsBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SpriteBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SpriteBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_AudioSampleBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_AudioSampleBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_BitCrusherBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_BitCrusherBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_ChannelBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_ChannelBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_ControlSignalBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_ControlSignalBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_DelayLineBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_DelayLineBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_DelayLineTapBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_DelayLineTapBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_OnePoleFilterBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_OnePoleFilterBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_OverdriveBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_OverdriveBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthSignalBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthSignalBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthEnvelopeBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthEnvelopeBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthLFOBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthLFOBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_RingModulatorBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_RingModulatorBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_SamplePlayerBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SamplePlayerBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_SequenceTrackBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SequenceTrackBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_SoundSequenceBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SoundSequenceBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_SourceBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SourceBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SoundBridge_TwoPoleFilterBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_TwoPoleFilterBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_SysBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_SysBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -c -fPIC -D TARGET_EXTENSION %GCC_INCLUDE% pd4j_bridge\com_am1goo_playdate4j_sdk_VideoBridge.cpp -o buildcache\compiled\com_am1goo_playdate4j_sdk_VideoBridge.o
if %ERRORLEVEL% NEQ 0 EXIT /B 1

g++ -shared -o pd4j_engine\lib\pd4j_bridge.dll^
 buildcache\compiled\pd4j_api.o^
 buildcache\compiled\pd4j_math.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_ApiBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_FilesystemBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_DisplayBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_InputBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_GraphicsBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SpriteBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_AudioSampleBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_BitCrusherBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_ChannelBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_ControlSignalBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_DelayLineBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_DelayLineTapBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_OnePoleFilterBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_OverdriveBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthSignalBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthEnvelopeBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_PDSynthLFOBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_RingModulatorBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SamplePlayerBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SequenceTrackBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SoundSequenceBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_SourceBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SoundBridge_TwoPoleFilterBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_SysBridge.o^
 buildcache\compiled\com_am1goo_playdate4j_sdk_VideoBridge.o^
 -Wl,--add-stdcall-alias
if %ERRORLEVEL% NEQ 0 EXIT /B 1

cd %CWD%