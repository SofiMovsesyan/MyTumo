package org.tumo.MyTumo.data

import org.tumo.MyTumo.service.student.Coach
import org.tumo.MyTumo.service.student.Name
import org.tumo.MyTumo.service.student.Schedule
import org.tumo.MyTumo.service.student.StudentInfo
import org.tumo.MyTumo.service.attendance.StudentAttendance
import org.tumo.MyTumo.service.extra.StudentSchedule
import java.text.SimpleDateFormat
import java.util.Locale

val mockCoach = Coach(
    id = "9876",
    photo = null,
    firstName = Name("John", "Ջոն"),
    middleName = Name("Doe", "Տոնական"),
    lastName = Name("Smith", "Սմիթ")
)

val mockSchedule = Schedule(
    id = "6491b534f2c09a51d4719342", weekday = "wednesday", startTime = "10:00", endTime = "12:00"
)

val mockStudentInfo = StudentInfo(
    id = "243546",
    domainId = "armenia",
    locationId = "yerevan",
    dob = "2010-03-31T19:00:00.000Z",
    gender = 2,
    status = 1,
    tumoId = "220221000011",
    coach = mockCoach,
    schedule = mockSchedule,
    email = "test.shogher1@tumo.org",
    firstName = Name("QA", "Թեստ"),
    middleName = Name("", ""),
    lastName = Name("Test Account", "Շողերյան"),
    photoUrl = null
)

val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

val mockAttendance = StudentAttendance(

    id = "6492ee1caa786c6184201bbd",
    status = "Present",
    type = "Onetime",
    sessionStart = formatter.parse("2023-07-24T13:15:00.000Z"),
    sessionEnd = formatter.parse("2023-07-24T14:45:00.000Z"),
    date = formatter.parse("2023-07-23T20:00:00.000Z")
)
val mockAttendance1 = StudentAttendance(

    id = "6492ee1caa786c6184201bbd",
    status = "Absent",
    type = "Onetime",
    sessionStart = formatter.parse("2023-07-24T13:15:00.000Z"),
    sessionEnd = formatter.parse("2023-07-24T14:45:00.000Z"),
    date = formatter.parse("2023-07-23T20:00:00.000Z")
)
val mockAttendance2 = StudentAttendance(

    id = "6492ee1caa786c6184201bbd",
    status = "Present",
    type = "kooke",
    sessionStart = formatter.parse("2023-07-24T13:15:00.000Z"),
    sessionEnd = formatter.parse("2023-07-24T14:45:00.000Z"),
    date = formatter.parse("2023-07-23T20:00:00.000Z")
)
val mockAttendance3 = StudentAttendance(

    id = "6492ee1caa786c6184201bbd",
    status = "Absent",
    type = "grgrrg",
    sessionStart = formatter.parse("2023-07-24T13:15:00.000Z"),
    sessionEnd = formatter.parse("2023-07-24T14:45:00.000Z"),
    date = formatter.parse("2023-07-23T20:00:00.000Z")
)

val mockExtra = StudentSchedule(
    type = "event",
    tags = listOf<String>("offline"),
    date = formatter.parse("2024-03-31T07:30:00.000Z")
)


val mockAttendances =
    listOf<StudentAttendance>(mockAttendance, mockAttendance1, mockAttendance2, mockAttendance3)
