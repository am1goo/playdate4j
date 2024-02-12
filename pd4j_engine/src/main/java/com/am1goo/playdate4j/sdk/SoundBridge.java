package com.am1goo.playdate4j.sdk;

public class SoundBridge {

	private final ChannelBridge channel = new ChannelBridge();
	private final SourceBridge source = new SourceBridge();
	private final AudioSampleBridge audioSample = new AudioSampleBridge();
	private final FilePlayerBridge filePlayer = new FilePlayerBridge();
	
	public ChannelBridge channel() {
		return channel;
	}
	
	public SourceBridge source() {
		return source;
	}
	
	public AudioSampleBridge audioSample() {
		return audioSample;
	}
	
	public FilePlayerBridge filePlayer() {
		return filePlayer;
	}
	
    static {
        Sdk.loadRequiredLibraries();
    }

    /* audio */
	public native int getCurrentTime();
	public native long getDefaultChannel();
	public native boolean addChannel(long channelPtr);
	public native boolean removeChannel(long channelPtr);
	public native void setOutputsActive(int headphone, int speaker);
	
	/* channels */
	public class ChannelBridge {
		
		public native long newChannel();
		public native void freeChannel(long channelPtr);
		public native void addSource(long channelPtr, long sourcePtr);
		public native boolean removeSource(long channelPtr, long sourcePtr);
		public native void addEffect(long channelPtr, long effectPtr);
		public native void removeEffect(long channelPtr, long effectPtr);
		public native void setVolume(long channelPtr, float volume);
		public native float getVolume(long channelPtr);
		public native void setVolumeModulator(long channelPtr, long modPtr);
		public native long getVolumeModulator(long channelPtr);
		public native void setPan(long channelPtr, float pan);
		public native void setPanModulator(long channelPtr, long mod);
		public native long getPanModulator(long channelPtr);
		public native long getDryLevelSignal(long channelPtr);
		public native long getWetLevelSignal(long channelPtr);
	}
	
	/* sound source */
	public class SourceBridge {
		
		public native void setVolume(long sourcePtr, float lvol, float rvol);
		public native void getVolume(long sourcePtr, StereoVolume volume);
		public native boolean isPlaying(long sourcePtr);
	}
	
	/* audio sample */
	public class AudioSampleBridge {
		
		public native long newSampleBuffer(int length);
		public native long load(String path);
		public native void loadIntoSample(long samplePtr, String path);
		public native void freeSample(long samplePtr);
		public native float getLength(long samplePtr);
	}
	
	/* file player */
	public class FilePlayerBridge {
		
		public native long newPlayer();
		public native boolean loadIntoPlayer(long playerPtr, String path); 
		public native void freePlayer(long playerPtr);
		public native void pause(long playerPtr);
		public native boolean play(long playerPtr, int repeat);
		public native boolean isPlaying(long playerPtr);
		public native void setBufferLength(long playerPtr, float bufferLen);
		public native float getLength(long playerPtr);
	}
	
	public static class StereoVolume {
		
		private float lvol;
		private float rvol;
		
		public float lvol() {
			return lvol;
		}
		
		public float rvol() {
			return rvol;
		}
		
		public void set(float lvol, float rvol) {
			this.lvol = lvol;
			this.rvol = rvol; 
		}
	}
}
