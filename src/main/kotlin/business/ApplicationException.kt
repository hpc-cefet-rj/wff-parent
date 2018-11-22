package business

open class ApplicationException : Exception {

  var code = 0
    private set

  constructor(message: String) : super(message) {}

  constructor(throwable: Throwable) : super(throwable) {}

  constructor(message: String, code: Int) : super(message) {
    this.code = code
  }

  constructor(cause: Throwable, code: Int) : super(cause) {
    this.code = code
  }

  companion object {
    private val serialVersionUID = 849633239167295022L
  }
}
