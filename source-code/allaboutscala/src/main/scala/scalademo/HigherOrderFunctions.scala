package scalademo

import scala.util.Random

object HigherOrderFunctions extends App {

  println("1: Review how to define function with curried parameter groups")
  def totalCost(donutType: String)(quantity: Int)(discount: Double): Double = {
    println(s"Calculating total cost for $quantity $donutType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }


  // Higher Order Function which is a function that takes another function as its parameter.

  println("\n2: How to define a higher order function which takes another function as parameter")
  def totalCostWithDiscountFunctionParameter(donutType: String)(quantity: Int)(f: Double => Double): Double = {
    println(s"Calculating total cost for $quantity $donutType")
    val totalCost = 2.50 * quantity
    f(totalCost)
  }



  println("\n3: How to call higher order function and pass an anonymous function as parameter")
  val totalCostOf5Donuts = totalCostWithDiscountFunctionParameter("Glazed Donut")(5){totalCost =>
    val discount = 2 // assume you fetch discount from database
    totalCost - discount
  }
  println(s"Total cost of 5 Glazed Donuts with anonymous discount function = $totalCostOf5Donuts")



  println("\n4: How to define and pass a function to a higher order function")
  // A better approach to Step 3 is to pass-through a common discount function
  // which would encapsulate the discount logic instead of providing an anonymous function.
  def applyDiscount(totalCost: Double): Double = {
    val discount = 2 // assume you fetch discount from database
    totalCost - discount
  }
  println(s"Total cost of 5 Glazed Donuts with discount function = ${totalCostWithDiscountFunctionParameter("Glazed Donut")(5)(applyDiscount(_))}")


  println("\n5: Call by value example")
  // Let's start by defining a List containing Tuple3 elements
  // which would represent the name of a donut, the quantity to be purchased and its price.
  val listOrders = List(("Glazed Donut", 5, 2.50), ("Vanilla Donut", 10, 3.50))

  // Assume that your donut store sells donuts worldwide
  // and as such you need to convert the total cost of buying donuts to the local currency being used.
  def placeOrder(orders: List[(String, Int, Double)])(exchangeRate: Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }

  // call it
  println(s"Total cost of order = £${placeOrder(listOrders)(0.5)}")

  println("\n6. Call by name example")
  // The call-by-name function parameter exchangeRate: => Double will evaluate any exchangeRate function each time it is called.
  // This is in contrast to the function defined above which had a call-by-value function parameter for exchange rate.
  // This meant that any exchange rate passed through would be evaluated only once.
  def placeOrderWithByNameParameter(orders: List[(String, Int, Double)])(exchangeRate: => Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }

  //Define a simple USD to GBP function
  val randomExchangeRate = new Random(10)
  def usdToGbp: Double = {
    val rate = randomExchangeRate.nextDouble()
    println(s"Fetching USD to GBP exchange rate = $rate")
    rate
  }

  // For each order in the list, a new exchange rate is being created
  // and that's because the call-by-name function usdToGbp function is being evaluated each time.
  println(s"By name: Total cost of order = £${placeOrderWithByNameParameter(listOrders)(usdToGbp)}")
  println()
  println(s"By value: Total cost of order = £${placeOrder(listOrders)(usdToGbp)}")

  println("\n7. Callback function parameter")
  println("7-1: How to define a method with a callback parameter")
  def printReport(sendEmailCallback: () => Unit) {
    println("Printing report ... started")
    // look up some data in database and create a report
    println("Printing report ... finished")
    sendEmailCallback()
  }

  println("\n7-2: How to call a method which has a callback parameter")
  printReport(() =>
    println("Sending email ... finished")
  )



  println("\n7-3: How to call a method without providing its callback parameter")
  // printReport() // You get compile time error
  printReport(() => {}) // Not that elegant.


  println("\n7-4: How to define a method with an Option callback")
  def printReportWithOptionCallback(sendEmailCallback: Option[() => Unit] = None) {
    println("Printing report ... started")
    // look up some data in database and create a report
    println("Printing report ... finished")
    sendEmailCallback.map(callback => callback())
  }

  println("\n7-5: How to call a method without providing its callback parameter")
  printReportWithOptionCallback() // more elegant

  println("\n7-6: How to call a method with Option callback parameter")
  printReportWithOptionCallback(Some(() =>
    println("Sending email wrapped in Some() ... finished")
  ))

}
