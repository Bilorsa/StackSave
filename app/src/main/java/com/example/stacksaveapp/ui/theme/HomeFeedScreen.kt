package com.example.stacksaveapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp

@Composable
fun HomeFeedScreen(activeCashback: String, savingsStreak: Int, todaysDeals: List<String>, personalizedDeals: List<String>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Dashboard Header: Cashback & Streak[cite: 1]
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DashboardCard("Active Cashback", activeCashback)
                DashboardCard("Savings Streak", "$savingsStreak days")
            }
        }

        // 2. Today's Deals Section[cite: 1]
        item {
            Text(
                text = "Today's Deals",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() } // Marks as an H1 equivalent for navigation
            )
        }
        items(todaysDeals) { deal ->
            DealItemCard(dealText = deal)
        }

        // 3. For You (Personalized) Section[cite: 1]
        item {
            Text(
                text = "For You (Personalised)",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() }
            )
        }
        items(personalizedDeals) { deal ->
            DealItemCard(dealText = deal)
        }
    }
}

@Composable
fun DealItemCard(dealText: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            // mergeDescendants groups the text so TalkBack reads the whole card at once
            .semantics(mergeDescendants = true) {
                contentDescription = "Deal: $dealText"
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = dealText, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun DashboardCard(title: String, value: String) {
    Card(modifier = Modifier.width(160.dp)) {
        Column(
            modifier = Modifier.padding(16.dp).semantics(mergeDescendants = true) { },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.headlineSmall)
        }
    }
}