package di;

import dagger.Binds;
import dagger.Module;
import manager.AudioLoader;
import manager.AudioLoaderImpl;


@Module
abstract class AudioModule {
    @Binds
    abstract AudioLoader audioLoader(AudioLoaderImpl impl);
}
