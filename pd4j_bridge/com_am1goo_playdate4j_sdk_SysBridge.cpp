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

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getFlipped
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;

	return api->system->getFlipped();
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getReduceFlashing
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;

	return api->system->getReduceFlashing();
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getBatteryPercentage
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getBatteryPercentage();
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getBatteryVoltage
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getBatteryVoltage();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_clearICache
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->system->clearICache();
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getCurrentTimeMilliseconds
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getCurrentTimeMilliseconds();
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getSecondsSinceEpoch
(JNIEnv* env, jobject thisObject, jlong milliseconds) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	unsigned int millis = static_cast<unsigned int>(milliseconds);
	return api->system->getSecondsSinceEpoch(&millis);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_resetElapsedTime
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->system->resetElapsedTime();
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getElapsedTime
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getElapsedTime();
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getTimezoneOffset
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getTimezoneOffset();
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_shouldDisplay24HourTime
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;

	return api->system->shouldDisplay24HourTime();
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