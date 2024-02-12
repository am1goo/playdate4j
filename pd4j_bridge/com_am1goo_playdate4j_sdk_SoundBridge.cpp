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

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_getDefaultChannel
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = api->sound->getDefaultChannel();
	uintptr_t channel_ptr = reinterpret_cast<uintptr_t>(channel);
	return channel_ptr;
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_addChannel
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	return api->sound->addChannel(channel);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_removeChannel
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	return api->sound->removeChannel(channel);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_setOutputsActive
  (JNIEnv* env, jobject thisObject, jint headphone, jint speaker) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->sound->setOutputsActive(headphone, speaker);
}