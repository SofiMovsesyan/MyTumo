package org.tumo.MyTumo.ui.progress

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.progress.Event
import org.tumo.MyTumo.ui.components.CenterAlignedProgressIndicator
import org.tumo.MyTumo.ui.components.MyTumoAppBar
import org.tumo.MyTumo.ui.profile.ProfileRoutes
import org.tumo.MyTumo.ui.progress.components.ProgressItem
import org.tumo.MyTumo.viewmodels.ProgressViewModel

@Composable
fun Progresses(navController: NavController?, paddingValues: PaddingValues = PaddingValues(0.dp)) {
    val progressViewModel = viewModel<ProgressViewModel>()
    val studentProgress by progressViewModel.liveDataStudentProgress.observeAsState()
    val limit = remember{ mutableStateOf(10) }

    LaunchedEffect(limit.value) {
        progressViewModel.getStudentProgress(limit.value)
    }

    Scaffold(
        topBar = {
            MyTumoAppBar(
                content = stringResource(R.string.my_progress),
                showRightIcon = true,
                showLeftIcon = true,
                onRightIconPressed = { navController?.navigate(ProfileRoutes.Settings.name) },
                onLeftIconPressed = { navController?.popBackStack() }
            )
        },
    ) { PaddingValues ->
        Box(
            modifier = Modifier
                .padding(PaddingValues)
        ) {
            studentProgress?.progress?.events?.let {
                ProgressContent(it, limit.value, paddingValues, Modifier, loadMoreContent = { limit.value = limit.value + 10 })
            } ?: run {
                CenterAlignedProgressIndicator()
            }
        }
    }
}

@Composable
fun ProgressContent(events: List<Event>, limit: Int, paddingValues: PaddingValues, modifier: Modifier = Modifier, loadMoreContent: ()->Unit = {}) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState, contentPadding = PaddingValues(horizontal = 20.dp), modifier = modifier) {
        items(events) { event ->
            Column {
                ProgressItem(event)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
        }
        if (listState.isScrollInProgress &&
            listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= events.size &&
            events.size == limit // Add this check
        ) {
            Log.i("asd", "ProgressContent: ${events.size} $limit")
            // Call a function to load more content
            loadMoreContent()
        }
    }

}
