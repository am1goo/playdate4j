#include "com_am1goo_playdate4j_sdk_SoundBridge_RingModulatorBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024RingModulatorBridge_newRingMod
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	RingModulator* filter = api->sound->effect->ringmodulator->newRingmod();
	uintptr_t filter_ptr = reinterpret_cast<uintptr_t>(filter);
	return filter_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024RingModulatorBridge_freeRingMod
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	RingModulator* filter = reinterpret_cast<RingModulator*>(filter_ptr);
	api->sound->effect->ringmodulator->freeRingmod(filter);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024RingModulatorBridge_setFrequency
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat frequency) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	RingModulator* filter = reinterpret_cast<RingModulator*>(filter_ptr);
	api->sound->effect->ringmodulator->setFrequency(filter, frequency);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024RingModulatorBridge_setFrequencyModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	RingModulator* filter = reinterpret_cast<RingModulator*>(filter_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->ringmodulator->setFrequencyModulator(filter, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024RingModulatorBridge_getFrequencyModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	RingModulator* filter = reinterpret_cast<RingModulator*>(filter_ptr);
	PDSynthSignalValue* mod = api->sound->effect->ringmodulator->getFrequencyModulator(filter);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}