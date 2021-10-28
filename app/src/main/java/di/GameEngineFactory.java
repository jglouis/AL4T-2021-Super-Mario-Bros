package di;

import dagger.Component;
import manager.GameEngine;

import javax.inject.Singleton;

@Singleton
@Component(modules = {UIModule.class})
public interface GameEngineFactory {
    GameEngine gameEngine();
}
