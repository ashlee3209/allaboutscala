package com.allaboutscala.chapter.three

/**
  * Created by Nadim Bahadoor on 26/06/2016.
  *
  *  Tutorial: Learn How To Create And Use Functions
  *
  * [[http://allaboutscala.com/tutorials/chapter-3-beginner-tutorial-using-functions-scala/scala-tutorial-learn-create-use-functions/ Tutorial]]
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
object CreateAndUseFunction_Tutorial extends App {


  println("Step 1: How to define and use a function which has a return type")
  // def <functionName>(<parameters>): <ReturnType> = {}
  // The last line within the body of the function is the one that will be returned back to the caller.
  def favoriteDonut(): String = {
    "Glazed Donut"
  }

  val myFavoriteDonut = favoriteDonut()
  println(s"My favorite donut is $myFavoriteDonut")



  println("\nStep 2: How to define and use a function with no parenthesis")
  // In general, you should define your functions without parenthesis
  // if you are defining a function that does not have any side effects.
  // Limit its usage to pure and simple functions only!
  def leastFavoriteDonut = "Plain Donut"
  println(s"My least favorite donut is $leastFavoriteDonut")



  println("\nStep 3: How to define and use a function with no return type")
  def printDonutSalesReport(): Unit = {
    // lookup sales data in database and create some report
    println("Printing donut sales report... done!")
  }
  printDonutSalesReport()



  println("\nStep 4: Use type inference to define function with no return type")
  // No the parenthesis () when calling the function printReport.
  // This feature becomes more useful and concise as you get to do function chaining which we will see in upcoming tutorials.
  def printReport {
    // lookup sales data in database and create some report
    println("Printing donut report... done!")
  }
  printReport



}
