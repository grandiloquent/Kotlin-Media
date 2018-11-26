package euphoria.psycho.common

import android.content.Context
import android.media.AudioManager

fun AudioManager.getMusicMaxVolume(): Int {
    // Used to identify the volume of audio streams for music playback
    return getStreamMaxVolume(AudioManager.STREAM_MUSIC)
}


fun AudioManager.getMusicVolume(): Int {
    // Used to identify the volume of audio streams for music playback
    return getStreamVolume(AudioManager.STREAM_MUSIC)
}

fun AudioManager.setMusicVolume(volume: Int) {
    setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
    if (getStreamVolume(AudioManager.STREAM_MUSIC) != volume) {
        setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_SHOW_UI)
    }
}