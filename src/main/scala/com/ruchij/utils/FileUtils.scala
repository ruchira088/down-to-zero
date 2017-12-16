package com.ruchij.utils

import java.nio.ByteBuffer
import java.nio.channels.{AsynchronousFileChannel, CompletionHandler}
import java.nio.file.{Path, StandardOpenOption}

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.control.NonFatal

object FileUtils
{
  def readFile(filePath: Path): Future[Array[Byte]] =
  {
    val promise = Promise[Array[Byte]]

    try {
      val fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.READ)
      val byteBuffer = ByteBuffer.allocate(fileChannel.size().toInt)

      fileChannel.read(byteBuffer, 0, byteBuffer, new CompletionHandler[Integer, ByteBuffer]
      {
        override def failed(exception: Throwable, attachment: ByteBuffer): Unit =
          promise.failure(exception)

        override def completed(result: Integer, attachment: ByteBuffer): Unit =
          promise.success(attachment.array())
      })
    }
    catch {
      case NonFatal(exception) => promise.failure(exception)
    }

    promise.future
  }

  def readTextFile(filePath: Path)(implicit executionContext: ExecutionContext): Future[List[String]] =
    for {
      byteArray <- readFile(filePath)
      text = new String(byteArray).trim
    } yield text.split("\n").toList
}
