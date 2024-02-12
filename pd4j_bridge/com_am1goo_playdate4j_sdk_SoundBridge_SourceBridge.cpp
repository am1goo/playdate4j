#include "com_am1goo_playdate4j_sdk_SoundBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SourceBridge_setVolume
  (JNIEnv* env, jobject thisObject, jlong source_ptr, jfloat lvol, jfloat rvol) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSource* source = reinterpret_cast<SoundSource*>(source_ptr);
	api->sound->source->setVolume(source, lvol, rvol);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SourceBridge_getVolume
  (JNIEnv* env, jobject thisObject, jlong source_ptr, jobject volume) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSource* source = reinterpret_cast<SoundSource*>(source_ptr);
	float lvol;
	float rvol;
	api->sound->source->getVolume(source, &lvol, &rvol);
	
	jclass class_volume = env->GetObjectClass(volume);
	jmethodID class_volume_method_set = env->GetMethodID(class_volume, "set", "(FF)V");
	env->CallVoidMethod(volume, class_volume_method_set, lvol, rvol);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SourceBridge_isPlaying
  (JNIEnv* env, jobject thisObject, jlong source_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SoundSource* source = reinterpret_cast<SoundSource*>(source_ptr);
	return api->sound->source->isPlaying(source);
}