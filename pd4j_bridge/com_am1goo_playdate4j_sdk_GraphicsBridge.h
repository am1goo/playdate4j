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
 * Method:    checkMaskCollision
 * Signature: (JIIIJIIIIIII)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_checkMaskCollision
  (JNIEnv *, jobject, jlong, jint, jint, jint, jlong, jint, jint, jint, jint, jint, jint, jint);

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

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    loadIntoBitmap
 * Signature: (Ljava/lang/String;J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_loadIntoBitmap
  (JNIEnv *, jobject, jstring, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    newBitmap
 * Signature: (III)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_newBitmap
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    tileBitmap
 * Signature: (JIIIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_tileBitmap
  (JNIEnv *, jobject, jlong, jint, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    rotatedBitmap
 * Signature: (JFFF)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_rotatedBitmap
  (JNIEnv *, jobject, jlong, jfloat, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setBitmapMask
 * Signature: (JJ)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setBitmapMask
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getBitmapMask
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getBitmapMask
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawText
 * Signature: (Ljava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawText
  (JNIEnv *, jobject, jstring, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getFontHeight
 * Signature: (J)S
 */
JNIEXPORT jshort JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getFontHeight
  (JNIEnv *, jobject, jlong);

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
 * Method:    setTextLeading
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setTextLeading
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawEllipse
 * Signature: (IIIIIFFI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawEllipse
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jfloat, jfloat, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    fillEllipse
 * Signature: (IIIIFFI)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillEllipse
  (JNIEnv *, jobject, jint, jint, jint, jint, jfloat, jfloat, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawLine
 * Signature: (IIIIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawLine
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    drawRect
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_drawRect
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    fillRect
 * Signature: (IIIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillRect
  (JNIEnv *, jobject, jint, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    fillTriangle
 * Signature: (IIIIIII)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_fillTriangle
  (JNIEnv *, jobject, jint, jint, jint, jint, jint, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    clear
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_clear
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setBackgroundColor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setBackgroundColor
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    display
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_display
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getDebugBitmap
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getDebugBitmap
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    getDisplayBufferBitmap
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_getDisplayBufferBitmap
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    copyFrameBufferBitmap
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_copyFrameBufferBitmap
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    markUpdatedRows
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_markUpdatedRows
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_am1goo_playdate4j_sdk_GraphicsBridge
 * Method:    setDrawOffset
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_GraphicsBridge_setDrawOffset
  (JNIEnv *, jobject, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
