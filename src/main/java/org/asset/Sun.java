package org.asset;

class Sun {
  private static Sun newSun = null;

  private int totalSun;

  public static void setTotalSun(int sunNumber) {
    newSun.totalSun = sunNumber;
  }

  public static int getTotalSun() {
    return newSun.totalSun;
  }

  public static synchronized Sun getInstance() {
    if (newSun == null) {
      newSun = new Sun();
    }
    return newSun;
  }

}
