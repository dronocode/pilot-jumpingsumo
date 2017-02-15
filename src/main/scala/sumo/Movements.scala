package sumo

import de.devoxx4kids.dronecontroller.command.movement.Pcmd
import de.devoxx4kids.dronecontroller.network.DroneConnection

trait Movements {

  def pcmd(speed: Int, degree: Int)(implicit drone: Option[DroneConnection]) =
    drone.map { c =>
      println(s"sending (${c}) ${speed} ${degree} ")
      c.sendCommand(Pcmd.pcmd(speed, degree))
    }

  def forward(speed: Int = 40)(implicit drone: Option[DroneConnection]) =
    pcmd(speed, 0)(drone)

  def backward(speed: Int = 40)(implicit drone: Option[DroneConnection]) =
    pcmd(-speed, 0)(drone)

  def left(degree: Int = 90)(implicit drone: Option[DroneConnection]) =
    pcmd(0, degree)(drone)

  def right(degree: Int = 90)(implicit drone: Option[DroneConnection]) =
    pcmd(0, -degree)(drone)

}
