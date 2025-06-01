package org.chtan.ginger.ginger.presentation.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.time.Duration.Companion.days

fun generateRandomColor(): Color {
    val red = Random.nextInt(0, 256)
    val green = Random.nextInt(0, 256)
    val blue = Random.nextInt(0, 256)

    return Color(red / 255f, green / 255f, blue / 255f, 1f)
}

fun roundTowDecimalNumber(value: Double): Float {
    return (value * 100f).roundToInt() / 100f
}


@Serializable
data class DateTime(
    val date: String,
    val time: String
)

fun convertTimestampToDateTime(timestamp: String): DateTime {
    val instant = Instant.parse(timestamp) // Parse the timestamp into an Instant
    val localDateTime =
        instant.toLocalDateTime(TimeZone.UTC) // Convert Instant to LocalDateTime in UTC
    val formattedDate = localDateTime.date.toString() // yyyy-MM-dd
    val formattedTime = localDateTime.time.toString() // HH:mm:ss
    return DateTime(formattedDate, formattedTime)
}


data class MyDateTimeUTC(
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val dateTime: String? = null,
    val day: String? = null
)


fun convertMillisToTime(millis: Long): String {
    // Convert milliseconds to Instant
    val instant = Instant.fromEpochMilliseconds(millis + 6400000)

    // Convert Instant to LocalDateTime using system's default time zone
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    // Extract time components (hours, minutes, seconds)
    val formattedTime = "${localDateTime.hour}:${localDateTime.minute}:${localDateTime.second}"

    // Display the formatted time
    return formattedTime
}

fun convertMillisToDate(millis: Long): String {
    // Convert milliseconds to Instant
    val instant = Instant.fromEpochMilliseconds(millis)

    // Convert Instant to LocalDateTime using system's default time zone
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    // Extract time components (hours, minutes, seconds)
    val formattedTime = "${localDateTime.date}"
    // Display the formatted time
    return formattedTime
}

fun currentDate(): String {
// Get the current instant (now)
    val currentInstant = Clock.System.now()

// Convert to LocalDateTime using the system's default time zone
    val localDateTime = currentInstant.toLocalDateTime(TimeZone.currentSystemDefault())

    // Extract time components (hours, minutes, seconds)
    val formattedTime = "${localDateTime.date}"
    // Display the formatted time
    return formattedTime
}


fun getServerUTCTime(): String {
    val now: Instant = Clock.System.now() // Get the current time as an Instant
    val utcDateTime =
        now.toLocalDateTime(TimeZone.UTC) // Convert it to LocalDateTime in UTC timezone
    return utcDateTime.toString() // Format as ISO 8601 string
}

fun getUtcId(): String {
    val utcTimeZone = TimeZone.currentSystemDefault().id // Reference the UTC timezone
    return utcTimeZone // Retrieve the ID of the timezone
}

fun getOneMonthBeforeUTC(): MyDateTimeUTC {
    val instant = Clock.System.now().minus(30.days)  // Get the current time as an Instant
    val localTimeZone = TimeZone.currentSystemDefault()// Get the system's local timezone
    val oneMonthBefore = instant.toLocalDateTime(localTimeZone)

    return MyDateTimeUTC(
        date = oneMonthBefore.date,
        time = oneMonthBefore.time,
        dateTime = "${oneMonthBefore}Z",
        day = oneMonthBefore.dayOfWeek.toString()
    )
}


fun getLocalTimeInUTC(): MyDateTimeUTC {
    val instant = Clock.System.now() // Get the current time as an Instant
    val localTimeZone = TimeZone.currentSystemDefault() // Get the system's local timezone
    val localDateTime =
        instant.toLocalDateTime(localTimeZone) // Convert Instant to LocalDateTime in local timezone
    return MyDateTimeUTC(
        date = localDateTime.date,
        time = localDateTime.time,
        dateTime = "${localDateTime}Z",
        day = localDateTime.dayOfWeek.toString()
    ) // Return the UTC time as ISO 8601
}


fun getUtcToTargetTime(utcTime: String, targetTimeZoneId: String?): MyDateTimeUTC {
    // Parse the input UTC time (ISO 8601 format)
    val instant = Instant.parse(utcTime)

    // Get the target timezone using its ID
    val targetTimeZone = TimeZone.of(targetTimeZoneId ?: getUtcId())

    // Convert the UTC time to the target timezone
    val targetDateTime = instant.toLocalDateTime(targetTimeZone)

    // Format and return the date and time as a string
    return MyDateTimeUTC(
        date = targetDateTime.date,
        time = targetDateTime.time,
        dateTime = "$targetDateTime:00.000Z",
        day = targetDateTime.dayOfWeek.toString()
    )
}

fun convertLocalDateTimeMillisToUtc(localDateTimeMillis: Long): MyDateTimeUTC {
    val instant = Instant.fromEpochMilliseconds(localDateTimeMillis)
    // Convert the Instant to a LocalDateTime in UTC
    val utcDateTime = instant.toLocalDateTime(TimeZone.UTC)
    // Return UTC Date and Time as a formatted string
    return MyDateTimeUTC(
        date = utcDateTime.date,
        time = utcDateTime.time,
        dateTime = "$utcDateTime:00.000Z",
        day = utcDateTime.dayOfWeek.toString()
    )
}



