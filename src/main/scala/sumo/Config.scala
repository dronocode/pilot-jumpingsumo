package sumo

import java.net.InetAddress

/**
  * Created by msciab on 29/01/17.
  */
trait Config {

  val droneIp = InetAddress.getByName("192.168.2.1")

  val droneTcpPort = 44444

  val droneLan = "JumpingSumo-w111440"

  val badIp = InetAddress.getByName("192.168.2.3")

}
