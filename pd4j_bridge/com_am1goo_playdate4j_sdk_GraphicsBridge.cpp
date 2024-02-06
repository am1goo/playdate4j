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
	PlaydateAPI* api = PlaydateHost::getApi();
	if (api == NULL)
		return;
	
	LCDSolidColor color = static_cast<LCDSolidColor>(colorValue);
	api->graphics->clear(color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawMode
  (JNIEnv* env, jobject thisObject, jint modeValue) {
	PlaydateAPI* api = PlaydateHost::getApi();
	if (api == NULL)
		return;

	LCDBitmapDrawMode mode = static_cast<LCDBitmapDrawMode>(modeValue);
	api->graphics->setDrawMode(mode);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawText
  (JNIEnv* env, jobject thisObject, jstring text, jint x, jint y) {
	PlaydateAPI* api = PlaydateHost::getApi();
	if (api == NULL)
		return;

	const char* str = env->GetStringUTFChars(text, 0);
	api->graphics->drawText(str, strlen(str), kASCIIEncoding, x, y);
	env->ReleaseStringUTFChars(text, str);
}