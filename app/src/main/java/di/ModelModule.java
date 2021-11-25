package di;

import dagger.Binds;
import dagger.Module;
import model.enemy.EnemyFactory;
import model.enemy.IEnemyFactory;

@Module
abstract class ModelModule {
    @Binds
    abstract IEnemyFactory bindEnemyFactory(EnemyFactory enemyFactory);
}
