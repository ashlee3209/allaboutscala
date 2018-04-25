package scalademo

object ImmutableCollections extends App {

  println("1. List")
  // initialize an immutable List with 3 elements
  val list1: List[String] = List("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of list1 = $list1 and its size is ${list1.length}")

  // access elements of immutable List at specific index
  println(s"Element at index 0 = ${list1(0)}")
  println(s"Element at index 1 = ${list1(1)}")
  println(s"Element at index 2 = ${list1(2)}")

  // another way to create a list
  // :: Adds an element at the beginning of this list.
  // Nil is an empty list
  val list2 =  "Plain Donut"::"Strawberry Donut"::"Chocolate Donut":: Nil
  println(s"Elements of list2 = $list2 and its size is ${list2.length}")

  // append elements at the end of immutable List using :+
  val list3: List[String] = list1 :+ "Vanilla Donut"
  println(s"Append elements at the end using :+ = $list3")

  // prepend elements at the front of immutable List using +:
  val list4: List[String] = "Vanilla Donut" +: list1
  println(s"Prepend elements at the front using +: = $list4")

  // add two immutable Lists together using :::
  // Adds the elements of a given list in front of this list.
  val list5: List[String] = list1 ::: list3
  println(s"Add two lists together using ::: = $list5")

  // add two immutable Lists together using ++
  val list6: List[String] = list1 ++ list3
  println(s"Add two lists together using ++ = $list6")

  // initialize an empty immutable List
  val emptyList: List[String] = List.empty[String]
  println(s"Empty list = $emptyList")

  println("2. Map")

  // initialize a Map with 3 elements
  val map1: Map[String, String] = Map(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))
  println(s"Elements of map1 = $map1")

  // initialize Map using key -> value notation")
  val map2: Map[String, String] = Map("VD"-> "Vanilla Donut", "GD" -> "Glazed Donut")
  println(s"Elements of map2 = $map2")

  // access elements by specific key")
  println(s"Element by key VD = ${map2("VD")}")
  println(s"Element by key GD = ${map2("GD")}")

  // add elements using +
  val map3: Map[String, String] = map1 + ("KD" -> "Krispy Kreme Donut")
  println(s"Element in map3 = $map3")

  // add two Maps together using ++
  val map4: Map[String, String] = map1 ++ map2
  println(s"Elements in map4 = $map4")

  // remove key and its value from map using -
  val map5: Map[String, String] = map4 - ("CD")
  println(s"Map without the key CD and its value = $map5")

  // initialize an empty Map
  val emptyMap: Map[String,String] = Map.empty[String,String]
  println(s"Empty Map = $emptyMap")

  println("3. Set")

  // initialize a Set with 3 elements
  val set1: Set[String] = Set("Plain Donut","Strawberry Donut","Chocolate Donut")
  println(s"Elements of set1 = $set1")

  // check specific elements exists in Set
  println(s"Element Plain Donut = ${set1("Plain Donut")}")
  println(s"Element Strawberry Donut = ${set1("Strawberry Donut")}")
  println(s"Element Chocolate Donut = ${set1("Chocolate Donut")}")

  // add elements in Set using +
  val set2: Set[String] = set1 + "Vanilla Donut" + "Vanilla Donut"
  println(s"Adding elements to Set using + = $set2")

  // add two Sets together using ++
  val set3: Set[String] = set1 ++ Set[String]("Vanilla Donut", "Glazed Donut")
  println(s"Add two Sets together using ++ = $set3")

  // remove element from Set using -
  val set4: Set[String] = set1 - "Plain Donut"
  println(s"Set without Plain Donut element = $set4")

  // find the intersection between two Sets using &
  val set5: Set[String] = Set("Vanilla Donut", "Glazed Donut", "Plain Donut")
  println(s"Intersection of set1 and set5 = ${set1 & set5}")

  // find the difference between two Sets using &~
  println(s"Difference between set1 and set5 = ${set1 &~ set5}")

  // initialize an empty Set
  val emptySet: Set[String] = Set.empty[String]
  println(s"Empty Set = $emptySet")

  // There are also other collections like ListSet, ListMap, Queue, HashMap, HashSet, SortedSet, Stack, Stream, Vector, etc. See API doc.
}
