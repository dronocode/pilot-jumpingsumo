import java.io.{BufferedWriter, OutputStreamWriter}
import java.net.URI
import java.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{CreateFlag, FileContext, FileSystem, Path}
import org.apache.hadoop.io.{LongWritable, SequenceFile}
import org.apache.hadoop.io.SequenceFile.Metadata
import org.apache.hadoop.io.compress.DefaultCodec
import org.apache.hadoop.util.Progressable
import org.scalatest.FunSuite

/**
  * Created by msciab on 19/02/17.
  */
class HadoopSpec
  extends FunSuite {
  val pilot = sys.props("pilot.hadoop")
  val conf = new Configuration
  conf.set("io.serializations", "org.apache.hadoop.io.serializer.JavaSerialization,org.apache.hadoop.io.serializer.WritableSerialization");
  val hdfs = FileSystem.get(new URI(pilot), conf)
  val fc = FileContext.getFileContext(conf)

  val progress = new Progressable {
    def progress {
      println("....")
    }
  }


  ignore("write hello") {
    info(sys.props("pilot.hadoop"))

    val file = new Path(s"${pilot}/test/hello.txt")
    val os = hdfs.create(file, true, 1024, progress)
    val br = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))
    br.write("Hello, world.")
    br.close()
    hdfs.close()
  }

  test("write sequence file") {
    val path = new Path(s"${pilot}/test/hello.seq")

    val sfw = SequenceFile.createWriter(fc, conf, path,
      classOf[String], classOf[String],
      SequenceFile.CompressionType.NONE,
      new DefaultCodec(),
      new Metadata(),
      util.EnumSet.of(CreateFlag.CREATE, CreateFlag.OVERWRITE)
    )

    sfw.append("1", "Hello")
    sfw.append("2", "World")
    sfw.sync()
    sfw.close()
  }
}
