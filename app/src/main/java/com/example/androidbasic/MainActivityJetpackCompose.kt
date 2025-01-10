package com.example.androidbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ValueElement
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasic.ui.theme.AndroidBasicTheme
import kotlinx.coroutines.launch


class MainActivityJetpackCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
//                    CrossFadeCompose(modifier = Modifier.padding(innerPadding))
                    InfiniteTransitionCompose(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationPreview() {
    AndroidBasicTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
//            CrossFadeCompose(modifier = Modifier.padding(innerPadding))
//            InfiniteTransitionCompose(modifier = Modifier.padding(innerPadding))
            ValueAnimation(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun ValueAnimation(modifier: Modifier = Modifier) {
    var click by remember { mutableStateOf(false) }
    val roundness by animateDpAsState(
        targetValue = if (click) 25.dp else 0.dp,
        label = "roundness",
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessMedium
//        )
    )

    Box(
        modifier = modifier
            .padding(15.dp)
            .size(100.dp)
            .background(Color.Black, RoundedCornerShape(roundness))
            .clip(RoundedCornerShape(roundness))
            .clickable {
                click = !click
            }
    )
}

@Composable
fun InfiniteTransitionCompose(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "progress")
    val lineStart by infiniteTransition.animateFloat(
        label = "lineStart",
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1000))
    )
    val lineEnd by infiniteTransition.animateFloat(
        label = "lineEnd",
        initialValue = 0f,
        targetValue = -1f,
        animationSpec = infiniteRepeatable(tween(1000))
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val lineWidthLength = size.width / 2
            val startX = lineWidthLength + (lineStart * lineWidthLength)
            val endX = lineWidthLength + (lineEnd * lineWidthLength)
            drawLine(
                start = Offset(x = startX, y = size.height / 2),
                end = Offset(x = endX, y = size.height / 2),
                color = Color.Blue,
                strokeWidth = 10f
            )
        }
    }
}

@Composable
fun CrossFadeCompose(modifier: Modifier = Modifier) {
    var pick by remember { mutableStateOf("A") }
    Crossfade(targetState = pick, label = "page", animationSpec = tween(2000)) { screen ->
        when (screen) {
            "A" -> Button(onClick = { pick = "B" }) { Text("Page A") }
            "B" -> Button(onClick = { pick = "A" }) { Text("Page B") }
        }
    }
}

@Composable
fun AnimatedContentCompose(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            label = "count",
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { it } + scaleIn() + fadeIn() togetherWith slideOutHorizontally { -it } + scaleOut() + fadeOut()
                } else {
                    slideInHorizontally { -it } + scaleIn() + fadeIn() togetherWith slideOutHorizontally { it } + scaleOut() + fadeOut()
                }.using(SizeTransform(clip = false))
            }
        ) { targetCount ->
            Text(text = "$targetCount", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            Button(onClick = { count-- }) {
                Text(text = "Minus")
            }
            Spacer(Modifier.size(60.dp))
            Button(onClick = { count++ }) { Text("Plus ") }
        }

    }
}

@Composable
fun Animation(modifier: Modifier = Modifier) {
    val (checked, onCheckedChange) = remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Checked: $checked")
        }
        AnimatedVisibility(
            visible = checked,
            enter = slideInVertically(
                initialOffsetY = { 100 },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
//                animationSpec = tween(300)
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { 100 },
                animationSpec = tween(300)
            ) + fadeOut(),

            ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Yellow), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Animation",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { 400 },
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessLow
                            )
//                            animationSpec = tween(400)
                        ) + fadeIn(),
                        exit = slideOutVertically() + fadeOut()
                    )
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AnimationPreview() {
//    AndroidBasicTheme {
//        Animation()
//    }
//}


data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val tabList = listOf(
        TabItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
        ),
        TabItem(
            title = "Browse",
            unselectedIcon = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart
        ),
        TabItem(
            title = "Account",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle
        )
    )
    var selectedIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabList.size }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedIndex = pagerState.currentPage
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerScope = rememberCoroutineScope()
    var selectedDrawer by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.padding(8.dp))
                    tabList.forEachIndexed { index, tabItem ->
                        NavigationDrawerItem(
                            label = { Text(text = tabItem.title) },
                            selected = selectedDrawer == index,
                            onClick = {
                                selectedDrawer = index
                                drawerScope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selectedDrawer == index) tabItem.selectedIcon else tabItem.unselectedIcon,
                                    contentDescription = null
                                )
                            },
                            badge = { Text(text = "$index") },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        ) {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//                        title = {
//                            Text(text = "Jetpack Compose")
//                        },
//                        navigationIcon = {
//                            IconButton(onClick = { drawerScope.launch { drawerState.open() } }) {
//                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
//                            }
//                        }
//                    )
//                }
//            ) { innerPadding ->
//                Greeting("Android", Modifier.padding(innerPadding))
//            }
        }
        TabRow(
            selectedTabIndex = selectedIndex,
            contentColor = Color.Green,
            indicator = { tabPositions ->
                if (selectedIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(
                            tabPositions[selectedIndex]
                        ),
                        color = Color.Green
                    )
                }
            },
        ) {
            tabList.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier,
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = if (selectedIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Tab ${tabList[index].title}", color = Color.Green)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndroidBasicTheme {
//        Greeting("Android")
//    }
//}