#include "com_am1goo_playdate4j_sdk_SoundBridge_PDSynthInstrumentBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_newInstrument
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynthInstrument* instrument = api->sound->instrument->newInstrument();
	uintptr_t instrument_ptr = reinterpret_cast<uintptr_t>(instrument);
	return instrument_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_freeInstrument
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	api->sound->instrument->freeInstrument(instrument);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_addVoice
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jlong synth_ptr, jfloat rangeStart_value, jfloat rangeEnd_value, jfloat transpose) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	MIDINote rangeStart = static_cast<MIDINote>(rangeStart_value);
	MIDINote rangeEnd = static_cast<MIDINote>(rangeEnd_value);
	return api->sound->instrument->addVoice(instrument, synth, rangeStart, rangeEnd, transpose);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_playNote
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat frequency, jfloat vel, jfloat len, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	PDSynth* synth = api->sound->instrument->playNote(instrument, frequency, vel, len, when);
	uintptr_t synth_ptr = reinterpret_cast<uintptr_t>(synth);
	return synth_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_playMIDINote
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat note_value, jfloat vel, jfloat len, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	MIDINote note = static_cast<MIDINote>(note_value);
	PDSynth* synth = api->sound->instrument->playMIDINote(instrument, note, vel, len, when);
	uintptr_t synth_ptr = reinterpret_cast<uintptr_t>(synth);
	return synth_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_noteOff
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat note_value, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	MIDINote note = static_cast<MIDINote>(note_value);
	api->sound->instrument->noteOff(instrument, note, when);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setPitchBend
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat bend) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	api->sound->instrument->setPitchBend(instrument, bend);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setPitchBendRange
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat halfSteps) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	api->sound->instrument->setPitchBendRange(instrument, halfSteps);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setTranspose
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat halfSteps) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	api->sound->instrument->setTranspose(instrument, halfSteps);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_allNotesOff
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	api->sound->instrument->allNotesOff(instrument, when);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_setVolume
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jfloat lvol, jfloat rvol) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	api->sound->instrument->setVolume(instrument, lvol, rvol);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_getVolume
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr, jobject volume) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	float lvol;
	float rvol;
	api->sound->instrument->getVolume(instrument, &lvol, &rvol);
	
	jclass class_volume = env->GetObjectClass(volume);
	jmethodID class_volume_method_set = env->GetMethodID(class_volume, "set", "(FF)V");
	env->CallVoidMethod(volume, class_volume_method_set, lvol, rvol);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthInstrumentBridge_activeVoiceCount
  (JNIEnv* env, jobject thisObject, jlong instrument_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	  
	PDSynthInstrument* instrument = reinterpret_cast<PDSynthInstrument*>(instrument_ptr);
	return api->sound->instrument->activeVoiceCount(instrument);
}