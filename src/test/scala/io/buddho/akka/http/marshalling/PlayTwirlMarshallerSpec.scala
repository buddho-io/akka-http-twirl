package io.buddho.akka.http.marshalling

import akka.actor.ActorSystem
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{MediaTypes, ResponseEntity}
import akka.stream.ActorMaterializer
import org.scalatest.{BeforeAndAfterAll, FunSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


case class Test(message: String)

class PlayTwirlMarshallerSpec extends FunSpec with PlayTwirlMarshaller with Matchers with BeforeAndAfterAll {

  val test = Test("foo")
  val timeout = 1.second

  implicit val system = ActorSystem()
  implicit val mat = ActorMaterializer()

  describe("PlayTwirlMarshaller") {
    it("should marshal test.scala.html to a string") {
      val entity = Await.result(Marshal(html.test.render(test)).to[ResponseEntity].flatMap(_.toStrict(timeout)), timeout)
      entity.contentType.mediaType shouldBe MediaTypes.`text/html`
      entity.data.decodeString("UTF-8") should include ("<p>Message is foo</p>")
    }
    it("should marshal test.scala.xml to a string") {
      val entity = Await.result(Marshal(xml.test.render(test)).to[ResponseEntity].flatMap(_.toStrict(timeout)), timeout)
      entity.contentType.mediaType shouldBe MediaTypes.`text/xml`
      entity.data.decodeString("UTF-8") should include ("<message>foo</message>")
    }
    it("should marshal test.scala.txt to a string") {
      val entity = Await.result(Marshal(txt.test.render(test)).to[ResponseEntity].flatMap(_.toStrict(timeout)), timeout)
      entity.contentType.mediaType shouldBe MediaTypes.`text/plain`
      entity.data.decodeString("UTF-8") should include ("Message is foo")
    }
  }

  override def afterAll(): Unit = {
    system.terminate()
    Await.result(system.whenTerminated, 5.seconds)
    super.afterAll()
  }

}
