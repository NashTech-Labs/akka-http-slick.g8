package com.knoldus.service

import javax.inject.{Inject, Named}

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import com.knoldus.json.JsonHelper
import com.knoldus.repo.{Bank, BankRepository}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kgoralski on 2016-05-02.
  */
@Named
class Routes @Inject()(bankRepository: BankRepository) extends JsonHelper{



   val bankRoutes = {

     (path("bank" / IntNumber) & get) { id =>
       onSuccess(bankRepository.getBankById(id)) {
         case Some(bank) => complete(bank)
         case None => complete(StatusCodes.NotFound)
       }
     } ~
       (path("banks") & get) {
         complete(bankRepository.getAllBanks())
       } ~
       (path("bank") & post) {
         entity(as[Bank]) { bank =>
           complete {
             bankRepository.createBank(bank).map { result => HttpResponse(entity = "Bank has  been saved successfully") }
           }
         }
       } ~
       (path("bank") & put) {
         entity(as[Bank]) { bank =>
           complete {
             bankRepository.updateBank(bank).map { result => HttpResponse(entity = "Bank has  been updated successfully") }
           }
         }
       } ~
       (path("bank" / IntNumber) & delete) { id =>
         complete {
           bankRepository.deleteBank(id).map { result => HttpResponse(entity = "Bank has  been deleted successfully") }
         }
       }
   }



}
