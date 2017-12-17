package com.ruchij.utils

object NumberUtils
{
  def squareRootCeiling(number: Int, current: Int = 1): Int =
    if (current * current >= number) current else squareRootCeiling(number, current + 1)

  def multiplications(number: Int): List[(Int, Int)] =
    (1 to squareRootCeiling(number)).toList
        .filter(number % _ == 0)
        .map(value => (value, number/value))

  def lowestMaxInMultiplicationPair(list: List[(Int, Int)]): Option[Int] =
    list match {
      case Nil => None
      case (a, b) :: xs => {
        val max = Integer.max(a, b)
        Some(lowestMaxInMultiplicationPair(xs).fold(max)(Integer.min(_, max)))
      }
    }
}
