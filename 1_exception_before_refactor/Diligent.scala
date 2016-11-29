object ThirdPartyA {
  def complicatedIncrement(i: Int): Int = i match {
    case i if i < 0 => throw new VendorAException
    case i => i + 1
  }

  class VendorAException extends RuntimeException
}

object Diligent extends App {
  try {
    val zero = ThirdPartyA.complicatedIncrement(-1)
    println("Oh yeah, zero\n")
  } catch {
    case _: ThirdPartyA.VendorAException => println("Ops!\n")
  }
}
