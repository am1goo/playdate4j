#include "com_am1goo_playdate4j_sdk_SoundBridge_OverdriveBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_newOverdrive
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	Overdrive* overdrive = api->sound->effect->overdrive->newOverdrive();
	uintptr_t overdrive_ptr = reinterpret_cast<uintptr_t>(overdrive);
	return overdrive_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_freeOverdrive
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	api->sound->effect->overdrive->freeOverdrive(overdrive);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_setGain
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr, jfloat gain) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	api->sound->effect->overdrive->setGain(overdrive, gain);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_setLimit
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr, jfloat limit) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	api->sound->effect->overdrive->setLimit(overdrive, limit);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_setLimitModulator
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->overdrive->setLimitModulator(overdrive, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_getLimitModulator
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	PDSynthSignalValue* mod = api->sound->effect->overdrive->getLimitModulator(overdrive);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_setOffset
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr, jfloat offset) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	api->sound->effect->overdrive->setOffset(overdrive, offset);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_setOffsetModulator
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->effect->overdrive->setOffsetModulator(overdrive, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024OverdriveBridge_getOffsetModulator
  (JNIEnv* env, jobject thisObject, jlong overdrive_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	Overdrive* overdrive = reinterpret_cast<Overdrive*>(overdrive_ptr);
	PDSynthSignalValue* mod = api->sound->effect->overdrive->getOffsetModulator(overdrive);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}