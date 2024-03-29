/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_am1goo_playdate4j_sdk_InputBridge */

#ifndef _Included_com_am1goo_playdate4j_sdk_InputBridge
#define _Included_com_am1goo_playdate4j_sdk_InputBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_am1goo_playdate4j_sdk_InputBridge
 * Method:    setPeripheralsEnabled
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_setPeripheralsEnabled
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_InputBridge
 * Method:    getAccelerometer
 * Signature: (Lcom/am1goo/playdate4j/sdk/InputBridge/AccelerometerBridge;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getAccelerometer
  (JNIEnv *, jobject, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_InputBridge
 * Method:    getButtonState
 * Signature: (Lcom/am1goo/playdate4j/sdk/InputBridge/ButtonStateBridge;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getButtonState
  (JNIEnv *, jobject, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_InputBridge
 * Method:    getCrankAngle
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getCrankAngle
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_InputBridge
 * Method:    getCrankChange
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_getCrankChange
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_InputBridge
 * Method:    isCrankDocked
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_InputBridge_isCrankDocked
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
