package com.ruchij.simple.utils

object NumberUtils
{
  def multiplications(number: Int): List[(Int, Int)] =
    (1 to number).toList
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
