package com.twelvehart

import com.pulumi.Pulumi
import com.pulumi.Context
import com.pulumi.aws.s3.Bucket

import java.util.function.Consumer

import scala.jdk.FutureConverters._
import scala.jdk.FunctionConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

object Infrastructure {
  def main(args: Array[String]): Unit = {
    Pulumi.run(stack.asJavaConsumer)
  }

  val stack: Context => Unit = ctx => {
    val bucket = new Bucket("pulumi-bucket")

    ctx.`export`("bucketName", bucket.getId)
  }
}
