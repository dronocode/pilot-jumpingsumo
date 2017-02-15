import org.scalatest.{BeforeAndAfter, FunSuite}
import sumo.{Connection, Movements}

/**
  * Created by msciab on 30/01/17.
  */
class MovementSpec
  extends FunSuite
    with Connection
    with Movements
    with DroneCommon {

  test("left right") {
    left()
    right()
  }

  test("forward backward") {
    forward()
    backward()
  }

}
