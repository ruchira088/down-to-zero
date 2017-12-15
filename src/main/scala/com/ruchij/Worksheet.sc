def multiplications(number: Int): List[(Int, Int)] =
  (1 to number).toList
    .filter(number % _ == 0)
    .map(value => (value, number/value))

def lowestMaxInMultiplicationPair(list: List[(Int, Int)]): Option[Int] =
  list match {
    case Nil => None
    case (a, b) :: xs =>
      Some(lowestMaxInMultiplicationPair(xs).fold(Integer.max(a, b))(Integer.min(_, Integer.max(a, b))))
  }

def calculate(number: Int): Int =
  if (number == 0)
    0
  else
    1 + calculate(Integer.min((multiplications _).andThen(lowestMaxInMultiplicationPair)(number).getOrElse(number), number -  1))
