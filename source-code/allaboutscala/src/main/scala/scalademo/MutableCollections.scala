package scalademo

object MutableCollections extends App {

  println("1. Array")

  // initialize a String Array with 3 elements
  val array1: Array[String] = Array("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of array1 = ${array1.mkString(", ")}")

  // access elements at specific index in an Array
  println(s"Element at index 0 = ${array1(0)}")
  println(s"Element at index 1 = ${array1(1)}")
  println(s"Element at index 2 = ${array1(2)}")

  // initialize an Array by specifying it's capacity
  val array2: Array[String] = new Array(3)
  array2(0) = "Plain Donut"
  array2(1) = "Strawberry Donut"
  array2(2) = "Chocolate Donut"
  println(s"Elements of array2 = ${array2.mkString(", ")}")

  // update an element in the array
  array2(2) = "Vanilla Donut"
  println(s"Elements of array2 = ${array2.mkString(", ")}")

  // create a 2D Array (2 dimension array)
  val rows = 2
  val columns = 2
  val array3: Array[Array[String]] = Array.ofDim[String](rows,columns)
  array3(0)(0) = "Plain"
  array3(0)(1) = "Donut"
  array3(1)(0) = "Strawberry"
  array3(1)(1) = "Donut"
  println(s"Elements of 2 dimensional array = ${array3.deep.toList}")

  // create Array using Range
  val rangeArray: Array[Int] = (1 to 10).toArray[Int]
  println(s"Array using Range from 1 to 10 = ${rangeArray.mkString(", ")}")

  // iterate over an Array using for comprehension
  for(d <- array1){
    println(s"d = $d")
  }

  // merge two Arrays using Array.concat
  val moreDonutsArray: Array[String] = Array("Vanilla Donut","Glazed Donut")
  val mergedDonutArray: Array[String] = Array.concat(array1, moreDonutsArray)
  println(s"Merged Array of donuts = ${mergedDonutArray.mkString(", ")}")

  // check if two Arrays are equal
  val arrayToCompare = Array[String]("Plain Donut","Strawberry Donut","Chocolate Donut")

  println(s"using == ${array1 == moreDonutsArray}") // prints false

  println(s"using == ${array1 == arrayToCompare}") // ALSO prints false ??? what ... be careful

  println(s"using sameElement function = ${array1 sameElements arrayToCompare}") // NOW this works and returns true!

  // check if two Arrays are equal using deep function and ==
  println(s"using deep function = ${array1.deep == arrayToCompare.deep}")

  println("2. ArrayBuffer")
  // both array and arraybuffere are mutable, but arraybuffer is resizable while array isn't.
  // If you add an element to arraybuffer, it gets larger.
  // If you add to array, you get a new array.
  import scala.collection.mutable.ArrayBuffer

  // initialize an ArrayBuffer with 3 elements
  val arrayBuffer1: ArrayBuffer[String] = ArrayBuffer("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  // add elements to an ArrayBuffer using +=
  arrayBuffer1 += "Vanilla Donut"
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")
  // NOTE: arrayBuffer1 is mutable and hence we were able to add a new element to it

  // add elements from a List to an ArrayBuffer using ++=
  arrayBuffer1 ++= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  // remove elements from an ArrayBuffer
  arrayBuffer1 -= "Plain Donut"
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  // remove elements of a List from ArrayBuffer using --=
  arrayBuffer1 --= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of arrayBuffer1 = $arrayBuffer1")

  println("2. ListBuffer")
  // Mutable List

  import scala.collection.mutable.ListBuffer
  // initialize a ListBuffer with 3 elements
  val listBuffer1: ListBuffer[String] = ListBuffer("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of listBuffer1 = $listBuffer1")

  // add elements to a ListBuffer using +=
  listBuffer1 += "Vanilla Donut"
  println(s"Elements of listBuffer1 = $listBuffer1")

  // add elements from a List to a ListBuffer using ++=
  listBuffer1 ++= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of listBuffer1 = $listBuffer1")

  // remove elements from a ListBuffer
  listBuffer1 -= "Plain Donut"
  println(s"Elements of listBuffer1 = $listBuffer1")

  // remove elements from a List to a ListBuffer using --=
  listBuffer1 --= List[String]("Glazed Donut", "Krispy creme")
  println(s"Elements of listBuffer1 = $listBuffer1")

  println("3. Mutable Map")

  import scala.collection.mutable.Map
  // initialize a Map with 3 elements
  val map1: Map[String, String] = Map(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))
  println(s"Elements of map1 = $map1")

  // initialize a Map using key -> value notation
  val map2: Map[String, String] = Map("VD"-> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of map2 = $map2")

  // access elements of Map by specific key
  println(s"Element by key VD = ${map2("VD")}")
  println(s"Element by key GD = ${map2("GD")}")

  // add elements to Map using +=
  map1 += ("KD" -> "Krispy Kreme Donut")
  println(s"Element in map1 = $map1")

  // add elements from a Map to an existing Map using ++=
  map1 ++= map2
  println(s"Elements in map1 = $map1")

  // remove key and its value from Map using -=
  map1 -= "CD"
  println(s"Map without the key CD and its value = $map1")

  println("4. Mutable Set")

  import scala.collection.mutable.Set
  // initialize a Set with 3 elements
  val set1: Set[String] = Set("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of set1 = $set1")

  // check specific elements in Set
  println(s"Element Plain Donut = ${set1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${set1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${set1("Chocolate Donut")}")

  // add elements to Set using +=
  set1 += "Vanilla Donut"
  println(s"Elements of set1 after adding elements Vanilla Donut = $set1")

  // add all elements from another Set using ++=
  set1 ++= Set[String]("Vanilla Donut", "Glazed Donut")
  println(s"Elements of set1 after adding second set = $set1")

  // remove element from Set using -=
  set1 -= "Plain Donut"
  println(s"Elements of set1 without Plain Donut element = $set1")

  // find the intersection between two Sets using &
  val set2: Set[String] = Set("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of set1 and set5 = ${set1 & set2}")

  // find the difference between two Sets using &~
  println(s"Difference of set1 and set2 = ${set1 &~ set2}")


}
