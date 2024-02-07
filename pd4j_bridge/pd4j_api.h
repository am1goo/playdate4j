#include <jni.h>
#include <pd_api.h>

#ifdef __cplusplus
#define EXTERNC extern "C"
#else
#define EXTERNC
#endif

EXTERNC PlaydateAPI* pd4j_get_api(JNIEnv* env);
EXTERNC void pd4j_set_api(JNIEnv* env, PlaydateAPI* api);