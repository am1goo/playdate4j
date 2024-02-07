cmake_minimum_required(VERSION 3.14)
set(CMAKE_C_STANDARD 11)

set(ENVSDK $ENV{PLAYDATE_SDK_PATH})

if (NOT ${ENVSDK} STREQUAL "")
	# Convert path from Windows
	file(TO_CMAKE_PATH ${ENVSDK} SDK)
else()
	execute_process(
			COMMAND bash -c "egrep '^\\s*SDKRoot' $HOME/.Playdate/config"
			COMMAND head -n 1
			COMMAND cut -c9-
			OUTPUT_VARIABLE SDK
			OUTPUT_STRIP_TRAILING_WHITESPACE
	)
endif()

if (NOT EXISTS ${SDK})
	message(FATAL_ERROR "SDK Path not found; set ENV value PLAYDATE_SDK_PATH")
	return()
endif()

set(CMAKE_CONFIGURATION_TYPES "Debug;Release")
set(CMAKE_XCODE_GENERATE_SCHEME TRUE)

# Game Name Customization
set(PLAYDATE_GAME_NAME app_name)
set(PLAYDATE_GAME_DEVICE app_name_DEVICE)

project(${PLAYDATE_GAME_NAME} C ASM)

if (TOOLCHAIN STREQUAL "armgcc")
	add_executable(${PLAYDATE_GAME_DEVICE} src/main.c)
else()
	add_library(${PLAYDATE_GAME_NAME} SHARED src/main.c )
endif()

include(${SDK}/C_API/buildsupport/playdate_game.cmake)

target_include_directories(${PLAYDATE_GAME_NAME} PUBLIC "${PROJECT_SOURCE_DIR}/../pd4j_host")
target_link_libraries(${PLAYDATE_GAME_NAME} PUBLIC "${PROJECT_SOURCE_DIR}/../pd4j_host/out/build/x64-Debug/pd4j_host.lib")
target_link_libraries(${PLAYDATE_GAME_NAME} PUBLIC "${PROJECT_SOURCE_DIR}/../pd4j_bridge/out/build/x64-Debug/pd4j_bridge.lib")
target_link_libraries(${PLAYDATE_GAME_NAME} PUBLIC "$ENV{JAVA_HOME}/lib/jvm.lib")