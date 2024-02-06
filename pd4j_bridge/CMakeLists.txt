cmake_minimum_required (VERSION 3.8)
set(CMAKE_CXX_STANDARD 20)

project ("pd4j_bridge")

add_library (pd4j_bridge STATIC "pd4j_api.h" "pd4j_api.cpp" )

include(playdate.cmake)
target_include_directories(pd4j_bridge PRIVATE ${PLAYDATE_INCLUDE_DIRS})