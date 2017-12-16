package com.ruchij.utils

import com.ruchij.exceptions.{EmptyInputFileException, InputCountMismatchException}

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object ParseUtils
{
  def convert[T, R](f: T => R)(value: T): Try[R] =
    try {
      Success(f(value))
    } catch {
      case NonFatal(exception) => Failure(exception)
    }

  def convertList[T, R](f: T => Try[R])(list: List[T]): Try[List[R]] =
    list match
    {
      case Nil => Success(List.empty)
      case x :: xs => for {
        value <- f(x)
        rest <- convertList(f)(xs)
      } yield value :: rest
    }

  def parseInt: String => Try[Int] = convert[String, Int](_.toInt)

  def parseInput(input: List[String]): Try[List[Int]] =
    convertList(parseInt)(input).flatMap {
      case Nil => Failure(EmptyInputFileException)
      case x :: xs if xs.length == x => Success(xs)
      case x :: xs => Failure(InputCountMismatchException(x, xs.length))
    }

}
