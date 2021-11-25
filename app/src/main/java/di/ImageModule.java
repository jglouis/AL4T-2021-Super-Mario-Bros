package di;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import view.IImageLoader;
import view.ImageLoader;

import java.awt.image.BufferedImage;

@Module
abstract class ImageModule {
    @Binds
    abstract IImageLoader bindImageLoader(ImageLoader impl);

    @Provides
    @SpriteSheet
    static BufferedImage spriteSheet(IImageLoader imageLoader) {
        return imageLoader.loadImage("/sprite.png");
    }
}
