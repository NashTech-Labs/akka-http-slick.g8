package com.goralski.service

import com.goralski.repo.BankRepository
import com.google.inject.{AbstractModule}
import net.codingwell.scalaguice.ScalaModule

/**
  * Created by kgoralski on 2016-05-02.
  */
class GuiceTestModule extends AbstractModule with ScalaModule {


  def configure() {
    bind(classOf[BankRepository]).to(classOf[BankRepositoryTestImpl])
  }

}
