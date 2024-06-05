package web.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.management.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Named("attemptStats")
@SessionScoped
public class AttemptStats implements AttemptStatsMBean, NotificationBroadcaster, Serializable {
  private final AtomicInteger totalAttempts = new AtomicInteger();
  private final AtomicInteger totalMisses = new AtomicInteger();
  private final AtomicInteger consecutiveMisses = new AtomicInteger();

  private final NotificationBroadcasterSupport broadcaster = new NotificationBroadcasterSupport();

  @Override
  public int getTotalAttempts() {
    return totalAttempts.get();
  }

  @Override
  public int getTotalMisses() {
    return totalMisses.get();
  }

  @Override
  public void checkForConsecutiveMisses() {
    if (consecutiveMisses.get() == 3) {
      broadcaster.sendNotification(new Notification(
        "consecutive.misses",
        this,
        System.currentTimeMillis(),
        "3 consecutive misses detected."
      ));

      consecutiveMisses.set(0);
    }
  }

  public void updateAttempt(boolean hit) {
    totalAttempts.incrementAndGet();
    if (!hit) {
      totalMisses.incrementAndGet();
      consecutiveMisses.incrementAndGet();
    } else {
      consecutiveMisses.set(0);
    }
    checkForConsecutiveMisses();
  }

  @Override
  public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws IllegalArgumentException {
    broadcaster.addNotificationListener(listener, filter, handback);
  }

  @Override
  public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
    broadcaster.removeNotificationListener(listener);
  }

  @Override
  public MBeanNotificationInfo[] getNotificationInfo() {
    String[] types = new String[] { "consecutive.misses" };
    String name = Notification.class.getName();
    String description = "Notification sent when 3 consecutive misses are recorded";
    return new MBeanNotificationInfo[] { new MBeanNotificationInfo(types, name, description) };
  }
}
