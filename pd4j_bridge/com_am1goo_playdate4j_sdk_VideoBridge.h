/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_am1goo_playdate4j_sdk_VideoBridge */

#ifndef _Included_com_am1goo_playdate4j_sdk_VideoBridge
#define _Included_com_am1goo_playdate4j_sdk_VideoBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    loadVideo
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_loadVideo
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    freePlayer
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_freePlayer
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    setContext
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_setContext
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    getContext
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_getContext
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    useScreenContext
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_useScreenContext
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    renderFrame
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_renderFrame
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    getError
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_getError
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_VideoBridge
 * Method:    getInfo
 * Signature: (JLcom/am1goo/playdate4j/sdk/VideoBridge/PDVideoInfo;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_VideoBridge_getInfo
  (JNIEnv *, jobject, jlong, jobject);

#ifdef __cplusplus
}
#endif
#endif
