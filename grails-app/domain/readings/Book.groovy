package readings

class Book {

  static hasMany = [readings: Readings]
  static belongsTo = [user:User]

  String title
  String author
  int totalPages
  Boolean read

    static constraints = {


    }
}
