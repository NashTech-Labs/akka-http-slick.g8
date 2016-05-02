package com.goralski.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.goralski.repo.Bank
import spray.json.DefaultJsonProtocol

trait JsonHelper extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val bankFormat = jsonFormat2(Bank.apply)

}
