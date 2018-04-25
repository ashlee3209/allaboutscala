package com.allaboutscala.chapter.two.tutorial_01

/**
  * Created by Nadim Bahadoor on 07/06/2016.
  *
  * Tutorial: Scala Basic Tutorial – How To Declare Variables And Types
  *
  * [[http://allaboutscala.com/tutorials/chapter-2-learning-basics-scala-programming/scala-basic-tutorial-declare-variables-types/ Tutorial]]
  *
  *
  * Copyright 2016 Nadim Bahadoor (http://allaboutscala.com)
  *
  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
  * use this file except in compliance with the License. You may obtain a copy of
  * the License at
  *
  *  [http://www.apache.org/licenses/LICENSE-2.0]
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  * License for the specific language governing permissions and limitations under
  * the License.
  */
object VariablesBasics_Tutorial extends App {

  println("Step 1: Immutable variable")

  // syntax: val <Name of our variable>: <Scala type> = <Some literal>
  val donutsToBuy: Int = 5

//    donutsToBuy = 10 // you will get compile error because donutToBuy is immutable



  println("\nStep 2: Mutable variable")
  var favoriteDonut: String = "Glazed Donut"
  favoriteDonut = "Vanilla Donut"



  println("\nStep 3: lazy initialization")
  // We've not specified the type of donutService!
  // However, in this case, the Scala compiler knew that it should be of type String.
  // This is called type inference which we will see in the upcoming tutorials.
  lazy val donutService = "initialize some donut service"



  println("\nStep 4: Scala Types")
  // Scala does not have Java's primitives or .NET's built-in types.
  // Instead, it was designed from the ground up to have a set of classes for representing its supporting types as shown below:
  val donutsBought: Int = 5

  val bigNumberOfDonuts: Long = 100000000L

  val smallNumberOfDonuts: Short = 1

  val priceOfDonut: Double = 2.50

  val donutPrice: Float = 2.50f

  val donutStoreName: String = "Allaboutscala Donut Store"

  val donutByte: Byte = 0xa

  val donutFirstLetter: Char = 'D'

  val nothing: Unit = ()
  // Scala also provides a type called Unit.
  // For now, you can think of the Unit type similar to Java's or .NET's void keyword.



  println("\nStep 5: Declare a variable without initialization")
  // We've use the wildcard operator _ when defining our variable.
  // Somewhere later in our code base, we can then assign a String value for our variable which in our case was "Plain Donut" String value.
  // Now, this may be controversial as you should strive to use the immutable pattern.
  // As a matter of fact, when you code review some Scala application, you should always look out for code smell such as the use of var!
  // There are better ways to avoid the use of var which we will see in upcoming tutorials.
  var leastFavoriteDonut: String = _

  leastFavoriteDonut = "Plain Donut"

}
