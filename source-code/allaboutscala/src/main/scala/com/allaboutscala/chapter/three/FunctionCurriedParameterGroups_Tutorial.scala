package com.allaboutscala.chapter.three

/**
  * Created by Nadim Bahadoor on 28/06/2016.
  *
  *  Tutorial: Learn How To Create Function Currying With Parameter Groups
  *
  * [[http://allaboutscala.com/tutorials/chapter-3-beginner-tutorial-using-functions-scala/scala-tutorial-create-function-currying-parameter-groups/ Tutorial]]
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
object FunctionCurriedParameterGroups_Tutorial extends App {

  println("Step 1: How to define function with curried parameter groups")
  // Functions defined with parameter groups are also commonly referred to as curried functions.
  def totalCost(donutType: String)(quantity: Int)(discount: Double): Double = {
    println(s"Calculating total cost for $quantity $donutType with ${discount * 100}% discount")
    val totalCost = 2.50 * quantity
    totalCost - (totalCost * discount)
  }



  println("\nStep 2: How to call a function with curried parameter groups")
  // When calling a curried function,
  // you will need to fill in its parameters by enclosing each parameter within () as shown below:
  println(s"Total cost = ${totalCost("Glazed Donut")(10)(0.1)}")



  println("\nStep 3: How to create a partial function from a function with curried parameter groups")
  // one common application of curried function is
  // to be a building block where you can reuse functions by creating partial functions.
  val totalCostForGlazedDonuts = totalCost("Glazed Donut") _
  // this was defined as a value function using the val keyword as opposed to the def function.
  // In upcoming tutorials, we will show additional examples of value functions.
  // this is a partial function because it only fills in the first parameter Glazed Donut.
  // It uses the wildcard _ as a placeholder for the other parameters.


  println("\nStep 4: How to call a partial function")
  // you do not need to fill in the first parameter for the donutType String parameter
  // as you have already pre-filled it with the Glazed Donut String value in Step 3.
  println(s"Total cost for Glazed Donuts ${totalCostForGlazedDonuts(10)(0.1)}")
}
