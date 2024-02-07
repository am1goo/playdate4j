#include "com_am1goo_playdate4j_sdk_DisplayBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_getHeight
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	return api->display->getHeight();
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_getWidth
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	return api->display->getWidth();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setInverted
  (JNIEnv* env, jobject thisObject, jboolean value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->display->setInverted(value);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setMosaic
  (JNIEnv* env, jobject thisObject, jint x, jint y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->display->setMosaic(x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setFlipped
  (JNIEnv* env, jobject thisObject, jint x, jint y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->display->setFlipped(x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setRefreshRate
  (JNIEnv* env, jobject thisObject, jint rate) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->display->setRefreshRate(rate);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setScale
  (JNIEnv* env, jobject thisObject, jint value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->display->setScale(value);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_DisplayBridge_setOffset
  (JNIEnv* env, jobject thisObject, jint x, jint y){
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->display->setOffset(x, y);
}