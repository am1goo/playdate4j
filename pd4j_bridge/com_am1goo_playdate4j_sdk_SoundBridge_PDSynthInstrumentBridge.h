/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge */

#ifndef _Included_com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
#define _Included_com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    newInstrument
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_newInstrument
  (JNIEnv *, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    freeInstrument
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_freeInstrument
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    addVoice
 * Signature: (JJFFF)Z
 */
JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_addVoice
  (JNIEnv *, jobject, jlong, jlong, jfloat, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    playNote
 * Signature: (JFFFJ)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_playNote
  (JNIEnv *, jobject, jlong, jfloat, jfloat, jfloat, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    playMIDINote
 * Signature: (JFFFJ)J
 */
JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_playMIDINote
  (JNIEnv *, jobject, jlong, jfloat, jfloat, jfloat, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    noteOff
 * Signature: (JFJ)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_noteOff
  (JNIEnv *, jobject, jlong, jfloat, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    setPitchBend
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setPitchBend
  (JNIEnv *, jobject, jlong, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    setPitchBendRange
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setPitchBendRange
  (JNIEnv *, jobject, jlong, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    setTranspose
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setTranspose
  (JNIEnv *, jobject, jlong, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    allNotesOff
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_allNotesOff
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    setVolume
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setVolume
  (JNIEnv *, jobject, jlong, jfloat, jfloat);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    getVolume
 * Signature: (JLcom/am1goo/playdate4j/sdk/SoundBridge/StereoVolume;)V
 */
JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_getVolume
  (JNIEnv *, jobject, jlong, jobject);

/*
 * Class:     com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge
 * Method:    activeVoiceCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_activeVoiceCount
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif