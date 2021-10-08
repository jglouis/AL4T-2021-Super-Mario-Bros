package model;

import model.brick.Brick;
import model.brick.OrdinaryBrick;
import model.enemy.Enemy;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.Prize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Map {

    private double remainingTime;
    private Mario mario;
    private final ArrayList<Brick> bricks = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Brick> groundBricks = new ArrayList<>();
    private final ArrayList<Prize> revealedPrizes = new ArrayList<>();
    private final ArrayList<Brick> revealedBricks = new ArrayList<>();
    private final ArrayList<Fireball> fireballs = new ArrayList<>();
    private EndFlag endPoint;
    private final BufferedImage backgroundImage;
    private String path;


    private Map(double remainingTime, BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.remainingTime = remainingTime;
    }

    public static class Builder {
        private double remainingTime;
        private Mario mario;
        private final ArrayList<Brick> bricks = new ArrayList<>();
        private final ArrayList<Enemy> enemies = new ArrayList<>();
        private final ArrayList<Brick> groundBricks = new ArrayList<>();
        private EndFlag endPoint;
        private BufferedImage backgroundImage;
        private String path;

        public Builder setRemainingTime(double remainingTime) {
            this.remainingTime = remainingTime;
            return this;
        }

        public Builder setBackgroundImage(BufferedImage backgroundImage) {
            this.backgroundImage = backgroundImage;
            return this;
        }

        public Builder addBrick(Brick brick) {
            bricks.add(brick);
            return this;
        }

        public Builder addGroundBrick(Brick brick) {
            groundBricks.add(brick);
            return this;
        }

        public Builder addEnemy(Enemy enemy) {
            enemies.add(enemy);
            return this;
        }

        public Builder setMario(Mario mario) {
            this.mario = mario;
            return this;
        }

        public Builder setEndPoint(EndFlag endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Map build() {
            Map map = new Map(remainingTime, backgroundImage);
            map.setMario(mario);
            map.setEndPoint(endPoint);
            map.setPath(path);
            map.bricks.addAll(bricks);
            map.groundBricks.addAll(groundBricks);
            map.enemies.addAll(enemies);
            return map;
        }
    }


    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Fireball> getFireballs() {
        return fireballs;
    }

    public ArrayList<Prize> getRevealedPrizes() {
        return revealedPrizes;
    }

    public ArrayList<Brick> getAllBricks() {
        ArrayList<Brick> allBricks = new ArrayList<>();

        allBricks.addAll(bricks);
        allBricks.addAll(groundBricks);

        return allBricks;
    }

    public void addBrick(Brick brick) {
        this.bricks.add(brick);
    }

    public void addGroundBrick(Brick brick) {
        this.groundBricks.add(brick);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void drawMap(Graphics2D g2){
        drawBackground(g2);
        drawPrizes(g2);
        drawBricks(g2);
        drawEnemies(g2);
        drawFireballs(g2);
        drawMario(g2);
        endPoint.draw(g2);
    }

    private void drawFireballs(Graphics2D g2) {
        for(Fireball fireball : fireballs){
            fireball.draw(g2);
        }
    }

    private void drawPrizes(Graphics2D g2) {
        for(Prize prize : revealedPrizes){
            if(prize instanceof Coin){
                ((Coin) prize).draw(g2);
            }
            else if(prize instanceof  BoostItem){
                ((BoostItem) prize).draw(g2);
            }
        }
    }

    private void drawBackground(Graphics2D g2){
        g2.drawImage(backgroundImage, 0, 0, null);
    }

    private void drawBricks(Graphics2D g2) {
        for(Brick brick : bricks){
            if(brick != null)
                brick.draw(g2);
        }

        for(Brick brick : groundBricks){
            brick.draw(g2);
        }
    }

    private void drawEnemies(Graphics2D g2) {
        for(Enemy enemy : enemies){
            if(enemy != null)
                enemy.draw(g2);
        }
    }

    private void drawMario(Graphics2D g2) {
        mario.draw(g2);
    }

    public void updateLocations() {
        mario.updateLocation();
        for(Enemy enemy : enemies){
            enemy.updateLocation();
        }

        for(Iterator<Prize> prizeIterator = revealedPrizes.iterator(); prizeIterator.hasNext();){
            Prize prize = prizeIterator.next();
            if(prize instanceof Coin){
                ((Coin) prize).updateLocation();
                if(((Coin) prize).getRevealBoundary() > ((Coin) prize).getY()){
                    prizeIterator.remove();
                }
            }
            else if(prize instanceof BoostItem){
                ((BoostItem) prize).updateLocation();
            }
        }

        for (Fireball fireball: fireballs) {
            fireball.updateLocation();
        }

        for(Iterator<Brick> brickIterator = revealedBricks.iterator(); brickIterator.hasNext();){
            OrdinaryBrick brick = (OrdinaryBrick)brickIterator.next();
            brick.animate();
            if(brick.getFrames() < 0){
                bricks.remove(brick);
                brickIterator.remove();
            }
        }

        endPoint.updateLocation();
    }

    public double getBottomBorder() {
        return 720 - 96;
    }

    public void addRevealedPrize(Prize prize) {
        revealedPrizes.add(prize);
    }

    public void addFireball(Fireball fireball) {
        fireballs.add(fireball);
    }

    public void setEndPoint(EndFlag endPoint) {
        this.endPoint = endPoint;
    }

    public EndFlag getEndPoint() {
        return endPoint;
    }

    public void addRevealedBrick(OrdinaryBrick ordinaryBrick) {
        revealedBricks.add(ordinaryBrick);
    }

    public void removeFireball(Fireball object) {
        fireballs.remove(object);
    }

    public void removeEnemy(Enemy object) {
        enemies.remove(object);
    }

    public void removePrize(Prize object) {
        revealedPrizes.remove(object);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void updateTime(double passed){
        remainingTime = remainingTime - passed;
    }

    public boolean isTimeOver(){
        return remainingTime <= 0;
    }

    public double getRemainingTime() {
        return remainingTime;
    }
}
