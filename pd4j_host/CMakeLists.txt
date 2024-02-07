# CMakeList.txt : CMake project for pd4j_host, include source and define
# project specific logic here.
#
cmake_minimum_required (VERSION 3.8)
set(CMAKE_CXX_STANDARD 20)

project ("pd4j_host")

# Add source to this project's executable.
add_executable(pd4j_exec "pd4j_exec.h" "pd4j_exec.cpp")

# Add source to this project's library.
add_library (pd4j_host STATIC "pd4j_host.h" "pd4j_host.cpp" )

include("../pd4j_buildtools/jdk.cmake")
target_include_directories(pd4j_host PUBLIC ${JNI_INCLUDE_DIRS})

include("../pd4j_buildtools/playdate.cmake")
target_include_directories(pd4j_host PUBLIC ${PLAYDATE_INCLUDE_DIRS})

target_include_directories(pd4j_host PUBLIC "../pd4j_bridge/include")
target_link_libraries(pd4j_host PUBLIC ${JNI_LIBRARIES})
target_link_libraries(pd4j_host PUBLIC "${PROJECT_SOURCE_DIR}/../pd4j_bridge/out/build/x64-Debug/pd4j_bridge.lib")
target_link_libraries(pd4j_exec PUBLIC pd4j_host)