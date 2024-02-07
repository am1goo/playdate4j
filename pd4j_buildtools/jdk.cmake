cmake_minimum_required (VERSION 3.8)

find_package(Java REQUIRED)
find_package(JNI REQUIRED)
if (JNI_FOUND)
    message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
    message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
endif()

include(UseJava)
set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.6" "-target" "1.6")

message(STATUS "JAVA_HOME" = $ENV{JAVA_HOME})