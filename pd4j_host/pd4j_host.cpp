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
jmethodID class_game_method_event;
jmethodID class_game_method_get_frame_count;
jmethodID class_game_method_is_cycling;

JavaVM* create_vm(const char* path_to_jar, const char* path_to_libs);
JavaVM* create_vm(const char* path_to_jar, const char* path_to_libs)
{
    JavaVM* jvm;
    JNIEnv* env;

    JavaVMInitArgs vm_args;

    char* java_class_path_prefix = (char*)"-Djava.class.path=";
    char* java_class_path = (char*)malloc(strlen(java_class_path_prefix) + strlen(path_to_jar));
    strcpy(java_class_path, java_class_path_prefix);
    strcat(java_class_path, path_to_jar);

    char* java_library_path_prefix = (char*)"-Djava.library.path=";
    char* java_library_path = (char*)malloc(strlen(java_library_path_prefix) + strlen(path_to_libs));
    strcpy(java_library_path, java_library_path_prefix);
    strcat(java_library_path, path_to_libs);

    JavaVMOption options[2];
    options[0].optionString = java_class_path;
    options[1].optionString = java_library_path;
    vm_args.version = JNI_VERSION_1_6;
    vm_args.nOptions = 2;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_TRUE;

    int ret = JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
    if (ret < 0)
    {
        printf("Unable to Launch JVM\n");
        return NULL;
    }

    printf("JVM launch OK\n");
    return jvm;
}

int pd4j_init(PlaydateAPI* api, Options* options)
{
    jvm = create_vm(options->pathToJar, options->pathToLibs);
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
    class_game_method_init = env->GetStaticMethodID(class_game, "init", "(Z)V");
    class_game_method_shutdown = env->GetStaticMethodID(class_game, "shutdown", "()V");
    class_game_method_loop = env->GetStaticMethodID(class_game, "loop", "()V");
    class_game_method_event = env->GetStaticMethodID(class_game, "event", "(I)V");
    class_game_method_get_frame_count = env->GetStaticMethodID(class_game, "getFrameCount", "()I");
    class_game_method_is_cycling = env->GetStaticMethodID(class_game, "isCycling", "()Z");

    const char* engine_class_name_str = "com.am1goo.playdate4j.engine.JGameEngine";
    jstring engine_class_name = env->NewStringUTF(engine_class_name_str);
    env->CallVoidMethod(class_game, class_game_method_engine, engine_class_name);

    const char* cycle_class_name_str = options->gameCycleClass;
    jstring cycle_class_name = env->NewStringUTF(cycle_class_name_str);
    env->CallVoidMethod(class_game, class_game_method_create, cycle_class_name);

    bool is_running_in_simulator = options->runningInSimulator;
    env->CallVoidMethod(class_game, class_game_method_init, is_running_in_simulator);
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

int pd4j_event(PDSystemEvent event_value)
{
    if (!initialized)
        return PD4J_NOT_INITIALIZED;

    env->CallVoidMethod(class_game, class_game_method_event, event_value);
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
