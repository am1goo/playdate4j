#include "com_am1goo_playdate4j_sdk_SoundBridge_TwoPoleFilterBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_newFilter
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	TwoPoleFilter* filter = api->sound->effect->twopolefilter->newFilter();
	uintptr_t filter_ptr = reinterpret_cast<uintptr_t>(filter);
	return filter_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_freeFilter
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	api->sound->effect->twopolefilter->freeFilter(filter);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_setType
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jint type_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	TwoPoleFilterType type = static_cast<TwoPoleFilterType>(type_value);
	api->sound->effect->twopolefilter->setType(filter, type);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_setFrequency
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat frequency) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	api->sound->effect->twopolefilter->setFrequency(filter, frequency);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_setFrequencyModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->twopolefilter->setFrequencyModulator(filter, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_getFrequencyModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	PDSynthSignalValue* mod = api->sound->effect->twopolefilter->getFrequencyModulator(filter);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_setGain
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat gain) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	api->sound->effect->twopolefilter->setGain(filter, gain);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_setResonance
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat resonance) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	api->sound->effect->twopolefilter->setGain(filter, resonance);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_setResonanceModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->twopolefilter->setResonanceModulator(filter, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024TwoPoleFilterBridge_getResonanceModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	TwoPoleFilter* filter = reinterpret_cast<TwoPoleFilter*>(filter_ptr);
	PDSynthSignalValue* mod = api->sound->effect->twopolefilter->getResonanceModulator(filter);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}