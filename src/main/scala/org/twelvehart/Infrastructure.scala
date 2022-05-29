package org.twelvehart

import com.pulumi.*
import com.pulumi.Context
import com.pulumi.aws.s3.BucketV2
import com.pulumi.resources.CustomResource
import org.twelvehart.modules.Stackable

import java.util.function.Consumer
import scala.jdk.FutureConverters.*
import scala.jdk.FunctionConverters.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.*

object Infrastructure:
  import modules._

  def main(args: Array[String]): Unit =
    Pulumi.run(stack.asJavaConsumer)

  val resources: Vector[Stackable] =
    Vector(BucketWithSSE("pulumi-test-bucket"))

  def stack(ctx: Context): Unit =
    resources.foreach(r => r.create(ctx))
