package com.jay.handsome.scalaTest

import com.jay.handsome.cglib.Person

object Test2 {
  def main(args: Array[String]): Unit = {
    val name = new Person().getName
    print(name)
  }
}
