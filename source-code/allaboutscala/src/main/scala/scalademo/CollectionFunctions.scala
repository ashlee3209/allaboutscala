package scalademo

object CollectionFunctions extends App {

  val donutList = List("Plain","Strawberry","Chocolate")

  println("1. Foreach")
  // foreach does not have a return value
  // loop through using wildcard
  donutList.foreach(println(_))
  // loop through all elements
  println()
  donutList.foreach(donutName => println(s"donutName = $donutName"))

  println("\n2. Map")
  // The map method takes a predicate function and applies it to every element in the collection.
  // It returns a new collection with the result of the predicate function applied to each and every element of the collection.

  // append the word Donut to each element using the map function
  val donuts2: Seq[String] = donutList.map(_ + " Donut")
  println(s"Elements of donuts2 = $donuts2")

  println("\n3. Flatten")
  // The flatten method will collapse the elements of a collection to create a single collection with elements of the same type.
  val donutList2 = List("Glazed", "Vanilla")

  // create a List of donuts initialized using the two Lists
  val listDonuts: List[Seq[String]] = List(donutList, donutList2)
  println(s"Elements of listDonuts = $listDonuts")

  // return a single list of donut using the flatten function
  val listDonutsFromFlatten: List[String] = listDonuts.flatten
  println(s"Elements of listDonutsFromFlatten = $listDonutsFromFlatten")

  // append the word Donut to each element of listDonuts using flatten and map functions
  val listDonutsFromFlatten2: List[String] = listDonuts.flatten.map(_ + " Donut")
  println(s"Elements of listDonutsFromFlatten2 = $listDonutsFromFlatten2")

  println("\n4. FlatMap")
  // map + 1-To-N transformation + flatten
  val owners = (donut: String) => {(1 to 5).map(i => donut + i.toString).toList}: List[String]
  // map will return a List of Seq
  println(s"Result from map function: ${donutList.map(owners)}")
  println(s"Result from flatmap function: ${donutList.flatMap(owners)}")
  println(s"Result from map + flatten: ${donutList.map(owners).flatten}")

  // Monad: Concept from category theroy, but you dont need to know that.
  // A simple definition: Something that has map and flatMap functions.
  // Different monad has different effect of map and flatMap.

  println("\n5. Partition")
  // def partition(p: (A) ⇒ Boolean): (Seq[A], Seq[A])
  // Partitions this traversable collection in two traversable collections according to a predicate.

  // initialize a sequence which contains donut names and prices
  val donutNamesAndPrices: Seq[Any] = Seq("Plain Donut", 1.5, "Strawberry Donut", 2.0, "Glazed Donut", 2.5)
  println(s"Elements of donutNamesAndPrices = $donutNamesAndPrices")

  // split the sequence by the element types using partition function
  val namesAndPrices: (Seq[Any], Seq[Any]) = donutNamesAndPrices.partition {
    case name: String => true
    case price: Double => false
  }
  println(s"Elements of namesAndPrices = $namesAndPrices")

  // access the returned sequences
  println(s"Donut names = ${namesAndPrices._1}")
  println(s"Donut prices = ${namesAndPrices._2}")

  // extract the pair returned by partition function
  val (donutNames, donutPrices) = donutNamesAndPrices.partition {
    case name: String => true
    case _ => false
  }
  println(s"donutNames = $donutNames")
  println(s"donutPrices = $donutPrices")

  println("\n6. Filter")
  // def filter(p: (A) ⇒ Boolean): List[A]
  // Selects all elements of this traversable collection which satisfy a predicate.

  // def filterNot(p: (A) ⇒ Boolean): List[A]
  // Selects all elements of this traversable collection which do not satisfy a predicate.

  // keep only Plain and Glazed Donuts using the filter method
  val listWithPlainAndGlazedDonut = listDonutsFromFlatten2.filter { donutName =>
    donutName.contains("Plain") || donutName.contains("Glazed")
  }
  println(s"List with Plain and Glazed donuts only = $listWithPlainAndGlazedDonut")

  // filter out element Vanilla Donut using the filterNot function
  val listWithoutVanillaDonut = listDonutsFromFlatten2.filterNot(donutName => donutName == "Vanilla Donut" )
  println(s"Sequence without vanilla donut = $listWithoutVanillaDonut")

  println("\n7. Scan")
  val someNumbers = List(10,20,30,40,50,60)
  // Scan, Fold, Reduce act on mutiple elements at a time.
  // All of them have left and right variants, in total 9 functions.

  // def scanRight[B, That](z: B)(op: (A, B) ⇒ B)(implicit bf: CanBuildFrom[List[A], B, That]): That
  // Produces a collection containing cumulative results of applying the operator going right to left.
  // 2 parameter groups: an initial value and a function object. The return value is a List.

  val scanr = someNumbers.scanRight(0)(_ - _)
  // Here the function object we specified computes the difference between the two operands.
  // The initial value we give 0 which will be used for the first iteration.
  // Right variant is evaluated right-associatively.

  // NOTE: scanRight method iterations
  // 0   = 0
  // 60 - 0 = 60 0
  // 50 - 60 = -10 60 0
  // 40 - (-10) = 50 -10 60 0
  // 30 - 50 = -20 50 -10 60 0
  // 20 - (-20) = 40 -20 50 -10 60 0
  // 10 - 40 = -30 40 -20 50 -10 60 0

  println(s"Elements of scanr = $scanr")

  val scanl = someNumbers.scanLeft(0)(_ - _)
  // Left variant is evaluated left-associatively.

  // NOTE: scanLeft method iterations
  // 0   = 0
  // 0 - 10 = 0 -10
  // -10 - 20 = 0 -10 -30
  // -30 - 30 = 0 -10 -30 -60
  // -60 - 40 = 0 -10 -30 -60 -100
  // -100 - 50 = 0 -10 -30 -60 -100 -150
  // -150 - 60 = 0 -10 -30 -60 -100 -150 -210
  println(s"Elements of scanl = $scanl")

  val scanv = someNumbers.scan(0)(_ - _)
  // is the same as scanLeft???
  println(s"Elements of scanv = $scanv")

  println("\n8. Fold")
  // do the exact the same thing as scan but return only the 'last' element of the result List
  // last = last iteration
  val foldr = someNumbers.foldRight(0)(_ - _)
  println(s"Return of foldRight = $foldr")

  val foldl = someNumbers.foldLeft(0)(_ - _)
  println(s"Return of foldLeft = $foldl")

  val foldv = someNumbers.fold(0)(_ - _)
  println(s"Return of fold = $foldv")

  println("\n9. Reduce")
  // similar to fold but no initial value
  // first two list elements as operands in the first step
  val reducer = someNumbers.reduceRight(_ - _)

  // NOTE: reduceRight method iterations
  // 50 - 60 = -10
  // 40 - (-10) =  50
  // 30 - 50 = -20
  // 20 - (-20) = 40
  // 10 - 40 = -30
  println(s"Return of reduceRight = $reducer")

  val reducel = someNumbers.reduceLeft(_ - _)

  // NOTE: reduceLeft method iterations
  // 10 - 20 = -10
  // -10 - 30 = -40
  // -40 - 40 = -80
  // -80 - 50 = -130
  // -130 - 60 = -190
  println(s"Return of reduceLeft = $reducel")

  val reducev = someNumbers.reduce(_ - _)
  println(s"Return of reduce = $reducev")

  println("\n10. zip")
  // def zip[B](that: GenIterable[B]): List[(A, B)]
  // Returns a list formed from this list and another iterable collection by combining corresponding elements in pairs.
  val donutPrice = List(1.5, 2.0, 2.5)
  val zippedDonutsAndPrices: List[(String, Double)] = donutList zip donutPrice
  println(s"Zipped donuts and prices = $zippedDonutsAndPrices")

  // use unzip method to un-merge a zipped collections
  val unzipped: (List[String], List[Double]) = zippedDonutsAndPrices.unzip
  println(s"Donut names unzipped = ${unzipped._1}")
  println(s"Donut prices unzipped = ${unzipped._2}")

  println("\n11. par")
  // def par: ParSeq[A]
  // Returns a parallel implementation of this collection.
  // Not always faster with a parallel implementation. Test your algorithm first.
  val v = Vector.range(0, 10)
  println("\nNormal result:")
  v.foreach(print)
  // you will always see the same result.

  // turn it into a parallel collection.
  // runs concurrently
  println("\nParallel result:")
  v.par.foreach(print)
  println()
  v.par.foreach(print)
  println()
  v.par.foreach{e => print(e); Thread.sleep(50)}

}
