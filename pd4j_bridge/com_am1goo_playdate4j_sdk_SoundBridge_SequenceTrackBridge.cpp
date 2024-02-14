#include "com_am1goo_playdate4j_sdk_SoundBridge_SequenceTrackBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_newTrack
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = api->sound->track->newTrack();
	uintptr_t track_ptr = reinterpret_cast<uintptr_t>(track);
	return track_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_freeTrack
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	api->sound->track->freeTrack(track);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_setInstrument
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jlong instrument_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getInstrument
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	PDSynthInstrument* instrument = api->sound->track->getInstrument(track);
	uintptr_t instrument_ptr = reinterpret_cast<uintptr_t>(instrument);
	return instrument_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_addNoteEvent
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jlong step, jlong length, jfloat note_value, jfloat vel) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	MIDINote note = static_cast<MIDINote>(note_value);
	api->sound->track->addNoteEvent(track, step, length, note, vel);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_removeNoteEvent
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jlong step, jfloat note_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	MIDINote note = static_cast<MIDINote>(note_value);
	api->sound->track->removeNoteEvent(track, step, note);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_clearNotes
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	api->sound->track->clearNotes(track);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getLength
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	return api->sound->track->getLength(track);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getIndexForStep
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jlong step) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return -1;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	return api->sound->track->getIndexForStep(track, step);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getNoteAtIndex
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jint index, jobject note) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	uint32_t outStep;
	uint32_t outLen;
	MIDINote outNote;
	float outVelocity;
	bool ret = api->sound->track->getNoteAtIndex(track, index, &outStep, &outLen, &outNote, &outVelocity);

	jclass class_note = env->GetObjectClass(note);
	jmethodID class_note_method_set = env->GetMethodID(class_note, "set", "(JJFF)V");
	env->CallVoidMethod(note, class_note_method_set, outStep, outLen, outNote, outVelocity);
	return ret;
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getControlSignalCount
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	return api->sound->track->getControlSignalCount(track);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getControlSignal
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jint idx) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	ControlSignal* signal = api->sound->track->getControlSignal(track, idx);
	uintptr_t signal_ptr = reinterpret_cast<uintptr_t>(signal);
	return signal_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getSignalForController
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jint controller, jboolean create) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	ControlSignal* signal = api->sound->track->getSignalForController(track, controller, create);
	uintptr_t signal_ptr = reinterpret_cast<uintptr_t>(signal);
	return signal_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_clearControlEvents
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	api->sound->track->clearControlEvents(track);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_activeVoiceCount
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	return api->sound->track->activeVoiceCount(track);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_getPolyphony
  (JNIEnv* env, jobject thisObject, jlong track_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	return api->sound->track->getPolyphony(track);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SequenceTrackBridge_setMuted
  (JNIEnv* env, jobject thisObject, jlong track_ptr, jboolean muted) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	api->sound->track->setMuted(track, muted);
}