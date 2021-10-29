package manager;

import org.junit.Assert;
import org.junit.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundManagerTest {

    private static class MockAudioLoader implements AudioLoader {
        private String loaded;

        public String getLoaded() {
            return loaded;
        }

        @Override
        public AudioInputStream loadAudio(String url) {
            try {
                InputStream audioSrc = getClass().getResourceAsStream("/media/audio/fake.wav");
                InputStream bufferedIn = new BufferedInputStream(audioSrc);
                loaded = url;
                return AudioSystem.getAudioInputStream(bufferedIn);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return null;
        }
    }

    @Test
    public void playCoin() {
        MockAudioLoader mock = new MockAudioLoader();
        SoundManager soundManager = new SoundManager(mock);
        soundManager.playCoin();
        Assert.assertEquals("coin", mock.getLoaded());
    }
}