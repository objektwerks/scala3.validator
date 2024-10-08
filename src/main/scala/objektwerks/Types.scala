package objektwerks

object Types:
  opaque type Name <: String = String
  object Name:
    def apply(value: String): Name = value
    extension (name: Name)
      def value: String = name

  opaque type Age <: Int = Int
  object Age:
    def apply(value: Int): Age = value
    extension (age: Age)
      def value: Int = age

  opaque type Field = String
  object Field:
    def apply(value: String): Field = value
    extension (field: Field)
      def value: String = field

  opaque type Message = String
  object Message:
    def apply(value: String): Message = value
    extension (message: Message)
      def value: String = message