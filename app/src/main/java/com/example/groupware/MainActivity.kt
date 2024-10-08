package com.example.groupware


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.groupware.entryScreen.EntryScreen
import com.example.groupware.entryScreen.TheVeryFirstScreen
import com.example.groupware.graphScreen.GraphScreen
import com.example.groupware.gymScreen.GymScreen
import com.example.groupware.loginScreen.UserLoginScreen
import com.example.groupware.loginScreen.UserRegisterScreen
import com.example.groupware.mainScreen.MainScreen
import com.example.groupware.managerScreen.ManagerCalendarScreen
import com.example.groupware.gymScreen.GymInfoScreen
import com.example.groupware.managerScreen.ManagerLoginScreen
import com.example.groupware.managerScreen.MainManagerScreen
import com.example.groupware.managerScreen.ManagerRegisterScreen
import com.example.groupware.profileScreen.GymProfileScreen
import com.example.groupware.managerScreen.ManagerProfileScreen
import com.example.groupware.profileScreen.UserProfileScreen
import com.example.groupware.ui.theme.GroupWareTheme

const val serverURL = "http://192.168.45.126/"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroupWareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class BottomNavItem(val title: String, val route: String)

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Column(modifier = Modifier.fillMaxSize()) {
        var responseUser = ""
        Box(modifier = Modifier.weight(1f)) {
            NavHost(navController, startDestination = "EntryScreen") {
                composable("entryScreen") { EntryScreen(navController) }
                composable("mainScreen") { MainScreen(navController) }
                composable("profileScreen") { ManagerProfileScreen(navController) }
//                composable("gymScreen") { GymScreen(navController) }
                composable("gyminfoScreen") { GymInfoScreen(navController) }
                composable("userloginScreen") { UserLoginScreen(navController) }
                composable("managerloginScreen") { ManagerLoginScreen(navController) }
                composable("userregisterScreen") { UserRegisterScreen(navController) }
//                composable("userprofileScreen") { UserProfileScreen(navController) }
                composable("managerCalendarScreen") { ManagerCalendarScreen(navController) }
                composable("TheVeryFirstScreen") { TheVeryFirstScreen(navController) }
                composable("gymprofileScreen") { GymProfileScreen(navController) }
                composable("graphScreen") { GraphScreen(navController) }
                composable("mainManagerScreen") { MainManagerScreen(navController) }
                composable("managerRegisterScreen") { ManagerRegisterScreen(navController) }

                composable(
                    "gymScreen/{responseUser}",
                    arguments = listOf(navArgument("responseUser") { type = NavType.StringType })
                ) { backStackEntry ->
                    responseUser = backStackEntry.arguments?.getString("responseUser")?: ""
                    GymScreen(
                        navController,
                        responseUser
                    )

                }

                composable(
                    "userprofileScreen/{responseUser}",
                    arguments = listOf(navArgument("responseUser") { type = NavType.StringType })
                ) { backStackEntry ->
                    UserProfileScreen(
                        navController,
                        backStackEntry.arguments?.getString("responseUser")
                    )

                }
            }
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute in listOf(
                "mainScreen",
                "gymScreen/{responseUser}",
                "favoriteScreen",
                "userprofileScreen/{responseUser}",
            )
        ) {
            BottomNavigationBar(navController, responseUser)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, responseUser: String) {
    val items = listOf(
        BottomNavItem("홈", "gymScreen"),
        BottomNavItem("달력", "managerScreen"),
        BottomNavItem("프로필", "profileScreen/$responseUser"),
        BottomNavItem("마이다짐", "userprofileScreen/$responseUser"),
    )

    NavigationBar(
        modifier = Modifier.background(Color(0xFF211C40)) // 배경색 설정
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // 백 스택 관리 설정
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                            inclusive = item.route == "mainScreen" // 메인 스크린일 경우 현재 스택을 모두 제거
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val icon = when (item.route) {
                        "gymScreen" -> Icons.Filled.Home
                        "managerScreen" -> Icons.Filled.CalendarMonth
                        "favoriteScreen" -> Icons.Filled.Favorite // GMobiledata 아이콘 대신 ShowChart 사용
                        "userprofileScreen/{responseUser}" -> Icons.Filled.AccountCircle
                        else -> null
                    }
                    if (icon != null) {
                        Icon(icon, contentDescription = item.title)
                    }
                },
                label = { Text(item.title) },
                alwaysShowLabel = false, // 라벨을 항상 표시하지 않도록 설정
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF5927EB), // 선택된 아이콘 색상
                    unselectedIconColor = Color.White, // 선택되지 않은 아이콘 색상
                    selectedTextColor = Color(0xFF5927EB), // 선택된 텍스트 색상
                    unselectedTextColor = Color.White // 선택되지 않은 텍스트 색상
                )
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            Greeting(name = "Android")
        }
    }
}