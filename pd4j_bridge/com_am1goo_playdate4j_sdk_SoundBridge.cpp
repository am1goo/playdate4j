#include "com_am1goo_playdate4j_sdk_SoundBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_getCurrentTime
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	return api->sound->getCurrentTime();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_setOutputsActive
  (JNIEnv* env, jobject thisObject, jint headphone, jint speaker) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->sound->setOutputsActive(headphone, speaker);
}