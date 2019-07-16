package com.spark.practice

import org.apache.spark._
import org.apache.spark.SparkContext._

object friendsByAge {

  def main(args:Array[String]): Unit = {

    val sc = new SparkContext("local[*]","FriendsByAge")



  }

}
