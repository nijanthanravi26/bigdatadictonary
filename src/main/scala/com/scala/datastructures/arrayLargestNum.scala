package com.scala.datastructures

object arrayLargestNum {

  def main (args:Array[String]): Unit ={

    val given = Array(3,6,9,7,67,8,20)

    var largest = 0

    for ( i <- 0 to (given.length -1))
      {
        if ( given(i) > largest)
          {
            largest=given(i)
          }
      }
    print("the largest number is "+largest)

  }

}
