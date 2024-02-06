#include "com_am1goo_playdate4j_sdk_DebugBridge.h"
#include "pd4j_api.h"

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_DebugBridge_isApiAvailable
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = PlaydateHost::getApi();
	return api != NULL;
}