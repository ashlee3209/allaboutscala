package scalademo

object Classes extends App {

  println("1. A simple class")
  // Classes in Scala are blueprints for creating objects.

  println("\n1-1: How to define a simple class to represent a Donut object")
  class Donut(name: String, productCode: Long) {

    def print = println(s"Donut name = $name, productCode = $productCode")

  }

  println("\n1-2: How to create instances of Donut class")
  // The keyword new is used to create an instance of the class.
  val glazedDonut = new Donut("Glazed Donut", 1111)
  val vanillaDonut = new Donut("Vanilla Donut", 2222)



  println("\n1-3: How to call the print function for each of the donut object")
  glazedDonut.print
  vanillaDonut.print



  println("\n1-4: How to access the properties of class Donut")
  // glazedDonut.name
  // glazedDonut.productCode

  // NOTE:
  // - You will get compiler error as we have not exposed the name and productCode properties using getters.
  // - We will introduce Companion objects and Case Class in upcoming tutorials to automatically provide accessor methods.


  println("\n2: How to declare a companion object for the Donut class")
  // A Companion Object is defined using the object keyword and the name of the object should be identical to the class name.
  // Conversely, the class is the object’s companion class.
  // They are defined in the same source file.
  // In addition, the companion object should define an apply() method which will be responsible for instantiating an instance of the given class.
  object Donut {

    def apply(name: String, productCode: Long): Donut = {
      new Donut(name, productCode)
    }

  }

  // A companion class or object can access the private members of its companion.
  // Use a companion object for methods and values which are not specific to instances of the companion class.


  println("\n2-1: How to create instances of the Donut class using the companion object")
  // you can now create instances of the Donut class without having to use the new keyword.
  val glazedDonutO = Donut("Glazed Donut", 1111)
  val vanillaDonutO = Donut("Vanilla Donut", 2222)

  println("\n3: Use companion objects' apply method as a factory")
  // create a new class with an option parameter
  class Donut2(name: String, productCode: Option[Long] = None){

    def print = println(s"Donut name = $name, productCode = ${productCode.getOrElse(0)}")

  }

  // declare class hierarchy through inheritance using extends keyword
  // create two sub-classes
  class GlazedDonut(name: String) extends Donut2(name)

  class VanillaDonut(name: String) extends Donut2(name)


  // declare apply method of companion object as a factory
  object Donut2 {

    def apply(name: String): Donut2 = {
      name match {
        case "Glazed Donut" => new GlazedDonut(name)
        case "Vanilla Donut" => new VanillaDonut(name)
        case _ => new Donut2(name)
      }
    }

  }

  // call the apply factory method of the companion object same as call the companion object
  val glazedDonut2 = Donut2("Glazed Donut")
  println(s"The class type of glazedDonut = ${glazedDonut2.getClass}")
  glazedDonut2.print

  val vanillaDonut2 = Donut2("Vanilla Donut")
  println(s"The class type of vanillaDonut = ${vanillaDonut2.getClass}")
  vanillaDonut2.print

  println("\n4. Declare values and fields in companion object")
  // update the Donut class's print method to printout a uuid.
  class Donut4(name: String, productCode: Option[Long] = None){

    def print = println(s"Donut name = $name, productCode = ${productCode.getOrElse(0)}, uuid = ${Donut4.uuid}")

  }

  // Declare the uuid as a private value in the companion object which can be accessed in the class.
  object Donut4 {

    private val uuid = 1 // we've declared a uuid value and it is also marked as private.

    def apply(name: String, productCode: Option[Long]): Donut4 = {
      new Donut4(name, productCode)
    }

    def apply(name: String): Donut4 = {
      new Donut4(name)
    }
  }

  // create instances
  val glazedDonut4 = Donut4("Glazed Donut", Some(1111))
  val vanillaDonut4 = Donut4("Vanilla Donut")

  glazedDonut4.print
  vanillaDonut4.print

  println("\n5. Case classes")
  // A case class is similar to any other classes except that it also creates the Companion Object.
  // In addition, a case class will automatically create the apply(),  toString(), hashCode and equals() methods for you.
  println("\n5-1: Define a case class to represent a Donut object")
  case class DonutCase(name: String, price: Double, productCode: Option[Long] = None)

  println("\n5-2: How to create instances or objects for the Donut case class")
  val vanillaDonut5: DonutCase = DonutCase("Vanilla Donut", 1.50)
  val glazedDonut5: DonutCase = DonutCase("Glazed Donut", 2.0)
  println(s"Vanilla Donut = $vanillaDonut5")
  println(s"Glazed Donut = $glazedDonut5")
  // You did not have to use the new keyword when creating instances of the Donut case class.
  // The case class will automatically create the Companion Object.

  println("\n5-3: How to access fields of the Donut object")
  // you do not have to write any boiler plate accessor code
  println(s"Vanilla Donut name field = ${vanillaDonut5.name}")
  println(s"Vanilla Donut price field = ${vanillaDonut5.price}")
  println(s"Vanilla Donut productCode field = ${vanillaDonut5.productCode}")

  println("\n5-4: By default, you can not modify or update fields of the Donut object")
  // fields defined on case class are immutable by default and as such you cannot modify them.

  // vanillaDonut.name = "vanilla donut" // compiler error. fields are immutable by default.

  println("\n5-5: How to create a new object of Donut by using the copy() method of the case class")
  val chocolateVanillaDonut: DonutCase = vanillaDonut5.copy(name = "Chocolate And Vanilla Donut", price = 5.0)
  println(s"Chocolate And Vanilla Donut = $chocolateVanillaDonut")

  println("\n6. Type alias")
  // TODO: remove?
  println("\n6-1: Use type alias to name a Tuple2 pair into a domain type called CartItem")
  type CartItem[DonutCase, Int] = (DonutCase, Int)

  println("\n6-2: How to create instances of the aliased typed CartItem")
  val cartItem = new CartItem(vanillaDonut5, 4)
  println(s"cartItem = $cartItem")
  println(s"cartItem first value = ${cartItem._1}")
  println(s"cartItem second value = ${cartItem._2}")

  println("\n6-3: How to use an aliased typed into a function parameter")
  def calculateTotal(shoppingCartItems: Seq[CartItem[DonutCase, Int]]): Double = {
    // calculate the total cost
    shoppingCartItems.foreach { cartItem =>
      println(s"CartItem donut = ${cartItem._1}, quantity = ${cartItem._2}")
    }
    10 // some random total cost
  }

  println("\n6-4: How to use a case class instead of an aliased typed")
  case class ShoppingCartItem(donut: DonutCase, quantity: Int)

  val shoppingItem: ShoppingCartItem = ShoppingCartItem(DonutCase("Glazed Donut", 2.50), 10)
  println(s"shoppingItem donut = ${shoppingItem.donut}")
  println(s"shoppingItem quantity = ${shoppingItem.quantity}")

  println("\n6-5: Represent a Sequence of Donut items in a shopping cart")
  def calculateTotal2(shoppingCartItems: Seq[ShoppingCartItem]): Double = {
    // calculate the total cost
    shoppingCartItems.foreach { shoppingCartItem =>
      println(s"ShoppingCartItem donut = ${shoppingCartItem.donut}, quantity = ${shoppingCartItem.quantity}")
    }
    10 // some random total cost
  }

  println("\n7. Implicit class")
  // how use Implicit Class to add methods to an object without modifying the source code of the object
  // - also commonly known as extension methods.
  println("\n7-1: Define an implicit class to augment or extend the Donut object with a uuid field")
  object DonutImplicits{
    implicit class AugmentedDonut(donut: DonutCase) {
      def uuid: String = s"${donut.name} - ${donut.productCode.getOrElse(12345)}"
    }
  }

  println("\n7-2: How to import and use the implicit class AugmentedDonut")
  import DonutImplicits._
  println(s"Vanilla donut uuid = ${vanillaDonut5.uuid}")

  println("\n8. Class Inheritance")
  println("\n8-1: Define an abstract class called DonutAbstract")
  // Any class which extends the abstract class Donut
  // will have to provide an implementation for the printName method.
  abstract class DonutAbstract(name: String) {

    def printName: Unit

  }

  println("\n8-2: Extend abstract class DonutAbstract and define a sub-class of DonutAbstract called VanillaDonut")
  class VanillaDonut8(name: String) extends DonutAbstract(name) {

    override def printName: Unit = println(name)

  }

  object VanillaDonut8 {

    def apply(name: String): DonutAbstract = {
      new VanillaDonut8(name)
    }

  }

  println("\n8-3: Extend abstract class DonutAbstract and define another sub-class of DonutAbstract called GlazedDonut")
  class GlazedDonut8(name: String) extends DonutAbstract(name) {

    override def printName: Unit = println(name)

  }

  object GlazedDonut8 {

    def apply(name: String): DonutAbstract = {
      new GlazedDonut8(name)
    }

  }

  println("\n8-4: Instantiate DonutAbstract objects")
  val vanillaDonut8: DonutAbstract = VanillaDonut8("Vanilla Donut")
  vanillaDonut8.printName

  val glazedDonut8: DonutAbstract = GlazedDonut8("Glazed Donut")
  glazedDonut8.printName
  // Since VanillaDonut and GlazedDonut are sub-classes of the base class Donut,
  // they both have access to the printName method.

  println("\n9. Case Class Inheritance")
  println("\n9-1: Extend the abstract class DonutAbstract and define a case class named VanillaDonutCase")
  case class VanillaDonutCase(name: String) extends DonutAbstract(name) {

    override def printName: Unit = println(name)

  }

  println("\n9-2: Extend the abstract class DonutAbstract and define a case class named GlazedDonutCase")
  case class GlazedDonutCase(name: String) extends DonutAbstract(name) {

    override def printName: Unit = println(name)

  }

  // we did not have to create a Companion Object for the VanillaDonut case class
  // because the case class will automatically provide a companion object.

  println("\n9-3: How to instantiate Donut objects")
  val vanillaDonut9: DonutAbstract = VanillaDonutCase("Vanilla Donut")
  vanillaDonut9.printName

  val glazedDonut9: DonutAbstract = GlazedDonutCase("Glazed Donut")
  glazedDonut9.printName

  // Avoid having a case class extend other case classes.
  // Instead, encapsulate common behaviour in an abstract class - see Scala Documentation.
  // However, we will show how Scala provides greater flexibility to class inheritance by making use of mix-in with traits.


}
