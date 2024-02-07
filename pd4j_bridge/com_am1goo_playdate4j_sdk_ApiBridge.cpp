#include "com_am1goo_playdate4j_sdk_ApiBridge.h"
#include "pd4j_api.h"

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_ApiBridge_isApiAvailable
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	return api != NULL;
}