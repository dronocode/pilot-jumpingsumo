package sumo

import de.devoxx4kids.dronecontroller.network.{DroneConnection, WirelessLanDroneConnection}
import java.net.InetAddress
import scala.util.Try

/**
  * Created by msciab on 29/01/17.
  */
trait Connection extends Config {

  def ping(ip: InetAddress = droneIp): Option[String] =
    if (ip.isReachable(5000))
      Some(ip.getHostAddress)
    else None

  def connect(ip: InetAddress = droneIp,
              port: Int = droneTcpPort,
              lan: String = droneLan): Option[DroneConnection] =
    ping(droneIp).map { ip =>
      println(ip)
      Try(new WirelessLanDroneConnection(ip, port, lan))
        .map { c =>
          c.connect()
          c
        }.get
    }

  def disconnect(implicit drone: Option[DroneConnection]) =
    drone.map(_.disconnect())

}
