#include "com_am1goo_playdate4j_sdk_SoundBridge_ControlSignalBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ControlSignalBridge_newSignal
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	ControlSignal* signal = api->sound->controlsignal->newSignal();
	uintptr_t signal_ptr = reinterpret_cast<uintptr_t>(signal);
	return signal_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ControlSignalBridge_freeSignal
  (JNIEnv* env, jobject thisObject, jlong signal_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	ControlSignal* signal = reinterpret_cast<ControlSignal*>(signal_ptr);
	api->sound->controlsignal->freeSignal(signal);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ControlSignalBridge_clearEvents
  (JNIEnv* env, jobject thisObject, jlong signal_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	ControlSignal* signal = reinterpret_cast<ControlSignal*>(signal_ptr);
	api->sound->controlsignal->clearEvents(signal);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ControlSignalBridge_addEvent
  (JNIEnv* env, jobject thisObject, jlong signal_ptr, jint step, jfloat value, jint interpolate) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	ControlSignal* signal = reinterpret_cast<ControlSignal*>(signal_ptr);
	api->sound->controlsignal->addEvent(signal, step, value, interpolate);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ControlSignalBridge_removeEvent
  (JNIEnv* env, jobject thisObject, jlong signal_ptr, jint step) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	ControlSignal* signal = reinterpret_cast<ControlSignal*>(signal_ptr);
	api->sound->controlsignal->removeEvent(signal, step);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ControlSignalBridge_getMIDIControllerNumber
  (JNIEnv* env, jobject thisObject, jlong signal_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return -1;
	  
	ControlSignal* signal = reinterpret_cast<ControlSignal*>(signal_ptr);
	return api->sound->controlsignal->getMIDIControllerNumber(signal);
}