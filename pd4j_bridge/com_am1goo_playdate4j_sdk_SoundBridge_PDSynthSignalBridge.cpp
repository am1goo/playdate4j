#include "com_am1goo_playdate4j_sdk_SoundBridge_PDSynthSignalBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthSignalBridge_newSignal
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynthSignal* signal = api->sound->signal->newSignal(NULL, NULL, NULL, NULL, NULL);
	uintptr_t signal_ptr = reinterpret_cast<uintptr_t>(signal);
	return signal_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthSignalBridge_freeSignal
  (JNIEnv* env, jobject thisObject, jlong signal_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthSignal* signal = reinterpret_cast<PDSynthSignal*>(signal_ptr);
	api->sound->signal->freeSignal(signal);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthSignalBridge_getValue
  (JNIEnv* env, jobject thisObject, jlong signal_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynthSignal* signal = reinterpret_cast<PDSynthSignal*>(signal_ptr);
	return api->sound->signal->getValue(signal);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthSignalBridge_setValueOffset
  (JNIEnv* env, jobject thisObject, jlong signal_ptr, jfloat offset) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthSignal* signal = reinterpret_cast<PDSynthSignal*>(signal_ptr);
	api->sound->signal->setValueOffset(signal, offset);
  }

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthSignalBridge_setValueScale
  (JNIEnv* env, jobject thisObject, jlong signal_ptr, jfloat scale) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthSignal* signal = reinterpret_cast<PDSynthSignal*>(signal_ptr);
	api->sound->signal->setValueScale(signal, scale);
}