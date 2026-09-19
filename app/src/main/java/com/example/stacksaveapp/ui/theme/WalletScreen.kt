package com.example.stacksaveapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Data model for the transaction history list
data class TransactionItem(val storeName: String, val amount: Double)

@Composable
fun WalletScreen(
    pendingAmount: Double,
    availableAmount: Double,
    transactions: List<TransactionItem>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Pending Cashback Status[cite: 1]
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)) // Light amber/cream
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Pending Cashback", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = "R String.format(\"%.2f\", pendingAmount)",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 2. Available to Withdraw Status[cite: 1]
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)) // Light green
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Available to withdraw", style = MaterialTheme.typography.labelLarge)
                Text(
                    text = "R String.format(\"%.2f\", availableAmount)",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32) // Dark green text
                )
            }
        }

        // 3. Action Buttons[cite: 1]
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { /* Trigger withdrawal flow */ },
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B3DF5)) // Brand color[cite: 1]
            ) {
                Text(text = "Withdraw to\nBank / PayPal", textAlign = TextAlign.Center)
            }

            OutlinedButton(
                onClick = { /* Trigger voucher conversion */ },
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp)
            ) {
                Text(text = "Convert to\nVoucher\n(+3% bonus)", textAlign = TextAlign.Center)
            }
        }

        // 4. Transaction History[cite: 1]
        Card(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Transaction history",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(transactions) { tx ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "• ${tx.storeName}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "+R${String.format("%.0f", tx.amount)}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}