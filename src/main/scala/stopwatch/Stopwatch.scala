package stopwatch

import scala.concurrent.duration.Duration

/**
  * Clock trait should return monotonically increasing sequence of numbers
  * which can be interpreted for past time estimation
  */
trait Clock {
  def currentTime: Duration
}

/**
  * Stopwatch implementation which provides methods for
  * time intervals measurement.
  */
trait Stopwatch {
  def start()(implicit clock: Clock): () => Duration = {
    val startTime = clock.currentTime
    () => clock.currentTime - startTime
  }

  def measure[R](run: => R)(implicit clock: Clock): (R, Duration) = {
    val stopwatch = start()(clock)
    (run, stopwatch())
  }
}

object Stopwatch extends Stopwatch
