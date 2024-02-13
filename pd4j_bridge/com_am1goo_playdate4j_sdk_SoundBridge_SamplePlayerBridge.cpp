#include "com_am1goo_playdate4j_sdk_SoundBridge_SamplePlayerBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_newPlayer
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SamplePlayer* player = api->sound->sampleplayer->newPlayer();
	uintptr_t player_ptr = reinterpret_cast<uintptr_t>(player);
	return player_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_freePlayer
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->freePlayer(player);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_getLength
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	return api->sound->sampleplayer->getLength(player);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_isPlaying
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	return api->sound->sampleplayer->isPlaying(player);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_play
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jint repeat, jfloat rate) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	return api->sound->sampleplayer->play(player, repeat, rate);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_stop
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->stop(player);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_setOffset
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jfloat offset) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->setOffset(player, offset);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_getOffset
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	return api->sound->sampleplayer->getOffset(player);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_setRate
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jfloat rate) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->setRate(player, rate);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_getRate
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	return api->sound->sampleplayer->getRate(player);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_setPlayRange
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jint start, jint end) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->setPlayRange(player, start, end);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_setPaused
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jboolean paused) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->setPaused(player, paused);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_setSample
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jlong sample_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	AudioSample* sample = reinterpret_cast<AudioSample*>(sample_ptr);
	api->sound->sampleplayer->setSample(player, sample);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_setVolume
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jfloat lvol, jfloat rvol) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	api->sound->sampleplayer->setVolume(player, lvol, rvol);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SamplePlayerBridge_getVolume
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jobject volume) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SamplePlayer* player = reinterpret_cast<SamplePlayer*>(player_ptr);
	float lvol;
	float rvol;
	api->sound->sampleplayer->getVolume(player, &lvol, &rvol);
	
	jclass class_volume = env->GetObjectClass(volume);
	jmethodID class_volume_method_set = env->GetMethodID(class_volume, "set", "(FF)V");
	env->CallVoidMethod(volume, class_volume_method_set, lvol, rvol);
}