// Hypothesis: still on the classpath somehow
object ThirdPartyA {
  sealed trait Failure
  case object Negative extends Failure
}

object ThirdPartyB {
  def complicatedIncrement(i: Int): Either[Failure, Int] = i match {
    case i if i < 0 => Left(Negative)
    case i => Right(i + 1)
  }

  sealed trait Failure
  case object Negative extends Failure
}

object Diligent extends App {
  ThirdPartyB.complicatedIncrement(-1) match {
    case Right(_) => println("Oh yeah!")
    case Left(ThirdPartyA.Negative) => println("Ops!")
  }
}
