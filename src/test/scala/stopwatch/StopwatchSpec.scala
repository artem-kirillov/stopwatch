package stopwatch

import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration.{Duration, FiniteDuration, MILLISECONDS}

class StopwatchSpec extends FlatSpec with Matchers {
  "Stopwatch" should "spot time intervals" in {
    val stopwatch = Stopwatch.start()(new FixedClock(1, 10))
    stopwatch().toMillis shouldBe 10
    stopwatch().toMillis shouldBe 20
    stopwatch().toMillis shouldBe 30
  }

  it should "measure code blocks execution time" in {
    implicit val clock = new FixedClock(1, 10)
    val (result: Int, duration: Duration) = Stopwatch.measure { 42 }

    result shouldBe 42
    duration.toMillis shouldBe 10
  }
}

/**
  * Pure mock clock implementation
  */
class FixedClock(from: Int, tick: Int) extends Clock {
  private val tickStream = Stream.from(from, tick).iterator
  override def currentTime: Duration = FiniteDuration(tickStream.next, MILLISECONDS)
}
