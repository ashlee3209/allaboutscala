package com.allaboutscala.chapter.three

import scala.util.Random

/**
  * Created by Nadim Bahadoor on 28/06/2016.
  *
  *  Tutorial: Learn How To Create Call By Name Function
  *
  * [[http://allaboutscala.com/tutorials/chapter-3-beginner-tutorial-using-functions-scala/scala-tutorial-learn-create-call-name-function/ Tutorial]]
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
object FunctionByName_Tutorial extends App {


  println("Step 1: How to define a List with Tuple3 elements")
  // Let's start by defining a List containing Tuple3 elements
  // which would represent the name of a donut, the quantity to be purchased and its price.
  val listOrders = List(("Glazed Donut", 5, 2.50), ("Vanilla Donut", 10, 3.50))



  println("\nStep 2: How to define a function to loop through each Tuple3 of the List and calculate total cost")
  // Assume that your donut store sells donuts worldwide
  // and as such you need to convert the total cost of buying donuts to the local currency being used.
  def placeOrder(orders: List[(String, Int, Double)])(exchangeRate: Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }



  println("\nStep 3: How to call function with curried group parameter for List of Tuple3 elements")
  println(s"Total cost of order = £${placeOrder(listOrders)(0.5)}")



  println("\nStep 4: How to define a call-by-name function")
  // The call-by-name function parameter exchangeRate: => Double will evaluate any exchangeRate function each time it is called.
  // This is in contrast to the function defined in Step 2 above which had a call-by-value function parameter for exchange rate.
  // This meant that any exchange rate passed through would be evaluated only once.
  def placeOrderWithByNameParameter(orders: List[(String, Int, Double)])(exchangeRate: => Double): Double = {
    var totalCost: Double = 0.0
    orders.foreach {order =>
      val costOfItem = order._2 * order._3 * exchangeRate
      println(s"Cost of ${order._2} ${order._1} = £$costOfItem")
      totalCost += costOfItem
    }
    totalCost
  }


  println("\nStep 5: Define a simple USD to GBP function")
  val randomExchangeRate = new Random(10)
  def usdToGbp: Double = {
    val rate = randomExchangeRate.nextDouble()
    println(s"Fetching USD to GBP exchange rate = $rate")
    rate
  }



  println("\nStep 6: How to call function with call-by-name parameter")
  // For each order in the list, a new exchange rate is being created
  // and that's because the call-by-name function usdToGbp function is being evaluated each time.
  println(s"By name: Total cost of order = £${placeOrderWithByNameParameter(listOrders)(usdToGbp)}")
  println()
  println(s"By value: Total cost of order = £${placeOrder(listOrders)(usdToGbp)}")
}
