#include "com_am1goo_playdate4j_sdk_SoundBridge_BitCrusherBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_newBitCrusher
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	BitCrusher* filter = api->sound->effect->bitcrusher->newBitCrusher();
	uintptr_t filter_ptr = reinterpret_cast<uintptr_t>(filter);
	return filter_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_freeBitCrusher
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	api->sound->effect->bitcrusher->freeBitCrusher(filter);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_setAmount
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat amount) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	api->sound->effect->bitcrusher->setAmount(filter, amount);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_setAmountModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->bitcrusher->setAmountModulator(filter, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_getAmountModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	PDSynthSignalValue* mod = api->sound->effect->bitcrusher->getAmountModulator(filter);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_setUndersampling
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jfloat undersampling) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	api->sound->effect->bitcrusher->setUndersampling(filter, undersampling);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_setUndersampleModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->bitcrusher->setUndersampleModulator(filter, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024BitCrusherBridge_getUndersampleModulator
  (JNIEnv* env, jobject thisObject, jlong filter_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	BitCrusher* filter = reinterpret_cast<BitCrusher*>(filter_ptr);
	PDSynthSignalValue* mod = api->sound->effect->bitcrusher->getUndersampleModulator(filter);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}