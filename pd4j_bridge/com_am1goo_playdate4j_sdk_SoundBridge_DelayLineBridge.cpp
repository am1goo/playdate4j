#include "com_am1goo_playdate4j_sdk_SoundBridge_DelayLineBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineBridge_newDelayLine
  (JNIEnv* env, jobject thisObject, jint length, jint stereo) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	DelayLine* delayLine = api->sound->effect->delayline->newDelayLine(length, stereo);
	uintptr_t delayLine_ptr = reinterpret_cast<uintptr_t>(delayLine);
	return delayLine_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineBridge_freeDelayLine
  (JNIEnv* env, jobject thisObject, jlong delayLine_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	DelayLine* delayLine = reinterpret_cast<DelayLine*>(delayLine_ptr);
	api->sound->effect->delayline->freeDelayLine(delayLine);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineBridge_setLength
  (JNIEnv* env, jobject thisObject, jlong delayLine_ptr, jint frames) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	DelayLine* delayLine = reinterpret_cast<DelayLine*>(delayLine_ptr);
	api->sound->effect->delayline->setLength(delayLine, frames);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineBridge_setFeedback
  (JNIEnv* env, jobject thisObject, jlong delayLine_ptr, jfloat fb) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	DelayLine* delayLine = reinterpret_cast<DelayLine*>(delayLine_ptr);
	api->sound->effect->delayline->setFeedback(delayLine, fb);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024DelayLineBridge_addTap
  (JNIEnv* env, jobject thisObject, jlong delayLine_ptr, jint delay) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	DelayLine* delayLine = reinterpret_cast<DelayLine*>(delayLine_ptr);
	DelayLineTap* delayLineTap = api->sound->effect->delayline->addTap(delayLine, delay);
	uintptr_t delayLineTap_ptr = reinterpret_cast<uintptr_t>(delayLineTap);
	return delayLineTap_ptr;
}