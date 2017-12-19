package com.ruchij

object DownToZero
{
  import utils.NumberUtils.multiplications

  def calculate(inputNumber: Int): List[Int] =
    (1 to inputNumber).toList.foldLeft(Map(0 -> List.empty[Int])) {
      case (mappings, number) => {

        val nextValues = (number - 1) :: multiplications(number)
          .map { case (a, b) => Integer.max(a, b) }
          .filter( _ != number)

        val value = number :: nextValues.map(mappings).minBy(_.length)

        mappings + (number -> value)
      }
    }(inputNumber)
}
