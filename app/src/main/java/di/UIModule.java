package di;

import dagger.Module;
import dagger.Provides;

@Module
interface UIModule {
    @Provides
    @Width
    static int width() {
        return 1268;
    }

    @Provides
    @Height
    static int height() {
        return 708;
    }
}
