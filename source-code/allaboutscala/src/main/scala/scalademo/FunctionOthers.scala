package scalademo

import scala.util.control.TailCalls.{TailRec, done, tailcall}

object FunctionOthers extends App {
  println("1. Function Composition")
  // Assume a pre-calculated total cost amount
  val totalCost: Double = 10

  // define a val function to apply discount to total cost
  val applyDiscountValFunction = (amount: Double) => {
    println("Apply discount function")
    val discount = 2 // fetch discount from database
    amount - discount
  }

  // call it
  println(s"Total cost of 5 donuts with discount = ${applyDiscountValFunction(totalCost)}")

  // define a val function to apply tax to total cost
  val applyTaxValFunction = (amount: Double) => {
    println("Apply tax function")
    val tax = 1 // fetch tax from database
    amount + tax
  }

  println("\n1-1. Using andThen")
  // Calling andThen will take the result from the first function and pass it as input parameter to the second function.
  // Let's use the andThen semantics to apply discount andThen apply tax to the totalCost figure as shown below.

  // Mathematically speaking, f(x) andThen g(x) = g(f(x)).
  // The results of the first function f(x) is ran first and will be passed as input to the second function g(x)
  println(s"Total cost of 5 donuts = ${ (applyDiscountValFunction andThen applyTaxValFunction)(totalCost) }")

  // The apply discount function was called first andThen the apply tax function was called.
  // The output from the first apply discount function was also passed through as input parameter to the second apply tax function

  println("\n1-2. Using compose")
  // Mathematically speaking, f(x) compose g(x) = f(g(x)).
  // The second function g(x) is ran first and its result is passed along to the function f(x).

  println(s"Total cost of 5 donuts = ${ (applyDiscountValFunction compose applyTaxValFunction)(totalCost) }")

  println("\n2. Tail recursive")
  val arrayDonuts: Array[String] = Array("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

  println("\n2-1: Using annotation tailrec")
  @annotation.tailrec
  def search(donutName: String, donuts: Array[String], index: Int): Option[Boolean] = {
    if(donuts.length == index) {
      None
    } else if(donuts(index) == donutName) {
      Some(true)
    } else {
      val nextIndex = index + 1
      search(donutName, donuts, nextIndex)
    }
  }

  // call it
  val found = search("Glazed Donut", arrayDonuts, 0)
  println(s"Find Glazed Donut = $found")

  val notFound = search("Chocolate Donut", arrayDonuts, 0)
  println(s"Find Chocolate Donut = $notFound")


  println("\n2-2: Using scala.util.control.TailCalls._")
  def tailSearch(donutName: String, donuts: Array[String], index: Int): TailRec[Option[Boolean]] = {
    if(donuts.length == index) {
      done(None) // NOTE: done is imported from scala.util.control.TailCalls._
    } else if(donuts(index) == donutName) {
      done(Some(true))
    } else {
      val nextIndex = index + 1
      tailcall(tailSearch(donutName, donuts, nextIndex)) // NOTE: tailcall is imported from scala.util.control.TailCalls._
    }
  }

  // call it
  val tailFound = tailcall(tailSearch("Glazed Donut", arrayDonuts, 0))
  println(s"Find Glazed Donut using TailCall = ${tailFound.result}") // NOTE: our returned value is wrapped so we need to get it by calling result

  val tailNotFound = tailcall(tailSearch("Chocolate Donut", arrayDonuts, 0))
  println(s"Find Chocolate Donut using TailCall = ${tailNotFound.result}")

  println("\n2-3: How to define a trampoline function using scala.util.control.TailCalls")
  def verySweetDonut(donutList: List[String]): TailRec[Boolean] = {
    println(s"verySweetDonut function: donut list = $donutList")
    if (donutList.isEmpty) {
      println("verySweetDonut function: donut list isEmpty, returning false")
      done(false)
    } else {
      if(Set(donutList.head).subsetOf(Set("Vanilla Donut","Strawberry Donut","Glazed Donut"))) {
        println(s"verySweetDonut function: found donut list's head = ${donutList.head} to be VERY sweet, returning true")
        done(true)
      } else {
        println(s"verySweetDonut function: donut list's head = ${donutList.head} is NOT VERY sweet, forwarding donut list's to notSweetDonut function")
        tailcall(notSweetDonut(donutList))
      }
    }
  }

  def notSweetDonut(donutList: List[String]): TailRec[Boolean] = {
    println(s"notSweetDonut function: with donut list = $donutList")
    if (donutList.isEmpty) {
      println("notSweetDonut function: donut list isEmpty, returning false")
      done(false)
    } else {
      println(s"notSweetDonut function: donut list's head = ${donutList.head} is NOT sweet, forwarding donut list's tail to verySweetDonut function")
      tailcall(verySweetDonut(donutList.tail))
    }
  }

  println("\n2-4: How to call a trampoline tail recursive function")
  val donutList: List[String] = List("Plain Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")
  val foundVerySweetDonut = tailcall(verySweetDonut(donutList)).result
  println(s"Found very sweet donut = $foundVerySweetDonut")

  println("\n3. PartialFunction Trait")

  val donut = "Glazed Donut"
  val tasteLevel = donut match {
    case "Glazed Donut" | "Strawberry Donut" => "Very tasty"
    case "Plain Donut" => "Tasty"
    case _  => "Tasty"
  }
  println(s"Taste level of $donut = $tasteLevel")



  println("\n3-1: How to define a Partial Function named isVeryTasty")
  val isVeryTasty: PartialFunction[String, String] = {
    case "Glazed Donut" | "Strawberry Donut" => "Very Tasty"
  }



  println("\n3-2: How to call the Partial Function named isVeryTasty")
  println(s"Calling partial function isVeryTasty = ${isVeryTasty("Glazed Donut")}")
  // NOTE: you will get scala.MatchError



  println("\n3-3: How to define PartialFunction named isTasty and unknownTaste")
  val isTasty: PartialFunction[String, String] = {
    case "Plain Donut" => "Tasty"
  }

  val unknownTaste: PartialFunction[String, String] = {
    case donut @ _ => s"Unknown taste for donut = $donut"
  }



  println("\n3-4: How to compose PartialFunction using orElse")
  val donutTaste = isVeryTasty orElse isTasty orElse unknownTaste
  println(donutTaste("Glazed Donut"))
  println(donutTaste("Plain Donut"))
  println(donutTaste("Chocolate Donut"))

  println("\n4. Nested Function")

  def checkDonutAvailability(donutName: String): Boolean = {
    // retrieve donut list that is currently in stock
    val listDonutsFromStock: List[String] = List("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

    val iDonutInStock = (donutName.nonEmpty && donutName.length > 0) && (listDonutsFromStock contains donutName)

    iDonutInStock
  }

  println(s"Calling checkDonutAvailability with Vanilla Donut = ${checkDonutAvailability("Vanilla Donut")}")



  println("\n4-1: How to define a Nested Function")
  def checkDonutAvailabilityWithNestedFunction(donutName: String): Boolean = {
    // retrieve donut list that is currently in stock
    val listDonutsFromStock = List[String]("Vanilla Donut", "Strawberry Donut", "Plain Donut", "Glazed Donut")

    // validate the donutName parameter by some business logic
    val validate = (name: String) => {
      name.nonEmpty && name.length > 0
    }

    // first run validate and then check if we have a matching donut from our list
    val isDonutInStock = validate(donutName) && (listDonutsFromStock contains donutName)

    isDonutInStock
  }



  println("\n4-2: How to call a Nested Function")
  println(s"Calling checkDonutAvailabilityWithNestedFunction with Vanilla Donut = ${checkDonutAvailabilityWithNestedFunction("Vanilla Donut")}")

}
