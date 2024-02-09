#include "com_am1goo_playdate4j_sdk_GraphicsBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getLCDColumns
  (JNIEnv* env, jobject thisObject) {
	  return LCD_COLUMNS;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getLCDRows
  (JNIEnv* env, jobject thisObject) {
	  return LCD_ROWS;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getLCDRowSize
  (JNIEnv *, jobject) {
	  return LCD_ROWSIZE;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clear
  (JNIEnv* env, jobject thisObject, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSolidColor color = static_cast<LCDSolidColor>(color_value);
	api->graphics->clear(color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_pushContext
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->pushContext(bitmap);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_popContext
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->graphics->popContext();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setStencil
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->setStencil(bitmap);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setStencilImage
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint tile) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->setStencilImage(bitmap, tile);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawMode
  (JNIEnv* env, jobject thisObject, jint mode_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmapDrawMode mode = static_cast<LCDBitmapDrawMode>(mode_value);
	api->graphics->setDrawMode(mode);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setClipRect
(JNIEnv* env, jobject, jint x, jint y, jint width, jint height) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->graphics->setClipRect(x, y, width, height);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setScreenClipRect
(JNIEnv* env, jobject thisObject, jint x, jint y, jint width, jint height) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->graphics->setScreenClipRect(x, y, width, height);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clearClipRect
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->graphics->clearClipRect();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setLineCapStyle
(JNIEnv* env, jobject thisObject, jint style_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDLineCapStyle style = static_cast<LCDLineCapStyle>(style_value);
	api->graphics->setLineCapStyle(style);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawText
  (JNIEnv* env, jobject thisObject, jstring text, jint x, jint y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	const char* str = env->GetStringUTFChars(text, 0);
	api->graphics->drawText(str, strlen(str), kASCIIEncoding, x, y);
	env->ReleaseStringUTFChars(text, str);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadFont
(JNIEnv* env, jobject thisObject, jstring font_path) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	const char* font_path_str = env->GetStringUTFChars(font_path, 0);
	const char* err;
	LCDFont* font = api->graphics->loadFont(font_path_str, &err);
	if (font == NULL)
		api->system->error("%s:%i Couldn't load font %s: %s", __FILE__, __LINE__, font_path_str, err);
	env->ReleaseStringUTFChars(font_path, font_path_str);
	if (font == NULL)
		return 0;

	uintptr_t font_ptr = reinterpret_cast<uintptr_t>(font);
	return font_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setFont
(JNIEnv* env, jobject thisObject, jlong font_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDFont* font = reinterpret_cast<LCDFont*>(font_ptr);
	api->graphics->setFont(font);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setTextTracking
(JNIEnv* env, jobject thisObject, jint tracking) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->graphics->setTextTracking(tracking);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getTextTracking
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return -1;

	return api->graphics->getTextTracking();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clearBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->clearBitmap(bitmap, color);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_copyBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDBitmap* copy = api->graphics->copyBitmap(bitmap);
	uintptr_t copy_ptr = reinterpret_cast<uintptr_t>(copy);
	return copy_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint x, jint y, jint flip_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDBitmapFlip flip = static_cast<LCDBitmapFlip>(flip_value);
	api->graphics->drawBitmap(bitmap, x, y, flip);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawScaledBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint x, jint y, jfloat xScale, jfloat yScale) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->drawScaledBitmap(bitmap, x, y, xScale, yScale);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawRotatedBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint x, jint y, jfloat degrees, jfloat xCenter, jfloat yCenter, jfloat xScale, jfloat yScale) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->drawRotatedBitmap(bitmap, x, y, degrees, xCenter, yCenter, xScale, yScale);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadBitmap
(JNIEnv* env, jobject thisObject, jstring bitmap_path) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	const char* bitmap_path_str = env->GetStringUTFChars(bitmap_path, 0);
	const char* err;
	LCDBitmap* bitmap = api->graphics->loadBitmap(bitmap_path_str, &err);
	if (bitmap == NULL)
		api->system->error("%s:%i Couldn't load bitmap %s: %s", __FILE__, __LINE__, bitmap_path_str, err);
	env->ReleaseStringUTFChars(bitmap_path, bitmap_path_str);
	if (bitmap == NULL)
		return 0;

	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_freeBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->freeBitmap(bitmap);
}