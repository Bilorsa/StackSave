package com.example.stacksaveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stacksaveapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StackSaveApp()
        }
    }
}

@Composable
fun StackSaveApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Only show the bottom navigation on the main dashboard screens
            if (currentRoute in listOf("Home", "Deals", "Wallet", "Profile")) {
                StackSaveBottomNav(
                    currentRoute = currentRoute ?: "Home",
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo("Home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Welcome",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Welcome") {
                WelcomeScreen(onLoginClick = { navController.navigate("Home") })
            }
            composable("Home") {
                HomeFeedScreen(
                    activeCashback = "6%",
                    savingsStreak = 12,
                    todaysDeals = listOf("Nike -20%", "Takealot -15%"),
                    personalizedDeals = listOf("Electronics", "Fashion")
                )
            }
            composable("Wallet") {
                WalletScreen(
                    pendingAmount = 412.50,
                    availableAmount = 1208.00,
                    transactions = emptyList<TransactionItem>()
                )
            }
            composable("Profile") {
                ProfileScreen(
                    userName = "Thabo M.",
                    userLevel = "Level 4 Saver",
                    totalSaved = 6340.0,
                    badgesEarned = 7,
                    weeklyGoalProgress = 0.8f
                )
            }
            // The CheckoutAssist WebView would be triggered when tapping a deal on the Home screen
            composable("CheckoutAssist") {
                CheckoutAssistWebView(cartUrl = "https://example.com/cart", couponCode = "SAVE15")
            }
        }
    }
}

@Composable
fun StackSaveBottomNav(currentRoute: String, onNavigate: (String) -> Unit) {
    TODO("Not yet implemented")
}