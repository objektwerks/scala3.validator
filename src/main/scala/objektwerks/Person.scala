package objektwerks

import scala.util.Try
import scala.collection.mutable.ArrayBuffer

import ujson.*

import Person.*
import Types.*
import Validators.*

object Person:
  val nameField = Field("Name")
  val ageField = Field("Age")

  val nameMessage = Message("Name is less than 1 character.")
  val ageMessage = Message("Age is less than 1.")

final case class Person(name: Name, age: Age)

extension (person: Person)
  def validate: Person =
    require(person.name.nonEmpty, nameMessage)
    require(person.age > 0, ageMessage)
    person

  def invalidate: Invalidations =
    Invalidations()
      .invalidate(person.name.isEmpty)(nameField, nameMessage)
      .invalidate(person.age < 1)(ageField, ageMessage)

given EntityValidator[Person, Throwable, Person] with
  def validate(person: Person): Either[Throwable, Person] = Try( person.validate ).toEither

given EntitiesValidator[Person, Throwable, Person] with
  def validate(persons: Seq[Person]): Seq[Either[Throwable, Person]] = persons.map { person => Try( person.validate ).toEither }

given EntityValidator[Csv, Throwable, Seq[Person]] with
  def validate(csv: Csv): Either[Throwable, Seq[Person]] =
    Try {
      val persons = ArrayBuffer[Person]()
      for row <- csv.rows
        yield
          val name = row.head
          val age = Try( row(1).toInt ).getOrElse(0)
          persons.addOne( Person(Name(name), Age(age)).validate )
      persons.toSeq
    }.toEither

given EntityValidator[Json, Throwable, Seq[Person]] with
  def validate(json: Json): Either[Throwable, Seq[Person]] =
    Try {
      val persons = ArrayBuffer[Person]()
      for jsonObject <- json.jsonObjects
        yield
          val jsonValue = ujson.read(jsonObject)
          val name = jsonValue("name").str
          val age = jsonValue("age").num.toInt
          persons.addOne( Person(Name(name), Age(age)).validate )
      persons.toSeq
    }.toEither