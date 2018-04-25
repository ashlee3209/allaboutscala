package scalademo

object SingletonObject extends App {

  println("Singleton Object")
  // Singleton Object: create exactly one instance of an object.
  // which you can use to store global fields and utility functions or methods.

  // 1: Use object keyword to declare a Singleton Object
  object DonutShoppingCartCalculator {

    // 2: define a global field
    // Scala does not have a static keyword!
    // Instead, you can simply encapsulate a global field using the val keyword inside a Singleton Object.
    val discount: Double = 0.01
    // NOTE: In practice you won't assign a hard code value, but just showing how to declare global fields


    // 3: define utility method called calculateTotalCost
    def calculateTotalCost(donuts: List[String]): Double = {
      // calculate the cost of donuts
      return 1
    }
  }

  // call the global field
  println(s"Global discount = ${DonutShoppingCartCalculator.discount}")

  // call the utility method
  println(s"Call to calculateTotalCost function = ${DonutShoppingCartCalculator.calculateTotalCost(List())}")
}
