package edsl

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

typealias CmdItem = KeyValuePair<String, String>
typealias Param = KeyValuePair<String, String>
typealias ScalaCode = String
typealias LocalDateTimeISOFormatted = String

/**
 * Represents a generic pair of two values which we can deserialize using Moshi.
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * KeyValuePair exhibits value semantics, i.e. two pairs are equal if both components are equal.
 *
 * @param A type of the key.
 * @param B type of the value.
 * @property key The key.
 * @property value The value.
 * @constructor Creates a new instance of KeyValuePair.
 */

@JsonClass(generateAdapter = true)
data class KeyValuePair<out A, out B>(
    @Json(name = "key")  val key: A,
    @Json(name = "val")  val value: B
) {

    /**
     * Returns string representation of the [KeyValuePair] including its [key] and [value] values.
     */
    public override fun toString(): String = "($key, $value)"
}
