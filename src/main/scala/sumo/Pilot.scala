package sumo

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import javax.swing.{JButton, JFrame}

/**
  * Created by msciab on 01/02/17.
  */
class Pilot
  extends JFrame("Pilot")
    with Connection
    with Movements {

  import BorderLayout._
  import KeyEvent._

  implicit val drone = connect()
  if (drone.isEmpty)
    throw new Error("Cannot connect to the Drone!")

  getContentPane.setLayout(new BorderLayout)
  Seq(
    ("Forward", NORTH, VK_UP, () => forward()),
    ("Backward", SOUTH, VK_DOWN, () => backward()),
    (">>", EAST, VK_LEFT, () => left()),
    ("<<", WEST, VK_RIGHT, () => right())
  ) foreach { t =>
    val (label, constraint, key, action) = t
    val button = new JButton(label)
    add(button, constraint)
    button.addKeyListener(new KeyAdapter {
      override def keyPressed(e: KeyEvent): Unit = {
        if (e.getKeyCode == key)
          action()
      }
    })
    button.addActionListener(new ActionListener {
      override def actionPerformed(e: ActionEvent) =
        if (e.getSource == button)
          action()
    })
  }

  pack()
  setVisible(true)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

}

object Pilot {
  def main(args: Array[String]) {
      new Pilot
  }
}