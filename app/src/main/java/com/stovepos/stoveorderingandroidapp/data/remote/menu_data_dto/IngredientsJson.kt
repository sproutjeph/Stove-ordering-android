package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class IngredientsJson(
    val featured_images: List<String>,
    val ingredients: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Ingredient>,
    val instructions: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Instruction>,
    val plating: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Plating>,
    val prep_instructions: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.PrepInstruction>
)