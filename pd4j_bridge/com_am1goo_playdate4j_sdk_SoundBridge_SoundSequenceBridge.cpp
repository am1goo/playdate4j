#include "com_am1goo_playdate4j_sdk_SoundBridge_SoundSequenceBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_newSequence
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = api->sound->sequence->newSequence();
	uintptr_t seq_ptr = reinterpret_cast<uintptr_t>(seq);
	return seq_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_freeSequence
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->freeSequence(seq);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_loadMIDIFile
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jstring path_str) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	const char* path = env->GetStringUTFChars(path_str, 0);
	int ret = api->sound->sequence->loadMIDIFile(seq, path);
	env->ReleaseStringUTFChars(path_str, path);
	return ret;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_play
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->play(seq, NULL, NULL);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_stop
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->stop(seq);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_isPlaying
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	return api->sound->sequence->isPlaying(seq);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_getTime
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	return api->sound->sequence->getTime(seq);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_setTime
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jlong time) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->setTime(seq, time);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_setLoops
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jint startStep, jint endStep, jint loops) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->setLoops(seq, startStep, endStep, loops);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_getTempo
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	return api->sound->sequence->getTempo(seq);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_setTempo
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jfloat stepsPerSecond) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->setTempo(seq, stepsPerSecond);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_getLength
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	return api->sound->sequence->getLength(seq);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_getTrackCount
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	return api->sound->sequence->getTrackCount(seq);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_addTrack
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	SequenceTrack* track = api->sound->sequence->addTrack(seq);
	uintptr_t track_ptr = reinterpret_cast<uintptr_t>(track);
	return track_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_getTrackAtIndex
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jint idx) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	SequenceTrack* track = api->sound->sequence->getTrackAtIndex(seq, idx);
	uintptr_t track_ptr = reinterpret_cast<uintptr_t>(track);
	return track_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_setTrackAtIndex
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jlong track_ptr, jint idx) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	SequenceTrack* track = reinterpret_cast<SequenceTrack*>(track_ptr);
	api->sound->sequence->setTrackAtIndex(seq, track, idx);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_allNotesOff
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->allNotesOff(seq);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_getCurrentStep
  (JNIEnv* env, jobject thisObject, jlong seq_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return -1;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	return api->sound->sequence->getCurrentStep(seq, NULL);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024SoundSequenceBridge_setCurrentStep
  (JNIEnv* env, jobject thisObject, jlong seq_ptr, jint step, jint timeOffset, jint playNotes) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundSequence* seq = reinterpret_cast<SoundSequence*>(seq_ptr);
	api->sound->sequence->setCurrentStep(seq, step, timeOffset, playNotes);
}