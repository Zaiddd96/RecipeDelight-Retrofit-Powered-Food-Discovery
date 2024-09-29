package com.example.recipedelight

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeModel: MainViewModel = viewModel()
    val viewstate by recipeModel.categorieState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewstate, navigateToDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        composable(Screen.DetailScreen.route){
            val category = navController
                .previousBackStackEntry?.
                savedStateHandle?.
                get<Category>("cat")?:
                Category("","","","")
            CategoryDetails(category = category)
        }
    }
}