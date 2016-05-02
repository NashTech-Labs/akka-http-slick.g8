package com.goralski.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.goralski.repo.{Bank, BankRepository, BankRepositoryImpl}
import com.google.inject.{AbstractModule, Guice}


object HttpService extends App {

  private val injector = Guice.createInjector(new AbstractModule() {
    override def configure() {
      bind(classOf[BankRepository]).to(classOf[BankRepositoryImpl])
    }
  })

  private val bankService = injector.getInstance(classOf[Routes])

  implicit val system: ActorSystem = ActorSystem()

  implicit val materializer = ActorMaterializer()

  implicit val dispatcher = system.dispatcher

  private val repositoryImpl: BankRepositoryImpl = new BankRepositoryImpl
  repositoryImpl.ddl.onComplete {
    _ =>
      repositoryImpl.createBank(Bank("SBI"))
      repositoryImpl.createBank(Bank("PNB"))
      repositoryImpl.createBank(Bank("RBS"))
      Http().bindAndHandle(bankService.bankRoutes, "localhost", 9000)
  }


}
