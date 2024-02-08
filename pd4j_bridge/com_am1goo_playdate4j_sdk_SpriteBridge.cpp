#include "com_am1goo_playdate4j_sdk_SpriteBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_newSprite
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = api->sprite->newSprite();
	uintptr_t sprite_ptr = reinterpret_cast<uintptr_t>(sprite);
	return sprite_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_copy
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDSprite* copy = api->sprite->copy(sprite);
	uintptr_t copy_ptr = reinterpret_cast<uintptr_t>(copy);
	return copy_ptr;
}
  
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_freeSprite
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr){
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->freeSprite(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setTag
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jint tag_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	uint8_t tag = static_cast<uint8_t>(tag_value);
	api->sprite->setTag(sprite, tag);
}
  
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getTag
(JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	uint8_t tag = api->sprite->getTag(sprite);
	return static_cast<jint>(tag);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setDrawMode
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jint mode_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmapDrawMode mode = static_cast<LCDBitmapDrawMode>(mode_value);
	api->sprite->setDrawMode(sprite, mode);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setImageFlip
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jint flip_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmapFlip flip = static_cast<LCDBitmapFlip>(flip_value);
	api->sprite->setImageFlip(sprite, flip);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getImageFlip
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmapFlip flip = api->sprite->getImageFlip(sprite);
	return static_cast<int>(flip);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setUpdatesEnabled
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jboolean value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setUpdatesEnabled(sprite, value);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_updatesEnabled
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	return api->sprite->updatesEnabled(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setVisible
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jboolean value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setVisible(sprite, value);
}
  
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_isVisible
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	return api->sprite->isVisible(sprite);
}