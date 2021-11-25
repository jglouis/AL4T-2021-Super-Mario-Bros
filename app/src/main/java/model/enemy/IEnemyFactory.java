package model.enemy;

import model.game.DifficultySettings;

public interface IEnemyFactory {
    Enemy create(EnemyType type, int xLocation, int yLocation, DifficultySettings difficultySettings);
}
