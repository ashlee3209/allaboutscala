package com.allaboutscala.chapter.two.tutorial_02

/**
  * Created by Nadim Bahadoor on 08/06/2016.
  *
  * Tutorial: Scala String Interpolation – Print And Format Variables
  *
  * [[http://allaboutscala.com/tutorials/chapter-2-learning-basics-scala-programming/scala-string-interpolation-print-format-variables/ Tutorial]]
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
object StringInterpolation_Tutorial extends App {

  println("Step 1: Using String interpolation to print a variable")
  val favoriteDonut: String = "Glazed Donut"
  println(s"My favorite donut = $favoriteDonut")



  println("\nStep 2: Using String interpolation on object properties")
  case class Donut(name: String, tasteLevel: String)

  val favoriteDonut2: Donut = Donut("Glazed Donut", "Very Tasty")
  println(s"My favorite donut name = ${favoriteDonut2.name}, tasteLevel = ${favoriteDonut2.tasteLevel}")



  println("\nStep 3: Using String interpolation to evaluate expressions")
  val qtyDonutsToBuy: Int = 10
  println(s"Are we buying 10 donuts = ${qtyDonutsToBuy == 10}")



  println("\nStep 4: Using String interpolation for formatting text")
  // similar to printf in other languages.
  // all variable references should be followed by a printf-style format string.
  val donutName: String = "Vanilla Donut"
  val donutTasteLevel: String = "Tasty"
  println(f"$donutName%20s $donutTasteLevel")



  println("\nStep 5: Using f interpolation to format numbers")
  val donutPrice: Double = 2.50
  println(s"Donut price = $donutPrice")
  println(f"Formatted donut price = $donutPrice%.2f")

  // The f interpolator makes use of the string format utilities available from Java.
  // The formats allowed after the % character are outlined in the Formatter javadoc.
  // If there is no % character after a variable definition a formatter of %s (String) is assumed.


  println("\nStep 6: Using raw interpolation")
  // The raw String interpolation will allow you to print any symbols within your String.
  println(raw"Favorite donut\t$donutName")


  // In addition to the three default string interpolators, users can define their own.
  // To define our own string interpolation, we simply need to create an implicit class that adds a new method to StringContext.
  // see https://docs.scala-lang.org/overviews/core/string-interpolation.html#advanced-usage

}
