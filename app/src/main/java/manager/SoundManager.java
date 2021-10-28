package manager;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

@Singleton
public class SoundManager {
    private Clip background;
    private long clipTime = 0;
    private final AudioLoader loader;

    @Inject
    public SoundManager(AudioLoader loader) {
        this.loader = loader;
        background = getClip(loader.loadAudio("background"));
    }

    private Clip getClip(AudioInputStream stream) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void resumeBackground(){
        background.setMicrosecondPosition(clipTime);
        background.start();
    }

    public void pauseBackground(){
        clipTime = background.getMicrosecondPosition();
        background.stop();
    }

    public void restartBackground() {
        clipTime = 0;
        resumeBackground();
    }

    public void playJump() {
        Clip clip = getClip(loader.loadAudio("jump"));
        clip.start();

    }

    public void playCoin() {
        Clip clip = getClip(loader.loadAudio("coin"));
        clip.start();

    }

    public void playFireball() {
        Clip clip = getClip(loader.loadAudio("fireball"));
        clip.start();

    }

    public void playGameOver() {
        Clip clip = getClip(loader.loadAudio("gameOver"));
        clip.start();

    }

    public void playStomp() {
        Clip clip = getClip(loader.loadAudio("stomp"));
        clip.start();

    }

    public void playOneUp() {
        Clip clip = getClip(loader.loadAudio("oneUp"));
        clip.start();

    }

    public void playSuperMushroom() {
        Clip clip = getClip(loader.loadAudio("superMushroom"));
        clip.start();
    }

    public void playMarioDies() {
        Clip clip = getClip(loader.loadAudio("marioDies"));
        clip.start();
    }

    public void playFireFlower() {

    }
}
