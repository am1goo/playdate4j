#include "com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_newPlayer
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	FilePlayer* player = api->sound->fileplayer->newPlayer();
	uintptr_t player_ptr = reinterpret_cast<uintptr_t>(player);
	return player_ptr;
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_loadIntoPlayer
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	const char* path = env->GetStringUTFChars(path_str, 0);
	int ret = api->sound->fileplayer->loadIntoPlayer(player, path);
	env->ReleaseStringUTFChars(path_str, path);
	return ret == 0;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_freePlayer
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	api->sound->fileplayer->freePlayer(player);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_pause
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	api->sound->fileplayer->pause(player);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_play
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jint repeat) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	return api->sound->fileplayer->play(player, repeat);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_isPlaying
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	return api->sound->fileplayer->isPlaying(player);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setBufferLength
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jfloat bufferLen) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	api->sound->fileplayer->setBufferLength(player, bufferLen);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_getLength
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	FilePlayer* player = reinterpret_cast<FilePlayer*>(player_ptr);
	return api->sound->fileplayer->getLength(player);
}