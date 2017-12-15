package com.ruchij

import java.time.Instant

import com.ruchij.simple.SimpleDownToZero

object ConsoleApp
{
  def getTimestamp(): Long = Instant.now().toEpochMilli

  def main(args: Array[String]): Unit =
  {
    val startTime = getTimestamp()

    println(SimpleDownToZero.calculate(10000000))

    println(getTimestamp() - startTime)
  }
}