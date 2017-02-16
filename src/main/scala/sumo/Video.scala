package sumo

import de.devoxx4kids.dronecontroller.command.multimedia.VideoStreaming
import de.devoxx4kids.dronecontroller.network.DroneConnection
import de.devoxx4kids.dronecontroller.listener.EventListener
import de.devoxx4kids.dronecontroller.command.PacketType

/**
  * Created by msciab on 15/02/17.
  */
trait Video {

  class VideoListener(action: Array[Byte] => Unit) extends EventListener {

    def getJpeg(data: Array[Byte]) = {
      val imageLength = data.length - 12
      val lastJpeg = new Array[Byte](imageLength)
      System.arraycopy(data, 12, lastJpeg, 0, imageLength)
      lastJpeg
    }

    def consume(bytes: Array[Byte]): Unit = action(getJpeg(bytes))

    def test(data: Array[Byte]): Boolean = {
      val jpgStart = data(12) == -1 && data(13) == -40
      return data(0) == PacketType.DATA_LOW_LATENCY.toByte && data(1) == 125 && jpgStart
    }
  }

  def handleVideo(action: Array[Byte] => Unit)(implicit drone: Option[DroneConnection]) =
    drone.map(_.addEventListener(new VideoListener(action)))

  def enableVideo(status: Boolean)(implicit drone: Option[DroneConnection]) = {
    if (status)
      drone.map(_.sendCommand(VideoStreaming.enableVideoStreaming()))
    else
      drone.map(_.sendCommand(VideoStreaming.disableVideoStreaming))
  }
}
