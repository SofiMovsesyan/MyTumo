package org.tumo.MyTumo.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.tumo.MyTumo.R
import org.tumo.MyTumo.data.mockStudentInfo
import org.tumo.MyTumo.service.attendance.StudentAttendance
import org.tumo.MyTumo.service.progress.Event
import org.tumo.MyTumo.service.progress.StudentProgress
import org.tumo.MyTumo.service.student.StudentInfo
import org.tumo.MyTumo.ui.attendance.Attendances
import org.tumo.MyTumo.ui.attendance.ProfileAttendance
import org.tumo.MyTumo.ui.components.CenterAlignedProgressIndicator
import org.tumo.MyTumo.ui.components.MyTumoAppBar
import org.tumo.MyTumo.ui.profile.components.CoachRow
import org.tumo.MyTumo.ui.profile.components.EventInfoItem
import org.tumo.MyTumo.ui.profile.components.WorkshopInfoItem
import org.tumo.MyTumo.ui.progress.ProfileProgress
import org.tumo.MyTumo.ui.progress.Progresses
import org.tumo.MyTumo.ui.extraschedule.Schedule
import org.tumo.MyTumo.ui.settings.Settings
import org.tumo.MyTumo.ui.theme.MyTumoTheme
import org.tumo.MyTumo.viewmodels.AttendanceViewModel
import org.tumo.MyTumo.viewmodels.ProgressViewModel
import org.tumo.MyTumo.viewmodels.StudentViewModel

enum class ProfileRoutes {
    Profile,
    Attendances,
    Progress,
    Extra,
    Settings
}

@Composable
fun ProfileNavigation(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ProfileRoutes.Profile.name) {
        composable(ProfileRoutes.Profile.name) {
            Profile(navController, paddingValues)
        }
        composable(ProfileRoutes.Attendances.name) { Attendances(navController, paddingValues) }
        composable(ProfileRoutes.Progress.name) { Progresses(navController, paddingValues) }
        composable(ProfileRoutes.Extra.name) { Schedule(navController, paddingValues) }
        composable(ProfileRoutes.Settings.name) { Settings(navController) }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavHostController, paddingValues: PaddingValues) {
    val studentViewModel = viewModel<StudentViewModel>()
    val progressViewModel = viewModel<ProgressViewModel>()
    val attendanceViewModel = viewModel<AttendanceViewModel>()
    val studentInfo by studentViewModel.liveDataStudentInfo.observeAsState()
    val studentProgress by progressViewModel.liveDataStudentProgress.observeAsState()
    val attendanceInfo by attendanceViewModel.liveDataStudentAttendance.observeAsState()

    val lastEvent = studentProgress?.progress?.events?.lastOrNull()

    val isFirstNavigation = remember { mutableStateOf(true) }

    LaunchedEffect(isFirstNavigation.value) {
        if (isFirstNavigation.value) {
            studentViewModel.getStudentInfo()
            progressViewModel.getStudentProgress(1)
            attendanceViewModel.getStudentAttendance(4)
            isFirstNavigation.value = false
        }
    }

    Scaffold(
        topBar = {
            MyTumoAppBar(
                content = stringResource(R.string.my_account),
                showRightIcon = true,
                onRightIconPressed = { navController.navigate(ProfileRoutes.Settings.name) })
        },
    ) { PaddingValues ->
        ProfileContent(
            Modifier
                .padding(PaddingValues),
            studentInfo,
            lastEvent,
            attendanceInfo,
            studentProgress,
            navController,
            paddingValues
        )
    }
}


@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    studentInfo: StudentInfo?,
    lastEvent: Event?,
    attendanceInfo: List<StudentAttendance>?,
    studentProgress: StudentProgress?,
    navController: NavHostController?,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            studentInfo?.let {
                StudentInfo(studentInfo, studentProgress, 1)
                Spacer(Modifier.height(10.dp))
                ProfileButtons(
                    lastEvent,
                    attendanceInfo,
                    { navController?.navigate(ProfileRoutes.Extra.name) },
                    { navController?.navigate(ProfileRoutes.Attendances.name) },
                    { navController?.navigate(ProfileRoutes.Progress.name) })
                Spacer(Modifier.padding(paddingValues))
            } ?: run {
                CenterAlignedProgressIndicator()
            }
        }
    }
}

@Composable
fun StudentInfo(studentInfo: StudentInfo, studentProgress: StudentProgress?, language: Int) {
    val fullName = remember { mutableStateOf("") }

    if (language == 1) {
        fullName.value =
            "${studentInfo.firstName.hy} ${studentInfo.middleName.hy} ${studentInfo.lastName.hy}"
    } else {
        fullName.value =
            "${studentInfo.firstName.en} ${studentInfo.middleName.en} ${studentInfo.lastName.en}"
    }

    Surface(
        modifier = Modifier
            .height(286.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            studentInfo.photoUrl?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(studentInfo.photoUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_user),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(65.dp)
                )
            } ?: run {
                Image(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(65.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = fullName.value, style = TextStyle(
                    color = Color.Black, fontWeight = Bold
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = studentInfo.email,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()
            ) {
                studentProgress?.overview?.activitiesCount?.let { WorkshopInfoItem(count = it) }
                studentProgress?.overview?.eventsCount?.let { EventInfoItem(count = it) }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider(
                Modifier
                    .height(2.dp)
                    .padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.coach),
                style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            CoachRow(studentInfo.coach, language)
        }
    }
}

@Composable
fun ProfileButtons(
    lastProgress: Event?,
    attendanceInfo: List<StudentAttendance>?,
    onExtraClicked: () -> Unit,
    onAttendanceClicked: () -> Unit,
    onProgressClicked: () -> Unit
) {
    val showAttendance = remember { mutableStateOf(true) }
    val showProgress = remember { mutableStateOf(false) }
    Column {
        Button(
            onClick = { onExtraClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Text(
                text = stringResource(R.string.extra_slot), style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(modifier = Modifier.clip(RoundedCornerShape(15.dp))) {
            Column {
                Button(
                    modifier = Modifier
                        .heightIn(min = 60.dp)
                        .padding(vertical = 2.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = Color.Black
                    ),
                    shape = RectangleShape,
                    onClick = {
                        showAttendance.value = !showAttendance.value; showProgress.value = false
                    },
                ) {
                    Text(
                        text = stringResource(R.string.my_attendances), style = TextStyle(
                            fontWeight = Bold,
                        )
                    )
                }
                if (showAttendance.value) {
                    Column {
                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                                .padding(horizontal = 10.dp),
                        )
                        ProfileAttendance(
                            attendanceInfo,
                            Modifier.clickable { onAttendanceClicked() })
                    }
                }
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .padding(horizontal = 10.dp),
                )

                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = Color.Black
                    ),
                    shape = RectangleShape,
                    onClick = {
                        showProgress.value = !showProgress.value; showAttendance.value = false
                    },
                ) {
                    Text(
                        text = stringResource(R.string.my_progress), style = TextStyle(
                            fontWeight = Bold
                        )
                    )
                }
                if (showProgress.value) {
                    Column {
                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                                .padding(horizontal = 10.dp),
                        )
                        ProfileProgress(Modifier.clickable { onProgressClicked() }, lastProgress)
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun ProfilePreview() {
    MyTumoTheme {
        ProfileContent(
            studentInfo = mockStudentInfo,
            studentProgress = null,
            lastEvent = null,
            navController = null,
            attendanceInfo = null
        )
    }
}

@Preview
@Composable
fun ButtonsPreview() {
    MyTumoTheme {
        ProfileButtons(null, null, {}, {}, {})
    }
}

@Preview
@Composable
fun ContentPreview() {
    MyTumoTheme {
        StudentInfo(studentInfo = mockStudentInfo, studentProgress = null, language = 1)
    }
}


