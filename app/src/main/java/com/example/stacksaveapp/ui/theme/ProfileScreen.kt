package com.example.stacksaveapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    userName: String,
    userLevel: String,
    totalSaved: Double,
    badgesEarned: Int,
    weeklyGoalProgress: Float // Float between 0.0 and 1.0
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // User Identity and Level[cite: 1]
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = userName, style = MaterialTheme.typography.headlineMedium)
                Text(text = userLevel, style = MaterialTheme.typography.bodyLarge)
            }
        }

        // Total Saved & Badges[cite: 1]
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Total saved: R $totalSaved", style = MaterialTheme.typography.titleLarge)
                Text(text = "Badges earned: $badgesEarned", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Weekly Savings Goal[cite: 1]
            Card(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Weekly Savings Goal", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    // Visual progress bar for the goal[cite: 1]
                    LinearProgressIndicator(
                        progress = { weeklyGoalProgress },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${(weeklyGoalProgress * 100).toInt()}%", style = MaterialTheme.typography.bodyLarge)
                }
            }

            // Referral Programme[cite: 1]
            Card(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Refer a friend,", style = MaterialTheme.typography.labelMedium)
                    Text(text = "earn R50 each", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        // Settings Menu[cite: 1]
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Settings", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { /* Navigate to Notifications */ }) {
                    Text("• Notifications")
                }
                TextButton(onClick = { /* Navigate to Linked Stores */ }) {
                    Text("• Linked stores")
                }
                TextButton(onClick = { /* Navigate to Privacy */ }) {
                    Text("• Privacy & data")
                }
            }
        }
    }
}