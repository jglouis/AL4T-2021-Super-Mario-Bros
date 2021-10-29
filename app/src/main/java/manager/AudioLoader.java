package manager;

import javax.sound.sampled.AudioInputStream;

public interface AudioLoader {
    AudioInputStream loadAudio(String url);
}
