package com.spark.practice

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

object sparkL2 {

  def main(args:Array[String]): Unit ={

    val spark = SparkSession.builder().master("local").appName("Spark L2").getOrCreate()

    //Reading file using csv format which gives DF with schema
    val csvData= spark.read.option("header","true").csv("C:\\Users\\nijanthan16\\Downloads\\spark L2\\climatic dataset.csv")

    val textData=spark.read.textFile("C:\\Users\\nijanthan16\\Downloads\\spark L2\\climatic dataset.csv")
    textData.show()
  }

}
