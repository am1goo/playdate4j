#include "com_am1goo_playdate4j_sdk_SoundBridge_OnePoleFilterBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OnePoleFilterBridge_newFilter
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	OnePoleFilter* filter = api->sound->effect->onepolefilter->newFilter();
	uintptr_t filter_ptr = reinterpret_cast<uintptr_t>(filter);
	return filter_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OnePoleFilterBridge_freeFilter
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	OnePoleFilter* filter = reinterpret_cast<OnePoleFilter*>(filter_ptr);
	api->sound->effect->onepolefilter->freeFilter(filter);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OnePoleFilterBridge_setParameter
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat parameter) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	OnePoleFilter* filter = reinterpret_cast<OnePoleFilter*>(filter_ptr);
	api->sound->effect->onepolefilter->setParameter(filter, parameter);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OnePoleFilterBridge_setParameterModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	OnePoleFilter* filter = reinterpret_cast<OnePoleFilter*>(filter_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->onepolefilter->setParameterModulator(filter, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OnePoleFilterBridge_getParameterModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	OnePoleFilter* filter = reinterpret_cast<OnePoleFilter*>(filter_ptr);
	PDSynthSignalValue* mod = api->sound->effect->onepolefilter->getParameterModulator(filter);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}