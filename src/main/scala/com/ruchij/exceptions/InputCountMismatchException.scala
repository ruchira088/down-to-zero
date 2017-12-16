package com.ruchij.exceptions

case class InputCountMismatchException(expected: Int, actual: Int) extends Exception
{
  override def getMessage: String = s"expected: $expected, actual: $actual"
}