package com.ruchij.simple

import com.ruchij.DownToZero
import com.ruchij.simple.utils.NumberUtils._

object SimpleDownToZero extends DownToZero
{
  override def calculate(number: Int): List[Int] =
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
