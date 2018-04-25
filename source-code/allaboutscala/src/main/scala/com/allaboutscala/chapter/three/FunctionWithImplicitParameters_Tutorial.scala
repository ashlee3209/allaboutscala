package com.allaboutscala.chapter.three

/**
  * Created by Nadim Bahadoor on 28/06/2016.
  *
  *  Tutorial: Learn How To Create Function With Implicit Parameter
  *
  * [[http://allaboutscala.com/tutorials/chapter-3-beginner-tutorial-using-functions-scala/scala-tutorial-learn-create-function-implicit-parameter/ Tutorial]]
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
object FunctionWithImplicitParameters_Tutorial extends App {

  // The use of implicit parameters is just one example of how dependency injection can be achieved in Scala.

  println(s"Step 1: How to define a function with an implicit parameter")
  // The implicit parameter discount of type Double is defined using the keyword implicit within parenthesis after your usual function parameters.
  // This means that the totalCost() function will require an implicit value of type Double to be in scope as defined in Step 2 below.
  def totalCost(donutType: String, quantity: Int)(implicit discount: Double): Double = {
    println(s"Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }



  println("\nStep 2: How to define an implicit value")
  implicit val discount: Double = 0.1
  println(s"All customer will receive a ${discount * 100}% discount")



  println("\nStep 3: How to call a function which has an implicit parameter")
  // You did not have to manually pass-through the discount value when calling the totalCost() function.
  // The Scala compiler will look for an implicit value of type Double for the discount implicit parameter which you've defined in Step 2.
  // If there are no implicit values in scope, you will get a compiler error.
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost("Glazed Donut", 5)}""")



  println("\nStep 4: How to define a function which takes multiple implicit parameters")
  def totalCost2(donutType: String, quantity: Int)(implicit discount: Double, storeName: String): Double = {
    println(s"[$storeName] Calculating the price for $quantity $donutType")
    val totalCost = 2.50 * quantity * (1 - discount)
    totalCost
  }



  println("\nStep 5: How to call a function which takes multiple implicit parameters")
  implicit val storeName: String = "Tasty Donut Store"
  println(s"""Total cost with discount of 5 Glazed Donuts = ${totalCost2("Glazed Donut", 5)}""")



  println("\nStep 6: How to manually pass-through implicit parameters")
  println(s"""Total cost with discount of 5 Glazed Donuts, manually passed-through = ${totalCost2("Glazed Donut", 5)(0.1, "Scala Donut Store")}""")

  // It is a good practice to encapsulate your implicit values into an Object or a Package Object.
}
