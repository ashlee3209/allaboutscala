package scalademo

object MethodImplicit extends App {

  println("\n1. Method with implicit parameters")
  // The use of implicit parameters is just one example of how dependency injection can be achieved in Scala.

  println(s"\n1-1: How to define a method with an implicit parameter")
  // The implicit parameter discount of type Double is defined using the keyword implicit within parenthesis after your usual function parameters.
  // This means that the totalCost() function will require an implicit value of type Double to be in scope as defined in Step 2 below.
  def totalCost(donutType: String, quantity: Int)(implicit discount: Double): Double = {
    println(s"Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  println("\n1-2: How to define an implicit value")
  implicit val discount: Double = 0.1
  println(s"All customer will receive a ${discount * 100}% discount")

  println("\n1-3: How to call a method which has an implicit parameter")
  // You did not have to manually pass-through the discount value when calling the totalCost() function.
  // The Scala compiler will look for an implicit value of type Double for the discount implicit parameter which you've defined in Step 2.
  // If there are no implicit values in scope, you will get a compiler error.
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost("Glazed Donut", 5)}""")

  println("\n1-4: How to define a method which takes multiple implicit parameters")
  def totalCost2(donutType: String, quantity: Int)(implicit discount: Double, storeName: String): Double = {
    println(s"[$storeName] Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }

  println("\n1-5: How to call a method which takes multiple implicit parameters")
  implicit val storeName: String = "Tasty Donut Store"
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost2("Glazed Donut", 5)}""")

  println("\n1-6: How to manually pass-through implicit parameters")
  println(s"""Total cost with discount of 5 Glazed Donuts, manually passed-through = ${totalCost2("Glazed Donut", 5)(0.1, "Scala Donut Store")}""")

  // It is a good practice to encapsulate your implicit values into an Object or a Package Object.

  println("\n2. Implicit Method")
  println("2-1: How to create a wrapper String class which will extend the String type")
  class DonutString(s: String) {

    def isFavoriteDonut: Boolean = s == "Glazed Donut"

  }

  println("\n2-2: How to create an implicit method to convert a String to the wrapper String class")
  // It is a good practice to encapsulate your implicit methods or conversions into a singleton using object.
  // You can also make use of package object which we will see in upcoming tutorials.
  object DonutConverstions {
    implicit def stringToDonutString(s: String) = new DonutString(s)
  }

  // Defining an implicit method is similar to defining any other method
  // except that we've prefixed the method signature using the implicit keyword.


  println("\n2-3: How to import the String conversion so that it is in scope")
  // As part of the import expression,
  // we are using the wildcard operator _ which will import any values or implicit functions.
  import DonutConverstions._



  println("\n2-4: How to create String values")
  val glazedDonut = "Glazed Donut"
  val vanillaDonut = "Vanilla Donut"



  println("\n2-5: How to access the custom String function called isFavaoriteDonut")
  println(s"Is Glazed Donut my favorite Donut  = ${glazedDonut.isFavoriteDonut}")
  println(s"Is Vanilla Donut my favorite Donut = ${vanillaDonut.isFavoriteDonut}")

  // The custom isFavoriteDonut() method looks built-into the String class.
  // However, we did not have to manually modify the source code of the String class.
  // Instead, we've used the secret powers of Scala's implicit function to extend the String class.

}
