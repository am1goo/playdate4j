#include "com_am1goo_playdate4j_sdk_SysBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_logToConsole
  (JNIEnv* env, jobject thisObject, jstring log) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	const char* str = env->GetStringUTFChars(log, 0);
	api->system->logToConsole(str);
	env->ReleaseStringUTFChars(log, str);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_error
  (JNIEnv* env, jobject thisObject, jstring error) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	const char* str = env->GetStringUTFChars(error, 0);
	api->system->error(str);
	env->ReleaseStringUTFChars(error, str);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_drawFps
  (JNIEnv* env, jobject thisObject, jint x, jint y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->system->drawFPS(x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setAutoLockDisabled
  (JNIEnv* env, jobject thisObject, jboolean disabled) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->system->setAutoLockDisabled(disabled);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setCrankSoundsDisabled
  (JNIEnv* env, jobject thisObject, jboolean disabled) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	return api->system->setCrankSoundsDisabled(disabled);
}