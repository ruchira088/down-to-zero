package com.ruchij

import java.nio.file.Paths

import com.ruchij.utils.{FileUtils, ParseUtils}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object ConsoleApp
{
  def main(args: Array[String]): Unit =
  {
    val result = for {
      input <- FileUtils.readTextFile(Paths.get("resources/input.txt"))
      numbers <- Future.fromTry(ParseUtils.parseInput(input))
    }
    yield numbers.map(DownToZero.calculate)

    println(Await.result(result, 30 seconds))
  }
}