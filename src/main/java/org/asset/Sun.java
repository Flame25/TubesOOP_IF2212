package org.asset;

class Sun {
  private static Sun newSun = null;

  private int totalSun = 100;

  public void setTotalSun(int sunNumber) {
    newSun.totalSun = sunNumber;
  }

  public int getTotalSun() {
    return newSun.totalSun;
  }

  public static synchronized Sun getInstance() {
    if (newSun == null) {
      newSun = new Sun();
    }
    return newSun;
  }

}
