package com.intellij.aws.cloudformation

enum class CloudFormationIntrinsicFunctions(val id: String) {
  FnBase64("Fn::Base64"),
  FnFindInMap("Fn::FindInMap"),
  FnGetAtt("Fn::GetAtt"),
  FnGetAZs("Fn::GetAZs"),
  FnJoin("Fn::Join"),
  FnSelect("Fn::Select"),
  FnIf("Fn::If"),
  FnSub("Fn::Sub"),
  Ref("Ref");

  val shortForm = if (this.id.startsWith("Fn::")) id.substring("Fn::".length) else id

  companion object {
    val fullNames = CloudFormationIntrinsicFunctions.values().map { Pair(it.id, it) }.toMap()
    val shortNames = CloudFormationIntrinsicFunctions.values().map { Pair(it.shortForm, it) }.toMap()
  }
}
