package scalademo

object FunctionMethodBasics extends App {

  println("1. Expressions vs Expression Blocks vs Functions vs Methods")

  println("\n1-1: Expressions are computable statements.")
  // Expressions are units of code that return a value
  // Statements are units of code that do not return a value
  // Scala prefer expressions.
  println("""1 + 1""")
  println(s"The result is ${1+1}")

  println("\n1-2. Blocks")
  // You can combine expressions by surrounding them with {}. We call this a block.
  // The result of the last expression in the block is the result of the overall block, too.
  println({
    val x = 1 + 1
    x + 1
  })

  println("\n1-2-1: Blocks can be nested")
  println({
    val x = 1 + 1
    println(s"Inside scope 1, x = $x");
    {
      val x = 2 + 2
      println(s"Inside scope 2, x = $x")
      x+1
    }
    x+1
  })



  println("\n1-3. Functions")
  // Functions are expressions that take parameters.
  println("\n1-3-1: Anonymous functions")
  //On the left of => is a list of parameters.
  // On the right is an expression involving the parameters.
  (x: Int) => x + 1

  println("\n1-3-2: Named functions")
  val addOne = (x: Int) => x + 1
  println(addOne(1))

  println("\n1-4. Methods")
  // Methods are defined with the def keyword.
  // def is followed by a name, parameter lists, a return type, and a body.
  def add(x: Int, y: Int): Int = x + y
  println(add(1, 2))

  // https://stackoverflow.com/questions/2529184/difference-between-method-and-function-in-scala

  println("\n2. Define a method with parameters")
  println("2-1: A simple case")
  // Although we could have leveraged the type inference feature of Scala,
  // it is a good practice to be explicit about the return types of your method.
  def calculateDonutCost(donutName: String, quantity: Int): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    // make some calculations ...
    2.50 * quantity
  }

  // call it
  val totalCost = calculateDonutCost("Glazed Donut", 5)
  println(s"Total cost of donuts = $totalCost")

  println("\n2-2: How to add default values to parameters")
  def calculateDonutCost(donutName: String, quantity: Int, couponCode: String = "NO CODE"): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity, couponCode = $couponCode")

    // make some calculations ...
    2.50 * quantity
  }

  // call it
  val totalCostWithDiscount = calculateDonutCost("Glazed Donut", 4, "COUPON_12345")
  val totalCostWithoutDiscount = calculateDonutCost("Glazed Donut", 4)
  // you dont need to use method overloading to achieve the same desired effect.


  println("\n3. Method with option parameters")
  println("3-1: A simple case")
  def calculateDonutCost(donutName: String, quantity: Int, couponCode: Option[String]): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    couponCode match {
      case Some(coupon) =>
        val discount = 0.1 // Let's simulate a 10% discount
        val totalCost = 2.50 * quantity * (1 - discount)
        totalCost

      case None => 2.50 * quantity
    }
  }

  // call it with None
  println(s"""Total cost = ${calculateDonutCost("Glazed Donut", 5, None)}""")

  println("\n3-2: You can assign a default value to an Option parameter")
  def calculateDonutCostWithDefaultOptionValue(donutName: String, quantity: Int, couponCode: Option[String] = None): Double = {
    println(s"Calculating cost for $donutName, quantity = $quantity")

    couponCode match{
      case Some(coupon) =>
        val discount = 0.1 // Let's simulate a 10% discount
      val totalCost = 2.50 * quantity * (1 - discount)
        totalCost

      case _ => 2.50 * quantity
    }
  }

  // call it with default/Some
  println(s"""Total cost = ${calculateDonutCostWithDefaultOptionValue("Glazed Donut", 5)}""")
  println(s"""Total cost with discount = ${calculateDonutCostWithDefaultOptionValue("Glazed Donut", 5, Some("COUPON_1234"))}""")

  println("\n4. Call Method with option return type")
  // Since there may or may not be a daily coupon code available,
  // it would be a good idea for the users of our dailyCouponCode() method
  // to be aware explicitly of the possibility that the daily coupon code may be empty.
  def dailyCouponCode(): Option[String] = {
    // look up in database if we will provide our customers with a coupon today
    val couponFromDb = "COUPON_1234"
    Option(couponFromDb).filter(_.nonEmpty)
  }

  println("\n4-1: Call it with Option return type using getOrElse")
  val todayCoupon: Option[String] = dailyCouponCode()
  println(s"Today's coupon code = ${todayCoupon.getOrElse("No Coupon's Today")}")

  println("\n4-2: Call it with Option return type using pattern matching")
  dailyCouponCode() match {
    case Some(couponCode) => println(s"Today's coupon code = $couponCode")
    case None => println(s"Sorry there is no coupon code today!")
  }

  println("\n4-3: Call it with Option return type using map")
  // When using the getOrElse() function or pattern matching on a function which returns an Option,
  // you will need to provide the default or None case.
  //However, if you only care about valid values from the Option, you can use the map() function
  dailyCouponCode().map(couponCode => println(s"Today's coupon code = $couponCode"))

  println("\n5. Typed Method")
  println("5-1: You can create methods with the same name which takes a String or Double parameter")
  def applyDiscount(couponCode: String) {
    println(s"Lookup percentage discount in database for $couponCode")
  }

  def applyDiscount(percentageDiscount: Double) {
    println(s"$percentageDiscount discount will be applied")
  }

  // call them
  applyDiscount("COUPON_1234")
  applyDiscount(10)

  println("\n5-2: How to define a generic typed method")
  // we will create a typed method which will specify a generic parameter of type T as follows:
  def applyDiscount[T](discount: T) {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")

      case d: Double =>
        println(s"$d discount will be applied")

      case _ =>
        println("Unsupported discount type")
    }
  }

  // call it with different types
  applyDiscount[String]("COUPON_123")
  applyDiscount[Double](10)

  println("\n5-3: How to define a method which also has a generic return type")
  def applyDiscountWithReturnType[T](discount: T): Seq[T] = {
    discount match {
      case d: String =>
        println(s"Lookup percentage discount in database for $d")
        Seq[T](discount)

      case d: Double =>
        println(s"$d discount will be applied")
        Seq[T](discount)

      case d @ _ =>
        println("Unsupported discount type")
        Seq[T](discount)
    }
  }

  // call it
  println(s"Result of applyDiscountWithReturnType with String parameter = ${applyDiscountWithReturnType[String]("COUPON_123")}")
  println()
  println(s"Result of applyDiscountWithReturnType with Double parameter = ${applyDiscountWithReturnType[Double](10.5)}")
  println()
  println(s"Result of applyDiscountWithReturnType with Char parameter = ${applyDiscountWithReturnType[Char]('U')}")

  println("\n6. Multiple parameter list (currying)")
  // TODO: add more from other couses
  println("6-1: Curried parameter groups")
  // Functions defined with parameter groups are also commonly referred to as curried functions.
  def totalCost(donutType: String)(quantity: Int)(discount: Double): Double = {
    println(s"Calculating total cost for $quantity $donutType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }

  // When calling a curried function,
  // you will need to fill in its parameters by enclosing each parameter within () as shown below:
  println(s"Total cost = ${totalCost("Glazed Donut")(10)(0.1)}")

  println("\n6-2: Partially applied function")
  // one common application of curried function is
  // to be a building block where you can reuse functions by creating partial functions.
  val totalCostForGlazedDonuts = totalCost("Glazed Donut") _
  // this was defined as a value function using the val keyword as opposed to the def function.
  // In upcoming tutorials, we will show additional examples of value functions.
  // this is a partial function because it only fills in the first parameter Glazed Donut.
  // It uses the wildcard _ as a placeholder for the other parameters.

  // you do not need to fill in the first parameter for the donutType String parameter
  // as you have already pre-filled it with the Glazed Donut String value in Step 3.
  println(s"Total cost for Glazed Donuts ${totalCostForGlazedDonuts(10)(0.1)}")


  // F(x,y,z) a function has three inputs
  // F(x = x1, y, z) a function has two inputs. =>
  // G(y,z) = F(x = x1, y, z)
  // G(y,z) is a partially specified version of F(x,y,z)

  // example in scala tour

  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)

  def modN(n: Int)(x: Int) = ((x % n) == 0)

  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
  println(filter(nums, modN(2)))
  println(filter(nums, modN(3)))

  // modN(2) => p(x) = ((x%2) == 0)

}
