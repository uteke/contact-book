package com.uteke.contactbook

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uteke.contactbook.features.userdetails.router.UserDetailsRouter
import com.uteke.contactbook.features.userlist.router.UserListRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.setBackgroundDrawable(ColorDrawable(Color.WHITE))

            MaterialTheme {
                NavGraph(navHostController = rememberNavController())
            }
        }
    }

    @Composable
    private fun NavGraph(navHostController: NavHostController) {
        NavHost(navController = navHostController, startDestination = UserListRouter.route) {
            composable(route = UserListRouter.route) {
                UserListRouter.Screen(
                    onItemClick = { id ->
                        navHostController.navigate(UserDetailsRouter.route(id))
                    },
                    openEmail = { email -> openEmail(email) },
                    openPhone = { phone -> openPhone(phone) }
                )
            }
            composable(
                route = UserDetailsRouter.route,
                arguments = listOf(
                    navArgument(
                        name = UserDetailsRouter.param,
                        builder = {
                            type = NavType.StringType
                            nullable = false
                        }
                    )
                )
            ) { backStackEntry ->
                UserDetailsRouter.Screen(
                    id = backStackEntry.arguments?.getString(UserDetailsRouter.param) ?: "",
                    goBack = { navHostController.popBackStack() },
                    openEmail = { email -> openEmail(email) },
                    openPhone = { phone -> openPhone(phone) },
                    openLocation = { latitude, longitude -> openLocation(latitude, longitude) }
                )
            }
        }
    }

    private fun openEmail(email: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            intent.setType("message/rfc822")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }
        startActivity(Intent.createChooser(intent,"Choose an email app"))
    }

    private fun openPhone(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(Intent.createChooser(intent,"Choose an phone app"))
    }

    private fun openLocation(latitude: Double, longitude: Double) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$latitude,$longitude"))
        startActivity(Intent.createChooser(intent,"Choose an phone app"))
    }
}
