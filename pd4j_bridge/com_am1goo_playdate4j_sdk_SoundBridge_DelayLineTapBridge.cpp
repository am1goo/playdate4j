#include "com_am1goo_playdate4j_sdk_SoundBridge_DelayLineTapBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineTapBridge_freeTap
  (JNIEnv* env, jobject thisObject, jlong tap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	DelayLineTap* tap = reinterpret_cast<DelayLineTap*>(tap_ptr);
	api->sound->effect->delayline->freeTap(tap);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineTapBridge_setTapDelay
  (JNIEnv* env, jobject thisObject, jlong tap_ptr, jint frames) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	DelayLineTap* tap = reinterpret_cast<DelayLineTap*>(tap_ptr);
	api->sound->effect->delayline->setTapDelay(tap, frames);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineTapBridge_setTapDelayModulator
  (JNIEnv* env, jobject thisObject, jlong tap_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	DelayLineTap* tap = reinterpret_cast<DelayLineTap*>(tap_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->delayline->setTapDelayModulator(tap, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineTapBridge_getTapDelayModulator
  (JNIEnv* env, jobject thisObject, jlong tap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	DelayLineTap* tap = reinterpret_cast<DelayLineTap*>(tap_ptr);
	PDSynthSignalValue* mod = api->sound->effect->delayline->getTapDelayModulator(tap);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineTapBridge_setTapChannelsFlipped
  (JNIEnv* env, jobject thisObject, jlong tap_ptr, jboolean flip) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	DelayLineTap* tap = reinterpret_cast<DelayLineTap*>(tap_ptr);
	api->sound->effect->delayline->setTapChannelsFlipped(tap, flip);
}