package com.ruchij

import org.scalatest.{FlatSpec, Matchers}

class DownToZeroTest extends FlatSpec with Matchers
{
  import DownToZero._

  "calculate(82)" should "return List(82, 81, 9, 3, 2, 1)" in
    {
      calculate(82) shouldEqual List(82, 81, 9, 3, 2, 1)
    }

}
