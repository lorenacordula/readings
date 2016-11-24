package readings

class User {

    static hasMany = [book: Book]
    static hasMany = [readings: Readings]

    String username

    static constraints = {
    }
}
