cmake_minimum_required(VERSION 3.22)
project(CLion C)

set(CMAKE_C_STANDARD 99)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -pthread")

add_executable(CLion User.c LoadBalancer.h InstanceHost.h InstanceHost.c LoadBalancer.c)
