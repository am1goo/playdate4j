package com.am1goo.playdate4j.sdk;

import java.util.ArrayList;
import java.util.List;

public class Sound {

	private static final SoundBridge bridge = new SoundBridge();
	
	private static final List<SoundChannel> channels = new ArrayList<SoundChannel>();
	private static final List<PDSynthSignalValue> modulators = new ArrayList<PDSynthSignalValue>();
	private static final List<AudioSample> audioSamples = new ArrayList<AudioSample>();
	private static final List<FilePlayer> filePlayers = new ArrayList<FilePlayer>();
	
	private static SoundChannel defaultChannel = null;
	
	/* audio */
	public static int getCurrentTime() {
		return bridge.getCurrentTime();
	}
	
	public static SoundChannel getDefaultChannel() {
		if (defaultChannel != null)
			return defaultChannel;
		
		long ptr = bridge.channel().getDefaultChannel();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		defaultChannel = new SoundChannel(pointer);
		channels.add(defaultChannel);
		return defaultChannel;
	}
	
	public static boolean addChannel(SoundChannel channel) {
		if (channel == null)
			return false;

		return bridge.channel().addChannel(channel.getPointer().getValue());
	}
	
	public static boolean removeChannel(SoundChannel channel) {
		if (channel == null)
			return false;

		if (channel.ptr.invalid())
			return false;
		
		boolean removed = bridge.channel().removeChannel(channel.ptr.getValue());
		if (!removed)
			return false;
		
		channel.ptr.invalidate();
		channels.remove(channel);
		return true;
	}
	
	public static void setOutputsActive(int headphone, int speaker) {
		bridge.setOutputsActive(headphone, speaker);
	}
	
	/* channels */
	public static SoundChannel newChannel() {
		long ptr = bridge.channel().newChannel();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		SoundChannel channel = new SoundChannel(pointer);
		channels.add(channel);
		return channel;
	}

	public static SoundChannel freeChannel(SoundChannel channel) {
		if (channel == null)
			return null;

		if (channel.ptr.invalid())
			return null;
		
		bridge.channel().freeChannel(channel.getPointer().getValue());
		channel.ptr.invalidate();
		channels.remove(channel);
		return null;
	}
	
	private static PDSynthSignalValue findModulator(long ptr) {
		for (PDSynthSignalValue mod : modulators) {
			if (mod.ptr.getValue() == ptr) {
				return mod;
			}
		}
		return null;
	}
	
	public static AudioSample newSampleBuffer(int length) {
		long ptr = bridge.audioSample().newSampleBuffer(length);
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		AudioSample sample = new AudioSample(pointer, null);
		audioSamples.add(sample);
		return sample;
	}
	
	public static AudioSample loadSample(String path) {
		long ptr = bridge.audioSample().load(path);
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		AudioSample sample = new AudioSample(pointer, path);
		audioSamples.add(sample);
		return sample;
	}
	
	public static void loadIntoSample(AudioSample sample, String path) {
		if (sample == null)
			return;
		
		bridge.audioSample().loadIntoSample(sample.ptr.getValue(), path);
		sample.path = path;
	}
	
	public static AudioSample freeSample(AudioSample sample) {
		if (sample == null)
			return null;

		if (sample.ptr.invalid())
			return null;
		
		bridge.audioSample().freeSample(sample.ptr.getValue());
		sample.ptr.invalidate();
		audioSamples.remove(sample);
		return null;
	}
	
	public static FilePlayer newPlayer() {
		long ptr = bridge.filePlayer().newPlayer();
		Api.Pointer pointer = new Api.Pointer(ptr);
		if (pointer.invalid())
			return null;
		
		FilePlayer player = new FilePlayer(pointer, null);
		filePlayers.add(player);
		return player;
	}
	
	public static void loadIntoPlayer(FilePlayer player, String path) {
		if (player == null)
			return;
		
		bridge.filePlayer().loadIntoPlayer(player.ptr.getValue(), path);
		player.path = path;
	}
	
	public static FilePlayer freePlayer(FilePlayer player) {
		if (player == null)
			return null;

		if (player.ptr.invalid())
			return null;
		
		bridge.filePlayer().freePlayer(player.ptr.getValue());
		player.ptr.invalidate();
		filePlayers.remove(player);
		return null;
	}
	
	public static class SoundChannel {
		
		private final Api.Pointer ptr;
		
		public SoundChannel(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void free() {
			Sound.freeChannel(this);
		}
		
		public void addSource(SoundSource source) {
			if (source == null)
				return;

			bridge.channel().addSource(ptr.getValue(), source.getPointer().getValue());
		}
		
		public boolean removeSource(SoundSource source) {
			if (source == null)
				return false;

			return bridge.channel().removeSource(ptr.getValue(), source.getPointer().getValue());
		}
		
		public void addEffect(SoundEffect effect) {
			if (effect == null)
				return;

			bridge.channel().addEffect(ptr.getValue(), effect.getPointer().getValue());
		}
		
		public void removeEffect(SoundEffect effect) {
			if (effect == null)
				return;

			bridge.channel().removeEffect(ptr.getValue(), effect.getPointer().getValue());
		}
		
		public void setVolume(float volume) {
			bridge.channel().setVolume(ptr.getValue(), volume);
		}
		
		public float getVolume() {
			return bridge.channel().getVolume(ptr.getValue());
		}
		
		public void setVolumeModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;

			bridge.channel().setVolumeModulator(ptr.getValue(), mod.getPointer().getValue());
		}
		
		public PDSynthSignalValue getVolumeModulator() {
			long modPtr = bridge.channel().getVolumeModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public void setPan(float pan) {
			bridge.channel().setPan(ptr.getValue(), pan);
		}
		
		public void setPanModulator(PDSynthSignalValue mod) {
			if (mod == null)
				return;

			bridge.channel().setPanModulator(ptr.getValue(), mod.getPointer().getValue());
		}
		
		public PDSynthSignalValue getPanModulator() {
			long modPtr = bridge.channel().getPanModulator(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public PDSynthSignalValue getDryLevelSignal() {
			long modPtr = bridge.channel().getDryLevelSignal(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
		
		public PDSynthSignalValue getWetLevelSignal() {
			long modPtr = bridge.channel().getWetLevelSignal(ptr.getValue());
			PDSynthSignalValue found = findModulator(modPtr);
			if (found != null)
				return found;
			
			Api.Pointer modPointer = new Api.Pointer(modPtr);
			if (modPointer.invalid())
				return null;
			
			PDSynthSignalValue mod = new PDSynthSignalValue(modPointer);
			modulators.add(mod);
			return mod;
		}
	}
	
	public static class SoundSource {
		
		private final Api.Pointer ptr;
		
		private final SoundBridge.StereoVolume volume = new SoundBridge.StereoVolume();
		
		public SoundSource(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public void setVolume(float lvol, float rvol) {
			bridge.source().setVolume(ptr.getValue(), lvol, rvol);
		}
		
		public float getVolume(boolean left) {
			bridge.source().getVolume(ptr.getValue(), volume);
			if (left)
				return volume.lvol();
			else
				return volume.rvol();
		}
		
		public boolean isPlaying() {
			return bridge.source().isPlaying(ptr.getValue());
		}
	}
	
	public static class SoundEffect {
		
		private final Api.Pointer ptr;
		
		public SoundEffect(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
	}
	
	public static class PDSynthSignalValue {
		
		private final Api.Pointer ptr;
		
		public PDSynthSignalValue(Api.Pointer ptr) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
	}
	
	public static class AudioSample {
		
		private final Api.Pointer ptr;
		private String path;
		
		public AudioSample(Api.Pointer ptr, String path) {
			this.ptr = ptr;
			this.path = path;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public String getPath() {
			return path;
		}
		
		public float getLength() {
			return bridge.audioSample().getLength(ptr.getValue());
		}
		
		public void free() {
			Sound.freeSample(this);
		}
	}
	
	public static class FilePlayer {
		
		private final Api.Pointer ptr;
		private String path;
		
		public FilePlayer(Api.Pointer ptr, String path) {
			this.ptr = ptr;
		}
		
		public Api.Pointer getPointer() {
			return ptr;
		}
		
		public String getPath() {
			return path;
		}
		
		public void free() {
			Sound.freePlayer(this);
		}
		
		public void pause() {
			bridge.filePlayer().pause(ptr.getValue());
		}
		
		public boolean play(int repeat) {
			return bridge.filePlayer().play(ptr.getValue(), repeat);
		}
		
		public boolean isPlaying() {
			return bridge.filePlayer().isPlaying(ptr.getValue());
		}
		
		public void setBufferLength(float bufferLen) {
			bridge.filePlayer().setBufferLength(ptr.getValue(), bufferLen);
		}
		
		public float getLength() {
			return bridge.filePlayer().getLength(ptr.getValue());
		}
	}
}
