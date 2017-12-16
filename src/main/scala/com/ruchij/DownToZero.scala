package com.ruchij

import com.ruchij.utils.NumberUtils._

object DownToZero
{
  def calculate(number: Int): List[Int] =
    if (number == 0)
      List.empty
    else
    number :: calculate(nextStepNumber(number))

  private def nextStepNumber(number: Int): Int =
    Integer.min(
      (multiplications _).andThen(lowestMaxInMultiplicationPair)(number).getOrElse(number),
      number -  1
    )
}
