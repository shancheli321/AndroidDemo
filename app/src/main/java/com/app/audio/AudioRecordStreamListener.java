package com.app.audio;

/**
 * 获取录音的音频流,用于拓展的处理
 * @author chenmy0709
 * @version V001R001C01B001
 */
public interface AudioRecordStreamListener {
    void recordOfByte(byte[] data, int begin, int end);
}
