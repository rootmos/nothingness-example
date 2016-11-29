object ThirdPartyA {
  def complicatedIncrement(i: Int): Either[Failure, Int] = i match {
    case 13 => Left(DontLike13)
    case i if i < 0 => Left(Negative)
    case i => Right(i + 1)
  }

  sealed trait Failure
  case object Negative extends Failure
  case object DontLike13 extends Failure
}

object Diligent extends App {
  ThirdPartyA.complicatedIncrement(-1) match {
    case Right(_) => println("Oh yeah!")
    case Left(ThirdPartyA.Negative) => println("Ops!")
  }
}
