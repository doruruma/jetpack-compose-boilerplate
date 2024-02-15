package id.andra.jetpack_compose_boilerplate.utils.extension

import androidx.navigation.NavController
import id.andra.jetpack_compose_boilerplate.utils.mock.Screen

fun NavController.navigateSingleTop(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(
            Screen.HomeScreen.fullRoute
        ) {
            saveState = true
        }
        //restoreState = true
    }
}