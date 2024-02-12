#include "com_am1goo_playdate4j_sdk_SoundBridge.h"
#include "pd4j_api.h"
#include <pd_api.h>

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_newChannel
  (JNIEnv* env, jobject thisObject) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = api->sound->channel->newChannel();
	uintptr_t channel_ptr = reinterpret_cast<uintptr_t>(channel);
	return channel_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_freeChannel
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	api->sound->channel->freeChannel(channel);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_addSource
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jlong source_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	SoundSource* source = reinterpret_cast<SoundSource*>(source_ptr);
	api->sound->channel->addSource(channel, source);
}

JNIEXPORT jboolean JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_removeSource
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jlong source_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return false;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	SoundSource* source = reinterpret_cast<SoundSource*>(source_ptr);
	bool removed = api->sound->channel->removeSource(channel, source);
	return removed;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_addEffect
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jlong effect_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	SoundEffect* effect = reinterpret_cast<SoundEffect*>(effect_ptr);
	api->sound->channel->addEffect(channel, effect);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_removeEffect
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jlong effect_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	SoundEffect* effect = reinterpret_cast<SoundEffect*>(effect_ptr);
	api->sound->channel->removeEffect(channel, effect);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_setVolume
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jfloat volume) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	api->sound->channel->setVolume(channel, volume);
}

JNIEXPORT jfloat JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_getVolume
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	return api->sound->channel->getVolume(channel);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_setVolumeModulator
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	PDSynthSignalValue* mod = reinterpret_cast<PDSynthSignalValue*>(mod_ptr);
	api->sound->channel->setVolumeModulator(channel, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_getVolumeModulator
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	PDSynthSignalValue* mod = api->sound->channel->getVolumeModulator(channel);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_setPan
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jfloat pan) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	api->sound->channel->setPan(channel, pan);
}

JNIEXPORT void JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_setPanModulator
  (JNIEnv* env, jobject thisObject, jlong channel_ptr, jlong mod_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	PDSynthSignalValue* mod = api->sound->channel->getVolumeModulator(channel);
	api->sound->channel->setPanModulator(channel, mod);
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_getPanModulator
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	PDSynthSignalValue* mod = api->sound->channel->getPanModulator(channel);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_getDryLevelSignal
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	PDSynthSignalValue* mod = api->sound->channel->getDryLevelSignal(channel);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}

JNIEXPORT jlong JNICALL Java_com_am1goo_playdate4j_sdk_SoundBridge_00024ChannelBridge_getWetLevelSignal
  (JNIEnv* env, jobject thisObject, jlong channel_ptr) {
	PlaydateAPI* api = pd4j_get_api(env);
	if (api == NULL)
		return 0;
	
	SoundChannel* channel = reinterpret_cast<SoundChannel*>(channel_ptr);
	PDSynthSignalValue* mod = api->sound->channel->getWetLevelSignal(channel);
	uintptr_t mod_ptr = reinterpret_cast<uintptr_t>(mod);
	return mod_ptr;
}