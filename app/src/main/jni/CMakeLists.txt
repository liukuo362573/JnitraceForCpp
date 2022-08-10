cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_STANDARD 14)
#添加内联支持
enable_language(C ASM)

include_directories(
        ./
        ./log/
        ./Substrate/
        ./utils/
        ./xhook/
)


add_library(
        tracetest

        SHARED

        test/test.cpp
        log/logging.h
        utils/parse.cpp
)
target_link_libraries(
        tracetest

        ${log-lib}
)

add_library(
        jnitrace

        SHARED

        main_substrate.cpp
        Jnitrace.cpp

        #Substrate
        Substrate/hde64.c
        Substrate/SubstrateDebug.cpp
        Substrate/SubstrateHook.cpp
        Substrate/SubstratePosixMemory.cpp
        Substrate/And64InlineHook.cpp

        utils/parse.cpp

        #xhook
        xhook/xhook.c
        xhook/xh_core.c
        xhook/xh_elf.c
        xhook/xh_jni.c
        xhook/xh_log.c
        xhook/xh_util.c
        xhook/xh_version.c
        main_xhook.c
)

find_library(
        log-lib
        log
)

target_link_libraries(
        jnitrace
        ${log-lib}
)