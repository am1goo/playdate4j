/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_am1goo_playdate4j_sdk_SysBridge */

#ifndef _Included_com_am1goo_playdate4j_sdk_SysBridge
#define _Included_com_am1goo_playdate4j_sdk_SysBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    logToConsole
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_logToConsole
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    error
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_error
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    removeMenuItem
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_removeMenuItem
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    removeAllMenuItems
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_removeAllMenuItems
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getMenuItemTitle
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getMenuItemTitle
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    setMenuItemTitle
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setMenuItemTitle
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getMenuItemValue
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getMenuItemValue
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    setMenuItemValue
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setMenuItemValue
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    drawFps
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_drawFps
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getFlipped
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getFlipped
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getReduceFlashing
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getReduceFlashing
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    setMenuImage
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setMenuImage
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getBatteryPercentage
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getBatteryPercentage
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getBatteryVoltage
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getBatteryVoltage
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    clearICache
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_clearICache
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getCurrentTimeMilliseconds
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getCurrentTimeMilliseconds
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getSecondsSinceEpoch
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getSecondsSinceEpoch
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    resetElapsedTime
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_resetElapsedTime
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getElapsedTime
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getElapsedTime
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getTimezoneOffset
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getTimezoneOffset
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    convertEpochToDateTime
 * Signature: (JLcom/am1goo/playdate4j/sdk/SysBridge/PDDateTime;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_convertEpochToDateTime
  (JNIEnv *, jobject, jlong, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    convertDateTimeToEpoch
 * Signature: (ISSSSSS)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_convertDateTimeToEpoch
  (JNIEnv *, jobject, jint, jshort, jshort, jshort, jshort, jshort, jshort);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    shouldDisplay24HourTime
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_shouldDisplay24HourTime
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    setAutoLockDisabled
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setAutoLockDisabled
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    setCrankSoundsDisabled
 * Signature: (Z)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_setCrankSoundsDisabled
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     com_am1goo_playdate4j_sdk_SysBridge
 * Method:    getLanguage
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SysBridge_getLanguage
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
