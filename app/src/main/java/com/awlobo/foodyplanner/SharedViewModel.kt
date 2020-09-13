package com.awlobo.foodyplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.awlobo.foodyplanner.data.domain.food.Food
import com.awlobo.foodyplanner.data.domain.food.FoodRepository
import com.awlobo.foodyplanner.data.domain.food.FoodTable
import com.awlobo.foodyplanner.data.domain.planning.PlaningRepository
import com.awlobo.foodyplanner.data.domain.planning.crossref.PlanningFoodCrossRef
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val foodRepository = FoodRepository(application)
    private val planningRepository = PlaningRepository(application)

    /*------------------------- FOOD -------------------------*/
    fun getFoodList(): LiveData<List<Food>>? {
        return foodRepository.getAllFoods()
    }

    fun insertFood(food: Food) = viewModelScope.launch {
        foodRepository.insertFood(food)
    }

    fun deleteFoodById(id: Int) = viewModelScope.launch {
        foodRepository.deleteFoodById(id)
    }

    suspend fun getIdByFoodName(name: String): Food? {
        return foodRepository.getIdByFood(name)
    }
//
//    fun getIdByFoodName(name: String) = viewModelScope.launch {
//        foodRepository.getIdByFood(name) as Food
//    }


    /*--------------------- PLANNING -------------------------*/

    fun getPlanningList(): LiveData<List<FoodTable>>? {
        return planningRepository.getPlanning()
    }

    fun insertToPlanning(join: PlanningFoodCrossRef) = viewModelScope.launch {
        planningRepository.insert(join)
    }

    /*----------------------- SHARED -------------------------*/

    val addedToPlan = MutableLiveData(false)
    val deleteAllData = MutableLiveData(false)
    val deleteButtonState = MutableLiveData(false)

}