package web.beans;

public interface HitRatioMBean {
  int getTotalAttempts();
  int getTotalHits();
  double getHitRatio();
}
