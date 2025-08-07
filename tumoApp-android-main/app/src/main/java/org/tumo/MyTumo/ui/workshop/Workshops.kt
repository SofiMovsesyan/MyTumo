package org.tumo.MyTumo.ui.workshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.WorkshopFilter.WorkshopSkill
import org.tumo.MyTumo.service.workshop.WorkshopInfo
import org.tumo.MyTumo.ui.components.CenterAlignedProgressIndicator
import org.tumo.MyTumo.ui.components.MyTumoAppBar
import org.tumo.MyTumo.ui.theme.LightGrayVariant
import org.tumo.MyTumo.ui.theme.Purple200
import org.tumo.MyTumo.ui.theme.SelectedSkillColor
import org.tumo.MyTumo.ui.theme.SkillColor
import org.tumo.MyTumo.ui.theme.SkillText
import org.tumo.MyTumo.viewmodels.WorkshopViewModel

@Composable
fun WorkshopsScreen(onNavigateToWorkshopByIdScreen: (id: String) -> Unit) {
    val workshopViewModel = viewModel<WorkshopViewModel>()
    val workshops by workshopViewModel.liveDataWorkshopsInfo.observeAsState()
    val skills by workshopViewModel.liveDataSkillsInfo.observeAsState()

    LaunchedEffect(Unit) {
        workshopViewModel.getWorkshopInfo()
        workshopViewModel.getSkills()
    }

    Scaffold(
        topBar = {
            MyTumoAppBar(
                content = stringResource(R.string.workshops)
            )
        },
    ) { PaddingValues ->
        Box(
            modifier = Modifier
                .padding(PaddingValues)
        ) {
            workshops?.let { workshopsList ->
                WorkshopFirstPage(
                    workshops = workshopsList,
                    skills = skills ,
                    onNavigateToWorkshopByIdScreen = onNavigateToWorkshopByIdScreen,
                    onMenuItemClicked = {
                        workshopViewModel.addOrRemoveFilter(it)
                    }
                )
            } ?: run {
                CenterAlignedProgressIndicator()
            }
        }
    }

}


@Composable
fun WorkshopFirstPage(
    workshops: List<WorkshopInfo>,
    skills: List<WorkshopSkill>?,
    onNavigateToWorkshopByIdScreen: (id: String) -> Unit,
    onMenuItemClicked: (WorkshopSkill) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(40.dp)
        )
        MenuBar(skills, onMenuItemClicked)
        Spacer(
            modifier = Modifier
                .height(40.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(start = 0.dp)
        ) {
            items(workshops.size) { index ->
                WorkshopBox(workshops[index], onNavigateToWorkshopByIdScreen)
                Spacer(modifier = Modifier.height(53.dp))
            }
            item{
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
@Composable
fun MenuBar(skills: List<WorkshopSkill>?, onItemClicked: (WorkshopSkill)->Unit) {
    var showSkills by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .background(color = Purple200, shape = RoundedCornerShape(size = 10.dp))
                .width(145.dp)
                .height(40.dp)
                .clickable {
                    showSkills = !showSkills
                }
        ) {
            Text(
                text = "Ընտրիր ոլորտը",
                style = TextStyle(
                    fontSize = 10.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,

                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, end = 63.dp, bottom = 5.dp, start = 63.dp)
            ) {
                Image(
                    painter = (painterResource(id = R.drawable.polygon)),
                    contentDescription = "image description",
                    modifier = Modifier
                        .width(19.dp)
                        .height(15.dp)
                )
            }

        }
        if (showSkills) {
            Filter(skills, onItemClicked)
        }
    }
}

@Composable
fun Filter(
    skills: List<WorkshopSkill>?,
    onItemClicked: (WorkshopSkill)->Unit
) {
    skills?.let {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(it) { skill ->
                SkillItem(skill, onItemClicked)
            }
        }
    }
}

@Composable
private fun SkillItem(
    skill: WorkshopSkill,
    onClick: (WorkshopSkill)->Unit,
) {
    val selected = remember {
        mutableStateOf(skill.selected)
    }
    val color = if (selected.value) SelectedSkillColor
    else SkillColor
    Box(
        modifier = Modifier
            .background(
                color = color
            )
            .width(170.dp)
            .height(40.dp)
            .clickable {
                selected.value = !selected.value
                onClick(skill)
            },
        contentAlignment = Alignment.Center

    ) {
        Text(
            skill.title.armenian,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(800),
                color = SkillText,
                textAlign = TextAlign.Center,

                ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
    Spacer(
        modifier = Modifier
            .height(3.dp)
    )
}

@Composable
fun WorkshopHeader(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .padding(8.dp)
    ) {
        Modifier
            .offset(x = 142.dp, y = 16.dp)
            .width(75.dp)
            .height(14.dp)
        Text(
            text = name,

            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = LightGrayVariant,
                textAlign = TextAlign.Center,

                )
        )
    }
}


@Composable
fun WorkshopBox(workshopInfo: WorkshopInfo, onNavigateToWorkshopByIdScreen: (id: String) -> Unit) {

    val skill = Skill.values().find { it.nameKey == workshopInfo.skill }
    val color = skill?.let { Skill.getColorByNameKey(it.nameKey) } ?: Color.Gray
    skill?.imageResId ?: R.drawable.event_icon
    Box(
        modifier = Modifier
            .offset(x = 0.dp, y = 0.dp)
            .width(342.dp)
            .height(240.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
            .clickable {
                onNavigateToWorkshopByIdScreen(workshopInfo.id)
            }

    ) {
        WorkshopHeader(name = "Learning Lab")
        WorkshopIcon(workshopInfo.eventHosts.get(0).photoUrl, color)
        WorkshopName(workshopInfo.eventHosts.get(0).name.english, color)
        WorkshopBottomBox(workshopInfo.title.english, color)
    }
}

@Composable
fun WorkshopBottomBox(name: String, color: Color) {
    Box(
        Modifier
            .offset(x = 0.dp, y = 171.dp)
            .width(343.dp)
            .height(77.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .padding(start = 5.dp, top = 6.dp, end = 135.dp, bottom = 43.dp)
    )
    {
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .offset(x = 11.dp, y = 6.dp)
                    .width(197.dp)
                    .height(28.dp),
                text = name,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White,

                    )
            )
        }
    }
}

@Composable
fun WorkshopName(name: String, color: Color) {

    Text(
        text = name,
        style = TextStyle(
            fontSize = 10.sp,
        ),
        fontWeight = FontWeight(700),
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .offset(x = 24.dp, y = 128.dp)
            .width(87.dp)
            .height(28.dp)
    )
}

@Composable
fun WorkshopIcon(url: String, color: Color) {

    AsyncImage(
        model = url,
        placeholder = painterResource(id = R.drawable.subtract),
        error = painterResource(id = R.drawable.subtract),
        contentDescription = "The design logo",
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .offset(x = 34.dp, y = 53.dp)
            .width(65.dp)
            .height(65.dp)
            .clip(CircleShape)
            .border(2.dp, color, CircleShape),

        )

}



