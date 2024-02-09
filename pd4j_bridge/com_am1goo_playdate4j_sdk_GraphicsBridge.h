/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_am1goo_playdate4j_sdk_GraphicsBridge */

#ifndef _Included_com_am1goo_playdate4j_sdk_GraphicsBridge
#define _Included_com_am1goo_playdate4j_sdk_GraphicsBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getLCDColumns
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getLCDColumns
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getLCDRows
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getLCDRows
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getLCDRowSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getLCDRowSize
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    clear
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clear
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    pushContext
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_pushContext
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    popContext
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_popContext
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setStencil
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setStencil
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setStencilImage
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setStencilImage
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setDrawMode
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawMode
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setClipRect
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setClipRect
  (JNIEnv *, jobject, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setScreenClipRect
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setScreenClipRect
  (JNIEnv *, jobject, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    clearClipRect
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clearClipRect
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setLineCapStyle
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setLineCapStyle
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawText
 * Signature: (Ljava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawText
  (JNIEnv *, jobject, jstring, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    loadFont
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadFont
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setFont
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setFont
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setTextTracking
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setTextTracking
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getTextTracking
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getTextTracking
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    clearBitmap
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clearBitmap
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    copyBitmap
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_copyBitmap
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawBitmap
 * Signature: (JIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawBitmap
  (JNIEnv *, jobject, jlong, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawScaledBitmap
 * Signature: (JIIFF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawScaledBitmap
  (JNIEnv *, jobject, jlong, jint, jint, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawRotatedBitmap
 * Signature: (JIIFFFFF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawRotatedBitmap
  (JNIEnv *, jobject, jlong, jint, jint, jfloat, jfloat, jfloat, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    freeBitmap
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_freeBitmap
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    loadBitmap
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadBitmap
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
