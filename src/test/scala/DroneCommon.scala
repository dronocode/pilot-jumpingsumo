import sumo.Connection
import org.scalatest.{BeforeAndAfterAll, Suite}
import de.devoxx4kids.dronecontroller.network.DroneConnection

/**
  * Created by msciab on 31/01/17.
  */
trait DroneCommon
  extends BeforeAndAfterAll
    with Connection {
  this: Suite =>

  implicit var drone = Option.empty[DroneConnection]

  override def beforeAll() {
    if (ping().nonEmpty)
      drone = connect()
    else println("cannot ping the drone")
    if (drone.isEmpty)
      throw new Error("cannot connect to the drone")
  }

  override def afterAll {
    Thread.sleep(1000)
    disconnect
  }
}
