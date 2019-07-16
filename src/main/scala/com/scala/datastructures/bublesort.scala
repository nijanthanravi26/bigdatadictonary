package com.scala.datastructures

object bublesort {

  def main(args:Array[String]): Unit = {

    val given = Array(6,3,5,4,7,1)
    var temp=0
    var check=false

    while(check==false) {
      check=true
      for (i <- 0 to (given.length - 2)) {
        if (given(i) > given(i + 1))
        // if 6 > 3
        {
          temp = given(i)
          // store 6 in temp
          given(i) = given(i + 1)
          // store 3 in 6th place
          given(i + 1) = temp
          // store 6 in 3th place
          check=false
        }
      }
    }

      for ( j <- given)
        {
          print(j+",")
        }

  }

}
