cmake_minimum_required(VERSION 3.17)
project(C__)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(Hangman)

add_executable(C__ Hangman/Hangman.cpp)
