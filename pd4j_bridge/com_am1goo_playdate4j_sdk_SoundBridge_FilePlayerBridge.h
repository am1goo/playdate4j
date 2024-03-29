/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge */

#ifndef _Included_com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
#define _Included_com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    newPlayer
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_newPlayer
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    loadIntoPlayer
 * Signature: (JLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_loadIntoPlayer
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    freePlayer
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_freePlayer
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    pause
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_pause
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    play
 * Signature: (JI)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_play
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    isPlaying
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_isPlaying
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    setBufferLength
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setBufferLength
  (JNIEnv *, jobject, jlong, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    getLength
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_getLength
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    didUnderrun
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_didUnderrun
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    setLoopRange
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setLoopRange
  (JNIEnv *, jobject, jlong, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    setOffset
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setOffset
  (JNIEnv *, jobject, jlong, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    getOffset
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_getOffset
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    setRate
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setRate
  (JNIEnv *, jobject, jlong, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    getRate
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_getRate
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    setStopOnUnderrun
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setStopOnUnderrun
  (JNIEnv *, jobject, jlong, jboolean);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    setVolume
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_setVolume
  (JNIEnv *, jobject, jlong, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    getVolume
 * Signature: (JLcom/am1goo/playdate4j/sdk/SoundBridge/StereoVolume;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_getVolume
  (JNIEnv *, jobject, jlong, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    stop
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_stop
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_FilePlayerBridge
 * Method:    fadeVolume
 * Signature: (JFFI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024FilePlayerBridge_fadeVolume
  (JNIEnv *, jobject, jlong, jfloat, jfloat, jint);

#ifdef __cplusplus
}
#endif
#endif
