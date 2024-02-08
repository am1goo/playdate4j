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
  (JNIEnv* env, jobject thisObject, jint colorValue) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSolidColor color = static_cast<LCDSolidColor>(colorValue);
	api->graphics->clear(color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawMode
  (JNIEnv* env, jobject thisObject, jint modeValue) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmapDrawMode mode = static_cast<LCDBitmapDrawMode>(modeValue);
	api->graphics->setDrawMode(mode);
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