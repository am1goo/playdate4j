#include "com_am1goo_playdate4j_sdk_SoundBridge_PDSynthEnvelopeBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_newEnvelope
  (JNIEnv* env, jobject thisObject, jfloat attack, jfloat decay, jfloat sustain, jfloat release) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	PDSynthEnvelope* envelope = api->sound->envelope->newEnvelope(attack, decay, sustain, release);
	uintptr_t envelope_ptr = reinterpret_cast<uintptr_t>(envelope);
	return envelope_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_freeEnvelope
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->freeEnvelope(envelope);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setAttack
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat attack) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setAttack(envelope, attack);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setDecay
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat decay) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setDecay(envelope, decay);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setSustain
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat sustain) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setSustain(envelope, sustain);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setRelease
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat release) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setRelease(envelope, release);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setCurvature
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat curvature) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setCurvature(envelope, curvature);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setVelocitySensitivity
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat velsens) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setVelocitySensitivity(envelope, velsens);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setRateScaling
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jfloat scaling, jfloat start_value, jfloat end_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	MIDINote start = static_cast<MIDINote>(start_value);
	MIDINote end = static_cast<MIDINote>(end_value);
	api->sound->envelope->setRateScaling(envelope, scaling, start, end);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setLegato
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jboolean flag) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setLegato(envelope, flag);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_setRetrigger
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr, jboolean flag) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	api->sound->envelope->setRetrigger(envelope, flag);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthEnvelopeBridge_getValue
  (JNIEnv* env, jobject thisObject, jlong envelope_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	PDSynthEnvelope* envelope = reinterpret_cast<PDSynthEnvelope*>(envelope_ptr);
	return api->sound->envelope->getValue(envelope);
}