// Hypothesis: still on the classpath somehow
object ThirdPartyA {
  class VendorAException extends RuntimeException
}

object ThirdPartyB {
  def complicatedIncrement(i: Int): Int = i match {
    case i if i < 0 => throw new VendorBException
    case i => i + 1
  }

  class VendorBException extends RuntimeException
}

object Diligent extends App {
  try {
    val zero = ThirdPartyB.complicatedIncrement(-1)
    println("Oh yeah, zero\n")
  } catch {
    case _: ThirdPartyA.VendorAException => println("Ops!\n")
  }
}
