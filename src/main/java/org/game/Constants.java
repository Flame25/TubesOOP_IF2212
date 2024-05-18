package org.game;

public class Constants {
  public static final int ANI_SPEED = 15;

  public static class Directions {
    public static final int LEFT = 2;
    public static final int UP = 4;
    public static final int RIGHT = 6;
    public static final int DOWN = 0;
  }

  public static class PlayerConstants {
    public static final int IDLE = 0;
    public static final int RUNNING = 1;

    public static int GetSpriteAmount(int player_action) {
      switch (player_action) {
        case RUNNING:
          return 4;
        case IDLE:
          return 2;
        default:
          return 1;
      }
    }
  }
}
