#include "com_am1goo_playdate4j_sdk_SoundBridge_PDSynthBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_newSynth
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynth* synth = api->sound->synth->newSynth();
	uintptr_t synth_ptr = reinterpret_cast<uintptr_t>(synth);
	return synth_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_freeSynth
  (JNIEnv* env, jobject thisObject, jlong synth_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->freeSynth(synth);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setWaveform
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jint wave_value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	SoundWaveform wave = static_cast<SoundWaveform>(wave_value);
	api->sound->synth->setWaveform(synth, wave);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setSample
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jlong sample_ptr, jlong sustainStart, jlong sustainEnd) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	  
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	AudioSample* sample = reinterpret_cast<AudioSample*>(sample_ptr);
	api->sound->synth->setSample(synth, sample, sustainStart, sustainEnd);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setWavetable
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jlong sample_ptr, jint log2size, jint columns, jint rows) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	  
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	AudioSample* sample = reinterpret_cast<AudioSample*>(sample_ptr);
	int ret = api->sound->synth->setWavetable(synth, sample, log2size, columns, rows);
	return ret == 1;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setAttackTime
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat attack) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->setAttackTime(synth, attack);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setDecayTime
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat decay) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->setDecayTime(synth, decay);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setSustainLevel
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat sustain) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->setSustainLevel(synth, sustain);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setReleaseTime
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat release) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->setReleaseTime(synth, release);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_getEnvelope
  (JNIEnv* env, jobject thisObject, jlong synth_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthEnvelope* envelope = api->sound->synth->getEnvelope(synth);
	uintptr_t envelope_ptr = reinterpret_cast<uintptr_t>(envelope);
	return envelope_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setTranspose
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat halfSteps) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->setTranspose(synth, halfSteps);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setFrequencyModulator
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->synth->setFrequencyModulator(synth, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_getFrequencyModulator
  (JNIEnv* env, jobject thisObject, jlong synth_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthSignalValue* mod = api->sound->synth->getFrequencyModulator(synth);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setAmplitudeModulator
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->synth->setAmplitudeModulator(synth, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_getAmplitudeModulator
  (JNIEnv* env, jobject thisObject, jlong synth_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthSignalValue* mod = api->sound->synth->getAmplitudeModulator(synth);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_playNote
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat freq, jfloat vel, jfloat len, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->playNote(synth, freq, vel, len, when);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_playMIDINote
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat note_value, jfloat vel, jfloat len, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	MIDINote note = static_cast<MIDINote>(note_value);
	api->sound->synth->playMIDINote(synth, note, vel, len, when);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_noteOff
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jlong when) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->noteOff(synth, when);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setVolume
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jfloat lvol, jfloat rvol) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	api->sound->synth->setVolume(synth, lvol, rvol);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_getVolume
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jobject volume) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	float lvol;
	float rvol;
	api->sound->synth->getVolume(synth, &lvol, &rvol);
	
	jclass class_volume = env->GetObjectClass(volume);
	jmethodID class_volume_method_set = env->GetMethodID(class_volume, "set", "(FF)V");
	env->CallVoidMethod(volume, class_volume_method_set, lvol, rvol);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_isPlaying
  (JNIEnv* env, jobject thisObject, jlong synth_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	return api->sound->synth->isPlaying(synth);
}

JNIEXPORT jint JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_getParameterCount
  (JNIEnv* env, jobject thisObject, jlong synth_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	return api->sound->synth->getParameterCount(synth);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setParameter
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jint num, jfloat value) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	return api->sound->synth->setParameter(synth, num, value);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_setParameterModulator
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jint num, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->synth->setParameterModulator(synth, num, mod);
	return true;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024PDSynthBridge_getParameterModulator
  (JNIEnv* env, jobject thisObject, jlong synth_ptr, jint num) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	PDSynth* synth = reinterpret_cast<PDSynth*>(synth_ptr);
	PDSynthSignalValue* mod = api->sound->synth->getParameterModulator(synth, num);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}