package com.goralski.service

import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.goralski.connection.H2DBImpl
import com.goralski.json.JsonHelper
import com.goralski.repo.{Bank, BankRepository}
import com.google.inject.{AbstractModule, Guice}
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

import scala.concurrent.Future

class RouteTest extends WordSpec with Matchers with MockitoSugar with ScalatestRouteTest with JsonHelper with BeforeAndAfter {

  val bankToSave = Bank("ICICI bank")
  val bankToUpdate = Bank("Santander bank", Some(1))


  private val injector = Guice.createInjector(new AbstractModule() {
    override def configure() {
      bind(classOf[BankRepository]).to(classOf[BankRepositoryTestImpl])
    }
  })

  private val bankService = injector.getInstance(classOf[Routes])

  "The Bank service" should {

    "get bank detail by bank id" in {
      Get("/bank/1") ~> bankService.bankRoutes ~> check {
        responseAs[Bank] === """{}"""
      }
    }

    "get all banks detail " in {
      Get("/banks") ~> bankService.bankRoutes ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
        responseAs[List[Bank]].length === 1
        responseAs[List[Bank]] shouldEqual List(Bank("TestB bank", Some(1)))
      }
    }

    "save bank detail" in {
      Post("/bank", bankToSave) ~> bankService.bankRoutes ~> check {
        responseAs[String] shouldEqual "Bank has  been saved successfully"
      }
    }


    "update bank detail" in {
      Put("/bank", bankToUpdate) ~> bankService.bankRoutes ~> check {
        responseAs[String] shouldEqual "Bank has  been updated successfully"
      }
    }

    "delete bank detail by id" in {
      Delete("/bank/1") ~> bankService.bankRoutes ~> check {
        responseAs[String] shouldEqual "Bank has  been deleted successfully"
      }
    }

  }


}

// For testing
class BankRepositoryTestImpl extends BankRepository with H2DBImpl {
  override def createBank(bank: Bank): Future[Int] = Future.successful(1)

  override def updateBank(bank: Bank): Future[Int] = Future.successful(1)

  override def getBankById(id: Int): Future[Option[Bank]] = Future.successful(Some(Bank("TestB bank", Some(1))))

  override def getAllBanks(): Future[List[Bank]] = Future.successful(List(Bank("TestB bank", Some(1))))

  override def deleteBank(id: Int): Future[Int] = Future.successful(1)

}