package org.twelvehart.modules

import com.pulumi.Context
import com.pulumi.resources.CustomResource
import com.pulumi.aws.kms.*
import com.pulumi.aws.s3.*
import com.pulumi.aws.s3.inputs.*

class BucketWithSSE(bucketName: String) extends Stackable(bucketName):
  private val KeyName               = s"$bucketName-key"
  private val DefaultDeletionWindow = 10

  override def create(ctx: Context): Unit =
    val keyArgs =
      KeyArgs.builder().description(s"SSE Key for $bucketName").deletionWindowInDays(DefaultDeletionWindow).build()

    val key = new Key(KeyName, keyArgs)

    val bucket = BucketV2(bucketName)

    val encryption = BucketServerSideEncryptionConfigurationV2RuleApplyServerSideEncryptionByDefaultArgs
      .builder()
      .sseAlgorithm("aws:kms")
      .kmsMasterKeyId(key.arn())
      .build()

    val encryptionRule =
      BucketServerSideEncryptionConfigurationV2RuleArgs.builder().applyServerSideEncryptionByDefault(encryption).build()

    val bucketSSEArgs =
      BucketServerSideEncryptionConfigurationV2Args
        .builder()
        .bucket(bucket.bucket())
        .rules(encryptionRule)
        .build()

    val _ = BucketServerSideEncryptionConfigurationV2(s"$bucketName-sse-config", bucketSSEArgs)

    ctx.`export`("bucketName", bucket.getId)
