package org.twelvehart.modules

import com.pulumi.Context
import com.pulumi.resources.CustomResource

trait Stackable(bucketName: String) {
  def create(ctx: Context): Unit
}
