package scalademo

object Basics_PatternMatching extends App {

  // It is a functional feature
  // similar to switch statements. But, Scala's pattern matching is a lot more powerful!

  println("Step 1: Pattern matching 101 - a very basic example")
  // there are no break statements!
  // In Java or .NET, forgetting to use the break clause would allow the logic to fall-through to the next case statement.
  // the scala compiler is smart enough to prevent fall-through and hence there is no need to use a break clause with pattern matching.
  val donutType = "Glazed Donut"
  donutType match {
    case "Glazed Donut" => println("Very tasty")
    case "Plain Donut"  => println("Tasty")
  }



  println("\nStep 2: Pattern matching and return the result")
  val tasteLevel = donutType match {
    case "Glazed Donut" => "Very tasty"
    case "Plain Donut"  => "Tasty"
  }
  println(s"Taste level of $donutType = $tasteLevel")



  println("\nStep 3: Pattern matching using the wildcard operator")
  // wildcard operator to set a default case
  val tasteLevel2 = donutType match {
    case "Glazed Donut" => "Very tasty"
    case "Plain Donut"  => "Tasty"
    case _              => "Tasty"
  }
  println(s"Taste level of $donutType = $tasteLevel2")



  println("\nStep 4: Pattern matching with two or more items on the same condition")
  val tasteLevel3 = donutType match {
    case "Glazed Donut" | "Strawberry Donut" => "Very tasty"
    case "Plain Donut" => "Tasty"
    case _  => "Tasty"
  }
  println(s"Taste level of $donutType = $tasteLevel3")



  println("\nStep 5; Pattern matching and using if expressions in the case clause")
  val tasteLevel4 = donutType match {
    case donut if (donut.contains("Glazed") || donut.contains("Strawberry")) => "Very tasty"
    case "Plain Donut"  => "Tasty"
    case _  => "Tasty"
  }
  println(s"Taste level of $donutType = $tasteLevel4")



  println("\nStep 6: Pattern matching by types")
  val priceOfDonut: Any = 2.50
  val priceType = priceOfDonut match {
    case _: Int     => "Int"
    case _: Double  => "Double"
    case _: Float   => "Float"
    case _: String  => "String"
    case _: Boolean => "Boolean"
    case _: Char    => "Char"
    case _: Long    => "Long"
  }
  println(s"Donut price type = $priceType")

  println("\nStep 7: How to use pattern matching on Tuples")
  val glazedDonut = ("Glazed Donut", "Very Tasty", 2.50)
  val strawberryDonut = ("Strawberry Donut", "Very Tasty", 2.50)
  val plainDonut = ("Plain Donut", "Tasty", 2)

  val donutList = List(glazedDonut, strawberryDonut, plainDonut)
  val priceOfPlainDonut = donutList.foreach { tuple => {
    tuple match {
      case ("Plain Donut", taste, price) => println(s"Donut type = Plain Donut, price = $price")
      case d if d._1 == "Glazed Donut" => println(s"Donut type = ${d._1}, price = ${d._3}")
      case _ => None
    }
  }
  }

  println("\nStep 8: A more elegant pattern matching within foreach function")
  donutList.foreach {
    case ("Plain Donut", taste, price) => println(s"Donut type = Plain Donut, price = $price")
    case d if d._1 == "Glazed Donut" => println(s"Donut type = ${d._1}, price = ${d._3}")
    case _ => None
  }

  // you can think of the Any type similar to the Object class.
  // which is at the root of Scala's type hierarchy as shown per the Scala Documentation.

}
