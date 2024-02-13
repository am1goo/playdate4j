#include "com_am1goo_playdate4j_sdk_VideoBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_loadVideo
  (JNIEnv* env, jobject thisObject, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	LCDVideoPlayer* player = api->graphics->video->loadVideo(path);
	env->ReleaseStringUTFChars(path_str, path);
	uintptr_t player_ptr = reinterpret_cast<uintptr_t>(player);
	return player_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_freePlayer
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	api->graphics->video->freePlayer(player);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_setContext
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	return api->graphics->video->setContext(player, bitmap);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_getContext
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	LCDBitmap* bitmap = api->graphics->video->getContext(player);
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_useScreenContext
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	api->graphics->video->useScreenContext(player);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_renderFrame
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jint n) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	api->graphics->video->renderFrame(player, n);
}

JNIEXPORT jstring JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_getError
  (JNIEnv* env, jobject thisObject, jlong player_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return NULL;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	const char* err = api->graphics->video->getError(player);
	return env->NewStringUTF(err);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_getInfo
  (JNIEnv* env, jobject thisObject, jlong player_ptr, jobject info) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDVideoPlayer* player = reinterpret_cast<LCDVideoPlayer*>(player_ptr);
	int outWidth;
	int outHeight;
	float outFrameRate;
	int outFrameCount;
	int outCurrentFrame;
	api->graphics->video->getInfo(player, &outWidth, &outHeight, &outFrameRate, &outFrameCount, &outCurrentFrame);
	
	jclass class_info = env->GetObjectClass(info);
	jmethodID class_info_method_set = env->GetMethodID(class_info, "set", "(IIFII)V");
	env->CallVoidMethod(info, class_info_method_set, outWidth, outHeight, outFrameRate, outFrameCount, outCurrentFrame);
}