package entities

import java.time.Instant

data class UsersLog(var name: String, var moment: Instant) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UsersLog

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
