package scalademo

object Basics_Option extends App {

  println("Step 1: How to use Option and Some - a basic example")
  val glazedDonutTaste: Option[String] = Some("Very Tasty")
  println(s"Glazed Donut taste = ${glazedDonutTaste.get}")
  // Because we have wrapped our String into an Option, to retrieve its value we call the get() function as shown above.
  // But calling get() in a production system is generally a code smell as you may end up with the same NullPointerException.



  println("\nStep 2: How to use Option and None - a basic example")
  val glazedDonutName: Option[String] = None
  println(s"Glazed Donut name = ${glazedDonutName.getOrElse("Glazed Donut")}")
  // getOrElse() function from the Option to supply a default value.
  // If you were to call the get() function as shown in Step 1, you will get the exception java.util.NoSuchElementException: None.get.
  // At this point, you may be asking yourself that this exception is similar to your NullPointerException.
  // And you are absolutely right! But Pattern Matching is your friend :) let's look at Step 3 below.



  println("\nStep 3: How to use Pattern Matching with Option")
  glazedDonutName match {
    case Some(name) => println(s"Received donut name = $name")
    case None       => println(s"No donut name was found!")
  }
  // As a consumer of that function call, you would not know that the function could potentially return null.
  // By being explicit that such a function will return an Option,
  // any consumer of the function can avoid NullPointerException by using getOfElse() or Pattern Matching.


  println("\nTip 1: Filter None using map function")
  glazedDonutTaste.map(taste => println(s"glazedDonutTaste = $taste"))
  glazedDonutName.map(name => println(s"glazedDonutName = $name"))


}
