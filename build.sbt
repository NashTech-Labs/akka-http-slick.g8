name := "akka-http-slick-guice"

version := "1.0"

scalaVersion := "2.11.7"




libraryDependencies ++=  Seq(
                      "mysql"                %     "mysql-connector-java"     %      "5.1.36",
                      "com.typesafe.akka"    %%    "akka-actor"                           % "2.4.4",
                      "com.typesafe.akka"    %%    "akka-http-spray-json-experimental"    % "2.0.3",
                      "com.typesafe.akka"    %%    "akka-stream-experimental" % "2.0.3",
                      "com.typesafe.akka"    %%    "akka-http-core-experimental" % "2.0.3",
                      "com.typesafe.slick"   %%    "slick-hikaricp"           %      "3.1.1",
                      "ch.qos.logback"       %     "logback-classic"          %      "1.1.3",
                      "com.typesafe.slick"   %%    "slick"            	      %      "3.1.1",
                      "org.scalatest"        %%    "scalatest"    	      %      "2.2.5"     %    "test",
                      "com.h2database"       % 	   "h2"                       %      "1.4.187",
                      "net.codingwell" %% "scala-guice" % "4.0.1",
                      "com.typesafe.akka" %% "akka-http-testkit-experimental" % "2.0.3" %    "test"
)
