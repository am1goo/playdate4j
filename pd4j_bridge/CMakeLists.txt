cmake_minimum_required (VERSION 3.8)

message(STATUS ${TARGET})
set(TARGET_NONE "NONE")
set(TARGET_PLAYDATE "PLAYDATE")

if (NOT DEFINED TARGET)
	set(TARGET ${TARGET_NONE})
	message(STATUS "Target wasn't defined, fallback to default value")
endif()
message(STATUS "Target: ${TARGET}")

if (${TARGET} STREQUAL ${TARGET_PLAYDATE})
	message(STATUS "Using toolchain from Playdate SDK")
	set(CMAKE_TOOLCHAIN_FILE "$ENV{PLAYDATE_SDK_PATH}/C_API/buildsupport/arm.cmake")
	message(STATUS "CMAKE_TOOLCHAIN_FILE=${CMAKE_TOOLCHAIN_FILE}")
endif()

set(CMAKE_CXX_STANDARD 20)

message(STATUS "Including arch.cmake file")
include("../pd4j_buildtools/arch.cmake")

project ("pd4j_bridge")

add_library (pd4j_bridge STATIC "pd4j_api.h" "pd4j_api.cpp" )

if (NOT ${TARGET} STREQUAL ${TARGET_PLAYDATE})
	include("../pd4j_buildtools/jdk.cmake")
	target_include_directories(pd4j_bridge PRIVATE ${JNI_INCLUDE_DIRS})
else()
	message(STATUS "Including jdk.cmake file skipped, working on that")
endif()

message(STATUS "Including playdate.cmake file")
include("../pd4j_buildtools/playdate.cmake")
target_include_directories(pd4j_bridge PRIVATE ${PLAYDATE_INCLUDE_DIRS})