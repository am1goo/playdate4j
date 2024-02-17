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

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_checkMaskCollision
  (JNIEnv* env, jobject thisObject, jlong bitmap_ptr_1, jint x1, jint y1, jint flip_value_1, jlong bitmap_ptr_2, jint x2, jint y2, jint flip_value_2, jint rect_left, jint rect_right, jint rect_top, jint rect_bottom) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	LCDBitmap* bitmap_1 = reinterpret_cast<LCDBitmap*>(bitmap_ptr_1);
	LCDBitmapFlip flip_1 = static_cast<LCDBitmapFlip>(flip_value_1);
	LCDBitmap* bitmap_2 = reinterpret_cast<LCDBitmap*>(bitmap_ptr_2);
	LCDBitmapFlip flip_2 = static_cast<LCDBitmapFlip>(flip_value_2);
	LCDRect rect;
	rect.left = rect_left;
	rect.right = rect_right;
	rect.top = rect_top;
	rect.bottom = rect_bottom;
	return api->graphics->checkMaskCollision(bitmap_1, x1, y1, flip_1, bitmap_2, x2, y2, flip_2, rect);
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

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_freeBitmap
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	api->graphics->freeBitmap(bitmap);
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

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadIntoBitmap
  (JNIEnv* env, jobject thisObject, jstring bitmap_path, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	const char* bitmap_path_str = env->GetStringUTFChars(bitmap_path, 0);
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	const char* err;
	api->graphics->loadIntoBitmap(bitmap_path_str, bitmap, &err);
	if (err != NULL) {
		api->system->error("%s:%i Couldn't load into bitmap %s: %s", __FILE__, __LINE__, bitmap_path_str, err);
	}
	env->ReleaseStringUTFChars(bitmap_path, bitmap_path_str);
	return err == NULL;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_newBitmap
  (JNIEnv* env, jobject thisObject, jint width, jint height, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDSolidColor color = static_cast<LCDSolidColor>(color_value);
	LCDBitmap* bitmap = api->graphics->newBitmap(width, height, color);
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_tileBitmap
  (JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint x, jint y, jint width, jint height, jint flip_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDBitmapFlip flip = static_cast<LCDBitmapFlip>(flip_value);
	api->graphics->tileBitmap(bitmap, x, y, width, height, flip);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_rotatedBitmap
  (JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jfloat rotation, jfloat xScale, jfloat yScale) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	int allocedSize;
	LCDBitmap* rotated = api->graphics->rotatedBitmap(bitmap, rotation, xScale, yScale, &allocedSize);
	uintptr_t rotated_ptr = reinterpret_cast<uintptr_t>(rotated);
	return rotated_ptr;
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setBitmapMask
  (JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jlong mask_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDBitmap* mask = reinterpret_cast<LCDBitmap*>(mask_ptr);
	return api->graphics->setBitmapMask(bitmap, mask);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getBitmapMask
  (JNIEnv* env, jobject thisObject, jlong bitmap_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	LCDBitmap* mask = api->graphics->getBitmapMask(bitmap);
	uintptr_t rotated_ptr = reinterpret_cast<uintptr_t>(mask);
	return rotated_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getBitmapData
  (JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jobject result) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	int width;
	int height;
	int rowbytes;
	uint8_t* mask;
	uint8_t* data;
	api->graphics->getBitmapData(bitmap, &width, &height, &rowbytes, &mask, &data);
	
	jclass class_result = env->GetObjectClass(result);
	jmethodID class_result_method_set = env->GetMethodID(class_result, "set", "(III)V");
	env->CallVoidMethod(result, class_result_method_set, width, height, rowbytes);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_newBitmapTable
  (JNIEnv* env, jobject thisObject, jint count, jint width, jint height) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmapTable* table = api->graphics->newBitmapTable(count, width, height);
	uintptr_t table_ptr = reinterpret_cast<uintptr_t>(table);
	return table_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_freeBitmapTable
  (JNIEnv* env, jobject thisObject, jlong table_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDBitmapTable* table = reinterpret_cast<LCDBitmapTable*>(table_ptr);
	api->graphics->freeBitmapTable(table);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getTableBitmap
  (JNIEnv* env, jobject thisObject, jlong table_ptr, jint idx) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmapTable* table = reinterpret_cast<LCDBitmapTable*>(table_ptr);
	LCDBitmap* bitmap = api->graphics->getTableBitmap(table, idx);
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadBitmapTable
  (JNIEnv* env, jobject thisObject, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	const char* outerr;
	LCDBitmapTable* table = api->graphics->loadBitmapTable(path, &outerr);
	env->ReleaseStringUTFChars(path_str, path);
	if (table == NULL) {
		api->system->error(outerr);
		return 0;
	}
	
	uintptr_t table_ptr = reinterpret_cast<uintptr_t>(table);
	return table_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadIntoBitmapTable
  (JNIEnv* env, jobject thisObject, jstring path_str, jlong table_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	const char* path = env->GetStringUTFChars(path_str, 0);
	LCDBitmapTable* table = reinterpret_cast<LCDBitmapTable*>(table_ptr);
	const char* outerr;
	api->graphics->loadIntoBitmapTable(path, table, &outerr);
	env->ReleaseStringUTFChars(path_str, path);
	if (outerr != NULL) {
		api->system->error(outerr);
		return;
	}
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
		return 0;

	return api->graphics->getTextTracking();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setTextLeading
  (JNIEnv* env, jobject thisObject, jint leading) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->graphics->setTextLeading(leading);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawText
  (JNIEnv* env, jobject thisObject, jstring text_str, jint len, jint encoding_value, jint x, jint y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	const char* text = env->GetStringUTFChars(text_str, 0);
	PDStringEncoding encoding = static_cast<PDStringEncoding>(encoding_value);
	api->graphics->drawText(text, len, encoding, x, y);
	env->ReleaseStringUTFChars(text_str, text);
}

JNIEXPORT jshort JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getFontHeight
  (JNIEnv* env, jobject thisObject, jlong font_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDFont* font = reinterpret_cast<LCDFont*>(font_ptr);
	return api->graphics->getFontHeight(font);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getFontPage
  (JNIEnv* env, jobject thisObject, jlong font_ptr, jlong c) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDFont* font = reinterpret_cast<LCDFont*>(font_ptr);
	LCDFontPage* page = api->graphics->getFontPage(font, c);
	uintptr_t page_ptr = reinterpret_cast<uintptr_t>(page);
	return page_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getPageGlyph
  (JNIEnv* env, jobject thisObject, jlong page_ptr, jlong c) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDFontPage* page = reinterpret_cast<LCDFontPage*>(page_ptr);
	int advance;
	LCDFontGlyph* glyph = api->graphics->getPageGlyph(page, c, NULL, &advance);
	uintptr_t glyph_ptr = reinterpret_cast<uintptr_t>(glyph);
	return glyph_ptr;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getGlyphKerning
  (JNIEnv* env, jobject thisObject, jlong glyph_ptr, jlong c1, jlong c2) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDFontGlyph* glyph = reinterpret_cast<LCDFontGlyph*>(glyph_ptr);
	return api->graphics->getGlyphKerning(glyph, c1, c2);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getTextWidth
  (JNIEnv* env, jobject thisObject, jlong font_ptr, jstring text_str, jint len, jint encoding_value, jint tracking) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDFont* font = reinterpret_cast<LCDFont*>(font_ptr);
	const char* text = env->GetStringUTFChars(text_str, 0);
	PDStringEncoding encoding = static_cast<PDStringEncoding>(encoding_value);
	int width = api->graphics->getTextWidth(font, text, len, encoding, tracking);
	env->ReleaseStringUTFChars(text_str, text);
	return width;
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

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawEllipse
  (JNIEnv* env, jobject thisObject, jint x, jint y, jint width, jint height, jint lineWidth, jfloat startAngle, jfloat endAngle, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->drawEllipse(x, y, width, height, lineWidth, startAngle, endAngle, color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillEllipse
  (JNIEnv* env, jobject thisObject, jint x, jint y, jint width, jint height, jfloat startAngle, jfloat endAngle, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->fillEllipse(x, y, width, height, startAngle, endAngle, color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawLine
  (JNIEnv* env, jobject thisObject, jint x1, jint y1, jint x2, jint y2, jint width, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->drawLine(x1, y1, x2, y2, width, color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawRect
  (JNIEnv* env, jobject thisObject, jint x, jint y, jint width, jint height, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->drawRect(x, y, width, height, color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillRect
  (JNIEnv* env, jobject thisObject, jint x, jint y, jint width, jint height, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->fillRect(x, y, width, height, color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillTriangle
  (JNIEnv* env, jobject thisObject, jint x1, jint y1, jint x2, jint y2, jint x3, jint y3, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDColor color = static_cast<LCDColor>(color_value);
	api->graphics->fillTriangle(x1, y1, x2, y2, x3, y3, color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillPolygon
  (JNIEnv* env, jobject thisObject, jint nPoints, jintArray points_array, jint color_value, jint fillrule_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	jint* points = env->GetIntArrayElements(points_array, 0);
	LCDSolidColor color = static_cast<LCDSolidColor>(color_value);
	LCDPolygonFillRule fillrule = static_cast<LCDPolygonFillRule>(fillrule_value);
	api->graphics->fillPolygon(nPoints, (int*)points, color, fillrule);
	env->ReleaseIntArrayElements(points_array, points, 0);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clear
  (JNIEnv* env, jobject thisObject, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSolidColor color = static_cast<LCDSolidColor>(color_value);
	api->graphics->clear(color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setBackgroundColor
  (JNIEnv* env, jobject thisObject, jint color_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	LCDSolidColor color = static_cast<LCDSolidColor>(color_value);
	api->graphics->setBackgroundColor(color);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_display
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->graphics->display();
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getDebugBitmap
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmap* bitmap = api->graphics->getDebugBitmap();
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getDisplayBufferBitmap
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmap* bitmap = api->graphics->getDisplayBufferBitmap();
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_copyFrameBufferBitmap
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	LCDBitmap* bitmap = api->graphics->copyFrameBufferBitmap();
	uintptr_t bitmap_ptr = reinterpret_cast<uintptr_t>(bitmap);
	return bitmap_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_markUpdatedRows
  (JNIEnv* env, jobject thisObject, jint start, jint end) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->graphics->markUpdatedRows(start, end);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawOffset
  (JNIEnv* env, jobject thisObject, jint dx, jint dy) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->graphics->setDrawOffset(dx, dy);
}