package scalademo

object Basics extends App {
  // The App trait encapsulates the main function and more!
  // by extending the App trait, we dont need to define the main function.

  println("1. Variables and Values")

  println("\n1-1. Mutable variable")
  var favoriteDonut: String = "Glazed Donut"
  favoriteDonut = "Vanilla Donut"

  println("\n1-2: Immutable variable")

  // syntax: val <Name of our variable>: <Scala type> = <Some literal>
  val donutsToBuy: Int = 5
  // donutsToBuy = 10
  // you will get compile error because donutToBuy is immutable

  println("\n2: String interpolation")
  // prefix s, f, raw

  println(s"My favorite donut = $favoriteDonut")

  println("\n2-1: Using String interpolation for formatting text")
  // similar to printf in other languages.
  // all variable references should be followed by a printf-style format string.
  val donutName: String = "Vanilla Donut"
  val donutTasteLevel: String = "Tasty"
  println(f"$donutName%20s $donutTasteLevel")

  println("\n2-2: Using f interpolation to format numbers")
  val donutPrice: Double = 2.50
  println(s"Donut price = $donutPrice")
  println(f"Formatted donut price = $donutPrice%.2f")

  // The f interpolator makes use of the string format utilities available from Java.
  // The formats allowed after the % character are outlined in the Formatter javadoc.
  // If there is no % character after a variable definition a formatter of %s (String) is assumed.

  println("\n2-3: Using raw interpolation")
  // The raw String interpolation will allow you to print any symbols within your String.
  println(raw"Favorite donut\t$donutName")

  // In addition to the three default string interpolators, users can define their own.
  // To define our own string interpolation, we simply need to create an implicit class that adds a new method to StringContext.
  // see https://docs.scala-lang.org/overviews/core/string-interpolation.html#advanced-usage


  println("\n3: Escape characters and multi-line string")
  println("\n3-1: Using triple quotes \"\"\" to escape characters")
  val donutJson3: String = """{"donut_name":"Glazed Donut","taste_level":"Very Tasty","price":2.50}"""
  println(s"donutJson3 = $donutJson3")

  println("\n3-2:  Creating multi-line text using stripMargin")
  val donutJson4: String =
    """
      |{
      |"donut_name":"Glazed Donut",
      |"taste_level":"Very Tasty",
      |"price":2.50
      |}
    """.stripMargin
  println(s"donutJson4 = $donutJson4")



  println("\n3-3:  stripMargin using a different character")
  val donutJson5: String =
    """
      #{
      #"donut_name":"Glazed Donut",
      #"taste_level":"Very Tasty",
      #"price":2.50
      #}
    """.stripMargin('#')
  println(s"donutJson5 = $donutJson4")

  println("\n4: Types Inference")
  // Through type inference, Scala comelier is smart enough to figure out that the literal 5 is actually  an Integer.
  // Try in REPL, it will tell you the type.
  val donutsBoughtToday = 5

  val bigNumberOfDonuts = 100000000L

  val smallNumberOfDonuts = 1

  val priceOfDonut = 2.50

  val donutPriceF = 2.50f

  val donutStoreName = "Allaboutscala Donut Store"

  val donutByte = 0xa

  val donutFirstLetter = 'D'

  val nothing = ()

  println("\n5: If and else")
  // In Scala, you can use the if and else clause as a statement to test for some condition or logical step.
  // In addition, you can also use if and else clause as an expression where you get back the result of your condition or logical step.

  val numberOfPeople = 20
  val donutsPerPerson = 2
  val defaultDonutsToBuy = 8

  println("\n5-1: Using if, else if, and else clause as a statement")
  if (numberOfPeople > 10) {
    println(s"Number of donuts to buy = ${numberOfPeople * donutsPerPerson}")
  } else if (numberOfPeople == 0) {
    println("Number of people is zero.")
    println("No need to buy donuts.")
  } else {
    println(s"Number of donuts to buy = $defaultDonutsToBuy")
  }

  println("\n5-2: Using if and else clause as expression")
  // What if you had to store the result of the if and else expressions above into a variable.
  // With Scala, you can easily inline this as follows:
  val numberOfDonutsToBuy = if (numberOfPeople > 10) (numberOfPeople * donutsPerPerson) else defaultDonutsToBuy
  println(s"Number of donuts to buy = $numberOfDonutsToBuy")

  // Similar in Java: Object bar = foo.isSelected() ? foo : baz;
  // but much easier to read and less error prone
  // pattern matching more popular than if and else expressions.

  println("\n6: For Loops")
  val donutIngredients = List("flour", "sugar", "egg yolks", "syrup", "flavouring")

  println("\n6-1: Filter values using if conditions in for loop and return the result back using the yield keyword")
  // filter for either "sugar" or "syrup" ingredients in our donutsIngredients list.
  // Instead of using the (), we are using the {} in our for comprehension to make our expressions more explicit.
  // In addition, to return the result of the for comprehension
  // and store it in the sweeteningIngredients variable, we will make use of the yield keyword.
  val sweeteningIngredients = for {
    ingredient <- donutIngredients
    if (ingredient == "sugar" || ingredient == "syrup")
  } yield ingredient
  println(s"Sweetening ingredients = $sweeteningIngredients")

  println("\n6-2: For loop with yield actually is converted into a call to map")
  val sweeteningIngredientsUsingMap = donutIngredients.withFilter(ingredient => (ingredient == "sugar" || ingredient == "syrup")).
          map(ingredient => ingredient)
  println(s"Sweetening ingredients using map function = $sweeteningIngredientsUsingMap")

  println("\n6-3: Using for comprehension to loop through 2-Dimensional array")
  val twoDimensionalArray = Array.ofDim[String](2,2)
  twoDimensionalArray(0)(0) = "flour"
  twoDimensionalArray(0)(1) = "sugar"
  twoDimensionalArray(1)(0) = "egg"
  twoDimensionalArray(1)(1) = "syrup"

  // We used of keyword until which meant that the iteration number 2 was NOT included.
  // You can use the keyword to to make the iteration number 2 is included.
  for { x <- 0 until 2
        y <- 0 until 2
  } println(s"Donut ingredient at index ${(x,y)} = ${twoDimensionalArray(x)(y)}")

  println("\n6-4: This is converted into calls to flatMap followed by a map")
  (0 until 2).flatMap {
    x => (0 until 2).map {
      y => println(s"Donut ingredient at index ${(x,y)} = ${twoDimensionalArray(x)(y)}")
    }
  }

  // This part can be talked after we talked about map and flatmap functions. See CollectionFunctions.scala

  // Just a reminder that from a pure functional perspective the use of loop such as the while loop is generally less favoured.
  //Instead, fold and recursive operations are preferred

  println("\n7: Tuples")

  println("\n7-1: Using TupleN classes to store more than 2 data points")
  // N from 1 to 22.
  val glazedDonut = Tuple3("Glazed Donut", "Very Tasty", 2.50)
  println(s"${glazedDonut._1} taste leve is ${glazedDonut._2} and it's price is ${glazedDonut._3}")

  println("\n7-2: Using () to store more than 2 data points")
  // You can also simply enclose your data points within ().
  val chocolateDonut = ("Chocolate Donut", "Very Tasty", 3.0)
  println(s"Chocolate donut taste level = ${chocolateDonut._2}, price = ${chocolateDonut._3}")

  println(s"${glazedDonut.productElement(2)}")
}
