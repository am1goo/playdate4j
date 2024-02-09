// pd4j_host.cpp : Defines the entry point for the application.
//

#include <cstdint>
#include <jni.h>
#include <pd_api.h>
#include "pd4j_consts.h"
#include "pd4j_host.h"
#include "pd4j_bridge.h"

JavaVM* jvm;
JNIEnv* env;
bool initialized;

/*
+---+---------+
| Z | boolean |
| B | byte    |
| C | char    |
| S | short   |
| I | int     |
| J | long    |
| F | float   |
| D | double  |
+-------------+
*/

jclass class_api;
jmethodID class_api_method_is_api_loaded;

jclass class_game;
jmethodID class_game_method_engine;
jmethodID class_game_method_create;
jmethodID class_game_method_init;
jmethodID class_game_method_shutdown;
jmethodID class_game_method_loop;
jmethodID class_game_method_get_frame_count;
jmethodID class_game_method_is_cycling;

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

    pd4j_set_api(env, api);

    class_api = env->FindClass("com/am1goo/playdate4j/sdk/Api");
    class_api_method_is_api_loaded = env->GetStaticMethodID(class_api, "isApiAvailable", "()Z");

    class_game = env->FindClass("com/am1goo/playdate4j/sdk/Game");
    class_game_method_engine = env->GetStaticMethodID(class_game, "engine", "(Ljava/lang/String;)V");
    class_game_method_create = env->GetStaticMethodID(class_game, "create", "(Ljava/lang/String;)V");
    class_game_method_init = env->GetStaticMethodID(class_game, "init", "()V");
    class_game_method_shutdown = env->GetStaticMethodID(class_game, "shutdown", "()V");
    class_game_method_loop = env->GetStaticMethodID(class_game, "loop", "()V");
    class_game_method_get_frame_count = env->GetStaticMethodID(class_game, "getFrameCount", "()I");
    class_game_method_is_cycling = env->GetStaticMethodID(class_game, "isCycling", "()Z");

    jstring engine_class_name = env->NewStringUTF("com.am1goo.playdate4j.engine.JGameEngine");
    env->CallVoidMethod(class_game, class_game_method_engine, engine_class_name);

    jstring cycle_class_name = env->NewStringUTF("com.am1goo.playdate4j.example.ExampleGameCycle");
    env->CallVoidMethod(class_game, class_game_method_create, cycle_class_name);

    env->CallVoidMethod(class_game, class_game_method_init);
    return PD4J_OK;
}

int pd4j_shutdown()
{
    if (!initialized)
        return PD4J_OK;

    if (env != NULL)
    {
        env->CallVoidMethod(class_game, class_game_method_shutdown);
        env = NULL;
    }

    jvm = NULL;
    return PD4J_OK;
}

int pd4j_update(int* redraw)
{
    if (!initialized) {
        *redraw = 0;
        return PD4J_NOT_INITIALIZED;
    }

    env->CallVoidMethod(class_game, class_game_method_loop);
    *redraw = 1;
    return PD4J_OK;
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
