#include "com_am1goo_playdate4j_sdk_SoundBridge_PDSynthLFOBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024LFOBridge_newLFO
  (JNIEnv* env, jobject thisObject, jint type_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LFOType type = static_cast<LFOType>(type_value);
	PDSynthLFO* lfo = api->sound->lfo->newLFO(type);
	uintptr_t lfo_ptr = reinterpret_cast<uintptr_t>(lfo);
	return lfo_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024LFOBridge_freeLFO
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->freeLFO(lfo);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setType
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jint type_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	LFOType type = static_cast<LFOType>(type_value);
	api->sound->lfo->setType(lfo, type);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setRate
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jfloat rate) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setRate(lfo, rate);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setPhase
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jfloat phase) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setPhase(lfo, phase);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setStartPhase
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jfloat phase) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setStartPhase(lfo, phase);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setCenter
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jfloat center) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setCenter(lfo, center);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setDepth
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jfloat depth) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setDepth(lfo, depth);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setArpeggiation
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jint nSteps, jfloatArray steps_array) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	float* steps = env->GetFloatArrayElements(steps_array, 0);
	api->sound->lfo->setArpeggiation(lfo, nSteps, steps);
	env->ReleaseFloatArrayElements(steps_array, steps, 0);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setDelay
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jfloat holdoff, jfloat ramptime) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setDelay(lfo, holdoff, ramptime);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setRetrigger
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jboolean flag) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setRetrigger(lfo, flag);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_getValue
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	return api->sound->lfo->getValue(lfo);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthLFOBridge_setGlobal
  (JNIEnv* env, jobject thisObject, jlong lfo_ptr, jboolean global) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynthLFO* lfo = reinterpret_cast<PDSynthLFO*>(lfo_ptr);
	api->sound->lfo->setGlobal(lfo, global);
}