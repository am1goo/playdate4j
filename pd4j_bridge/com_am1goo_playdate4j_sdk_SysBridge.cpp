#include "com_am1goo_playdate4j_sdk_SysBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_logToConsole
  (JNIEnv* env, jobject thisObject, jstring log_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	const char* log = env->GetStringUTFChars(log_str, 0);
	api->system->logToConsole(log);
	env->ReleaseStringUTFChars(log_str, log);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_error
  (JNIEnv* env, jobject thisObject, jstring error_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	const char* error = env->GetStringUTFChars(error_str, 0);
	api->system->error(error);
	env->ReleaseStringUTFChars(error_str, error);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_addMenuItem
  (JNIEnv* env, jobject thisObject, jstring title_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	const char* title = env->GetStringUTFChars(title_str, 0);
	api->system->addMenuItem(title, NULL, NULL);
	env->ReleaseStringUTFChars(title_str, title);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_addCheckmarkMenuItem
  (JNIEnv* env, jobject thisObject, jstring title_str, jboolean value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	const char* title = env->GetStringUTFChars(title_str, 0);
	api->system->addCheckmarkMenuItem(title, value, NULL, NULL);
	env->ReleaseStringUTFChars(title_str, title);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_addOptionsMenuItem
  (JNIEnv* env, jobject thisObject, jstring title_str, jobjectArray options_array, jint optionsCount) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	const char* title = env->GetStringUTFChars(title_str, 0);
	int options_length = env->GetArrayLength(options_array);
	
	const char** options = new const char*[options_length];
	for (int i = 0; i< options_length; i++) {
        jstring text_str = (jstring) (env->GetObjectArrayElement(options_array, i));
        const char* text = env->GetStringUTFChars(text_str, 0);
		options[i] = text;
    }
	
	api->system->addOptionsMenuItem(title, options, optionsCount, NULL, NULL);
	
	env->ReleaseStringUTFChars(title_str, title);
	for (int i = 0; i < options_length; i++) {
		jstring text_str = (jstring) (env->GetObjectArrayElement(options_array, i));
		const char* text = options[i];
		env->ReleaseStringUTFChars(text_str, text);
	}
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_removeMenuItem
  (JNIEnv* env, jobject thisObject, jlong menuItem_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDMenuItem* menuItem = reinterpret_cast<PDMenuItem*>(menuItem_ptr);
	api->system->removeMenuItem(menuItem);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_removeAllMenuItems
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->system->removeAllMenuItems();
}

JNIEXPORT jstring JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getMenuItemTitle
  (JNIEnv* env, jobject thisObject, jlong menuItem_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDMenuItem* menuItem = reinterpret_cast<PDMenuItem*>(menuItem_ptr);
	const char* title_str = api->system->getMenuItemTitle(menuItem);
	return env->NewStringUTF(title_str);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setMenuItemTitle
  (JNIEnv* env, jobject thisObject, jlong menuItem_ptr, jstring title) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDMenuItem* menuItem = reinterpret_cast<PDMenuItem*>(menuItem_ptr);
	const char* title_str = env->GetStringUTFChars(title, 0);
	api->system->setMenuItemTitle(menuItem, title_str);
	env->ReleaseStringUTFChars(title, title_str);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getMenuItemValue
  (JNIEnv* env, jobject thisObject, jlong menuItem_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDMenuItem* menuItem = reinterpret_cast<PDMenuItem*>(menuItem_ptr);
	return api->system->getMenuItemValue(menuItem);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setMenuItemValue
  (JNIEnv* env, jobject thisObject, jlong menuItem_ptr, jint value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDMenuItem* menuItem = reinterpret_cast<PDMenuItem*>(menuItem_ptr);
	api->system->setMenuItemValue(menuItem, value);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_drawFps
  (JNIEnv* env, jobject thisObject, jint x, jint y) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->system->drawFPS(x, y);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getFlipped
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;

	return api->system->getFlipped();
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getReduceFlashing
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;

	return api->system->getReduceFlashing();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setMenuImage
(JNIEnv* env, jobject thisObject, jlong bitmap_ptr, jint x_offset) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	LCDBitmap* bitmap = reinterpret_cast<LCDBitmap*>(bitmap_ptr);
	return api->system->setMenuImage(bitmap, x_offset);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getBatteryPercentage
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getBatteryPercentage();
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getBatteryVoltage
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getBatteryVoltage();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_clearICache
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->system->clearICache();
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getCurrentTimeMilliseconds
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getCurrentTimeMilliseconds();
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getSecondsSinceEpoch
(JNIEnv* env, jobject thisObject, jlong milliseconds) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	unsigned int millis = static_cast<unsigned int>(milliseconds);
	return api->system->getSecondsSinceEpoch(&millis);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_resetElapsedTime
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;

	api->system->resetElapsedTime();
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getElapsedTime
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getElapsedTime();
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getTimezoneOffset
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;

	return api->system->getTimezoneOffset();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_convertEpochToDateTime
  (JNIEnv* env, jobject thisObject, jlong epoch_value, jobject dateTime_object) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	uint32_t epoch = static_cast<uint32_t>(epoch_value);
	PDDateTime dateTime;
	api->system->convertEpochToDateTime(epoch, &dateTime);
	
	jclass class_pddatetime = env->GetObjectClass(dateTime_object);
	jmethodID class_pddatetime_method_set = env->GetMethodID(class_pddatetime, "set", "(ISSSSSS)V");
	env->CallVoidMethod(dateTime_object, class_pddatetime_method_set, dateTime.year, dateTime.month, dateTime.day, dateTime.weekday, dateTime.hour, dateTime.minute, dateTime.second);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_convertDateTimeToEpoch
  (JNIEnv* env, jobject thisObject, jint year, jshort month, jshort day, jshort weekday, jshort hour, jshort minute, jshort second) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDDateTime dateTime;
	dateTime.year = year;
	dateTime.month = month;
	dateTime.day = day;
	dateTime.weekday = weekday;
	dateTime.hour = hour;
	dateTime.minute = minute;
	dateTime.second = second;
	uint32_t epoch = api->system->convertDateTimeToEpoch(&dateTime);
	return static_cast<jlong>(epoch);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_shouldDisplay24HourTime
(JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;

	return api->system->shouldDisplay24HourTime();
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setAutoLockDisabled
  (JNIEnv* env, jobject thisObject, jboolean disabled) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	api->system->setAutoLockDisabled(disabled);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setCrankSoundsDisabled
  (JNIEnv* env, jobject thisObject, jboolean disabled) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	return api->system->setCrankSoundsDisabled(disabled);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getLanguage
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL) {
		PDLanguage language = kPDLanguageUnknown;
		int language_value = static_cast<int>(language);
		return language_value;
	}
	
	PDLanguage language = api->system->getLanguage();
	int language_value = static_cast<int>(language);
	return language_value;
}