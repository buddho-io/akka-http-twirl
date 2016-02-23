package io.buddho.akka.http.marshalling

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.MediaType
import akka.http.scaladsl.model.MediaTypes._
import play.twirl.api.{Html, Txt, Xml}

object PlayTwirlMarshaller extends PlayTwirlMarshaller

/**
 * A trait providing Marshallers for the Play Twirl template result types.
 *
 * Import this support for use with the new Play Twirl plugin ("com.typesafe.sbt" % "sbt-twirl" % "1.1.1").
 */
trait PlayTwirlMarshaller {

  implicit val twirlHtmlMarshaller = twirlMarshaller[Html](`text/html`)

  implicit val twirlTxtMarshaller = twirlMarshaller[Txt](`text/plain`)

  implicit val twirlXmlMarshaller = twirlMarshaller[Xml](`text/xml`)

  protected def twirlMarshaller[A <: AnyRef: Manifest](contentType: MediaType): ToEntityMarshaller[A] =
    Marshaller.StringMarshaller.wrap(contentType)(_.toString)

}
