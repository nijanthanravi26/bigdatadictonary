package com.spark.practice

object functions {

  def main(args:Array[String]): Unit = {


    def square(x:Int):Int = {
      x*x
    }

    // Higher order Function: pass function as paramater to other function

    def anonyFunc(x:Int, f : Int => Int) : Int= {
    f(x)
    }
    val result= anonyFunc(2,square)
    println(result)

println("********************* Options in scala **************")
    /* Scala option
    use the Option class when returning a value from a function that can be null.
    Simply stated, instead of returning one object when a function succeeds and null when it fails,
    your function should instead return an instance of an Option,
    where the instance is either:
        * An instance of the Scala Some class
        * An instance of the Scala None class
    Because Some and None are both children of Option
    */

    def optFunction( cho:String, quantity:Int, coupon:Option[String])={

      println(s"Price of "+quantity+" "+cho+" : ")

      coupon match {
        case Some(coup) =>
          val cost = 2.50 * quantity * (0.5)
          cost
        case None => 2.50 * quantity
      }
    }

    val discountCost = optFunction("5star",10,Some("Flat 50"))
    println(discountCost)
    val totalCost = optFunction("5star",10,None)
    println(totalCost)

    /*
    A Scala implicit method argument and field example
    --------------------------------------------------
    See through below example and read the below comments:

    As you can see from the code, defining a method argument as implicit makes it like a magnet:
    If an implicit field of the same type is in scope, it pulls it right in ... with the limit that
    it can only deal with one implicit field of the matching type at one time.

    Ref: https://alvinalexander.com/scala/scala-2.10-implicit-class-example
         https://alvinalexander.com/scala/scala-implicit-method-arguments-fields-example
     */
println("********************* IMPLICIT method argument and field **************")
    // Define a method that takes implicit string
    def maldiv(implicit s:String)={
      println(s"im going to visit "+s)
    }

    // call method with string
    maldiv("waterIsland")
    // OP : im going to visit waterIsland

    //Declare Implicit string field
    implicit val place = "paradise island"

    // call method without any argument
    maldiv
    // OP : im going to visit paradise island

    //Declare Implicit Int Field
    implicit val pincode = 600100

    maldiv
    // OP : im going to visit paradise island

    // Declare another Implicit string field
//    implicit val expensive = "Fun Island"
//
//    maldiv
    //    Error:(83, 5) ambiguous implicit values:
    //    both value expensive of type String
    //    and value place of type String

println("********************* IMPLICIT class **************")

    implicit class StringImprovements(s: String) {
     def increment = s.map(c => (c + 1).toChar)
     }

    val result1 = "HAL".increment
    println(result1)

println("********************* TYPED Functions ****************")
    /*
    Typed functions -> Functions whose parameter can take any datatype

    Example :  lets say i need a method which should take both Int & String type like below.
               But Declaring two methods with same name will throw error.
               So the solution here is TYPED Function

    def honeyMoon(place:String): Unit ={
      println("im going to "+place)
    }

    def honeyMoon(budget:Int): Unit ={
      println("My honeymoon budget is"+budget)
    }

    honeyMoon(10000)
    honeyMoon("maldives")
    */

    def honeyMoon[T](plan:T): Unit ={

      plan match {
        case d : String => println("im going to "+d)
        case d : Int => println("my honeymoon budget is "+d)
        case _ =>  println("unsupported discount type")
      }

    }
    //How to call a function which has typed parameters
    honeyMoon("maldives")
    honeyMoon(100000)
    // o/p:
    // ********************* TYPED Functions ****************
    //      im going to maldives
    //      my honeymoon budget is 100000


  }

}
