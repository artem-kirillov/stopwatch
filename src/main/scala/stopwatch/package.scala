import scala.concurrent.duration.{Duration, FiniteDuration, _}

package object stopwatch {
  // Default clock implementation using JVM's nanoTime.
  implicit val systemClock: Clock = new Clock {
    override def currentTime: Duration = FiniteDuration(System.nanoTime, NANOSECONDS)
  }
}
