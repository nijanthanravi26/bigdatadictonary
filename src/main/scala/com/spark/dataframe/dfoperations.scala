package com.spark.dataframe

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object dfoperations {

  def main(args:Array[String]): Unit = {

    val sparkConf = new SparkConf()
                    .setAppName("DF Operations")
                    .setMaster("local[2]")

    val spark = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    // Read a csv file as dataframe with column names
    val fullData = spark.read.option("header",true).option("inferSchema",true).csv("C:\\SparkScala\\fakefriends.csv").toDF("id","name","age","count")
    /* Sample Data
    0,Will,33,385
    1,Jean-Luc,26,2
    2,Hugh,55,221
     */
    fullData.printSchema()
    /*
    root
     |-- id: integer (nullable = true)
     |-- name: string (nullable = true)
     |-- age: integer (nullable = true)
     |-- count: integer (nullable = true)
     */
    //create subset of data
    val data=fullData.filter("age >20 and age <35")
    data.cache()
      // Select & filter from dataframe
    val ageCount=data.select("age","count").filter("count > 200")
    println(s"Number of records with age count > 200 : ${ageCount.count()}")

      //DataFrame Query: SQL like query
    data.select("name","age").filter("name like 's%'")

    //DataFrame Query: SQL IN clause
    data.filter("age in (20,22,25)") // instead of doing age=20 or age=22 or age=25

    // GroupBy
    data.groupBy("age").count()
    /*
    +---+-----+
    |age|count|
    +---+-----+
    | 31|    8|
    | 65|    5|
    | 53|    7|
     */
    //Group by with filter
    data.groupBy("age").count().filter("age < 30")
    //Order by
    data.groupBy("age").count().filter("age < 30").orderBy("age")
    // Change column data type
    val schemaUpdate = data.select(
      data.col("id").cast("float"),
      data.col("name"),
      data.col("age"),
      data.col("count").cast("double")
    )
    schemaUpdate.printSchema()

    // Create subset for join
    val dataSubset = data.filter("age > 20 and age < 25")

    // Join
    dataSubset.join(data,"age").show(5)
    /*
    +---+---+------+-----+---+------+-----+
    |age| id|  name|count| id|  name|count|
    +---+---+------+-----+---+------+-----+
    | 22| 16|Weyoun|  323|484| Leeta|  478|
    | 22| 16|Weyoun|  323|390|Martok|  266|
    | 22| 16|Weyoun|  323|215|  Will|    6|
     */

    import org.apache.spark.sql.functions._
    // Aggregations
    data.select(max("age"))
    data.select(min("age"))
    data.groupBy("age").avg("count").show(20)



    data.createOrReplaceTempView("tempTable")
    //Spark register UDF
    // Function to prefix a String with so_ short for StackOverflow
    def prefixStackoverflow(s: String): String = s"so_$s"
    spark.udf.register("prefix_so",prefixStackoverflow _)
    spark.sql("select prefix_so(name) from tempTable").show(5)


  }

}
