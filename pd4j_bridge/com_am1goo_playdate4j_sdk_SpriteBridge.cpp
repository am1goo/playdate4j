#include "com_am1goo_playdate4j_sdk_SpriteBridge.h"
#include "pd4j_api.h"
#include "pd4j_math.h"
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

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setBounds
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat x, jfloat y, jfloat width, jfloat height) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	PDRect bounds;
	bounds.x = x;
	bounds.y = y;
	bounds.width = width;
	bounds.height = height;
	api->sprite->setBounds(sprite, bounds);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getBounds
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jobject pdrect) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	PDRect bounds = api->sprite->getBounds(sprite);
	
	jclass class_pdrect = env->GetObjectClass(pdrect);
	jmethodID class_pdrect_method_set = env->GetMethodID(class_pdrect, "set", "(FFFF)V");
	env->CallVoidMethod(pdrect, class_pdrect_method_set, bounds.x, bounds.y, bounds.width, bounds.height);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_moveTo
(JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat x, jfloat y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->moveTo(sprite, x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_moveBy
(JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat dx, jfloat dy) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->moveBy(sprite, dx, dy);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getPosition
(JNIEnv* env, jobject thisObject, jlong sprite_ptr, jobject pdxy) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	float x;
	float y;
	api->sprite->getPosition(sprite, &x, &y);

	jclass class_pdxy = env->GetObjectClass(pdxy);
	jmethodID class_pdxy_method_set = env->GetMethodID(class_pdxy, "set", "(FF)V");
	env->CallVoidMethod(pdxy, class_pdxy_method_set, x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setCenter
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat x, jfloat y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setCenter(sprite, x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getCenter
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jobject pdxy) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	float x;
	float y;
	api->sprite->getCenter(sprite, &x, &y);

	jclass class_pdxy = env->GetObjectClass(pdxy);
	jmethodID class_pdxy_method_set = env->GetMethodID(class_pdxy, "set", "(FF)V");
	env->CallVoidMethod(pdxy, class_pdxy_method_set, x, y);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setImage
(JNIEnv* env, jobject thisObject, jlong sprite_ptr, jlong bitmap_ptr, jint flip_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDBitmapFlip flip = static_cast<LCDBitmapFlip>(flip_value);
	api->sprite->setImage(sprite, bitmap, flip);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getImage
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmap* bitmap = api->sprite->getImage(sprite);
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setSize
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat width, jfloat height) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setSize(sprite, width, height);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setZIndex
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jshort zIndex) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setZIndex(sprite, zIndex);
}

JNIEXPORT jshort JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getZIndex
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	return api->sprite->getZIndex(sprite);
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

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setStencil
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->sprite->setStencil(sprite, bitmap);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setStencilImage
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jlong bitmap_ptr, jint tile) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->sprite->setStencilImage(sprite, bitmap, tile);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_clearStencil
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->clearStencil(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setClipRect
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jint left, jint right, jint top, jint bottom) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDRect clipRect;
	clipRect.left = left;
	clipRect.right = right;
	clipRect.top = top;
	clipRect.bottom = bottom;
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setClipRect(sprite, clipRect);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_clearClipRect
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->clearClipRect(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setClipRectsInRange
  (JNIEnv* env, jobject thisObject, jint left, jint right, jint top, jint bottom, jint zStart, jint zEnd) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDRect clipRect;
	clipRect.left = left;
	clipRect.right = right;
	clipRect.top = top;
	clipRect.bottom = bottom;
	api->sprite->setClipRectsInRange(clipRect, zStart, zEnd);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_clearClipRectsInRange
  (JNIEnv* env, jobject thisObject, jint zStart, jint zEnd) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->sprite->clearClipRectsInRange(zStart, zEnd);
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

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setOpaque
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jboolean value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setOpaque(sprite, value);
}


JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setAlwaysRedraw
  (JNIEnv* env, jobject thisObject, jboolean value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->sprite->setAlwaysRedraw(value);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_markDirty
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->markDirty(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_addDirtyRect
  (JNIEnv* env, jobject thisObject, jint left, jint right, jint top, jint bottom) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDRect dirtyRect;
	dirtyRect.left = left;
	dirtyRect.right = right;
	dirtyRect.top = top;
	dirtyRect.bottom = bottom;
	api->sprite->addDirtyRect(dirtyRect);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setIgnoresDrawOffset
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jboolean flag) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setIgnoresDrawOffset(sprite, flag);
}
  
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_addSprite
(JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->addSprite(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_removeSprite
(JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->removeSprite(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_removeAllSprites
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->sprite->removeAllSprites();
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getSpriteCount
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->sprite->getSpriteCount();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_drawSprites
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->sprite->drawSprites();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_updateAndDrawSprites
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->sprite->updateAndDrawSprites();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_resetCollisionWorld
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->sprite->resetCollisionWorld();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setCollisionsEnabled
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jboolean flag) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->setCollisionsEnabled(sprite, flag);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_collisionsEnabled
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	return api->sprite->collisionsEnabled(sprite);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_setCollideRect
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat x, jfloat y, jfloat width, jfloat height) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	PDRect collideRect;
	collideRect.x = x;
	collideRect.y = y;
	collideRect.width = width;
	collideRect.height = height;
	api->sprite->setCollideRect(sprite, collideRect);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_getCollideRect
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jobject pdrect) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	PDRect collideRect = api->sprite->getCollideRect(sprite);
	
	jclass class_pdrect = env->GetObjectClass(pdrect);
	jmethodID class_pdrect_method_set = env->GetMethodID(class_pdrect, "set", "(FFFF)V");
	env->CallVoidMethod(pdrect, class_pdrect_method_set, collideRect.x, collideRect.y, collideRect.width, collideRect.height);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_clearCollideRect
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	api->sprite->clearCollideRect(sprite);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_checkCollisions
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat goalX, jfloat goalY, jobjectArray result_array, jint result_length, jobject actual) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	int len;
	float actualX;
	float actualY;
	SpriteCollisionInfo* query = api->sprite->checkCollisions(sprite, goalX, goalY, &actualX, &actualY, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		SpriteCollisionInfo q = query[i];
		LCDSprite* sprite = q.sprite;
		uintptr_t sprite_ptr = reinterpret_cast<uintptr_t>(sprite);
		LCDSprite* other = q.other;
		uintptr_t other_ptr = reinterpret_cast<uintptr_t>(other);
		int responseType_value = static_cast<int>(q.responseType);
		
		jobject obj = env->GetObjectArrayElement(result_array, i);
		jclass class_obj = env->GetObjectClass(obj);
		jmethodID class_obj_method_set = env->GetMethodID(class_obj, "set", "(JJISFFFIIFF)V");
		env->CallVoidMethod(obj, class_obj_method_set, sprite_ptr, other_ptr, responseType_value, q.overlaps, q.ti, q.move.x, q.move.y, q.normal.x, q.normal.y, q.touch.x, q.touch.y);
	}
	
	jclass class_actual = env->GetObjectClass(actual);
	jmethodID class_actual_method_set = env->GetMethodID(class_actual, "set", "(FF)V");
	env->CallVoidMethod(actual, class_actual_method_set, actualX, actualY);
	
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_moveWithCollisions
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jfloat goalX, jfloat goalY, jobjectArray result_array, jint result_length, jobject actual) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	int len;
	float actualX;
	float actualY;
	SpriteCollisionInfo* query = api->sprite->moveWithCollisions(sprite, goalX, goalY, &actualX, &actualY, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		SpriteCollisionInfo q = query[i];
		LCDSprite* sprite = q.sprite;
		uintptr_t sprite_ptr = reinterpret_cast<uintptr_t>(sprite);
		LCDSprite* other = q.other;
		uintptr_t other_ptr = reinterpret_cast<uintptr_t>(other);
		int responseType_value = static_cast<int>(q.responseType);
		
		jobject obj = env->GetObjectArrayElement(result_array, i);
		jclass class_obj = env->GetObjectClass(obj);
		jmethodID class_obj_method_set = env->GetMethodID(class_obj, "set", "(JJISFFFIIFF)V");
		env->CallVoidMethod(obj, class_obj_method_set, sprite_ptr, other_ptr, responseType_value, q.overlaps, q.ti, q.move.x, q.move.y, q.normal.x, q.normal.y, q.touch.x, q.touch.y);
	}
	
	jclass class_actual = env->GetObjectClass(actual);
	jmethodID class_actual_method_set = env->GetMethodID(class_actual, "set", "(FF)V");
	env->CallVoidMethod(actual, class_actual_method_set, actualX, actualY);
	
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_querySpritesAtPoint
  (JNIEnv* env, jobject thisObject, jfloat x, jfloat y, jlongArray result_array, jint result_length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	int len;
	LCDSprite** query = api->sprite->querySpritesAtPoint(x, y, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		LCDSprite* found = query[i];
		uintptr_t found_ptr = reinterpret_cast<uintptr_t>(found);
		jlong found_as_long = found_ptr;
		env->SetLongArrayRegion(result_array, i, 1, &found_as_long);
	}
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_querySpritesInRect
  (JNIEnv* env, jobject thisObject, jfloat x, jfloat y, jfloat width, jfloat height, jlongArray result_array, jint result_length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	int len;
	LCDSprite** query = api->sprite->querySpritesInRect(x, y, width, height, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		LCDSprite* found = query[i];
		uintptr_t found_ptr = reinterpret_cast<uintptr_t>(found);
		jlong found_as_long = found_ptr;
		env->SetLongArrayRegion(result_array, i, 1, &found_as_long);
	}
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_querySpritesAlongLine
  (JNIEnv* env, jobject thisObject, jfloat x1, jfloat y1, jfloat x2, jfloat y2, jlongArray result_array, jint result_length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	int len;
	LCDSprite** query = api->sprite->querySpritesAlongLine(x1, y1, x2, y2, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		LCDSprite* found = query[i];
		uintptr_t found_ptr = reinterpret_cast<uintptr_t>(found);
		jlong found_as_long = found_ptr;
		env->SetLongArrayRegion(result_array, i, 1, &found_as_long);
	}
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_overlappingSprites
  (JNIEnv* env, jobject thisObject, jlong sprite_ptr, jlongArray result_array, jint result_length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSprite* sprite = reinterpret_cast<LCDSprite*>(sprite_ptr);
	int len;
	LCDSprite** query = api->sprite->overlappingSprites(sprite, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		LCDSprite* found = query[i];
		uintptr_t found_ptr = reinterpret_cast<uintptr_t>(found);
		jlong found_as_long = found_ptr;
		env->SetLongArrayRegion(result_array, i, 1, &found_as_long);
	}
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_allOverlappingSprites
  (JNIEnv* env, jobject thisObject, jlongArray result_array, jint result_length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	int len;
	LCDSprite** query = api->sprite->allOverlappingSprites(&len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		LCDSprite* found = query[i];
		uintptr_t found_ptr = reinterpret_cast<uintptr_t>(found);
		jlong found_as_long = found_ptr;
		env->SetLongArrayRegion(result_array, i, 1, &found_as_long);
	}
	delete[] query;
	return min(len, result_length);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SpriteBridge_querySpriteInfoAlongLine
  (JNIEnv* env, jobject thisObject, jfloat x1, jfloat y1, jfloat x2, jfloat y2, jobjectArray result_array, jint result_length) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	int len;
	SpriteQueryInfo* query = api->sprite->querySpriteInfoAlongLine(x1, y1, x2, y2, &len);
	for (int i = 0; i < len; ++i)
	{
		if (i >= result_length)
			break;
		
		SpriteQueryInfo q = query[i];
		
		LCDSprite* sprite = q.sprite;
		uintptr_t sprite_ptr = reinterpret_cast<uintptr_t>(sprite);
		
		jobject obj = env->GetObjectArrayElement(result_array, i);
		
		jclass class_obj = env->GetObjectClass(obj);
		jmethodID class_obj_method_set = env->GetMethodID(class_obj, "set", "(JFFFFFF)V");
		env->CallVoidMethod(obj, class_obj_method_set, sprite_ptr, q.ti1, q.ti2, q.entryPoint.x, q.entryPoint.y, q.exitPoint.x, q.exitPoint.y);
	}
	delete[] query;
	return min(len, result_length);
}