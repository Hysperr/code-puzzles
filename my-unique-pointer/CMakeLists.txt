cmake_minimum_required(VERSION 3.8)
project(my_unique_pointer)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES
        my_unique_pointer.cpp)

add_executable(my_unique_pointer ${SOURCE_FILES})