#include <jni.h>
#include <logging.h>
#include "parse.h"
#include <string>

using namespace std;

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *_vm, void *) {
    LOGE("start test  JNI_OnLoad");

    return JNI_VERSION_1_6;
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_jnitrace_traceTest_testHook(
        JNIEnv *env,
        jclass,
        jstring jstr) {

    jclass tracetest = env->FindClass("com/example/jnitrace/traceTest");

    jmethodID md1 = env->GetStaticMethodID(tracetest, "test4", "(Ljava/lang/String;)I");

    int aa = env->CallStaticIntMethod(tracetest, md1, env->NewStringUTF("666"));
//    string str = parse::jstring2str(env, jstr);
//
//    LOG(ERROR) << "length  :  " << to_string(str.length()) << "  ";

    LOGE("length : %d", strlen("abcd"));

    env->DeleteLocalRef(tracetest);

    LOGE("---------------------------end-------------------------");
}
