package scalademo

/**
  * /**
  * Created by Nadim Bahadoor on 28/06/2016.
  *
  *  Tutorial: Learn How To Use Traits For Dependency Injection Part 2 - Avoid Cake Pattern
  *
  * [[http://allaboutscala.com/tutorials/chapter-5-traits/scala-traits-depedency-injection-avoid-cake-pattern/]]
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
  */
object TraitPart2 extends App {

  // try to avoid cake pattern in scala
  // what is cake pattern? TODO:
  // https://coderwall.com/p/t_rapw/cake-pattern-in-scala-self-type-annotations-explicitly-typed-self-references-explained
  println("Step 1: How to define a class to encapsulate inventory services")
  class DonutInventoryService[T] {
    def checkStock(donut: T): Boolean = {
      println("DonutInventoryService->checkStock")
      true
    }
  }



  println("\nStep 2: How to define a class to encapsulate pricing services")
  class DonutPricingService[T] {
    def calculatePrice(donut: T): Double = {
      println("DonutPricingService->calculatePrice")
      2.50
    }
  }



  println("\nStep 3: How to define a class to encapsulate creating a donut order")
  class DonutOrderService[T] {
    def createOrder(donut: T, quantity: Int, price: Double): Int = {
      println(s"Saving donut order to database: donut = $donut, quantity = $quantity, price = $price")
      100 // the id of the booked order
    }
  }



  println("\nStep 4: How to define a class to encapsulate shopping cart services")
  class DonutShoppingCartService[T] (
    donutInventoryService: DonutInventoryService[T],
    donutPricingService: DonutPricingService[T],
    donutOrderService: DonutOrderService[T]) {

    def bookOrder(donut: T, quantity: Int): Int = {
      println("DonutShoppingCartService->bookOrder")

      donutInventoryService.checkStock(donut) match {
        case true =>
          val price = donutPricingService.calculatePrice(donut)
          donutOrderService.createOrder(donut, quantity, price) // the id of the booked order

        case false =>
          println(s"Sorry donut $donut is out of stock!")
          -100 // return some error code to identify out of stock
      }
    }
  }



  println("\nStep 5: How to define a trait to encapsulate all the services for Donut store")
  trait DonutStoreServices {
    val donutInventoryService = new DonutInventoryService[String]
    val donutPricingService = new DonutPricingService[String]
    val donutOrderService = new DonutOrderService[String]
    val donutShoppingCartService = new DonutShoppingCartService(donutInventoryService, donutPricingService, donutOrderService)
  }



  println("\nStep 6: How to define a facade to expose functionality of DonutStoreServices")
  trait DonutStoreAppController {
    this: DonutStoreServices =>

    def bookOrder(donut: String, quantity: Int): Int = {
      println("DonutStoreAppController->bookOrder")
      donutShoppingCartService.bookOrder(donut, quantity)
    }
  }


  println("\nStep 7: How to create a Donut store app which extends facade from Step 5 and injects the required donut services from Step 4")
  object DonutStoreApp extends DonutStoreAppController with DonutStoreServices



  println("\nStep 8: How to call the bookOrder method of the Donut store app from Step 7")
  DonutStoreApp.bookOrder("Vanilla Donut", 10)



  println("\nStep 9: Test DonutStoreApp by injecting a mocked version of DonutStoreServices")
  // You may be asking yourself why did we go through all the trouble of having a single facade?
  // One of the most obvious benefit is to make our DonutStoreApp easily testable.
  // All you need to do is to create a trait which will mock the DonutStoreServices.
  trait MockedDonutStoreServices extends DonutStoreServices {
    override val donutInventoryService: DonutInventoryService[String] = ???
    override val donutPricingService: DonutPricingService[String] = ???
    override val donutOrderService: DonutOrderService[String] = ???
    override val donutShoppingCartService: DonutShoppingCartService[String] = new DonutShoppingCartService[String](
      donutInventoryService, donutPricingService, donutOrderService)
  }

  // ??? a handy method that you dont know yet.
  // it will throw run time exception if you call it.


  println("\nStep 10: Create a Mocked Donut Store App and inject mocked donut services")
  object MockedDonutStoreApp extends DonutStoreAppController with MockedDonutStoreServices

}
