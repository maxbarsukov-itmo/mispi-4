package web.beans;

public interface AttemptStatsMBean {
  int getTotalAttempts();
  int getTotalMisses();
  void checkForConsecutiveMisses();
}
