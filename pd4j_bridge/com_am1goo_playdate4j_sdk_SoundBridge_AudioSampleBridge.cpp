#include "com_am1goo_playdate4j_sdk_SoundBridge_AudioSampleBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024AudioSampleBridge_newSampleBuffer
  (JNIEnv* env, jobject thisObject, jint length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	AudioSample* sample = api->sound->sample->newSampleBuffer(length);
	uintptr_t sample_ptr = reinterpret_cast<uintptr_t>(sample);
	return sample_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024AudioSampleBridge_load
  (JNIEnv* env, jobject thisObject, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	AudioSample* sample = api->sound->sample->load(path);
	env->ReleaseStringUTFChars(path_str, path);
	uintptr_t sample_ptr = reinterpret_cast<uintptr_t>(sample);
	return sample_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024AudioSampleBridge_loadIntoSample
  (JNIEnv* env, jobject thisObject, jlong sample_ptr, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	AudioSample* sample = reinterpret_cast<AudioSample*>(sample_ptr);
	const char* path = env->GetStringUTFChars(path_str, 0);
	api->sound->sample->loadIntoSample(sample, path);
	env->ReleaseStringUTFChars(path_str, path);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024AudioSampleBridge_freeSample
  (JNIEnv* env, jobject thisObject, jlong sample_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	AudioSample* sample = reinterpret_cast<AudioSample*>(sample_ptr);
	return api->sound->sample->freeSample(sample);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024AudioSampleBridge_getLength
  (JNIEnv* env, jobject thisObject, jlong sample_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	AudioSample* sample = reinterpret_cast<AudioSample*>(sample_ptr);
	return api->sound->sample->getLength(sample);
}