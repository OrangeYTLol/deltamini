package main;

import entity.Entity;

public class CollisionHandler {

  GamePanel panel;

  public CollisionHandler(GamePanel panel) {
    this.panel = panel;
  }

  public void checkTile(Entity entity) {
    // Store positions that need to be checked
    int entityUpY = entity.y + entity.hitbox.y;
    int entityDownY = entity.y + entity.hitbox.y + entity.hitbox.height;
    int entityLeftX = entity.x + entity.hitbox.x;
    int entityRightX = entity.x + entity.hitbox.x + entity.hitbox.width;

    int entityUpRow = (entityUpY/panel.tileSize) + 1;
    int entityDownRow = (entityDownY/panel.tileSize) + 1;
    int entityLeftCol = (entityLeftX/panel.tileSize) + 1;
    int entityRightCol = (entityRightX/panel.tileSize) + 1;

    int tileNum1, tileNum2;


    // Check next position for level triggers and collision based on direction
    if (entity.direction.equals("up")) {
      entityUpRow = (entityUpY - entity.speed) / panel.tileSize + 1;
      tileNum1 = panel.tileM.mapTile[entityLeftCol][entityUpRow];
      tileNum2 = panel.tileM.mapTile[entityRightCol][entityUpRow];
      if (panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision) {
        entity.collided = true;
      } else if (panel.tileM.tile[tileNum1].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum1].levelTriggerInfo[0]);
        panel.player1.y = Integer.parseInt(panel.tileM.tile[tileNum1].levelTriggerInfo[2]);
      } else if (panel.tileM.tile[tileNum2].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum2].levelTriggerInfo[0]);
        panel.player1.y = Integer.parseInt(panel.tileM.tile[tileNum2].levelTriggerInfo[2]);
      }
    }
    if (entity.direction.equals("down")) {
      entityDownRow = (entityDownY + entity.speed) / panel.tileSize + 1;
      tileNum1 = panel.tileM.mapTile[entityLeftCol][entityDownRow];
      tileNum2 = panel.tileM.mapTile[entityRightCol][entityDownRow];
      if (panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision) {
        entity.collided = true;
      } else if (panel.tileM.tile[tileNum1].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum1].levelTriggerInfo[0]);
      } else if (panel.tileM.tile[tileNum2].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum2].levelTriggerInfo[0]);
      }
    }
    if (entity.direction.equals("left")) {
      entityLeftCol = (entityLeftX - entity.speed) / panel.tileSize + 1;
      tileNum1 = panel.tileM.mapTile[entityLeftCol][entityUpRow];
      tileNum2 = panel.tileM.mapTile[entityLeftCol][entityDownRow];
      if (panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision) {
        entity.collided = true;
      } else if (panel.tileM.tile[tileNum1].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum1].levelTriggerInfo[0]);
        panel.player1.x = Integer.parseInt(panel.tileM.tile[tileNum1].levelTriggerInfo[1]);
      } else if (panel.tileM.tile[tileNum2].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum2].levelTriggerInfo[0]);
        panel.player1.x = Integer.parseInt(panel.tileM.tile[tileNum2].levelTriggerInfo[1]);
      }
    }
    if (entity.direction.equals("right")) {
      entityRightCol = (entityRightX + entity.speed) / panel.tileSize + 1;
      tileNum1 = panel.tileM.mapTile[entityRightCol][entityUpRow];
      tileNum2 = panel.tileM.mapTile[entityRightCol][entityDownRow];
      if (panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision) {
        entity.collided = true;
      } else if (panel.tileM.tile[tileNum1].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum1].levelTriggerInfo[0]);
        panel.player1.x = Integer.parseInt(panel.tileM.tile[tileNum1].levelTriggerInfo[1]);
      } else if (panel.tileM.tile[tileNum2].levelTriggerInfo[0] != null) {
        panel.tileM.loadMap(panel.tileM.tile[tileNum2].levelTriggerInfo[0]);
        panel.player1.x = Integer.parseInt(panel.tileM.tile[tileNum2].levelTriggerInfo[1]);
      }
    }
  }
}
