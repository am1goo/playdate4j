// pd4j_host.cpp : Defines the entry point for the application.
//

#include "pd4j_consts.h"
#include "pd4j_host.h"
#include "pd4j_bridge.h"
#include <jni.h>
#include <pd_api.h>

JavaVM* jvm;
JNIEnv* env;
bool initialized;

jclass class_debug;
jmethodID class_debug_method_is_api_loaded;

jclass class_core;
jmethodID class_core_method_init;
jmethodID class_core_method_shutdown;
jmethodID class_core_method_loop;
jmethodID class_core_method_get_frame_count;
jmethodID class_core_method_is_cycling;

JavaVM* create_vm();
JavaVM* create_vm()
{
    JavaVM* jvm;
    JNIEnv* env;

    JavaVMInitArgs vm_args;

    JavaVMOption* options = new JavaVMOption[2];
    options[0].optionString = (char*)"-Djava.class.path=D:\\Projects\\pd4j\\pd4j_engine\\out\\artifacts\\pd4j_engine_jar\\pd4j_engine.jar";
    options[1].optionString = (char*)"-Djava.library.path=D:\\Projects\\pd4j\\pd4j_engine\\lib";
    vm_args.version = JNI_VERSION_1_6;
    vm_args.nOptions = 2;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_TRUE;

    int ret = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
    delete[] options;

    if (ret < 0)
    {
        printf("Unable to Launch JVM\n");
        return NULL;
    }

    printf("JVM launch OK\n");
    return jvm;
}

int pd4j_init(PlaydateAPI* api)
{
    jvm = create_vm();
    initialized = jvm != NULL;
    if (!initialized)
        return PD4J_FAILED;

    int envStat = jvm->GetEnv((void**)&env, JNI_VERSION_1_6);
    if (envStat == JNI_EDETACHED)
        return PD4J_NOT_INITIALIZED;

    if (envStat == JNI_EVERSION)
        return PD4J_WRONG_JAVA_VERSION;

    PlaydateHost::setApi(api);

    class_debug = env->FindClass("com/am1goo/playdate4j/sdk/Debug");
    class_debug_method_is_api_loaded = env->GetStaticMethodID(class_debug, "isApiAvailable", "()Z");

    class_core = env->FindClass("com/am1goo/playdate4j/Core");
    class_core_method_init = env->GetStaticMethodID(class_core, "init", "()V");
    class_core_method_shutdown = env->GetStaticMethodID(class_core, "shutdown", "()V");
    class_core_method_loop = env->GetStaticMethodID(class_core, "loop", "()V");
    class_core_method_get_frame_count = env->GetStaticMethodID(class_core, "getFrameCount", "()I");
    class_core_method_is_cycling = env->GetStaticMethodID(class_core, "isCycling", "()Z");

    env->CallVoidMethod(class_core, class_core_method_init);
    return PD4J_OK;
}

int pd4j_shutdown()
{
    if (!initialized)
        return PD4J_OK;

    if (env != NULL)
    {
        env->CallVoidMethod(class_core, class_core_method_shutdown);
        env = NULL;
    }

    PlaydateHost::setApi(NULL);

    jvm = NULL;
    return PD4J_OK;
}

int pd4j_update(PlaydateAPI* api)
{
    if (!initialized)
        return PD4J_NOT_INITIALIZED;

    PlaydateHost::setApi(api);
    env->CallVoidMethod(class_core, class_core_method_loop);

    int frame_count = env->CallIntMethod(class_core, class_core_method_get_frame_count);
    bool is_cycling = env->CallBooleanMethod(class_core, class_core_method_is_cycling);
    bool is_api_available = env->CallBooleanMethod(class_debug, class_debug_method_is_api_loaded);
    return is_api_available != NULL ? 3 : -3;
}

JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM* vm, void* reserved)
{
    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL
JNI_OnUnload(JavaVM* vm, void* reserved)
{
    return;
}
