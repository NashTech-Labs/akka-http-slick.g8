package com.knoldus.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.repo.Bank
import spray.json.DefaultJsonProtocol

trait JsonHelper extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val bankFormat = jsonFormat2(Bank.apply)

}
