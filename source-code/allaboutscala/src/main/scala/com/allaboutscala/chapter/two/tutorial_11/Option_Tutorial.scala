package com.allaboutscala.chapter.two.tutorial_11

/**
  * Created by Nadim Bahadoor on 20/06/2016.
  *
  * Tutorial: Learn How To Use Option – Avoid Null
  *
  * [[http://allaboutscala.com/tutorials/chapter-2-learning-basics-scala-programming/scala-tutorial-learn-use-option-avoid-null/ Tutorial]]
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
  *
  */
object Option_Tutorial extends App {


  println("Step 1: How to use Option and Some - a basic example")
  val glazedDonutTaste: Option[String] = Some("Very Tasty")
  println(s"Glazed Donut taste = ${glazedDonutTaste.get}")
  // Because we have wrapped our String into an Option, to retrieve its value we call the get() function as shown above.
  // But calling get() in a production system is generally a code smell as you may end up with the same NullPointerException.



  println("\nStep 2: How to use Option and None - a basic example")
  val glazedDonutName: Option[String] = None
  println(s"Glazed Donut name = ${glazedDonutName.getOrElse("Glazed Donut")}")
  // getOrElse() function from the Option to supply a default value.
  // If you were to call the get() function as shown in Step 1, you will get the exception java.util.NoSuchElementException: None.get.
  // At this point, you may be asking yourself that this exception is similar to your NullPointerException.
  // And you are absolutely right! But Pattern Matching is your friend :) let's look at Step 3 below.



  println("\nStep 3: How to use Pattern Matching with Option")
  glazedDonutName match {
    case Some(name) => println(s"Received donut name = $name")
    case None       => println(s"No donut name was found!")
  }
  // As a consumer of that function call, you would not know that the function could potentially return null.
  // By being explicit that such a function will return an Option,
  // any consumer of the function can avoid NullPointerException by using getOfElse() or Pattern Matching.


  println("\nTip 1: Filter None using map function")
  glazedDonutTaste.map(taste => println(s"glazedDonutTaste = $taste"))
  glazedDonutName.map(name => println(s"glazedDonutName = $name"))


}
